/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao.I;
import com.legado.grupo.dom.Carrera;
import org.springframework.data.repository.CrudRepository;

public interface CarreraRepositorio extends CrudRepository<Carrera, Integer>{
    Carrera findByNombre(String nombre);
}
