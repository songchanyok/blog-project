package com.estsoft.blogproject.securityConfig;

import com.estsoft.blogproject.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    private final UserDetailService userDetailService;

    public WebSecurityConfig(UserDetailService userDetailService){
        this.userDetailService=userDetailService;
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return web -> web.ignoring().requestMatchers(toH2Console()).requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(auth->auth.requestMatchers("/login", "/signup", "/user").permitAll()
                                                            .anyRequest().authenticated())
                .formLogin(auth->auth.loginPage("/login").defaultSuccessUrl("/articles"))
                .logout(auth->auth.logoutSuccessUrl("/login").invalidateHttpSession(true))
                .csrf(auth->auth.disable()).build();


    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
