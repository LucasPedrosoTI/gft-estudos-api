package com.gft.estudosapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lancamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;

  @Column(name = "data_vencimento")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataVencimento;

  @Column(name = "data_pagamento")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataPagamento;

  private BigDecimal valor;

  private String observacao;

  @Enumerated(EnumType.STRING)
  private TipoLancamento tipo;

  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa;
}
