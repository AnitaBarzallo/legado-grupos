/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoDAO implements Crud {
    @Autowired
    private GrupoRepositorio repositorio;
    
    public void agregar(Grupo grupo, Asignatura asignatura, Periodo periodo) {
        //Bidireccional: Un Grupo tiene una Asinatura
        if(asignatura!=null){
            grupo.setAsignatura(asignatura);
        }
        //Bidireccional: Un Grupo tiene un periodo
        if(periodo!=null){
            grupo.setPeriodo(periodo);
        }
        
        
        repositorio.save(grupo);
    }

    @Override
    public Object buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Object o) {
        repositorio.save((Grupo)o);
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
