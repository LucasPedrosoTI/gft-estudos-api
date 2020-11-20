package com.gft.estudosapi.service;

import java.util.Optional;

import javax.validation.Valid;

import com.gft.estudosapi.model.Lancamento;
import com.gft.estudosapi.model.Pessoa;
import com.gft.estudosapi.repository.Lancamentos;
import com.gft.estudosapi.repository.Pessoas;
import com.gft.estudosapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

  @Autowired
  Lancamentos lancamentos;

  @Autowired
  Pessoas pessoas;

  public Lancamento salvar(@Valid Lancamento lancamento) {

    Optional<Pessoa> pessoa = pessoas.findById(lancamento.getPessoa().getId());

    if (pessoa.isEmpty() || pessoa.get().isInativo()) {
      throw new PessoaInexistenteOuInativaException();
    }

    // if(){
    // throw new PessoaInexistenteOuInativaException();
    // }

    return lancamentos.save(lancamento);
  }

}
