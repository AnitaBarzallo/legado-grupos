/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.GrupoRepositorio;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoDAO implements Crud<Grupo> {
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
    public Grupo buscarPorID(int id) {
        return repositorio.findOne(id);
    }
    
    public Grupo buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void actualizar(Grupo o) {
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
    public List<Grupo> listar() {
        return (List<Grupo>) repositorio.findAll();
    }

    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }
    


}
