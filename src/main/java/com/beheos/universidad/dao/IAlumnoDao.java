package com.beheos.universidad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beheos.universidad.entity.Alumno;

public interface IAlumnoDao extends JpaRepository<Alumno, Long> {
	
	
	@Query(value = "Select * from universidad.alumno where eliminado != 1", nativeQuery = true)
	List<Alumno> findAllAlumnos();

}
