package com.gft.estudosapi.resource;

import java.util.List;

import com.gft.estudosapi.model.Categoria;
import com.gft.estudosapi.repository.Categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

  @Autowired
  private Categorias categorias;

  @GetMapping
  public List<Categoria> listar() {
    return categorias.findAll();
  }
}
