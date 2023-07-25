package com.example.websitesecondhand.config;

import com.example.websitesecondhand.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/ads/*/image",
            "/users/*/image",
            "/localhost:8080"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher(AUTH_WHITELIST)
                .authorizeHttpRequests(
                        (authorization) ->
                                authorization
                                        .requestMatchers("/swagger-ui/index.html", "/swagger-ui.html")
                                        .permitAll()
                                        .requestMatchers("/v3/api-docs")
                                        .permitAll()
                                        .requestMatchers("/login", "/register")
                                        .permitAll()
                                        .requestMatchers("/ads")
                                        .permitAll()
                                        .requestMatchers("/ads/**", "/users/**", "/setAdminRole/")
                                        .permitAll()
                                        .requestMatchers("/ads/**", "/users/**", "/setAdminRole/")
                                        .authenticated()
                );
        http.authenticationProvider(authenticationProvider());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
