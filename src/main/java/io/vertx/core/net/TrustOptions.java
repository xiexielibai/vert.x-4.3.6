/*
 * Copyright (c) 2011-2019 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 */

package io.vertx.core.net;

import io.vertx.core.Vertx;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.util.function.Function;

/**
 * Certification authority configuration options.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public interface TrustOptions {

  /**
   * @return a copy of these options
   */
  TrustOptions copy();

  /**
   * Create and return the trust manager factory for these options.
   * <p>
   * The returned trust manager factory should be already initialized and ready to use.
   *
   * @param vertx the vertx instance
   * @return the trust manager factory
   */
  TrustManagerFactory getTrustManagerFactory(Vertx vertx) throws Exception;

  /**
   * Returns a function that maps SNI server names to a {@link TrustManagerFactory} instance.
   *
   * The returned {@code TrustManagerFactory} must already be initialized and ready to use.
   *
   * The mapper is only used when the server has SNI enabled and the client indicated a server name.
   * <p/>
   * The returned function may return {@code null} in which case {@link #getTrustManagerFactory(Vertx)} is used as fallback.
   *
   * @param vertx the vertx instance
   * @return the trustManager
   */
  Function<String, TrustManager[]> trustManagerMapper(Vertx vertx) throws Exception;

  /**
   * Returns a {@link TrustOptions} from the provided {@link TrustManager}
   *
   * @param trustManager the trustManager instance
   * @return the {@link TrustOptions}
   */
  static TrustOptions wrap(TrustManager trustManager) {
    return new TrustManagerFactoryOptions(trustManager);
  }

  /**
   * Returns a {@link TrustOptions} from the provided {@link TrustManagerFactory}
   *
   * @param trustManagerFactory the trustManagerFactory instance
   * @return the {@link TrustOptions}
   */
  static TrustOptions wrap(TrustManagerFactory trustManagerFactory) {
    return new TrustManagerFactoryOptions(trustManagerFactory);
  }
}
