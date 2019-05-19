package com.appServices.AppServices.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appServices.AppServices.Services.AuthService;
import com.appServices.AppServices.Services.UserService;
import com.appServices.AppServices.dto.EmailDTO;
import com.appServices.AppServices.security.JWTUtil;
import com.appServices.AppServices.security.UserSpringSecurity;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	//MÉTODO DE ATUALIZAÇÃO DO TOKEN
		@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
		public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
			UserSpringSecurity user = UserService.authenticated();
			String token = jwtUtil.generateToken(user.getUsername());
			response.addHeader("Authorization", "Bearer " + token);
			response.addHeader("access-control-expose-headers", "Authorization");
			return ResponseEntity.noContent().build();
		}
	
	//MÉTODO "ESQUECI A SENHA"
		@RequestMapping(value = "/forgot", method = RequestMethod.POST)
		public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
			service.sendNewPassword(objDTO.getEmail());
			return ResponseEntity.noContent().build();
		}
}
