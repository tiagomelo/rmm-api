package com.ninjaone.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuration class that makes possible to inject the beans listed here.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Configuration
@EnableCaching
public class RmmApiApplicationConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("rmmCache");
	}
	
	@Bean
	 public OpenAPI customOpenAPI() {
       return new OpenAPI()
               .components(new Components())
               .info(new Info().title("NinjaOne's RMM API").description(
                       "This is a tiny RMM API."));
   }
}
