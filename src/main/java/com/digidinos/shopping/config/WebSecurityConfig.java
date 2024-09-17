package com.digidinos.shopping.config;

import com.digidinos.shopping.security.CustomSuccessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.digidinos.shopping.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    CustomSuccessHandle customSuccessHandle; // Inject CustomSuccessHandle

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Quy định quyền truy cập cho các tài nguyên tĩnh
        http.authorizeRequests()
                .antMatchers("/assets/**", "/product/image/**").permitAll() // Cho phép tất cả truy cập vào tài nguyên tĩnh
                .antMatchers("/", "/login", "/register").permitAll() // Các đường dẫn không yêu cầu đăng nhập
                .antMatchers("/admin/**").hasRole("MANAGER") // Quyền truy cập cho người dùng có vai trò ROLE_MANAGER
                .antMatchers("/product/**", "/cart/**", "/order/**").hasAnyRole("EMPLOYEE", "MANAGER") // Quyền truy cập cho người dùng có vai trò ROLE_EMPLOYEE, va MANAGER
                .anyRequest().authenticated() // Quyền truy cập cho tất cả người dùng đã đăng nhập
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // Submit URL
                .loginPage("/login") // Trang đăng nhập
                .successHandler(customSuccessHandle) // Sử dụng CustomSuccessHandle
                .failureUrl("/login?error=true") // Trang lỗi nếu đăng nhập không thành công
                .usernameParameter("userName")
                .passwordParameter("passWord")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }
}