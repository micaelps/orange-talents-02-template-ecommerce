package com.micaelps.ecommerce.security;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.micaelps.ecommerce.newUserSystem.UserSystem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public class LoggedUser implements UserDetails {

	private UserSystem user;
	private User springUserDetails;

	public LoggedUser(@NotNull @Valid UserSystem usuario) {
		this.user = usuario;
		springUserDetails = new User(usuario.getEmail(), usuario.getPassword(), List.of());
	}
	
	

	public Collection<GrantedAuthority> getAuthorities() {
		return springUserDetails.getAuthorities();
	}



	public String getPassword() {
		return springUserDetails.getPassword();
	}



	public String getUsername() {
		return springUserDetails.getUsername();
	}



	public boolean isEnabled() {
		return springUserDetails.isEnabled();
	}



	public boolean isAccountNonExpired() {
		return springUserDetails.isAccountNonExpired();
	}



	public boolean isAccountNonLocked() {
		return springUserDetails.isAccountNonLocked();
	}



	public boolean isCredentialsNonExpired() {
		return springUserDetails.isCredentialsNonExpired();
	}



	public UserSystem get() {
		return user;
	}

}
