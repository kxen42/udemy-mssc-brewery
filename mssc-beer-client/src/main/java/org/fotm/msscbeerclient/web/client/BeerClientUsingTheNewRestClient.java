package org.fotm.msscbeerclient.web.client;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fotm.msscbeerclient.config.ApiConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Setter
@Slf4j
@Component
//@ConfigurationProperties(prefix = "beer-api", ignoreUnknownFields = false)
public class BeerClientUsingTheNewRestClient implements InitializingBean {

  // property binding from application.properties
  // @Value v. @ConfigurationProperties
  // https://www.baeldung.com/configuration-properties-in-spring-boot
  // https://medium.com/@midhunbalan.dev/understanding-configurationproperties-annotation-in-springboot-674550f51f9a
  private String apihost;
  private String version;
  private String path;

  public BeerClientUsingTheNewRestClient(ApiConfig apiConfig) {
    this.apihost = apiConfig.apihost();
    this.version = apiConfig.version();
    this.path = apiConfig.path();
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("----- BeerClient apihost: " + apihost);
    log.info("----- BeerClient version: " + version);
    log.info("----- BeerClient path: " + path);
    log.info("----- BeerClient url: " + getApiUrl());
  }

  private String getApiUrl() {
    return ("%s" + path).formatted(apihost, version);
  }
}
