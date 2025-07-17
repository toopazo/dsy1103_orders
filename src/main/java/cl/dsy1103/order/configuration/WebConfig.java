package cl.dsy1103.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;

// This configuration is important to circumvent the default CORS restriction
// https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/CORS
// https://blog.postman.com/what-is-cors/

@Configuration
@Profile("dev")
// @Profile("do_not_run")
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
