package com.monal.OnlineBiddingSystem.BiddingSystem.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BiddingApplicationSecurity extends WebSecurityConfigurerAdapter{

	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = 
          PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	auth
          .inMemoryAuthentication()
          .withUser("Monal")
          .password(encoder.encode("Monal"))
          .roles("USER", "ADMIN")
          .and()
          .withUser("Kumar")
          .password(encoder.encode("Kumar"))
          .roles("USER")
          .and()
          .withUser("Raj")
          .password(encoder.encode("Raj"))
          .roles("USER")
          .and()
          .withUser("Raunik")
          .password(encoder.encode("Raunik"))
          .roles("USER");       
    }
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	  httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
          .authorizeRequests().antMatchers("/console/**").permitAll();
  httpSecurity.csrf().disable();
  httpSecurity.headers().frameOptions().disable();
    }
	
}
