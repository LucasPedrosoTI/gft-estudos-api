package com.gft.estudosapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.gft.estudosapi.event.RecursoCriadoEvent;
import com.gft.estudosapi.model.Lancamento;
import com.gft.estudosapi.repository.Lancamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

  @Autowired
  Lancamentos lancamentos;

  @Autowired
  private ApplicationEventPublisher publisher;

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

  @PostMapping
  public ResponseEntity<Lancamento> criar(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
    Lancamento lancamentoSalvo = lancamentos.save(lancamento);

    publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

    return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
  }
}
