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

package com.torodb.testing.core.junit5;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.util.Optional;

/**
 * A {@link ParameterResolver} that can only inject a single parameter but instanciate it lazily.
 *
 * <p/>The parameter is stored on the {@link ExtensionContext#getStore() context store} and it is
 * removed {@link AfterAllCallback after all} tests on the annotated context are executed. It is
 * also removed {@link AfterEachCallback after each method} if
 * {@link #cleanAfterTest(org.junit.jupiter.api.extension.ExtensionContext) } returns true.
 */
public abstract class SimplifiedParameterResolver<P>
    implements ParameterResolver, AfterEachCallback, AfterAllCallback {

  /**
   * The class of the resolved parameter.
   */
  protected abstract Class<P> getParameterClass();

  /**
   * Creates the parameter value.
   * 
   * @param context the execution context on which the parameter was required
   * @return the value that will be injected on the test execution
   */
  protected abstract P createParamValue(ExtensionContext context);

  /**
   * Whether the injected parameter must be recalculated for each executed test.
   *
   * If this method returns true, then
   * {@link #createParamValue(org.junit.jupiter.api.extension.ExtensionContext) } and
   * {@link #cleanCallback(java.util.Optional) } will be called once for each test. If it returns
   * false, the same injected parameter will be used for each test, calling createParamValue the
   * first time the parameter is required and cleanCallback {@link AfterAllCallback after all} test
   * have been executed.
   */
  protected abstract boolean cleanAfterTest(ExtensionContext context);

  /**
   * It is called when the injected parameter is not going to be used anymore.
   *
   * @param param the parameter that will be used no more (or empty if it was an error while it was
   *              being created).
   */
  protected abstract void cleanCallback(Optional<P> param);

  @Override
  public boolean supports(ParameterContext pc, ExtensionContext ec) throws
      ParameterResolutionException {
    return optionallyResolve(pc, ec).isPresent();
  }

  @Override
  public Object resolve(ParameterContext pc, ExtensionContext ec) throws
      ParameterResolutionException {
    return optionallyResolve(pc, ec)
        .orElseThrow(() -> illegalResolveException(pc));
  }

  @Override
  public void afterEach(TestExtensionContext context) throws Exception {
    if (cleanAfterTest(context)) {
      clean(context);
    }
  }

  @Override
  public void afterAll(ContainerExtensionContext context) throws Exception {
    clean(context);
  }

  protected void clean(ExtensionContext context) {
    Optional<P> param = getStoredParam(context);
    cleanCallback(param);

    getStore(context).remove(getStoreKey());
  }

  private ExtensionContext.Store getStore(ExtensionContext context) {
    return context.getStore();
  }

  protected Optional<P> getStoredParam(ExtensionContext context) {
    return Optional.ofNullable(
        (P) getStore(context).get(getStoreKey())
    );
  }

  protected P getOrCreateStoredParam(ExtensionContext context) {
    return (P) getStore(context).getOrComputeIfAbsent(getStoreKey(),
        (ignored) -> createParamValue(context)
    );
  }

  protected Object getStoreKey() {
    return this.getClass();
  }

  private Optional<P> optionallyResolve(ParameterContext pc, ExtensionContext ec) {
    Class<?> expectedType = pc.getParameter().getType();

    if (!getParameterClass().isAssignableFrom(expectedType)) {
      return Optional.empty();
    }
    P storedParam = getOrCreateStoredParam(ec);
    if (!expectedType.isAssignableFrom(storedParam.getClass())) {
      return Optional.empty();
    }
    return Optional.of(storedParam);
  }

  private static ParameterResolutionException illegalResolveException(ParameterContext pc) {
    return new ParameterResolutionException("Impossible to resolve parameter " + pc.getParameter());
  }
}
