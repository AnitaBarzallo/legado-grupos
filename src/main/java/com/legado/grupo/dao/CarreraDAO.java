
package com.legado.grupo.dao;

import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.CarreraRepositorio;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarreraDAO implements Crud<Carrera> {

    @Autowired
    private CarreraRepositorio repositorio;

    public void agregar(Carrera carrera, Facultad facultad) throws Exception {
        if (facultad == null) {
            throw new Exception("No se ha indicado la facultad de la carrera " + carrera.getNombre());
        }
        if (existe(carrera.getNombre())) {
            throw new Exception("La carrera " + carrera.getNombre() + " ya existe!");
        }

        //Una carrera pertenece a una Facultad
        carrera.setFacultad(facultad);
        facultad.addCarrera(carrera);
        
        repositorio.save(carrera);
    }

    @Override
    public Carrera buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    public Carrera buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void actualizar(Carrera o) {
        repositorio.save(o);
    }

    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {
            repositorio.delete(id);
        }
    }

    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    @Override
    public List<Carrera> listar() {
        return (List<Carrera>) repositorio.findAll();
    }

    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    public boolean existe(String nombre) {
        if (repositorio.findByNombre(nombre) != null) {
            return true;
        }
        return false;
    }
}
