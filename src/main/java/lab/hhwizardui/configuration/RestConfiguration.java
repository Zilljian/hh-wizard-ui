package lab.hhwizardui.configuration;

import lab.hhwizardui.dto.ConnectionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate(ConnectionProperties connectionProperties) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.rootUri(connectionProperties.getUrl());
        return new RestTemplate();
    }

    @Bean
    @ConfigurationProperties("connection.rest")
    public ConnectionProperties connectionProperties() {
        return new ConnectionProperties();
    }
}
