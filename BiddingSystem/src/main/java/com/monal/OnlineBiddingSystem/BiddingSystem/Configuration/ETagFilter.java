package com.monal.OnlineBiddingSystem.BiddingSystem.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;


@Configuration
public class ETagFilter {

	@Bean
	 public ShallowEtagHeaderFilter shallowEtagHeaderFilter(){
	  return new ShallowEtagHeaderFilter();
	 }
}
