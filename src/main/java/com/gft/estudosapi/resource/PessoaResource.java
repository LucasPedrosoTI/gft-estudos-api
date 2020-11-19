/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.estudosapi.resource;

import com.gft.estudosapi.event.RecursoCriadoEvent;
import com.gft.estudosapi.model.Pessoa;
import com.gft.estudosapi.repository.Pessoas;
import com.gft.estudosapi.service.PessoaService;
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

/**
 *
 * @author lps10
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    Pessoas pessoas;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoas.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoas.findById(id).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        }));
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoas.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PutMapping("/{id}")
    public Pessoa editar(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa) {
        return pessoaService.atualizar(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pessoas.deleteById(id);
    }
}
