/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.MiembroRepositorio;
import com.legado.grupo.dom.Miembro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MiembroDAO implements Crud<Miembro> {
    @Autowired
    private MiembroRepositorio repositorio;
    
    public void agregar(Miembro miembro) {
        repositorio.save(miembro);      
    }

    @Override
    public Miembro buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Miembro o) {
        repositorio.save(o);
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
    public List<Miembro> listar() {
        return (List<Miembro>) repositorio.findAll();
    }

    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }
    


}
