package com.gft.estudosapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.gft.estudosapi.event.RecursoCriadoEvent;
import com.gft.estudosapi.exceptionHandler.CustomExceptionHandler.Erro;
import com.gft.estudosapi.model.Lancamento;
import com.gft.estudosapi.repository.Lancamentos;
import com.gft.estudosapi.repository.filter.LancamentoFilter;
import com.gft.estudosapi.service.LancamentoService;
import com.gft.estudosapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
  LancamentoService lancamentoService;

  @Autowired
  ApplicationEventPublisher publisher;

  @Autowired
  MessageSource messageSource;

  @GetMapping
  public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
    return lancamentoService.pesquisarLancamentos(lancamentoFilter);
  }

  @GetMapping("/{id}")
  public Lancamento buscarPorId(@PathVariable Long id) {
    return lancamentos.findById(id).orElseThrow(() -> {
      throw new EmptyResultDataAccessException(1);
    });
  }

  @PostMapping
  public ResponseEntity<Lancamento> criar(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
    Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

    publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

    return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
  }

  @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
  public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
    String mensagemUsuario = this.messageSource.getMessage("pessoa.inexistente-ou-inativa", null,
        LocaleContextHolder.getLocale());
    String mensagemDev = ex.toString();

    List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));

    return ResponseEntity.badRequest().body(erros);
  }

}
