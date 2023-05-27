package com.beheos.universidad.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beheos.universidad.entity.UserRoles;

public interface IUsuariosRoles extends JpaRepository<UserRoles, String> {

}
