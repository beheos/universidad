package com.beheos.universidad.service;

import com.beheos.universidad.entity.Usuarios;

public interface UsuariosService {
	
	Usuarios loadUserByUsername(String username);

}
