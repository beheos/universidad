package com.beheos.universidad.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncriptarPassword {
	

	
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "123456";
        String passwordEncriptado = passwordEncoder.encode(password);
        System.out.println(passwordEncriptado);
	}
	
	
	
}
