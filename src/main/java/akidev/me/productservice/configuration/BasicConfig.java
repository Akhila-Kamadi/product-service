package akidev.me.productservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BasicConfig {

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
