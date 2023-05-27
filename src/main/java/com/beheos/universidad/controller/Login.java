package com.beheos.universidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class Login {
	
	@GetMapping("")
	public String mostar(){
		return "login";
	}
	
    @RequestMapping(value = "/entrar", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // Aquí puedes realizar la validación de credenciales y autenticación

        // Si la autenticación es exitosa, redirecciona al URL deseado
        return "redirect:/alumnos/lista";

        // Si la autenticación falla, puedes redirigir a una página de error o mostrar un mensaje de error al usuario
    }
	

}
