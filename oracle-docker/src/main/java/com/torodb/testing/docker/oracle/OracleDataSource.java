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

package com.torodb.testing.docker.oracle;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;

import javax.sql.DataSource;

public class OracleDataSource implements DataSource {
  
  private final Driver driver;
  private final Properties properties = new Properties();
  private String sid;
  private String host;
  private int port;
  private String database;
  private String user;
  private String password;
  
  public OracleDataSource() {
    try {
      driver = DriverManager.getDriver("jdbc:oracle:thin:@localhost:1521");
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return null;
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {
  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {
  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return 0;
  }

  @Override
  public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return driver.getParentLogger();
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return iface.cast(driver);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return iface.isAssignableFrom(iface);
  }

  @Override
  public Connection getConnection() throws SQLException {
    Properties properties = new Properties(this.properties);
    return driver.connect(getUrl(user, password, properties), properties);
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    Properties properties = new Properties(this.properties);
    return driver.connect(getUrl(username, password, properties), properties);
  }
  
  public void setProperty(String key, String value) {
    properties.setProperty(key, value);
  }
  
  public String getProperty(String key) {
    return properties.getProperty(key);
  }
  
  private String getUrl(Properties properties) {
    return "jdbc:oracle:thin:@" 
        + host + ":" + port + ":" + sid;
  }

  private String getUrl(String username, String password, Properties properties) {
    properties.setProperty("user", username);
    properties.setProperty("password", password);
    return getUrl(properties);
  }
  
  public void setSid(String sid) {
    this.sid = sid;
  }
  
  public void setHost(String host) {
    this.host = host;
  }
  
  public void setPort(int port) {
    this.port = port;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setDatabase(String database) {
    this.database = database;
  }

  public String getDatabase() {
    return database;
  }
}
