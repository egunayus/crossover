package com.crossover.techtrial.api.rest.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.crossover.techtrial.api.rest.security.filter.TokenPreAuthenticatedProcessingFilter;
import com.crossover.techtrial.api.rest.security.service.ApiPreAuthUserDetailsService;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ApiPreAuthUserDetailsService preAuthUserDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.antMatcher("/**")
		.csrf().disable() // http://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/auth/**").anonymous()
				.antMatchers(HttpMethod.GET, "/auth/**").anonymous()
				.antMatchers(HttpMethod.GET, "/buildInfo").anonymous()
				.antMatchers(HttpMethod.GET, "/app/**").anonymous()
				.antMatchers(HttpMethod.GET, "/assets/**").anonymous()
				.antMatchers(HttpMethod.GET, "/partials/**").anonymous()
				.antMatchers(HttpMethod.GET, "/index.html").anonymous()
				.antMatchers(HttpMethod.GET, "/favicon.ico").anonymous()
				.antMatchers(HttpMethod.GET, "/").anonymous()
				
				.antMatchers("/**").hasAnyRole("USER", "ADMIN")
		.and()
		.addFilter(preAuthFilter())
		//.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
		;
    }
	
	public Filter preAuthFilter() {
		TokenPreAuthenticatedProcessingFilter filter = new TokenPreAuthenticatedProcessingFilter();
		filter.setAuthenticationManager(preAuthAuthenticationManager());
		
		return filter;
	}
    
	protected AuthenticationManager preAuthAuthenticationManager() {
		PreAuthenticatedAuthenticationProvider preAuthProvider= new PreAuthenticatedAuthenticationProvider();
		preAuthProvider.setPreAuthenticatedUserDetailsService(preAuthUserDetailsService);
		
		List<AuthenticationProvider> providers = new ArrayList<>();
		providers.add(preAuthProvider);
		
		ProviderManager authMan = new ProviderManager(providers);
		return authMan;
	}
	
}
