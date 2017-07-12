/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarreraDAO implements Crud {
    @Autowired
    private CarreraRepositorio repositorio;
    
    public void agregar(Carrera carrera, Facultad facultad) {
        //Una carrera pertenece a una Facultad
        if(facultad!=null){
            carrera.setFacultad(facultad);
        }
        //bidireccionalidad Carrera-Asignatura: Una Asignatura pertenece a una Carrera
        for(Asignatura asignatura:carrera.getAsignaturas()){
            asignatura.setCarrera(carrera);
            actualizar(asignatura);
        }
        repositorio.save(carrera);     
    }

    @Override
    public Object buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Object o) {
        repositorio.save((Carrera)o);
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
