/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.estudosapi.service;

import com.gft.estudosapi.model.Categoria;
import com.gft.estudosapi.repository.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lps10
 */
@Service
public class CategoriaService {

    @Autowired
    Categorias categorias;

    public Categoria atualizar(Long id, Categoria categoria) {
        categorias.findById(id).orElseThrow(() -> {
            throw new EmptyResultDataAccessException(1);
        });

        categoria.setId(id);

        return categorias.save(categoria);
    }
}
