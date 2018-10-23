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

package com.torodb.testing.docker.sql;

import org.jooq.AlterSequenceRestartStep;
import org.jooq.AlterTableStep;
import org.jooq.Attachable;
import org.jooq.Batch;
import org.jooq.BatchBindStep;
import org.jooq.BindContext;
import org.jooq.CommonTableExpression;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.ConnectionCallable;
import org.jooq.ConnectionRunnable;
import org.jooq.CreateIndexStep;
import org.jooq.CreateSequenceFinalStep;
import org.jooq.CreateTableAsStep;
import org.jooq.CreateViewAsStep;
import org.jooq.Cursor;
import org.jooq.DDLFlag;
import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.DeleteQuery;
import org.jooq.DeleteWhereStep;
import org.jooq.DropIndexOnStep;
import org.jooq.DropSequenceFinalStep;
import org.jooq.DropTableStep;
import org.jooq.DropViewFinalStep;
import org.jooq.Field;
import org.jooq.InsertQuery;
import org.jooq.InsertSetStep;
import org.jooq.InsertValuesStep1;
import org.jooq.InsertValuesStep10;
import org.jooq.InsertValuesStep11;
import org.jooq.InsertValuesStep12;
import org.jooq.InsertValuesStep13;
import org.jooq.InsertValuesStep14;
import org.jooq.InsertValuesStep15;
import org.jooq.InsertValuesStep16;
import org.jooq.InsertValuesStep17;
import org.jooq.InsertValuesStep18;
import org.jooq.InsertValuesStep19;
import org.jooq.InsertValuesStep2;
import org.jooq.InsertValuesStep20;
import org.jooq.InsertValuesStep21;
import org.jooq.InsertValuesStep22;
import org.jooq.InsertValuesStep3;
import org.jooq.InsertValuesStep4;
import org.jooq.InsertValuesStep5;
import org.jooq.InsertValuesStep6;
import org.jooq.InsertValuesStep7;
import org.jooq.InsertValuesStep8;
import org.jooq.InsertValuesStep9;
import org.jooq.InsertValuesStepN;
import org.jooq.LoaderOptionsStep;
import org.jooq.MergeKeyStep1;
import org.jooq.MergeKeyStep10;
import org.jooq.MergeKeyStep11;
import org.jooq.MergeKeyStep12;
import org.jooq.MergeKeyStep13;
import org.jooq.MergeKeyStep14;
import org.jooq.MergeKeyStep15;
import org.jooq.MergeKeyStep16;
import org.jooq.MergeKeyStep17;
import org.jooq.MergeKeyStep18;
import org.jooq.MergeKeyStep19;
import org.jooq.MergeKeyStep2;
import org.jooq.MergeKeyStep20;
import org.jooq.MergeKeyStep21;
import org.jooq.MergeKeyStep22;
import org.jooq.MergeKeyStep3;
import org.jooq.MergeKeyStep4;
import org.jooq.MergeKeyStep5;
import org.jooq.MergeKeyStep6;
import org.jooq.MergeKeyStep7;
import org.jooq.MergeKeyStep8;
import org.jooq.MergeKeyStep9;
import org.jooq.MergeKeyStepN;
import org.jooq.MergeUsingStep;
import org.jooq.Meta;
import org.jooq.Name;
import org.jooq.Param;
import org.jooq.Queries;
import org.jooq.Query;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Record11;
import org.jooq.Record12;
import org.jooq.Record13;
import org.jooq.Record14;
import org.jooq.Record15;
import org.jooq.Record16;
import org.jooq.Record17;
import org.jooq.Record18;
import org.jooq.Record19;
import org.jooq.Record2;
import org.jooq.Record20;
import org.jooq.Record21;
import org.jooq.Record22;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.Record6;
import org.jooq.Record7;
import org.jooq.Record8;
import org.jooq.Record9;
import org.jooq.RenderContext;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.Results;
import org.jooq.SQL;
import org.jooq.SQLDialect;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.SelectField;
import org.jooq.SelectQuery;
import org.jooq.SelectSelectStep;
import org.jooq.SelectWhereStep;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableLike;
import org.jooq.TableRecord;
import org.jooq.TransactionalCallable;
import org.jooq.TransactionalRunnable;
import org.jooq.TruncateIdentityStep;
import org.jooq.UDT;
import org.jooq.UDTRecord;
import org.jooq.UpdatableRecord;
import org.jooq.UpdateQuery;
import org.jooq.UpdateSetFirstStep;
import org.jooq.WithAsStep;
import org.jooq.WithAsStep1;
import org.jooq.WithAsStep10;
import org.jooq.WithAsStep11;
import org.jooq.WithAsStep12;
import org.jooq.WithAsStep13;
import org.jooq.WithAsStep14;
import org.jooq.WithAsStep15;
import org.jooq.WithAsStep16;
import org.jooq.WithAsStep17;
import org.jooq.WithAsStep18;
import org.jooq.WithAsStep19;
import org.jooq.WithAsStep2;
import org.jooq.WithAsStep20;
import org.jooq.WithAsStep21;
import org.jooq.WithAsStep22;
import org.jooq.WithAsStep3;
import org.jooq.WithAsStep4;
import org.jooq.WithAsStep5;
import org.jooq.WithAsStep6;
import org.jooq.WithAsStep7;
import org.jooq.WithAsStep8;
import org.jooq.WithAsStep9;
import org.jooq.WithStep;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.InvalidResultException;
import org.jooq.exception.TooManyRowsException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConnectionProvider;
import org.jooq.lambda.UncheckedException;
import org.jooq.tools.jdbc.JDBCUtils;
import org.jooq.tools.jdbc.MockCallable;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockRunnable;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

import javax.sql.DataSource;

/**
 * A {@link DSLContext} whose lifecycle is associated with a single connection.
 *
 * When this object is closed, it calls {@link Connection#close() }.
 */
@SuppressWarnings({"checkstyle:LineLength", "checkstyle:MethodTypeParameterName", "checkstyle:OverloadMethodsDeclarationOrder"})
public class ConnectionOwnerDslContext implements DSLContext {

  private final Connection connection;
  private final DSLContext delegate;

  public ConnectionOwnerDslContext(Connection connection, SQLDialect dialect) {
    this.connection = connection;
    this.delegate = DSL.using(new DefaultConnectionProvider(connection), dialect);
  }

  public ConnectionOwnerDslContext(DataSource datasource, SQLDialect dialect) {
    try {
      this.connection = datasource.getConnection();
      this.delegate = DSL.using(new DefaultConnectionProvider(connection), dialect);
    } catch (SQLException ex) {
      throw new UncheckedException(ex);
    }
  }

  @Override
  public void close() throws DataAccessException {
    delegate.close();
    JDBCUtils.safeClose(connection);
  }

  @Override
  public Schema map(Schema schema) {
    return delegate.map(schema);
  }

  @Override
  public <R extends Record> Table<R> map(Table<R> table) {
    return delegate.map(table);
  }

  @Override
  public Meta meta() {
    return delegate.meta();
  }

  @Override
  public <T> T transactionResult(TransactionalCallable<T> transactional) {
    return delegate.transactionResult(transactional);
  }

  @Override
  public void transaction(TransactionalRunnable transactional) {
    delegate.transaction(transactional);
  }

  @Override
  public <T> CompletionStage<T> transactionResultAsync(TransactionalCallable<T> transactional) {
    return delegate.transactionResultAsync(transactional);
  }

  @Override
  public CompletionStage<Void> transactionAsync(TransactionalRunnable transactional) {
    return delegate.transactionAsync(transactional);
  }

  @Override
  public <T> CompletionStage<T> transactionResultAsync(Executor executor,
      TransactionalCallable<T> transactional) {
    return delegate.transactionResultAsync(executor, transactional);
  }

