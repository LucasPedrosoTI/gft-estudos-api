package com.gft.estudosapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gft.estudosapi.model.Lancamento;
import com.gft.estudosapi.model.Pessoa;
import com.gft.estudosapi.repository.Lancamentos;
import com.gft.estudosapi.repository.Pessoas;
import com.gft.estudosapi.repository.filter.LancamentoFilter;
import com.gft.estudosapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

  @Autowired
  Lancamentos lancamentos;

  @Autowired
  Pessoas pessoas;

  public List<Lancamento> pesquisarLancamentos(LancamentoFilter lancamentoFilter) {
    String descricao = Optional.ofNullable(lancamentoFilter.getDescricao()).orElse("");

    LocalDate dataVencimentoDe = Optional.ofNullable(lancamentoFilter.getDataVencimentoDe())
        .orElse(LocalDate.of(1970, 1, 1));

    LocalDate dataVencimentoAte = Optional.ofNullable(lancamentoFilter.getDataVencimentoAte())
        .orElse(LocalDate.of(3000, 12, 31));

    return lancamentos.pesquisarLancamentos(descricao, dataVencimentoDe, dataVencimentoAte);
  }

  public Lancamento salvar(@Valid Lancamento lancamento) {

    Optional<Pessoa> pessoa = pessoas.findById(lancamento.getPessoa().getId());

    if (pessoa.isEmpty() || pessoa.get().isInativo()) {
      throw new PessoaInexistenteOuInativaException();
    }

    return lancamentos.save(lancamento);
  }

}
