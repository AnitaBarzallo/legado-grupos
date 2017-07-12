/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacultadDAO implements Crud {
    @Autowired
    private FacultadRepositorio repositorio;
    
    
    public void agregar(Facultad facultad) {
        
        repositorio.save(facultad);      
    }

    @Override
    public Object buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Object o) {
        repositorio.save((Facultad)o);
    }

    @Override
    public void eliminarPorId(int id) {
        if(existe(id)){
            repositorio.delete(id);
        }

    }

    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    @Override
    public List<Object> listar() {
        return (List)repositorio.findAll();
    }

    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }
    


}
