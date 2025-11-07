package com.hotel.hotel.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hotel.hotel.service.CustomUserDetailsService;



@Configuration

public class SecurityConfig {



 private final CustomUserDetailsService userDetailsService;



 public SecurityConfig(CustomUserDetailsService userDetailsService) {

 this.userDetailsService = userDetailsService;

 }



 @Bean

 public SecurityFilterChain securityFilterChain(HttpSecurity http,CustomLoginSuccessHandler successHandler) throws Exception {

 http

 .authorizeHttpRequests(auth -> auth

 .requestMatchers("/admin/**").hasRole("ADMIN") 

 .requestMatchers("/client/**").hasRole("CLIENT")

 .requestMatchers("/", "/altaclient", "/hotels", "/reserves/crear", "/hotels/{id}", "/registrarClient", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

 .anyRequest().authenticated() 




 )

 .formLogin(form -> form

 .loginPage("/login") // Indiquem la pàgina de login personalitzada

 .successHandler(successHandler)

 .permitAll()

 )

 .logout(logout -> logout.logoutUrl("/logout").permitAll());



 return http.build();

 }



 @Bean

 public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {

 return authConfig.getAuthenticationManager();

 }



 @Bean

 public PasswordEncoder passwordEncoder() {

 //return new BCryptPasswordEncoder();

 return NoOpPasswordEncoder.getInstance(); // NO SEGUR, només per proves

 

 

 }

}