package com.appServices.AppServices.Services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.appServices.AppServices.security.UserSpringSecurity;

public class UserService {

	public static UserSpringSecurity authenticated() {
		try {
			return  (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		}
		catch (Exception e) {
			return null;
		}
	}	
	
	
}
