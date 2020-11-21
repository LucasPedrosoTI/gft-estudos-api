package com.gft.estudosapi.repository;

import com.gft.estudosapi.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long> {

  public Usuario findByEmail(String email);

}
