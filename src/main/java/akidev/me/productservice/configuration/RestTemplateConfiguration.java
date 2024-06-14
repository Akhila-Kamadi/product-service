package akidev.me.productservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean(name = "defaultRestTemplate")
    public RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    @Bean(name = "restTemplateWithHttpClient")
    public RestTemplate createRestTemplateWithHttpClient(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
    }
}
