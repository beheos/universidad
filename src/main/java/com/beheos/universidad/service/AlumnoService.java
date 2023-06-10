package com.beheos.universidad.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beheos.universidad.entity.Alumno;

public interface AlumnoService {
	
	Alumno guardar(Alumno alumno);
	Page<Alumno> findAllAlumnos(Pageable pageable);
	Alumno findAlumno(Long idAlumno);

}
