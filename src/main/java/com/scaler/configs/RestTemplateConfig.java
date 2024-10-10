package com.scaler.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Component is a generic stereotype annotation indicating that the class is a Spring-managed component.
//When Spring’s component-scanning mechanism is enabled, it will automatically detect classes annotated with
//@Component and register them as beans in the Spring context.

//RestTemplate is a helper class in Spring Framework used for making HTTP requests to other RESTful services.
@Configuration //spring will be ready to go through this class and create the beans of the methods with bean annotation.
public class RestTemplateConfig {
    //annotation to specially make a bean out of class
    @Bean //please create a bean out of the return type of this method and store it inside the container for reuse.
    public RestTemplate restTemplate() {
        // return new RestTemplate not used as builder class gives additional timeout methods, header classes
        return new RestTemplateBuilder().build();
    }


}
