package com.appGobal.patient_appGobal.config;

import com.appGobal.patient_appGobal.security.JwTAppGobalFilter;
import com.appGobal.patient_appGobal.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration(proxyBeanMethods=false)
@EnableWebSecurity
public class Config {
    @Autowired
    JwTAppGobalFilter jwTAppGobalFilter;
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest.requestMatchers("/api/v1/login").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sesssion ->
                        sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore( jwTAppGobalFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.
                        configurationSource(cor -> {
                            CorsConfiguration  configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
                        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","UPDATE"));
                        return configuration;}
                        )
                )
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider());

            return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new MyUserDetailService();
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        return daoAuthenticationProvider();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userDetailsService());
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }


}
