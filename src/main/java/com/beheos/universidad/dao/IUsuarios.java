package com.beheos.universidad.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beheos.universidad.entity.Usuarios;

public interface IUsuarios extends JpaRepository<Usuarios, String> {

}
