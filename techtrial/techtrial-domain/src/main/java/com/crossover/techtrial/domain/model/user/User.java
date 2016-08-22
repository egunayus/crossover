package com.crossover.techtrial.domain.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.crossover.techtrial.domain.model.AbstractPersonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User extends AbstractPersonEntity {
	
    @Column(unique = true)
	private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
	
    @JsonIgnore
    private Boolean locked = false;

    @JsonIgnore
    private Boolean expired = false;

    @JsonIgnore
    private Boolean enabled = true;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	public Boolean getExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
}
