package com.gft.estudosapi.resource;

import java.util.List;

import com.gft.estudosapi.model.Lancamento;
import com.gft.estudosapi.repository.Lancamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

  @Autowired
  Lancamentos lancamentos;

  @GetMapping
  public List<Lancamento> listarLancamentos() {
    return lancamentos.findAll();
  }

  @GetMapping("/{id}")
  public Lancamento buscarPorId(@PathVariable Long id) {
    return lancamentos.findById(id).orElseThrow(() -> {
      throw new EmptyResultDataAccessException(1);
    });
  }
}
