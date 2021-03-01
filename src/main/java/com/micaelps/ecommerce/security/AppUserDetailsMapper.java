package com.micaelps.ecommerce.security;

import com.micaelps.ecommerce.newUserSystem.UserSystem;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;


@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return new LoggedUser((UserSystem)shouldBeASystemUser);
	}

}
