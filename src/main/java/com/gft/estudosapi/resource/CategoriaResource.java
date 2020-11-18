package com.gft.estudosapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.gft.estudosapi.model.Categoria;
import com.gft.estudosapi.repository.Categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

  @Autowired
  private Categorias categorias;

  @GetMapping
  public List<Categoria> listar() {
    return categorias.findAll();
  }

  @PostMapping
  public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
    Categoria categoriaSalva = categorias.save(categoria);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoriaSalva.getId())
        .toUri();

    response.setHeader("Location", uri.toASCIIString());

    return ResponseEntity.created(uri).body(categoriaSalva);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
    Categoria categoria = categorias.findById(id).orElse(null);

    return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
  }

}
