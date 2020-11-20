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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

  @NotNull
  @Size(min = 3, max = 50)
  private String descricao;

  @NotNull
  @Column(name = "data_vencimento")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataVencimento;

  @Column(name = "data_pagamento")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataPagamento;

  @NotNull
  private BigDecimal valor;

  @Size(min = 3, max = 100)
  private String observacao;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TipoLancamento tipo;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa;
}
