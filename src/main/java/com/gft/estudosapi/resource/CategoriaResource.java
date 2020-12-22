package com.gft.estudosapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.estudosapi.event.RecursoCriadoEvent;
import com.gft.estudosapi.model.Categoria;
import com.gft.estudosapi.repository.Categorias;
import com.gft.estudosapi.service.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Categorias")
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private Categorias categorias;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    CategoriaService categoriaService;

    @ApiOperation("Lista todas as categorias")
    @GetMapping
    public List<Categoria> listar() {
        return categorias.findAll();
    }

    @ApiOperation("Cria uma categoria")
    @PostMapping
    public ResponseEntity<Categoria> criar(@ApiParam("Representação de uma categoria") @RequestBody @Valid Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categorias.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @ApiOperation("Busca uma categoria por ID")
    @GetMapping("/{id}")
    public Categoria buscarPorId(@ApiParam(value = "ID de uma categoria", example = "1") @PathVariable Long id) {
        return categorias.findById(id).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }

    @ApiOperation("Edita uma categoria por ID")
    @PutMapping("/{id}")
    public Categoria editar(@PathVariable Long id, @RequestBody @Valid Categoria categoria) {
        return categoriaService.atualizar(id, categoria);
    }

    @ApiOperation("Exclui uma categoria por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        categorias.deleteById(id);
    }

}
