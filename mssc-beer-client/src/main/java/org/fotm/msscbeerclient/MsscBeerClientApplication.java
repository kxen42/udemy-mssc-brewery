package org.fotm.msscbeerclient;

import org.fotm.msscbeerclient.config.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiConfig.class)
public class MsscBeerClientApplication {

   static void main(String[] args) {
    SpringApplication.run(MsscBeerClientApplication.class, args);
  }

}
