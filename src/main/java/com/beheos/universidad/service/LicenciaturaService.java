package com.beheos.universidad.service;

import java.util.List;

import com.beheos.universidad.entity.Alumno;
import com.beheos.universidad.entity.Licenciatura;

public interface LicenciaturaService {
	
	Licenciatura ObtenerLicenciatura(Long id);
	List<Licenciatura>listaLicenciaturas();

}
