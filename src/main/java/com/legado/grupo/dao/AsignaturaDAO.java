
package com.legado.grupo.dao;


import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.AsignaturaRepositorio;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AsignaturaDAO implements Crud<Asignatura>{
    @Autowired
    private AsignaturaRepositorio repositorio;
    
    public void agregar(Asignatura asignatura, Carrera carrera) {
        //Una asignatura pertenece a una carrera
        if(asignatura!=null){
            asignatura.setCarrera(carrera);
        }
        repositorio.save(asignatura);
    }

    @Override
    public Asignatura buscarPorID(int id) {
        return repositorio.findOne(id);
    }
    
    public Asignatura buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void actualizar(Asignatura o) {
        repositorio.save((Asignatura)o);
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
    public List<Asignatura> listar() {
        return (List<Asignatura>) repositorio.findAll();
    }
    
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

   
}
