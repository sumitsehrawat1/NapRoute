package com.potodev.NapRoute.configs;

import com.potodev.NapRoute.filters.JwtAuthFilter;
import com.potodev.NapRoute.props.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final SecurityProperties securityProperties;

    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter, SecurityProperties securityProperties) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.securityProperties = securityProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(securityProperties.getWhitelistedUrls().toArray(new String[0])).permitAll()
                        .requestMatchers(securityProperties.getFilterUrls().toArray(new String[0])).authenticated()
                        .anyRequest().denyAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

