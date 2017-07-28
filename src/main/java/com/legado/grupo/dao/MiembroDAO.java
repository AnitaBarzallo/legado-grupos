/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;
//librerias
import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.MiembroRepositorio;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//fin librerias
@Repository
public class MiembroDAO implements Crud<Miembro> {
    //atributos globales
    @Autowired
    private MiembroRepositorio repositorio;

    //metodo agregar a un miembro 
    public void agregar(Miembro miembro, Grupo grupo) throws Exception {
        if (grupo == null) {
            throw new Exception("No se ha indicado el grupo del miembro con id " + miembro.getId_usuario());
        }
        //Bidireccional: Un Miembro esta en un Grupo
        grupo.addMiembro(miembro);//relacion entre grupo y miembro
        miembro.setGrupo(grupo);
        
        repositorio.save(miembro);//guardamos al nuevo integrante
    }

    //busca un miembro mediante id
    @Override
    public Miembro buscarPorID(int id) {
        return repositorio.findOne(id);//retorna el miembro con el id correspondiente
    }

    //actualizacion del integrante
    @Override
    public void actualizar(Miembro o) {
        repositorio.save(o);
    }

    //elimina a un integrante mediante su id
    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {
            repositorio.delete(id);
        }
    }

    //elimina la lista de integrantes o miembros
    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    //retorna una lista con todos los integrantes
    @Override
    public List<Miembro> listar() {
        return (List<Miembro>) repositorio.findAll();
    }

    //metodo que verifica si existe un integrante
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

}
