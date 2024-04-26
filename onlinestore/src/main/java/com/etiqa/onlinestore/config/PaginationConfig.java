package com.etiqa.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;


@Configuration
public class PaginationConfig {
	
	 	@Bean
	    public PageableHandlerMethodArgumentResolverCustomizer customizePageableResolver() {
	        return resolver -> {
	            resolver.setFallbackPageable(org.springframework.data.domain.PageRequest.of(0, 10));
	            resolver.setOneIndexedParameters(true);
	        };
	    }
}
