package org.fotm.msscbeerclient.web.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class PropertyLoadingTest {

  @Autowired
  Environment env;

  @Test
  void testHttpClientConnectTimeoutLoaded() {
    assertEquals("5", env.getProperty("httpclient.connect.timeout"), "httpclient.connect.timeout should be 5 from httpclient-dev.properties");
  }

  @Test
  void testHttpClientSocketTimeoutLoaded() {
    assertEquals("5", env.getProperty("httpclient.socket.timeout"), "httpclient.socket.timeout should be 5 from httpclient-dev.properties");
  }

  @Test
  void testHttpClientMaxTotalLoaded() {
    assertEquals("50", env.getProperty("httpclient.max.connection.total"), "httpclient.max.connection.total should be 50 from httpclient-dev.properties");
  }

  @Test
  void testHttpClientMaxConnPerRouteLoaded() {
    assertEquals("10", env.getProperty("httpclient.max.connection.per.route"), "httpclient.max.connection.per.route should be 10 from httpclient-dev.properties");
  }

  @Test
  void testHttpClientConnectionRequestTimeoutLoaded() {
    assertEquals("5", env.getProperty("httpclient.connection.request.timeout"), "httpclient.connection.request.timeout should be 5 from httpclient-dev.properties");
  }

  @Test
  void testHttpClientResponseTimeoutLoaded() {
    assertEquals("5", env.getProperty("httpclient.response.timeout"), "httpclient.response.timeout should be 5 from httpclient-dev.properties");
  }
}
