package com.example.airlinebooking.configurations;//package com.example.airlinebooking.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurer;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig {
//
//    @Bean
//    public SecurityConfigurer securityConfigurer() {
//        return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
//            @Override
//            public void configure(HttpSecurity http) throws Exception {
//                http
//                        .authorizeRequests()
//                        .anyRequest().permitAll()
//                        .and()
//                        .csrf().disable();
//            }
//        };
//    }
//}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class GlobalRepositoryRestConfigurer implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        cors.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Client-side address
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true).maxAge(3600);

        // If you use additional repositories, expose their IDs with the following:
        // config.exposeIdsFor(Class1.class, Class2.class);
    }
}
