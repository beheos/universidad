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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beheos.universidad.dto.AlumnoDto;
import com.beheos.universidad.dto.LicenciaturaDto;
import com.beheos.universidad.entity.Alumno;
import com.beheos.universidad.entity.Licenciatura;
import com.beheos.universidad.service.AlumnoService;
import com.beheos.universidad.service.LicenciaturaService;
import com.beheos.universidad.util.Utilerias;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/alumnos")
public class AlumnosController {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	LicenciaturaService licenciaturaService;
	
	@GetMapping("/mostrar")
	public String mostrar(Model model){
		List<Licenciatura>licenciaturas = new ArrayList<Licenciatura>();
		licenciaturas = licenciaturaService.listaLicenciaturas();
		List<LicenciaturaDto> listaLicenciaturasDto = licenciaturas.stream().map(licenciatura -> modelMapper.map(licenciatura, LicenciaturaDto.class))
				.collect(Collectors.toList());
		model.addAttribute("alumnos", alumnoService.findAllAlumnos());
		model.addAttribute("licenciaturas", listaLicenciaturasDto);
		return "alumno/mostrar";
	}
	
	@GetMapping("/editar/{id}")
	public @ResponseBody String editar(@PathVariable("id") Long id, Model model){
		Alumno alumno = alumnoService.findAlumno(id);
		AlumnoDto alumnoDto =  modelMapper.map(alumno, AlumnoDto.class);
		Gson gson = new Gson();
		return gson.toJson(alumnoDto);
	}
	
	@PostMapping(value = "/")
	public @ResponseBody Map<String, String> guardar(@RequestBody AlumnoDto alumnoDto){
		Map<String, String> resp = new HashMap<>();
		try{
			alumnoDto.setFecha_modificacion(Utilerias.getFormatoFecha(new Date()));
			//ACA VA EL USUARIO DE SPRING
			alumnoDto.setUsuarioModifico("ADMIN");
			Alumno alumno = modelMapper.map(alumnoDto, Alumno.class);
			alumnoService.guardar(alumno);
			resp.put("mensaje", "Se modifico el suaurio correctamente");
		}catch (Exception e) {
			resp.put("mensaje", "Ocurrio un problema al modificarel alumno");
			e.printStackTrace();
		}
		return resp;
	}
	
	@GetMapping("/eliminar/{id}")
	public @ResponseBody Map<String, String> eliminar(@PathVariable("id")Long id){
		Map<String, String> resp = new HashMap<>();
		Alumno alumno = alumnoService.findAlumno(id);
		try {
			alumno.setFecha_modificacion(Utilerias.getFormatoFecha(new Date()));
			//ACA VA EL USUARIO DE SPRING
			alumno.setUsuarioModifico("ADMIN");
			alumno.setEliminado(true);
			alumnoService.guardar(alumno);
			resp.put("mensaje", "Se elimino el usuario correctamente");
		} catch (Exception e) {
			resp.put("mensaje", "Ocurrio un problema al eliminar alumno");
		}
		return resp;
	}
}
