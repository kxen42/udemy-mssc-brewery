package org.fotm.msscbeerclient.web.client;

import java.util.concurrent.TimeUnit;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.restclient.RestTemplateCustomizer;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

///
/// - Connection Timeout (http.connection.timeout) – the time to establish the connection with the
/// remote host
///
/// - Socket Timeout (http.socket.timeout) – the time waiting for data – after establishing the
/// connection; maximum time of inactivity between two data packets
///
/// - Connection Manager Timeout (http.connection-manager.timeout) – the time to wait for a
/// connection from the connection manager/pool
///
/// The first two parameters – the connection and socket timeouts – are the most important. However,
/// setting a timeout for getting a connection is definitely important in high load scenarios,
/// which is why the third parameter shouldn’t be ignored.
///
/// HttpClient doesn’t have any configuration that allows us to set an overall timeout for a
/// request; it does, however, provide abort functionality for requests, so we can leverage that
/// mechanism to implement a simple timeout mechanism. See <a
/// href="https://www.baeldung.com/httpclient-timeout">Apache HttpClient Timeout - Hard Timeout</a>
@Component // if you comment this out Spring will use the JDK HTTP Client
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

  private final Environment env;

  public BlockingRestTemplateCustomizer(Environment env) {
    this.env = env;
  }

  /**
   * Configures connection factory with timeouts and connection pooling.
   * This is all the Apache HttpClient 5 configuration.
   */
  public ClientHttpRequestFactory clientHttpRequestFactory() {

    ConnectionConfig connectionConfig = ConnectionConfig.custom()
        .setConnectTimeout(Timeout.ofSeconds(env.getProperty("httpclient.connect.timeout", Long.class, 2L)))
        .setSocketTimeout(Timeout.ofSeconds(env.getProperty("httpclient.socket.timeout", Long.class, 2L)))
        .build();

    PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
        .setDefaultConnectionConfig(connectionConfig)
        .setMaxConnTotal(env.getProperty("httpclient.max.connection.total", Integer.class, 2))
        .setMaxConnPerRoute(env.getProperty("httpclient.max.connection.per.route", Integer.class, 2))
        .build();

    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(env.getProperty("httpclient.connection.request.timeout", Integer.class, 2), TimeUnit.SECONDS)
        .setResponseTimeout(env.getProperty("httpclient.response.timeout", Integer.class, 2), TimeUnit.SECONDS)
        .build();

    CloseableHttpClient httpClient = HttpClients
        .custom()
        .setConnectionManager(connectionManager)
        .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
        .setDefaultRequestConfig(requestConfig)
        .build();

    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }

  /**
   * Spring will inject this in the RestTemplate instance here.
   * @param restTemplate provided by Spring during initialization
   */
  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.setRequestFactory(this.clientHttpRequestFactory());
  }
}
