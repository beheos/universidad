package com.beheos.universidad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beheos.universidad.dao.IUsuarios;
import com.beheos.universidad.entity.Usuarios;

@Service
public class UsuarioServImpl  {

	private final IUsuarios iUsuarios;
	
	public UsuarioServImpl(IUsuarios iUsuarios) {
        this.iUsuarios = iUsuarios;
    }
	
	/*
	@Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Usuarios user = iUsuarios.findByUsername(username);
	        
	        if (user == null) {
	            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
	        }
	        
	        return org.springframework.security.core.userdetails.User
	            .withUsername(user.getUsername())
	            .password(user.getPassword())
	            .roles("USER")
	            .build();
	    }

*/
	

}
