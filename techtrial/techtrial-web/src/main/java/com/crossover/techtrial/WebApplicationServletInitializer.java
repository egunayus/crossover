package com.crossover.techtrial;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This class is used to start web application from within an application server e.g. Tomcat
 * 
 * @author egunay
 * @see WebApplicationMain
 */
public class WebApplicationServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplicationMain.class);
	}
	
}
