package com.example.demo.Config;

import static org.springframework.http.HttpMethod.POST;

import com.example.demo.Config.LoginFilter;
import com.example.demo.Config.LogoutSuccessHandlerAdapter;
import com.example.demo.Database.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilter(logoutFilter())
                .addFilterBefore(customLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    @SneakyThrows
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    private LoginFilter customLoginFilter() {
        final LoginFilter loginFilter =  new LoginFilter(authenticationManagerBean(), objectMapper);
        loginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/my-login", POST.name()));

        return loginFilter;
    }

    private LogoutFilter logoutFilter() {
        final LogoutHandler[] logoutHandlers = new LogoutHandler[] {
                new SecurityContextLogoutHandler()
        };

        return new LogoutFilter(new LogoutSuccessHandlerAdapter(), logoutHandlers);
    }
}
