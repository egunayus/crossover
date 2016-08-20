package com.crossover.techtrial;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This class is used to start web application from within an application server e.g. Tomcat
 * 
 * @author egunay
 * @see ApiApplicationMain
 */
public class ApiApplicationServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiApplicationMain.class);
	}
	
}
