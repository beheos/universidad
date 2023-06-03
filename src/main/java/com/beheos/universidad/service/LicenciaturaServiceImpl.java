package com.beheos.universidad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beheos.universidad.dao.ILicenciatura;
import com.beheos.universidad.entity.Licenciatura;

@Service
public class LicenciaturaServiceImpl implements LicenciaturaService{

	@Autowired
	ILicenciatura iLicenciatura;
	
	@Override
	public Licenciatura ObtenerLicenciatura(Long id) {
		return iLicenciatura.findById(id).orElse(null);
	}

	@Override
	public List<Licenciatura> listaLicenciaturas() {
		return iLicenciatura.findAll();
	}

}
