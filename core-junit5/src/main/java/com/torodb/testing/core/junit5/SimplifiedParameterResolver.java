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
 * removed {@link AfterAllCallback after all} tests on the annotated context are executed.
 */
public abstract class SimplifiedParameterResolver<P>
    implements ParameterResolver, AfterEachCallback, AfterAllCallback {

  protected abstract Class<P> getParameterClass();

  protected abstract P createParamValue(ExtensionContext context);

  protected abstract boolean cleanAfterTest(ExtensionContext context);

  protected void cleanCallback(Optional<P> param) {
  }

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
