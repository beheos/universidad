package com.beheos.universidad.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beheos.universidad.entity.Alumno;

//public interface IAlumnoDao extends JpaRepository<Alumno, Long> {
public interface IAlumnoDao extends PagingAndSortingRepository<Alumno, Long> {
	
	
	@Query(value = "Select * from universidad.alumno where eliminado != 1", nativeQuery = true)
	Page<Alumno> findAllAlumnos(Pageable pageable);

}
