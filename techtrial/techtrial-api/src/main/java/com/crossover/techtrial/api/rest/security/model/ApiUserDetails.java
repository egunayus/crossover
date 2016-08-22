package com.crossover.techtrial.api.rest.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crossover.techtrial.domain.model.user.User;

public class ApiUserDetails implements UserDetails {
	
	private static final String ROLE_USER = "ROLE_USER";

	private User user;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public ApiUserDetails(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	/**
	 * add default USER role
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(ROLE_USER));
			
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return ! user.getExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return ! user.getLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
