package com.gft.estudosapi.repository;

import java.time.LocalDate;
import java.util.List;

import com.gft.estudosapi.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Lancamentos extends JpaRepository<Lancamento, Long> {

  @Query("select l from Lancamento l where l.descricao like %?1% and l.dataVencimento >= ?2 and l.dataVencimento <= ?3")
  public List<Lancamento> pesquisarLancamentos(String descricao, LocalDate dataVencimentoDe,
      LocalDate dataVencimentoAte);

}