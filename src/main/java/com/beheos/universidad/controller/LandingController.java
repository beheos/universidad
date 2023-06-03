package com.beheos.universidad.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beheos.universidad.dto.AlumnoDto;
import com.beheos.universidad.dto.LicenciaturaDto;
import com.beheos.universidad.entity.Alumno;
import com.beheos.universidad.entity.Licenciatura;
import com.beheos.universidad.service.AlumnoService;
import com.beheos.universidad.service.LicenciaturaService;
import com.beheos.universidad.util.Utilerias;

@Controller
public class LandingController {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LicenciaturaService licenciaturaService;
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/")
	public String mostrar(Model model){
		List<Licenciatura>licenciaturas = new ArrayList<Licenciatura>();
		licenciaturas = licenciaturaService.listaLicenciaturas();
		List<LicenciaturaDto> listaLicenciaturasDto = licenciaturas.stream().map(licenciatura -> modelMapper.map(licenciatura, LicenciaturaDto.class))
				.collect(Collectors.toList());
		model.addAttribute("licenciaturas", listaLicenciaturasDto);
		return "index";
	}
	
	@PostMapping(value = "/registrarse")
	public @ResponseBody Map<String, String>registrarse(@RequestBody AlumnoDto alumnoDto){
		Map<String, String> respuesta = new HashMap<>();
		Licenciatura licenciatura = licenciaturaService.ObtenerLicenciatura(alumnoDto.getLicenciatura().getId());
		if(licenciatura.getInscritos() == null || licenciatura.getInscritos() < licenciatura.getLimiteCupo()){
			try {
				//Dado que es un usuario no logeado por defecto se va como USER
				alumnoDto.setUsuarioIngreso("USER");
				alumnoDto.setFecha_ingreso(Utilerias.getFormatoFecha(new Date()));
				alumnoDto.setMatricula(Utilerias.getGenerarMatricula());
				alumnoDto.setEliminado(false);
				Alumno alumno = modelMapper.map(alumnoDto, Alumno.class);
				alumnoService.guardar(alumno);
				respuesta.put("mensaje", "se registro correctamente el usuario " + alumno.getNombre() + " con la matricula " + alumno.getMatricula());
			}catch (Exception e) {
				e.printStackTrace();
				respuesta.put("mensaje", "ocurrio un problema intentelo mas tarde");
			}
		}else{
			respuesta.put("mensaje", "lo sentimos el cupo para este Curso esta completo");
		}
		return respuesta;
	}
	
}
