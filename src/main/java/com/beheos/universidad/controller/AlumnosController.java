package com.beheos.universidad.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beheos.universidad.dao.IAlumnoDao;
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
	@Autowired
	IAlumnoDao iAlumnoDao;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/")
    public String getProductos(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5); // 10 productos por página
        Page<Alumno> productoPage = alumnoService.findAllAlumnos(pageable);
        model.addAttribute("page", productoPage);
        model.addAttribute("licenciaturas", licenciaturaService.listaLicenciaturas());
        return "alumno/mostrar2";
    }
	
	@GetMapping("/editar/{id}")
	public @ResponseBody String editar(@PathVariable("id") Long id, Model model){
		Alumno alumno = alumnoService.findAlumno(id);
		AlumnoDto alumnoDto =  modelMapper.map(alumno, AlumnoDto.class);
		Gson gson = new Gson();
		return gson.toJson(alumnoDto);
	}
	
	@PostMapping(value = "/modificar")
	public String guardar(AlumnoDto alumnoDto){
		try{
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			alumnoDto.setFecha_modificacion(Utilerias.getFormatoFecha(new Date()));
			alumnoDto.setUsuarioModifico(username);
			alumnoDto.setEliminado(false);
			Alumno alumno = modelMapper.map(alumnoDto, Alumno.class);
			alumnoService.guardar(alumno);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/alumnos/";
	}
	
	@GetMapping("/eliminar/{id}")
	public @ResponseBody Map<String, String> eliminar(@PathVariable("id")Long id){
		Map<String, String> resp = new HashMap<>();
		Alumno alumno = alumnoService.findAlumno(id);
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			alumno.setFecha_modificacion(Utilerias.getFormatoFecha(new Date()));
			alumno.setUsuarioModifico(username);
			alumno.setEliminado(true);
			alumnoService.guardar(alumno);
			resp.put("mensaje", "Se elimino el usuario correctamente");
		} catch (Exception e) {
			resp.put("mensaje", "Ocurrio un problema al eliminar alumno");
		}
		return resp;
	}
}
