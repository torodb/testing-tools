/*
 * Copyright 2017 8Kdata Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.torodb.testing.docker.mysql;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.net.HostAndPort;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;
import com.spotify.docker.client.messages.Volume;
import com.torodb.testing.docker.ImageInstaller;
import com.torodb.testing.docker.ImagePuller;
import com.torodb.testing.docker.SimpleDockerService;
import com.torodb.testing.docker.UntilStdLineContains;
import com.torodb.testing.docker.WaitCondition;
import com.torodb.testing.docker.sql.ConnectionOwnerDslContext;
import com.torodb.testing.docker.sql.SqlService;
import org.jooq.DSLContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

/**
 *
 */
public class MysqlService extends SimpleDockerService implements SqlService {
  private static final int MYSQL_PORT = 3306;
  private final MysqlConfig config;
  private HostAndPort bindingHostAndPort;
  private DataSource dataSource;

  public MysqlService(MysqlConfig config, WaitCondition waitCondition,
      ImageInstaller imageInstaller) {
    super(waitCondition, imageInstaller);
    this.config = config;
  }

  /**
   * Given a postgres version, creates a default service that, if needed, the image from Docker Hub.
   *
   * @param version the postgres version that will be used
   * @return a service ready to be started that creates a postgres docker with the default
   *         configuration.
   */
  public static MysqlService defaultService(MysqlVersion version) {
    MysqlConfig config = new MysqlConfig.Builder(version)
        .build();
    return defaultService(config);
  }

  /**
   * Given a postgres config, creates a default service that, if needed, the image from Docker Hub.
   *
   * @param config the postgres config that will be used
   * @return a service ready to be started that creates a postgres docker with the default
   *         configuration.
   */
  public static MysqlService defaultService(MysqlConfig config) {
    UntilStdLineContains waitCondition = new UntilStdLineContains("Database started");
    ImagePuller imageInstaller = new ImagePuller(
        config.getVersion().getDockerImageRef()
    );

    return new MysqlService(config, waitCondition, imageInstaller);
  }

  @Override
  protected String getImageRef() {
    return config.getVersion().getDockerImageRef();
  }

  private String getMyDataVolumeName() {
    return "mysql-" + System.identityHashCode(this);
  }

  @Override
  protected void additionalHostConfiguration(DockerClient client,
      HostConfig.Builder builder) throws DockerException, InterruptedException {
    Volume volume = client.createVolume(Volume.builder()
        .name(getMyDataVolumeName())
        .build()
    );
    builder.appendBinds(
        HostConfig.Bind.from(volume).to("/var/lib/mysql").build()
    );


    Map<String, List<PortBinding>> portBindings = new HashMap<>();
    portBindings.put(
        Integer.toString(MYSQL_PORT),
        Lists.newArrayList(PortBinding.randomPort("0.0.0.0"))
    );
    
    builder.portBindings(portBindings);
  }

  @Override
  protected void additionalContainerConfiguration(DockerClient client,
      ContainerConfig.Builder builder) throws DockerException, InterruptedException {
    builder.exposedPorts(Integer.toString(MYSQL_PORT));
  }

  @Override
  protected Stream<String> streamVolumesNamesToRemove() {
    return Stream.of(getMyDataVolumeName());
  }

  @Override
  protected void afterStarted(DockerClient client, String containerId) throws DockerException,
      InterruptedException {

    bindingHostAndPort = getBindingTcpPort(MYSQL_PORT)
        .orElseThrow(() ->
            new RuntimeException("There is no binding port for the postgres instance")
        );
    dataSource = createDataSource(bindingHostAndPort);
  }
  
  protected DataSource createDataSource(HostAndPort bindingHostAndPort) {

    MysqlDataSource ds = new MysqlDataSource();

    ds.setPortNumber(bindingHostAndPort.getPort());
    ds.setServerName(bindingHostAndPort.getHost());
    ds.setUser(config.getUsername());
    ds.setPassword(config.getPassword());
    ds.setDatabaseName(config.getDb());

    try (
        Connection conn = ds.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT 1")) {
      rs.next();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getLocalizedMessage());
    }
    return ds;
  }

  @Override
  public HostAndPort getAddress() {
    checkRunning();
    return bindingHostAndPort;
  }

  @Override
  public DataSource getDatasource() {
    checkRunning();
    return dataSource;
  }

  public MysqlConfig getConfig() {
    return config;
  }

  @Override
  public DSLContext getDslContext() {
    checkRunning();
    return new ConnectionOwnerDslContext(dataSource, config.getVersion().getDialect());
  }

  @Override
  protected List<String> getCmd() {
    try (InputStream is = MysqlService.class.getResourceAsStream("mysql-docker-cmd.sh");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charsets.UTF_8))) {
      String script = reader.lines().collect(Collectors.joining("\n"));
      return Lists.newArrayList(
          "bash",
          "-c",
          script
      );
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  protected List<String> getEnv() {
    return Lists.newArrayList(
        "MYSQL_ROOT_PASSWORD=" + config.getPassword(),
        "MYSQL_USER=" + config.getUsername(),
        "MYSQL_PASSWORD=" + config.getPassword(),
        "MYSQL_DATABASE=" + config.getDb()
    );
  }

}
