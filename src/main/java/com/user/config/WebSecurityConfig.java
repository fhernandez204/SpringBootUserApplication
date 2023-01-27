package com.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private PropertiesConfigurator propertiesConfigurator;
  private String user;

  public WebSecurityConfig() {
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic(withDefaults())  // (1)
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/public/**").permitAll()
           .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password("{noop}1234").roles("USER")
        .and()
        .withUser("admin").password("{noop}password").roles("ADMIN");
  }
}
