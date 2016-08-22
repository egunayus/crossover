package com.crossover.techtrial.api.rest.auth.model;

/**
 * Authentication request object with parameters
 * 
 * @author egunay
 *
 */
public class GoogleAuthRequest {

	private String state;

	private String code;
	
	private String redirectUri;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	
}
