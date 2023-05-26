package com.beheos.universidad.service;

import java.util.List;

import com.beheos.universidad.entity.Alumno;

public interface AlumnoService {
	
	Alumno guardar(Alumno alumno);
	List<Alumno>findAllAlumnos();
	Alumno findAlumno(Long idAlumno);

}
