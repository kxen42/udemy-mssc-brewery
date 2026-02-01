package org.fotm.msscbeerclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    @PropertySource("classpath:httpclient.properties"),
    @PropertySource(value = "classpath:httpclient-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
})
public class HttpClientConfig {
}
