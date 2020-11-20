/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.estudosapi.service;

import com.gft.estudosapi.model.Pessoa;
import com.gft.estudosapi.repository.Pessoas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lps10
 */
@Service
public class PessoaService {

    public void atualizarAtivo(Long id, boolean ativo) {

        Pessoa pessoaSalva = buscarPessoaPeloId(id);
        pessoaSalva.setAtivo(ativo);
        pessoas.save(pessoaSalva);

    }

    @Autowired
    Pessoas pessoas;

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPeloId(id);

        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

        return pessoas.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloId(Long id) throws EmptyResultDataAccessException {
        return pessoas.findById(id).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }
}