  @Override
  public CompletionStage<Void> transactionAsync(Executor executor,
      TransactionalRunnable transactional) {
    return delegate.transactionAsync(executor, transactional);
  }

  @Override
  public <T> T connectionResult(ConnectionCallable<T> callable) {
    return delegate.connectionResult(callable);
  }

  @Override
  public void connection(ConnectionRunnable runnable) {
    delegate.connection(runnable);
  }

  @Override
  public <T> T mockResult(MockDataProvider provider, MockCallable<T> mockable) {
    return delegate.mockResult(provider, mockable);
  }

  @Override
  public void mock(MockDataProvider provider, MockRunnable mockable) {
    delegate.mock(provider, mockable);
  }

  @Override
  public RenderContext renderContext() {
    return delegate.renderContext();
  }

  @Override
  public String render(QueryPart part) {
    return delegate.render(part);
  }

  @Override
  public String renderNamedParams(QueryPart part) {
    return delegate.renderNamedParams(part);
  }

  @Override
  public String renderNamedOrInlinedParams(QueryPart part) {
    return delegate.renderNamedOrInlinedParams(part);
  }

  @Override
  public String renderInlined(QueryPart part) {
    return delegate.renderInlined(part);
  }

  @Override
  public List<Object> extractBindValues(QueryPart part) {
    return delegate.extractBindValues(part);
  }

  @Override
  public Map<String, Param<?>> extractParams(QueryPart part) {
    return delegate.extractParams(part);
  }

  @Override
  public Param<?> extractParam(QueryPart part, String name) {
    return delegate.extractParam(part, name);
  }

  @Override
  public BindContext bindContext(PreparedStatement stmt) {
    return delegate.bindContext(stmt);
  }

  @Override
  public int bind(QueryPart part, PreparedStatement stmt) {
    return delegate.bind(part, stmt);
  }

  @Override
  public void attach(Attachable... attachables) {
    delegate.attach(attachables);
  }

  @Override
  public void attach(
      Collection<? extends Attachable> attachables) {
    delegate.attach(attachables);
  }

  @Override
  public <R extends Record> LoaderOptionsStep<R> loadInto(Table<R> table) {
    return delegate.loadInto(table);
  }

  @Override
  public Query query(SQL sql) {
    return delegate.query(sql);
  }

  @Override
  public Query query(String sql) {
    return delegate.query(sql);
  }

  @Override
  public Query query(String sql, Object... bindings) {
    return delegate.query(sql, bindings);
  }

  @Override
  public Query query(String sql, QueryPart... parts) {
    return delegate.query(sql, parts);
  }

  @Override
  public Result<Record> fetch(SQL sql) throws DataAccessException {
    return delegate.fetch(sql);
  }

  @Override
  public Result<Record> fetch(String sql) throws DataAccessException {
    return delegate.fetch(sql);
  }

  @Override
  public Result<Record> fetch(String sql, Object... bindings) throws DataAccessException {
    return delegate.fetch(sql, bindings);
  }

  @Override
  public Result<Record> fetch(String sql, QueryPart... parts) throws DataAccessException {
    return delegate.fetch(sql, parts);
  }

  @Override
  public Cursor<Record> fetchLazy(SQL sql) throws DataAccessException {
    return delegate.fetchLazy(sql);
  }

  @Override
  public Cursor<Record> fetchLazy(String sql) throws DataAccessException {
    return delegate.fetchLazy(sql);
  }

  @Override
  public Cursor<Record> fetchLazy(String sql, Object... bindings) throws DataAccessException {
    return delegate.fetchLazy(sql, bindings);
  }

  @Override
  public Cursor<Record> fetchLazy(String sql, QueryPart... parts) throws DataAccessException {
    return delegate.fetchLazy(sql, parts);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(SQL sql) {
    return delegate.fetchAsync(sql);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(String sql) {
    return delegate.fetchAsync(sql);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(String sql, Object... bindings) {
    return delegate.fetchAsync(sql, bindings);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(String sql, QueryPart... parts) {
    return delegate.fetchAsync(sql, parts);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, SQL sql) {
    return delegate.fetchAsync(executor, sql);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, String sql) {
    return delegate.fetchAsync(executor, sql);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, String sql, Object... bindings) {
    return delegate.fetchAsync(executor, sql, bindings);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, String sql, QueryPart... parts) {
    return delegate.fetchAsync(executor, sql, parts);
  }

  @Override
  public Stream<Record> fetchStream(SQL sql) throws DataAccessException {
    return delegate.fetchStream(sql);
  }

  @Override
  public Stream<Record> fetchStream(String sql) throws DataAccessException {
    return delegate.fetchStream(sql);
  }

  @Override
  public Stream<Record> fetchStream(String sql, Object... bindings) throws DataAccessException {
    return delegate.fetchStream(sql, bindings);
  }

  @Override
  public Stream<Record> fetchStream(String sql, QueryPart... parts) throws DataAccessException {
    return delegate.fetchStream(sql, parts);
  }

  @Override
  public Results fetchMany(SQL sql) throws DataAccessException {
    return delegate.fetchMany(sql);
  }

  @Override
  public Results fetchMany(String sql) throws DataAccessException {
    return delegate.fetchMany(sql);
  }

  @Override
  public Results fetchMany(String sql, Object... bindings) throws DataAccessException {
    return delegate.fetchMany(sql, bindings);
  }

  @Override
  public Results fetchMany(String sql, QueryPart... parts) throws DataAccessException {
    return delegate.fetchMany(sql, parts);
  }

  @Override
  public Record fetchOne(SQL sql) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOne(sql);
  }

  @Override
  public Record fetchOne(String sql) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOne(sql);
  }

  @Override
  public Record fetchOne(String sql, Object... bindings) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOne(sql, bindings);
  }

  @Override
  public Record fetchOne(String sql, QueryPart... parts) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOne(sql, parts);
  }

