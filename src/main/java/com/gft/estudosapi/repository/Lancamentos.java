package com.gft.estudosapi.repository;

import com.gft.estudosapi.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Lancamentos extends JpaRepository<Lancamento, Long> {

}