package org.fotm.msscbeerclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "beer-api")
public record ApiConfig(String apihost, String version, String path) {

}
