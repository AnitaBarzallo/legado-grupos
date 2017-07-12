/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;
import com.legado.grupo.dom.Miembro;
import org.springframework.data.repository.CrudRepository;

public interface MiembroRepositorio extends CrudRepository<Miembro, Integer>{
    
}
