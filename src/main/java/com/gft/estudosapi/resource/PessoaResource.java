package com.gft.estudosapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.gft.estudosapi.model.Lancamento;
import com.gft.estudosapi.model.Pessoa;
import com.gft.estudosapi.repository.Lancamentos;
import com.gft.estudosapi.repository.Pessoas;
import com.gft.estudosapi.service.PessoaService;

import io.swagger.annotations.Api;

/**
 *
 * @author lps10
 */
@Api(tags = "Pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    Pessoas pessoas;

    @Autowired
    Lancamentos lancamentos;

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

    @GetMapping("/{id}/lancamentos")
    public Page<Lancamento> buscarLancamentos(@PathVariable Long id, Pageable pageable) {
        return lancamentos.findByPessoaId(id, pageable);
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

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAtivo(@PathVariable Long id, @RequestBody boolean ativo) {
        pessoaService.atualizarAtivo(id, ativo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pessoas.deleteById(id);
    }
}
