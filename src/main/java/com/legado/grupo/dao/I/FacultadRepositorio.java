/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao.I;
//librerias
import com.legado.grupo.dom.Facultad;
import org.springframework.data.repository.CrudRepository;
//fin librerias
//creamos los metodos para buscar por nombre hereda de CrudRepository
public interface FacultadRepositorio extends CrudRepository<Facultad, Integer>{
    Facultad findByNombre(String nombre);
}
