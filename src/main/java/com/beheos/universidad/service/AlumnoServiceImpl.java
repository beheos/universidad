package com.beheos.universidad.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beheos.universidad.dao.IAlumnoDao;
import com.beheos.universidad.dao.ILicenciatura;
import com.beheos.universidad.entity.Alumno;
import com.beheos.universidad.entity.Licenciatura;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	IAlumnoDao iAlumnoDao;
	@Autowired
	ILicenciatura ILicenciatura;
	@Autowired
	LicenciaturaService licenciaturaService;
	
	@Transactional
	@Override
	public Alumno guardar(Alumno alumno) {
		if(alumno.getId() == null){
			alumno = iAlumnoDao.save(alumno);
			Licenciatura licenciatura = licenciaturaService.ObtenerLicenciatura(alumno.getLicenciatura().getId());
			licenciatura.setInscritos((licenciatura.getInscritos() == null ? 0 : licenciatura.getInscritos()) + 1);
			ILicenciatura.save(licenciatura);
		}else{
			alumno = iAlumnoDao.save(alumno);
		}
		
		return alumno;
	}

	@Override
	public Alumno findAlumno(Long idAlumno) {
		return iAlumnoDao.findById(idAlumno).orElse(null);
	}

	@Override
	public Page<Alumno> findAllAlumnos(Pageable pageable) {
		return iAlumnoDao.findAllAlumnos(pageable);
	}

	

}
