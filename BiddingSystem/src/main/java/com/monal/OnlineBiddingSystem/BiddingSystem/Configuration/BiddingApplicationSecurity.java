package com.monal.OnlineBiddingSystem.BiddingSystem.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
          .withUser("Kumar")
          .password(encoder.encode("Kumar"))
          .roles("USER", "ADMIN")
          .and()
          .withUser("Monal")
          .password(encoder.encode("Monal"))
          .roles("USER", "ADMIN")
          .and()
          .withUser("Raj")
          .password(encoder.encode("Raj"))
          .roles("USER", "ADMIN")
          .and()
          .withUser("Raunik")
          .password(encoder.encode("Raunik"))
          .roles("USER", "ADMIN");      
    }
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	  httpSecurity.
    	      authorizeRequests().
    	      antMatchers("/h2-console/**").permitAll().
    	      antMatchers("/auction/**").hasAnyRole("ADMIN","USER")
    	      .and()
    	      .httpBasic().and()
               .csrf().disable()
               .headers().frameOptions().disable();
    }
	
}
