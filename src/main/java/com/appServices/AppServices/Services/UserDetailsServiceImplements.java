package com.appServices.AppServices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appServices.AppServices.domain.Cliente;
import com.appServices.AppServices.repositories.ClienteRepository;
import com.appServices.AppServices.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clienteRepository.findByEmail(email);
			if(cli ==null) {
				throw new UsernameNotFoundException(email);
			}
		
		return new UserSpringSecurity(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
