package org.fotm.msscbeerclient.web.client;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Slf4j
@Component
@ConfigurationProperties(prefix = "beer.api", ignoreUnknownFields = false)
public class BeerClient implements InitializingBean {

  // property binding from application.properties
  // @Value v. @ConfigurationProperties
  // https://medium.com/@midhunbalan.dev/understanding-configurationproperties-annotation-in-springboot-674550f51f9a
  private String apihost;
  private String version;
  private String path;


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
