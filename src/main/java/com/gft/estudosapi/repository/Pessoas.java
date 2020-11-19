/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.estudosapi.repository;

import com.gft.estudosapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lps10
 */
public interface Pessoas extends JpaRepository<Pessoa, Long> {
    
}
