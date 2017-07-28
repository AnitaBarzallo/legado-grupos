package com.legado.grupo.dao;
//librerias
import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.FacultadRepositorio;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// fin librerias
@Repository
public class FacultadDAO implements Crud<Facultad> {
    // atributos globales
    @Autowired
    private FacultadRepositorio repositorio;

    //agrega una facultad
    public void agregar(Facultad facultad) throws Exception {
        if (existe(facultad)) {//valida facultades existentes
            throw new Exception("La facultad " + facultad.getNombre() + " ya existe!");
        }
        repositorio.save(facultad);
    }

    // busca facultad por id
    @Override
    public Facultad buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    //busca facultad por nombre
    public Facultad buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    //actualiza una facultad
    @Override
    public void actualizar(Facultad o) {
        repositorio.save(o);
    }

    //elimina facultad mediante id
    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {
            repositorio.delete(id);
        }
    }

    //elimina todas las facultades
    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    //obtenemos una lista con todas las facultades
    @Override
    public List<Facultad> listar() {
        return (List<Facultad>) repositorio.findAll();
    }

    //verifica si existe una facultad por id
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    //verifica si existe una facultad mediante nombre
    public boolean existe(Facultad facultad) {
        if (repositorio.findByNombre(facultad.getNombre()) != null) {
            return true;
        }
        return false;
    }

}
