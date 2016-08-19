package com.crossover.techtrial;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This class is used to start admin application from within an application server e.g. Tomcat
 * 
 * @author egunay
 * @see AdminApplicationMain
 */
public class AdminApplicationServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdminApplicationMain.class);
	}
	
}
