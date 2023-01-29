package com.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${ss.username}")
  private String username;
  @Value("${ss.password}")
  private String password;
  @Value("${ss.password_}")
  private String password_;
  @Value("${ss.username_}")
  private String username_;

  private PropertiesConfigurator propertiesConfigurator;
  private String user;

  final Logger LOG = Logger.getLogger("WebSecurityConfig");

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
    LOG.severe("username_: "+username_);
    LOG.severe("password_: "+password_);
    auth
        .inMemoryAuthentication()
        .withUser(username).password("{noop}"+password).roles("USER")
        .and()
        .withUser(username_).password("{noop}"+password_).roles("ADMIN");
  }
}
