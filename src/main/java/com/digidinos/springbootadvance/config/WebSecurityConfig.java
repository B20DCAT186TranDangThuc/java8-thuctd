package com.digidinos.springbootadvance.config;

import com.digidinos.springbootadvance.security.CustomAuthenticationSuccessHandler;
import com.digidinos.springbootadvance.service.AccountService;
import com.digidinos.springbootadvance.service.CustomerUserDetailsService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountService accountService) {
        return new CustomerUserDetailsService(accountService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        // authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE)
                        .permitAll()
                        .requestMatchers("/", "/login", "/lib/**", "/css/**", "/js/**",
                                "/img/**")
                        .permitAll()

                        .requestMatchers("/forgot-password/**", "/register").permitAll()
                        .anyRequest().authenticated())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/login?error")
                        .permitAll())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403"))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
