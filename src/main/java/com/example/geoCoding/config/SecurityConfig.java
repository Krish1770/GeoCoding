package com.example.geoCoding.config;

import com.example.geoCoding.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        System.out.println("security config");
        httpSecurity.csrf()
                .disable()
//                .authorizeHttpRequests(auth-> auth.anyRequest().permitAll()
//                        )
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                .authorizeHttpRequests()
                .requestMatchers("/company/login", "/subscription/add", "/plan/add", "/shop/add","/shop/find").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();


    }


}