  @Override
  public Optional<Record> fetchOptional(SQL sql) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(sql);
  }

  @Override
  public Optional<Record> fetchOptional(String sql) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(sql);
  }

  @Override
  public Optional<Record> fetchOptional(String sql, Object... bindings) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOptional(sql, bindings);
  }

  @Override
  public Optional<Record> fetchOptional(String sql, QueryPart... parts) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOptional(sql, parts);
  }

  @Override
  public Object fetchValue(SQL sql) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchValue(sql);
  }

  @Override
  public Object fetchValue(String sql) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchValue(sql);
  }

  @Override
  public Object fetchValue(String sql, Object... bindings) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchValue(sql, bindings);
  }

  @Override
  public Object fetchValue(String sql, QueryPart... parts) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchValue(sql, parts);
  }

  @Override
  public Optional<?> fetchOptionalValue(SQL sql) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchOptionalValue(sql);
  }

  @Override
  public Optional<?> fetchOptionalValue(String sql) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchOptionalValue(sql);
  }

  @Override
  public Optional<?> fetchOptionalValue(String sql, Object... bindings) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(sql, bindings);
  }

  @Override
  public Optional<?> fetchOptionalValue(String sql, QueryPart... parts) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(sql, parts);
  }

  @Override
  public List<?> fetchValues(SQL sql) throws DataAccessException {
    return delegate.fetchValues(sql);
  }

  @Override
  public List<?> fetchValues(String sql) throws DataAccessException {
    return delegate.fetchValues(sql);
  }

  @Override
  public List<?> fetchValues(String sql, Object... bindings) throws DataAccessException {
    return delegate.fetchValues(sql, bindings);
  }

  @Override
  public List<?> fetchValues(String sql, QueryPart... parts) throws DataAccessException {
    return delegate.fetchValues(sql, parts);
  }

  @Override
  public int execute(SQL sql) throws DataAccessException {
    return delegate.execute(sql);
  }

  @Override
  public int execute(String sql) throws DataAccessException {
    return delegate.execute(sql);
  }

  @Override
  public int execute(String sql, Object... bindings) throws DataAccessException {
    return delegate.execute(sql, bindings);
  }

  @Override
  public int execute(String sql, QueryPart... parts) throws DataAccessException {
    return delegate.execute(sql, parts);
  }

  @Override
  public ResultQuery<Record> resultQuery(SQL sql) {
    return delegate.resultQuery(sql);
  }

  @Override
  public ResultQuery<Record> resultQuery(String sql) {
    return delegate.resultQuery(sql);
  }

  @Override
  public ResultQuery<Record> resultQuery(String sql, Object... bindings) {
    return delegate.resultQuery(sql, bindings);
  }

  @Override
  public ResultQuery<Record> resultQuery(String sql, QueryPart... parts) {
    return delegate.resultQuery(sql, parts);
  }

  @Override
  public Result<Record> fetch(ResultSet rs) throws DataAccessException {
    return delegate.fetch(rs);
  }

  @Override
  public Result<Record> fetch(ResultSet rs,
      Field<?>... fields) throws DataAccessException {
    return delegate.fetch(rs, fields);
  }

  @Override
  public Result<Record> fetch(ResultSet rs,
      DataType<?>... types) throws DataAccessException {
    return delegate.fetch(rs, types);
  }

  @Override
  public Result<Record> fetch(ResultSet rs,
      Class<?>... types) throws DataAccessException {
    return delegate.fetch(rs, types);
  }

  @Override
  public Record fetchOne(ResultSet rs) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOne(rs);
  }

  @Override
  public Record fetchOne(ResultSet rs,
      Field<?>... fields) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOne(rs, fields);
  }

  @Override
  public Record fetchOne(ResultSet rs,
      DataType<?>... types) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOne(rs, types);
  }

  @Override
  public Record fetchOne(ResultSet rs,
      Class<?>... types) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOne(rs, types);
  }

  @Override
  public Optional<Record> fetchOptional(ResultSet rs) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOptional(rs);
  }

  @Override
  public Optional<Record> fetchOptional(ResultSet rs,
      Field<?>... fields) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(rs, fields);
  }

  @Override
  public Optional<Record> fetchOptional(ResultSet rs,
      DataType<?>... types) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(rs, types);
  }

  @Override
  public Optional<Record> fetchOptional(ResultSet rs,
      Class<?>... types) throws DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(rs, types);
  }

  @Override
  public Object fetchValue(ResultSet rs) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchValue(rs);
  }

  @Override
  public <T> T fetchValue(ResultSet rs, Field<T> field) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchValue(rs, field);
  }

  @Override
  public <T> T fetchValue(ResultSet rs, DataType<T> type) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchValue(rs, type);
  }

  @Override
  public <T> T fetchValue(ResultSet rs, Class<T> type) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchValue(rs, type);
  }

  @Override
  public Optional<?> fetchOptionalValue(ResultSet rs) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(rs);
  }

  @Override
  public <T> Optional<T> fetchOptionalValue(ResultSet rs, Field<T> field) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(rs, field);
  }

  @Override
  public <T> Optional<T> fetchOptionalValue(ResultSet rs, DataType<T> type) throws
      DataAccessException, TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(rs, type);
  }

  @Override
  public <T> Optional<T> fetchOptionalValue(ResultSet rs, Class<T> type) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(rs, type);
  }

  @Override
  public List<?> fetchValues(ResultSet rs) throws DataAccessException {
    return delegate.fetchValues(rs);
  }

  @Override
  public <T> List<T> fetchValues(ResultSet rs, Field<T> field) throws DataAccessException {
    return delegate.fetchValues(rs, field);
  }

  @Override
  public <T> List<T> fetchValues(ResultSet rs, DataType<T> type) throws DataAccessException {
    return delegate.fetchValues(rs, type);
  }

  @Override
  public <T> List<T> fetchValues(ResultSet rs, Class<T> type) throws DataAccessException {
    return delegate.fetchValues(rs, type);
  }

  @Override
  public Cursor<Record> fetchLazy(ResultSet rs) throws DataAccessException {
    return delegate.fetchLazy(rs);
  }

  @Override
  public Cursor<Record> fetchLazy(ResultSet rs,
      Field<?>... fields) throws DataAccessException {
    return delegate.fetchLazy(rs, fields);
  }

  @Override
  public Cursor<Record> fetchLazy(ResultSet rs,
      DataType<?>... types) throws DataAccessException {
    return delegate.fetchLazy(rs, types);
  }

  @Override
  public Cursor<Record> fetchLazy(ResultSet rs,
      Class<?>... types) throws DataAccessException {
    return delegate.fetchLazy(rs, types);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(ResultSet rs) {
    return delegate.fetchAsync(rs);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(ResultSet rs,
      Field<?>... fields) {
    return delegate.fetchAsync(rs, fields);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(ResultSet rs,
      DataType<?>... types) {
    return delegate.fetchAsync(rs, types);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(ResultSet rs,
      Class<?>... types) {
    return delegate.fetchAsync(rs, types);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs) {
    return delegate.fetchAsync(executor, rs);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs,
      Field<?>... fields) {
    return delegate.fetchAsync(executor, rs, fields);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs,
      DataType<?>... types) {
    return delegate.fetchAsync(executor, rs, types);
  }

  @Override
  public CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs,
      Class<?>... types) {
    return delegate.fetchAsync(executor, rs, types);
  }

  @Override
  public Stream<Record> fetchStream(ResultSet rs) throws DataAccessException {
    return delegate.fetchStream(rs);
  }

  @Override
  public Stream<Record> fetchStream(ResultSet rs,
      Field<?>... fields) throws DataAccessException {
    return delegate.fetchStream(rs, fields);
  }

  @Override
  public Stream<Record> fetchStream(ResultSet rs,
      DataType<?>... types) throws DataAccessException {
    return delegate.fetchStream(rs, types);
  }

  @Override
  public Stream<Record> fetchStream(ResultSet rs,
      Class<?>... types) throws DataAccessException {
    return delegate.fetchStream(rs, types);
  }

  @Override
  public Result<Record> fetchFromTXT(String string) throws DataAccessException {
    return delegate.fetchFromTXT(string);
  }

  @Override
  public Result<Record> fetchFromTXT(String string, String nullLiteral) throws DataAccessException {
    return delegate.fetchFromTXT(string, nullLiteral);
  }

  @Override
  public Result<Record> fetchFromHTML(String string) throws DataAccessException {
    return delegate.fetchFromHTML(string);
  }

  @Override
  public Result<Record> fetchFromCSV(String string) throws DataAccessException {
    return delegate.fetchFromCSV(string);
  }

  @Override
  public Result<Record> fetchFromCSV(String string, char delimiter) throws DataAccessException {
    return delegate.fetchFromCSV(string, delimiter);
  }

  @Override
  public Result<Record> fetchFromCSV(String string, boolean header) throws DataAccessException {
    return delegate.fetchFromCSV(string, header);
  }

  @Override
  public Result<Record> fetchFromCSV(String string, boolean header, char delimiter) throws
      DataAccessException {
    return delegate.fetchFromCSV(string, header, delimiter);
  }

  @Override
  public Result<Record> fetchFromJSON(String string) {
    return delegate.fetchFromJSON(string);
  }

  @Override
  public Result<Record> fetchFromStringData(String[]... data) {
    return delegate.fetchFromStringData(data);
  }

  @Override
  public Result<Record> fetchFromStringData(List<String[]> data) {
    return delegate.fetchFromStringData(data);
  }

  @Override
  public Result<Record> fetchFromStringData(List<String[]> data, boolean header) {
    return delegate.fetchFromStringData(data, header);
  }

  @Override
  public WithAsStep with(String alias) {
    return delegate.with(alias);
  }

  @Override
  public WithAsStep with(String alias, String... fieldAliases) {
    return delegate.with(alias, fieldAliases);
  }

  @Override
  public WithAsStep1 with(String alias, String fieldAlias1) {
    return delegate.with(alias, fieldAlias1);
  }

  @Override
  public WithAsStep2 with(String alias, String fieldAlias1, String fieldAlias2) {
    return delegate.with(alias, fieldAlias1, fieldAlias2);
  }

  @Override
  public WithAsStep3 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3);
  }

  @Override
  public WithAsStep4 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4);
  }

  @Override
  public WithAsStep5 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5);
  }

  @Override
  public WithAsStep6 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6);
  }

  @Override
  public WithAsStep7 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7);
  }

  @Override
  public WithAsStep8 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8);
  }

  @Override
  public WithAsStep9 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9);
  }

  @Override
  public WithAsStep10 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10);
  }

  @Override
  public WithAsStep11 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11);
  }

  @Override
  public WithAsStep12 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12);
  }

  @Override
  public WithAsStep13 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13);
  }

  @Override
  public WithAsStep14 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14);
  }

  @Override
  public WithAsStep15 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15);
  }

  @Override
  public WithAsStep16 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16);
  }

  @Override
  public WithAsStep17 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16, String fieldAlias17) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17);
  }

  @Override
  public WithAsStep18 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16, String fieldAlias17, String fieldAlias18) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17, fieldAlias18);
  }

  @Override
  public WithAsStep19 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17, fieldAlias18,
        fieldAlias19);
  }

  @Override
  public WithAsStep20 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19,
      String fieldAlias20) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17, fieldAlias18,
        fieldAlias19, fieldAlias20);
  }

  @Override
  public WithAsStep21 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19,
      String fieldAlias20, String fieldAlias21) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17, fieldAlias18,
        fieldAlias19, fieldAlias20, fieldAlias21);
  }

  @Override
  public WithAsStep22 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3,
      String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7,
      String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11,
      String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15,
      String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19,
      String fieldAlias20, String fieldAlias21, String fieldAlias22) {
    return delegate.with(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4, fieldAlias5,
        fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11, fieldAlias12,
        fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17, fieldAlias18,
        fieldAlias19, fieldAlias20, fieldAlias21, fieldAlias22);
  }

  @Override
  public WithStep with(
      CommonTableExpression<?>... tables) {
    return delegate.with(tables);
  }

  @Override
  public WithAsStep withRecursive(String alias) {
    return delegate.withRecursive(alias);
  }

  @Override
  public WithAsStep withRecursive(String alias, String... fieldAliases) {
    return delegate.withRecursive(alias, fieldAliases);
  }

  @Override
  public WithAsStep1 withRecursive(String alias, String fieldAlias1) {
    return delegate.withRecursive(alias, fieldAlias1);
  }

  @Override
  public WithAsStep2 withRecursive(String alias, String fieldAlias1, String fieldAlias2) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2);
  }

  @Override
  public WithAsStep3 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3);
  }

  @Override
  public WithAsStep4 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4);
  }

  @Override
  public WithAsStep5 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5);
  }

  @Override
  public WithAsStep6 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6);
  }

  @Override
  public WithAsStep7 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7);
  }

  @Override
  public WithAsStep8 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8);
  }

  @Override
  public WithAsStep9 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9);
  }

  @Override
  public WithAsStep10 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10);
  }

  @Override
  public WithAsStep11 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11);
  }

  @Override
  public WithAsStep12 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12);
  }

  @Override
  public WithAsStep13 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13);
  }

  @Override
  public WithAsStep14 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14);
  }

  @Override
  public WithAsStep15 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15);
  }

  @Override
  public WithAsStep16 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16);
  }

  @Override
  public WithAsStep17 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16, String fieldAlias17) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17);
  }

  @Override
  public WithAsStep18 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17,
        fieldAlias18);
  }

  @Override
  public WithAsStep19 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18,
      String fieldAlias19) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17,
        fieldAlias18, fieldAlias19);
  }

  @Override
  public WithAsStep20 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18,
      String fieldAlias19, String fieldAlias20) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17,
        fieldAlias18, fieldAlias19, fieldAlias20);
  }

  @Override
  public WithAsStep21 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18,
      String fieldAlias19, String fieldAlias20, String fieldAlias21) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17,
        fieldAlias18, fieldAlias19, fieldAlias20, fieldAlias21);
  }

  @Override
  public WithAsStep22 withRecursive(String alias, String fieldAlias1, String fieldAlias2,
      String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6,
      String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10,
      String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14,
      String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18,
      String fieldAlias19, String fieldAlias20, String fieldAlias21, String fieldAlias22) {
    return delegate.withRecursive(alias, fieldAlias1, fieldAlias2, fieldAlias3, fieldAlias4,
        fieldAlias5, fieldAlias6, fieldAlias7, fieldAlias8, fieldAlias9, fieldAlias10, fieldAlias11,
        fieldAlias12, fieldAlias13, fieldAlias14, fieldAlias15, fieldAlias16, fieldAlias17,
        fieldAlias18, fieldAlias19, fieldAlias20, fieldAlias21, fieldAlias22);
  }

  @Override
  public WithStep withRecursive(
      CommonTableExpression<?>... tables) {
    return delegate.withRecursive(tables);
  }

  @Override
  public <R extends Record> SelectWhereStep<R> selectFrom(Table<R> table) {
    return delegate.selectFrom(table);
  }

  @Override
  public SelectSelectStep<Record> select(
      Collection<? extends SelectField<?>> fields) {
    return delegate.select(fields);
  }

  @Override
  public SelectSelectStep<Record> select(
      SelectField<?>... fields) {
    return delegate.select(fields);
  }

  @Override
  public <T1> SelectSelectStep<Record1<T1>> select(SelectField<T1> field1) {
    return delegate.select(field1);
  }

  @Override
  public <T1, T2> SelectSelectStep<Record2<T1, T2>> select(SelectField<T1> field1,
      SelectField<T2> field2) {
    return delegate.select(field1, field2);
  }

  @Override
  public <T1, T2, T3> SelectSelectStep<Record3<T1, T2, T3>> select(SelectField<T1> field1,
      SelectField<T2> field2, SelectField<T3> field3) {
    return delegate.select(field1, field2, field3);
  }

  @Override
  public <T1, T2, T3, T4> SelectSelectStep<Record4<T1, T2, T3, T4>> select(SelectField<T1> field1,
      SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4) {
    return delegate.select(field1, field2, field3, field4);
  }

  @Override
  public <T1, T2, T3, T4, T5> SelectSelectStep<Record5<T1, T2, T3, T4, T5>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5) {
    return delegate.select(field1, field2, field3, field4, field5);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6> SelectSelectStep<Record6<T1, T2, T3, T4, T5, T6>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6) {
    return delegate.select(field1, field2, field3, field4, field5, field6);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7> SelectSelectStep<Record7<T1, T2, T3, T4, T5, T6, T7>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8> SelectSelectStep<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9> SelectSelectStep<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> SelectSelectStep<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> SelectSelectStep<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> SelectSelectStep<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> SelectSelectStep<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> SelectSelectStep<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> SelectSelectStep<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> SelectSelectStep<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> SelectSelectStep<Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> SelectSelectStep<Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> SelectSelectStep<Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> SelectSelectStep<Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> SelectSelectStep<Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20,
      SelectField<T21> field21) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20, field21);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> SelectSelectStep<Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>> select(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20,
      SelectField<T21> field21, SelectField<T22> field22) {
    return delegate.select(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20, field21, field22);
  }

  @Override
  public SelectSelectStep<Record> selectDistinct(
      Collection<? extends SelectField<?>> fields) {
    return delegate.selectDistinct(fields);
  }

  @Override
  public SelectSelectStep<Record> selectDistinct(
      SelectField<?>... fields) {
    return delegate.selectDistinct(fields);
  }

  @Override
  public <T1> SelectSelectStep<Record1<T1>> selectDistinct(SelectField<T1> field1) {
    return delegate.selectDistinct(field1);
  }

  @Override
  public <T1, T2> SelectSelectStep<Record2<T1, T2>> selectDistinct(SelectField<T1> field1,
      SelectField<T2> field2) {
    return delegate.selectDistinct(field1, field2);
  }

  @Override
  public <T1, T2, T3> SelectSelectStep<Record3<T1, T2, T3>> selectDistinct(SelectField<T1> field1,
      SelectField<T2> field2, SelectField<T3> field3) {
    return delegate.selectDistinct(field1, field2, field3);
  }

  @Override
  public <T1, T2, T3, T4> SelectSelectStep<Record4<T1, T2, T3, T4>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4) {
    return delegate.selectDistinct(field1, field2, field3, field4);
  }

  @Override
  public <T1, T2, T3, T4, T5> SelectSelectStep<Record5<T1, T2, T3, T4, T5>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6> SelectSelectStep<Record6<T1, T2, T3, T4, T5, T6>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7> SelectSelectStep<Record7<T1, T2, T3, T4, T5, T6, T7>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8> SelectSelectStep<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9> SelectSelectStep<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> SelectSelectStep<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> SelectSelectStep<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> SelectSelectStep<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> SelectSelectStep<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> SelectSelectStep<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> SelectSelectStep<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> SelectSelectStep<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> SelectSelectStep<Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> SelectSelectStep<Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> SelectSelectStep<Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> SelectSelectStep<Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> SelectSelectStep<Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20,
      SelectField<T21> field21) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20, field21);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> SelectSelectStep<Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>> selectDistinct(
      SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4,
      SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8,
      SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11,
      SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14,
      SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17,
      SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20,
      SelectField<T21> field21, SelectField<T22> field22) {
    return delegate.selectDistinct(field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20, field21, field22);
  }

  @Override
  public SelectSelectStep<Record1<Integer>> selectZero() {
    return delegate.selectZero();
  }

  @Override
  public SelectSelectStep<Record1<Integer>> selectOne() {
    return delegate.selectOne();
  }

  @Override
  public SelectSelectStep<Record1<Integer>> selectCount() {
    return delegate.selectCount();
  }

  @Override
  public SelectQuery<Record> selectQuery() {
    return delegate.selectQuery();
  }

  @Override
  public <R extends Record> SelectQuery<R> selectQuery(TableLike<R> table) {
    return delegate.selectQuery(table);
  }

  @Override
  public <R extends Record> InsertQuery<R> insertQuery(Table<R> into) {
    return delegate.insertQuery(into);
  }

  @Override
  public <R extends Record> InsertSetStep<R> insertInto(Table<R> into) {
    return delegate.insertInto(into);
  }

  @Override
  public <R extends Record, T1> InsertValuesStep1<R, T1> insertInto(Table<R> into, Field<T1> field1) {
    return delegate.insertInto(into, field1);
  }

  @Override
  public <R extends Record, T1, T2> InsertValuesStep2<R, T1, T2> insertInto(Table<R> into,
      Field<T1> field1, Field<T2> field2) {
    return delegate.insertInto(into, field1, field2);
  }

  @Override
  public <R extends Record, T1, T2, T3> InsertValuesStep3<R, T1, T2, T3> insertInto(Table<R> into,
      Field<T1> field1, Field<T2> field2, Field<T3> field3) {
    return delegate.insertInto(into, field1, field2, field3);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4> InsertValuesStep4<R, T1, T2, T3, T4> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4) {
    return delegate.insertInto(into, field1, field2, field3, field4);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5> InsertValuesStep5<R, T1, T2, T3, T4, T5> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6> InsertValuesStep6<R, T1, T2, T3, T4, T5, T6> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7> InsertValuesStep7<R, T1, T2, T3, T4, T5, T6, T7> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8> InsertValuesStep8<R, T1, T2, T3, T4, T5, T6, T7, T8> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9> InsertValuesStep9<R, T1, T2, T3, T4, T5, T6, T7, T8, T9> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> InsertValuesStep10<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> InsertValuesStep11<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> InsertValuesStep12<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> InsertValuesStep13<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> InsertValuesStep14<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> InsertValuesStep15<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> InsertValuesStep16<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> InsertValuesStep17<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> InsertValuesStep18<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> InsertValuesStep19<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> InsertValuesStep20<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19, Field<T20> field20) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> InsertValuesStep21<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20, field21);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> InsertValuesStep22<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> insertInto(
      Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21,
      Field<T22> field22) {
    return delegate.insertInto(into, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20, field21, field22);
  }

  @Override
  public <R extends Record> InsertValuesStepN<R> insertInto(Table<R> into,
      Field<?>... fields) {
    return delegate.insertInto(into, fields);
  }

  @Override
  public <R extends Record> InsertValuesStepN<R> insertInto(Table<R> into,
      Collection<? extends Field<?>> fields) {
    return delegate.insertInto(into, fields);
  }

  @Override
  public <R extends Record> UpdateQuery<R> updateQuery(Table<R> table) {
    return delegate.updateQuery(table);
  }

  @Override
  public <R extends Record> UpdateSetFirstStep<R> update(Table<R> table) {
    return delegate.update(table);
  }

  @Override
  public <R extends Record> MergeUsingStep<R> mergeInto(Table<R> table) {
    return delegate.mergeInto(table);
  }

  @Override
  public <R extends Record, T1> MergeKeyStep1<R, T1> mergeInto(Table<R> table, Field<T1> field1) {
    return delegate.mergeInto(table, field1);
  }

  @Override
  public <R extends Record, T1, T2> MergeKeyStep2<R, T1, T2> mergeInto(Table<R> table,
      Field<T1> field1, Field<T2> field2) {
    return delegate.mergeInto(table, field1, field2);
  }

  @Override
  public <R extends Record, T1, T2, T3> MergeKeyStep3<R, T1, T2, T3> mergeInto(Table<R> table,
      Field<T1> field1, Field<T2> field2, Field<T3> field3) {
    return delegate.mergeInto(table, field1, field2, field3);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4> MergeKeyStep4<R, T1, T2, T3, T4> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4) {
    return delegate.mergeInto(table, field1, field2, field3, field4);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5> MergeKeyStep5<R, T1, T2, T3, T4, T5> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6> MergeKeyStep6<R, T1, T2, T3, T4, T5, T6> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7> MergeKeyStep7<R, T1, T2, T3, T4, T5, T6, T7> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8> MergeKeyStep8<R, T1, T2, T3, T4, T5, T6, T7, T8> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9> MergeKeyStep9<R, T1, T2, T3, T4, T5, T6, T7, T8, T9> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> MergeKeyStep10<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> MergeKeyStep11<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> MergeKeyStep12<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> MergeKeyStep13<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> MergeKeyStep14<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> MergeKeyStep15<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> MergeKeyStep16<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> MergeKeyStep17<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> MergeKeyStep18<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> MergeKeyStep19<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> MergeKeyStep20<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19, Field<T20> field20) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> MergeKeyStep21<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20, field21);
  }

  @Override
  public <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> MergeKeyStep22<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> mergeInto(
      Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4,
      Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9,
      Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13,
      Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17,
      Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21,
      Field<T22> field22) {
    return delegate.mergeInto(table, field1, field2, field3, field4, field5, field6, field7, field8,
        field9, field10, field11, field12, field13, field14, field15, field16, field17, field18,
        field19, field20, field21, field22);
  }

  @Override
  public <R extends Record> MergeKeyStepN<R> mergeInto(Table<R> table,
      Field<?>... fields) {
    return delegate.mergeInto(table, fields);
  }

  @Override
  public <R extends Record> MergeKeyStepN<R> mergeInto(Table<R> table,
      Collection<? extends Field<?>> fields) {
    return delegate.mergeInto(table, fields);
  }

  @Override
  public <R extends Record> DeleteQuery<R> deleteQuery(Table<R> table) {
    return delegate.deleteQuery(table);
  }

  @Override
  public <R extends Record> DeleteWhereStep<R> deleteFrom(Table<R> table) {
    return delegate.deleteFrom(table);
  }

  @Override
  public <R extends Record> DeleteWhereStep<R> delete(Table<R> table) {
    return delegate.delete(table);
  }

  @Override
  public Batch batch(Query... queries) {
    return delegate.batch(queries);
  }

  @Override
  public Batch batch(Queries queries) {
    return delegate.batch(queries);
  }

  @Override
  public Batch batch(String... queries) {
    return delegate.batch(queries);
  }

  @Override
  public Batch batch(
      Collection<? extends Query> queries) {
    return delegate.batch(queries);
  }

  @Override
  public BatchBindStep batch(Query query) {
    return delegate.batch(query);
  }

  @Override
  public BatchBindStep batch(String sql) {
    return delegate.batch(sql);
  }

  @Override
  public Batch batch(Query query, Object[]... bindings) {
    return delegate.batch(query, bindings);
  }

  @Override
  public Batch batch(String sql, Object[]... bindings) {
    return delegate.batch(sql, bindings);
  }

  @Override
  public Batch batchStore(
      UpdatableRecord<?>... records) {
    return delegate.batchStore(records);
  }

  @Override
  public Batch batchStore(
      Collection<? extends UpdatableRecord<?>> records) {
    return delegate.batchStore(records);
  }

  @Override
  public Batch batchInsert(
      TableRecord<?>... records) {
    return delegate.batchInsert(records);
  }

  @Override
  public Batch batchInsert(
      Collection<? extends TableRecord<?>> records) {
    return delegate.batchInsert(records);
  }

  @Override
  public Batch batchUpdate(
      UpdatableRecord<?>... records) {
    return delegate.batchUpdate(records);
  }

  @Override
  public Batch batchUpdate(
      Collection<? extends UpdatableRecord<?>> records) {
    return delegate.batchUpdate(records);
  }

  @Override
  public Batch batchDelete(
      UpdatableRecord<?>... records) {
    return delegate.batchDelete(records);
  }

  @Override
  public Batch batchDelete(
      Collection<? extends UpdatableRecord<?>> records) {
    return delegate.batchDelete(records);
  }

  @Override
  public Queries ddl(Schema schema) {
    return delegate.ddl(schema);
  }

  @Override
  public Queries ddl(Schema schema, DDLFlag... flags) {
    return delegate.ddl(schema, flags);
  }

  @Override
  public Queries ddl(
      Table<?> table) {
    return delegate.ddl(table);
  }

  @Override
  public Queries ddl(
      Table<?> table, DDLFlag... flags) {
    return delegate.ddl(table, flags);
  }

  @Override
  public CreateTableAsStep<Record> createTable(String table) {
    return delegate.createTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createTable(Name table) {
    return delegate.createTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createTable(
      Table<?> table) {
    return delegate.createTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createTableIfNotExists(String table) {
    return delegate.createTableIfNotExists(table);
  }

  @Override
  public CreateTableAsStep<Record> createTableIfNotExists(Name table) {
    return delegate.createTableIfNotExists(table);
  }

  @Override
  public CreateTableAsStep<Record> createTableIfNotExists(
      Table<?> table) {
    return delegate.createTableIfNotExists(table);
  }

  @Override
  public CreateTableAsStep<Record> createTemporaryTable(String table) {
    return delegate.createTemporaryTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createTemporaryTable(Name table) {
    return delegate.createTemporaryTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createTemporaryTable(
      Table<?> table) {
    return delegate.createTemporaryTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createGlobalTemporaryTable(String table) {
    return delegate.createGlobalTemporaryTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createGlobalTemporaryTable(Name table) {
    return delegate.createGlobalTemporaryTable(table);
  }

  @Override
  public CreateTableAsStep<Record> createGlobalTemporaryTable(
      Table<?> table) {
    return delegate.createGlobalTemporaryTable(table);
  }

  @Override
  public CreateViewAsStep<Record> createView(String view, String... fields) {
    return delegate.createView(view, fields);
  }

  @Override
  public CreateViewAsStep<Record> createView(Name view, Name... fields) {
    return delegate.createView(view, fields);
  }

  @Override
  public CreateViewAsStep<Record> createView(
      Table<?> view,
      Field<?>... fields) {
    return delegate.createView(view, fields);
  }

  @Override
  public CreateViewAsStep<Record> createViewIfNotExists(String view, String... fields) {
    return delegate.createViewIfNotExists(view, fields);
  }

  @Override
  public CreateViewAsStep<Record> createViewIfNotExists(Name view, Name... fields) {
    return delegate.createViewIfNotExists(view, fields);
  }

  @Override
  public CreateViewAsStep<Record> createViewIfNotExists(
      Table<?> view,
      Field<?>... fields) {
    return delegate.createViewIfNotExists(view, fields);
  }

  @Override
  public CreateIndexStep createIndex(String index) {
    return delegate.createIndex(index);
  }

  @Override
  public CreateIndexStep createIndex(Name index) {
    return delegate.createIndex(index);
  }

  @Override
  public CreateIndexStep createIndexIfNotExists(String index) {
    return delegate.createIndexIfNotExists(index);
  }

  @Override
  public CreateIndexStep createIndexIfNotExists(Name index) {
    return delegate.createIndexIfNotExists(index);
  }

  @Override
  public CreateIndexStep createUniqueIndex(String index) {
    return delegate.createUniqueIndex(index);
  }

  @Override
  public CreateIndexStep createUniqueIndex(Name index) {
    return delegate.createUniqueIndex(index);
  }

  @Override
  public CreateIndexStep createUniqueIndexIfNotExists(String index) {
    return delegate.createUniqueIndexIfNotExists(index);
  }

  @Override
  public CreateIndexStep createUniqueIndexIfNotExists(Name index) {
    return delegate.createUniqueIndexIfNotExists(index);
  }

  @Override
  public CreateSequenceFinalStep createSequence(String sequence) {
    return delegate.createSequence(sequence);
  }

  @Override
  public CreateSequenceFinalStep createSequence(Name sequence) {
    return delegate.createSequence(sequence);
  }

  @Override
  public CreateSequenceFinalStep createSequence(
      Sequence<?> sequence) {
    return delegate.createSequence(sequence);
  }

  @Override
  public CreateSequenceFinalStep createSequenceIfNotExists(String sequence) {
    return delegate.createSequenceIfNotExists(sequence);
  }

  @Override
  public CreateSequenceFinalStep createSequenceIfNotExists(Name sequence) {
    return delegate.createSequenceIfNotExists(sequence);
  }

  @Override
  public CreateSequenceFinalStep createSequenceIfNotExists(
      Sequence<?> sequence) {
    return delegate.createSequenceIfNotExists(sequence);
  }

  @Override
  public AlterSequenceRestartStep<BigInteger> alterSequence(String sequence) {
    return delegate.alterSequence(sequence);
  }

  @Override
  public AlterSequenceRestartStep<BigInteger> alterSequence(Name sequence) {
    return delegate.alterSequence(sequence);
  }

  @Override
  public <T extends Number> AlterSequenceRestartStep<T> alterSequence(Sequence<T> sequence) {
    return delegate.alterSequence(sequence);
  }

  @Override
  public AlterTableStep alterTable(String table) {
    return delegate.alterTable(table);
  }

  @Override
  public AlterTableStep alterTable(Name table) {
    return delegate.alterTable(table);
  }

  @Override
  public AlterTableStep alterTable(
      Table<?> table) {
    return delegate.alterTable(table);
  }

  @Override
  public DropViewFinalStep dropView(String view) {
    return delegate.dropView(view);
  }

  @Override
  public DropViewFinalStep dropView(Name view) {
    return delegate.dropView(view);
  }

  @Override
  public DropViewFinalStep dropView(
      Table<?> view) {
    return delegate.dropView(view);
  }

  @Override
  public DropViewFinalStep dropViewIfExists(String view) {
    return delegate.dropViewIfExists(view);
  }

  @Override
  public DropViewFinalStep dropViewIfExists(Name view) {
    return delegate.dropViewIfExists(view);
  }

  @Override
  public DropViewFinalStep dropViewIfExists(
      Table<?> view) {
    return delegate.dropViewIfExists(view);
  }

  @Override
  public DropTableStep dropTable(String table) {
    return delegate.dropTable(table);
  }

  @Override
  public DropTableStep dropTable(Name table) {
    return delegate.dropTable(table);
  }

  @Override
  public DropTableStep dropTable(
      Table<?> table) {
    return delegate.dropTable(table);
  }

  @Override
  public DropTableStep dropTableIfExists(String table) {
    return delegate.dropTableIfExists(table);
  }

  @Override
  public DropTableStep dropTableIfExists(Name table) {
    return delegate.dropTableIfExists(table);
  }

  @Override
  public DropTableStep dropTableIfExists(
      Table<?> table) {
    return delegate.dropTableIfExists(table);
  }

  @Override
  public DropIndexOnStep dropIndex(String index) {
    return delegate.dropIndex(index);
  }

  @Override
  public DropIndexOnStep dropIndex(Name index) {
    return delegate.dropIndex(index);
  }

  @Override
  public DropIndexOnStep dropIndexIfExists(String index) {
    return delegate.dropIndexIfExists(index);
  }

  @Override
  public DropIndexOnStep dropIndexIfExists(Name index) {
    return delegate.dropIndexIfExists(index);
  }

  @Override
  public DropSequenceFinalStep dropSequence(String sequence) {
    return delegate.dropSequence(sequence);
  }

  @Override
  public DropSequenceFinalStep dropSequence(Name sequence) {
    return delegate.dropSequence(sequence);
  }

  @Override
  public DropSequenceFinalStep dropSequence(
      Sequence<?> sequence) {
    return delegate.dropSequence(sequence);
  }

  @Override
  public DropSequenceFinalStep dropSequenceIfExists(String sequence) {
    return delegate.dropSequenceIfExists(sequence);
  }

  @Override
  public DropSequenceFinalStep dropSequenceIfExists(Name sequence) {
    return delegate.dropSequenceIfExists(sequence);
  }

  @Override
  public DropSequenceFinalStep dropSequenceIfExists(
      Sequence<?> sequence) {
    return delegate.dropSequenceIfExists(sequence);
  }

  @Override
  public TruncateIdentityStep<Record> truncate(String table) {
    return delegate.truncate(table);
  }

  @Override
  public TruncateIdentityStep<Record> truncate(Name table) {
    return delegate.truncate(table);
  }

  @Override
  public <R extends Record> TruncateIdentityStep<R> truncate(Table<R> table) {
    return delegate.truncate(table);
  }

  @Override
  public BigInteger lastID() throws DataAccessException {
    return delegate.lastID();
  }

  @Override
  public BigInteger nextval(String sequence) throws DataAccessException {
    return delegate.nextval(sequence);
  }

  @Override
  public <T extends Number> T nextval(Sequence<T> sequence) throws DataAccessException {
    return delegate.nextval(sequence);
  }

  @Override
  public BigInteger currval(String sequence) throws DataAccessException {
    return delegate.currval(sequence);
  }

  @Override
  public <T extends Number> T currval(Sequence<T> sequence) throws DataAccessException {
    return delegate.currval(sequence);
  }

  @Override
  public <R extends UDTRecord<R>> R newRecord(UDT<R> type) {
    return delegate.newRecord(type);
  }

  @Override
  public <R extends Record> R newRecord(Table<R> table) {
    return delegate.newRecord(table);
  }

  @Override
  public <R extends Record> R newRecord(Table<R> table, Object source) {
    return delegate.newRecord(table, source);
  }

  @Override
  public Record newRecord(
      Field<?>... fields) {
    return delegate.newRecord(fields);
  }

  @Override
  public <T1> Record1<T1> newRecord(Field<T1> field1) {
    return delegate.newRecord(field1);
  }

  @Override
  public <T1, T2> Record2<T1, T2> newRecord(Field<T1> field1, Field<T2> field2) {
    return delegate.newRecord(field1, field2);
  }

  @Override
  public <T1, T2, T3> Record3<T1, T2, T3> newRecord(Field<T1> field1, Field<T2> field2,
      Field<T3> field3) {
    return delegate.newRecord(field1, field2, field3);
  }

  @Override
  public <T1, T2, T3, T4> Record4<T1, T2, T3, T4> newRecord(Field<T1> field1, Field<T2> field2,
      Field<T3> field3, Field<T4> field4) {
    return delegate.newRecord(field1, field2, field3, field4);
  }

  @Override
  public <T1, T2, T3, T4, T5> Record5<T1, T2, T3, T4, T5> newRecord(Field<T1> field1,
      Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5) {
    return delegate.newRecord(field1, field2, field3, field4, field5);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6> Record6<T1, T2, T3, T4, T5, T6> newRecord(Field<T1> field1,
      Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7> Record7<T1, T2, T3, T4, T5, T6, T7> newRecord(Field<T1> field1,
      Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6,
      Field<T7> field7) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8> Record8<T1, T2, T3, T4, T5, T6, T7, T8> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19, Field<T20> field20) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19, Field<T20> field20, Field<T21> field21) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20, field21);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> newRecord(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19, Field<T20> field20, Field<T21> field21, Field<T22> field22) {
    return delegate.newRecord(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20, field21, field22);
  }

  @Override
  public <R extends Record> Result<R> newResult(Table<R> table) {
    return delegate.newResult(table);
  }

  @Override
  public Result<Record> newResult(
      Field<?>... fields) {
    return delegate.newResult(fields);
  }

  @Override
  public <T1> Result<Record1<T1>> newResult(Field<T1> field1) {
    return delegate.newResult(field1);
  }

  @Override
  public <T1, T2> Result<Record2<T1, T2>> newResult(Field<T1> field1, Field<T2> field2) {
    return delegate.newResult(field1, field2);
  }

  @Override
  public <T1, T2, T3> Result<Record3<T1, T2, T3>> newResult(Field<T1> field1, Field<T2> field2,
      Field<T3> field3) {
    return delegate.newResult(field1, field2, field3);
  }

  @Override
  public <T1, T2, T3, T4> Result<Record4<T1, T2, T3, T4>> newResult(Field<T1> field1,
      Field<T2> field2, Field<T3> field3, Field<T4> field4) {
    return delegate.newResult(field1, field2, field3, field4);
  }

  @Override
  public <T1, T2, T3, T4, T5> Result<Record5<T1, T2, T3, T4, T5>> newResult(Field<T1> field1,
      Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5) {
    return delegate.newResult(field1, field2, field3, field4, field5);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6> Result<Record6<T1, T2, T3, T4, T5, T6>> newResult(Field<T1> field1,
      Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7> Result<Record7<T1, T2, T3, T4, T5, T6, T7>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8> Result<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Result<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Result<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Result<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Result<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Result<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Result<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Result<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Result<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Result<Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Result<Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Result<Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Result<Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19, Field<T20> field20) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Result<Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19, Field<T20> field20, Field<T21> field21) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20, field21);
  }

  @Override
  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Result<Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>> newResult(
      Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5,
      Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10,
      Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14,
      Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18,
      Field<T19> field19, Field<T20> field20, Field<T21> field21, Field<T22> field22) {
    return delegate.newResult(field1, field2, field3, field4, field5, field6, field7, field8, field9,
        field10, field11, field12, field13, field14, field15, field16, field17, field18, field19,
        field20, field21, field22);
  }

  @Override
  public <R extends Record> Result<R> fetch(ResultQuery<R> query) throws DataAccessException {
    return delegate.fetch(query);
  }

  @Override
  public <R extends Record> Cursor<R> fetchLazy(ResultQuery<R> query) throws DataAccessException {
    return delegate.fetchLazy(query);
  }

  @Override
  public <R extends Record> CompletionStage<Result<R>> fetchAsync(ResultQuery<R> query) {
    return delegate.fetchAsync(query);
  }

  @Override
  public <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor,
      ResultQuery<R> query) {
    return delegate.fetchAsync(executor, query);
  }

  @Override
  public <R extends Record> Stream<R> fetchStream(ResultQuery<R> query) throws DataAccessException {
    return delegate.fetchStream(query);
  }

  @Override
  public <R extends Record> Results fetchMany(ResultQuery<R> query) throws DataAccessException {
    return delegate.fetchMany(query);
  }

  @Override
  public <R extends Record> R fetchOne(ResultQuery<R> query) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOne(query);
  }

  @Override
  public <R extends Record> Optional<R> fetchOptional(ResultQuery<R> query) throws
      DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(query);
  }

  @Override
  public <T, R extends Record1<T>> T fetchValue(ResultQuery<R> query) throws DataAccessException,
      TooManyRowsException, InvalidResultException {
    return delegate.fetchValue(query);
  }

  @Override
  public <T> T fetchValue(
      TableField<?, T> field) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchValue(field);
  }

  @Override
  public <T, R extends Record1<T>> Optional<T> fetchOptionalValue(ResultQuery<R> query) throws
      DataAccessException, TooManyRowsException, InvalidResultException {
    return delegate.fetchOptionalValue(query);
  }

  @Override
  public <T> Optional<T> fetchOptionalValue(
      TableField<?, T> field) throws DataAccessException, TooManyRowsException,
      InvalidResultException {
    return delegate.fetchOptionalValue(field);
  }

  @Override
  public <T, R extends Record1<T>> List<T> fetchValues(ResultQuery<R> query) throws
      DataAccessException {
    return delegate.fetchValues(query);
  }

  @Override
  public <T> List<T> fetchValues(
      TableField<?, T> field) throws DataAccessException {
    return delegate.fetchValues(field);
  }

  @Override
  public int fetchCount(
      Select<?> query) throws DataAccessException {
    return delegate.fetchCount(query);
  }

  @Override
  public int fetchCount(
      Table<?> table) throws DataAccessException {
    return delegate.fetchCount(table);
  }

  @Override
  public int fetchCount(
      Table<?> table, Condition condition) throws DataAccessException {
    return delegate.fetchCount(table, condition);
  }

  @Override
  public boolean fetchExists(
      Select<?> query) throws DataAccessException {
    return delegate.fetchExists(query);
  }

  @Override
  public boolean fetchExists(
      Table<?> table) throws DataAccessException {
    return delegate.fetchExists(table);
  }

  @Override
  public boolean fetchExists(
      Table<?> table, Condition condition) throws DataAccessException {
    return delegate.fetchExists(table, condition);
  }

  @Override
  public int execute(Query query) throws DataAccessException {
    return delegate.execute(query);
  }

  @Override
  public <R extends Record> Result<R> fetch(Table<R> table) throws DataAccessException {
    return delegate.fetch(table);
  }

  @Override
  public <R extends Record> Result<R> fetch(Table<R> table, Condition condition) throws
      DataAccessException {
    return delegate.fetch(table, condition);
  }

  @Override
  public <R extends Record> R fetchOne(Table<R> table) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOne(table);
  }

  @Override
  public <R extends Record> R fetchOne(Table<R> table, Condition condition) throws
      DataAccessException, TooManyRowsException {
    return delegate.fetchOne(table, condition);
  }

  @Override
  public <R extends Record> Optional<R> fetchOptional(Table<R> table) throws DataAccessException,
      TooManyRowsException {
    return delegate.fetchOptional(table);
  }

  @Override
  public <R extends Record> Optional<R> fetchOptional(Table<R> table, Condition condition) throws
      DataAccessException, TooManyRowsException {
    return delegate.fetchOptional(table, condition);
  }

  @Override
  public <R extends Record> R fetchAny(Table<R> table) throws DataAccessException {
    return delegate.fetchAny(table);
  }

  @Override
  public <R extends Record> R fetchAny(Table<R> table, Condition condition) throws
      DataAccessException {
    return delegate.fetchAny(table, condition);
  }

  @Override
  public <R extends Record> Cursor<R> fetchLazy(Table<R> table) throws DataAccessException {
    return delegate.fetchLazy(table);
  }

  @Override
  public <R extends Record> Cursor<R> fetchLazy(Table<R> table, Condition condition) throws
      DataAccessException {
    return delegate.fetchLazy(table, condition);
  }

  @Override
  public <R extends Record> CompletionStage<Result<R>> fetchAsync(Table<R> table) {
    return delegate.fetchAsync(table);
  }

  @Override
  public <R extends Record> CompletionStage<Result<R>> fetchAsync(Table<R> table,
      Condition condition) {
    return delegate.fetchAsync(table, condition);
  }

  @Override
  public <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, Table<R> table) {
    return delegate.fetchAsync(executor, table);
  }

  @Override
  public <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, Table<R> table,
      Condition condition) {
    return delegate.fetchAsync(executor, table, condition);
  }

  @Override
  public <R extends Record> Stream<R> fetchStream(Table<R> table) throws DataAccessException {
    return delegate.fetchStream(table);
  }

  @Override
  public <R extends Record> Stream<R> fetchStream(Table<R> table, Condition condition) throws
      DataAccessException {
    return delegate.fetchStream(table, condition);
  }

  @Override
  public <R extends TableRecord<R>> int executeInsert(R record) throws DataAccessException {
    return delegate.executeInsert(record);
  }

  @Override
  public <R extends UpdatableRecord<R>> int executeUpdate(R record) throws DataAccessException {
    return delegate.executeUpdate(record);
  }

  @Override
  public <R extends TableRecord<R>, T> int executeUpdate(R record, Condition condition) throws
      DataAccessException {
    return delegate.executeUpdate(record, condition);
  }

  @Override
  public <R extends UpdatableRecord<R>> int executeDelete(R record) throws DataAccessException {
    return delegate.executeDelete(record);
  }

  @Override
  public <R extends TableRecord<R>, T> int executeDelete(R record, Condition condition) throws
      DataAccessException {
    return delegate.executeDelete(record, condition);
  }

  @Override
  public Configuration configuration() {
    return delegate.configuration();
  }

  @Override
  public Settings settings() {
    return delegate.settings();
  }

  @Override
  public SQLDialect dialect() {
    return delegate.dialect();
  }

  @Override
  public SQLDialect family() {
    return delegate.family();
  }

  @Override
  public Map<Object, Object> data() {
    return delegate.data();
  }

  @Override
  public Object data(Object key) {
    return delegate.data(key);
  }

  @Override
  public Object data(Object key, Object value) {
    return delegate.data(key, value);
  }
}
