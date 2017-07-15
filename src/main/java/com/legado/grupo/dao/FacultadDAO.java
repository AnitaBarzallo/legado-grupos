package com.legado.grupo.dao;

import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.FacultadRepositorio;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacultadDAO implements Crud<Facultad> {

    @Autowired
    private FacultadRepositorio repositorio;

    public void agregar(Facultad facultad) throws Exception {
        if (existe(facultad)) {
            throw new Exception("La facultad " + facultad.getNombre() + " ya existe!");
        }
        repositorio.save(facultad);
    }

    @Override
    public Facultad buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    public Facultad buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void actualizar(Facultad o) {
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
    public List<Facultad> listar() {
        return (List<Facultad>) repositorio.findAll();
    }

    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    public boolean existe(Facultad facultad) {
        if (repositorio.findByNombre(facultad.getNombre()) != null) {
            return true;
        }
        return false;
    }

}
