package com.crossover.techtrial.api.rest.security.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.StringUtils;


/**
 * Authentication filter for pre-authenticated requests.
 * 
 */
public class TokenPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public TokenPreAuthenticatedProcessingFilter() {}
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		log.trace("[AUTHENTICATION] [AUTH_FILTER] [TOKEN_PRE_AUTHENTICATED_PROCESSING_FILTER] [PROCESSING]");
		
		String xauthToken = request.getHeader("X-Auth-Token");
		if (xauthToken == null)
			xauthToken = request.getParameter("X-Auth-Token");
		
		if (!StringUtils.hasText(xauthToken)) {
			return null;
		}
		
		return xauthToken;
	}
	
	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		String xauthToken = request.getHeader("X-Auth-Token");
		if (xauthToken == null)
			xauthToken = request.getParameter("X-Auth-Token");
		
		return xauthToken;
	}
	
}
