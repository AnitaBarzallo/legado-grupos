/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodoDAO implements Crud {
    @Autowired
    private PeriodoRepositorio repositorio;
    
    public void agregar(Periodo periodo) {
        //bidireccionalidad Carrera-Asignatura: Una Asignatura pertenece a una Carrera
        for(Grupo grupo:periodo.getGrupos()){
            grupo.setPeriodo(periodo);
            actualizar(grupo);
        }
        repositorio.save(periodo);      
    }

    @Override
    public Object buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Object o) {
        repositorio.save((Periodo)o);
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
