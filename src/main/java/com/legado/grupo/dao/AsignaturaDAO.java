
package com.legado.grupo.dao;


import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Grupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AsignaturaDAO implements Crud{
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
    public Object buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Object o) {
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
    public List<Object> listar() {
        return (List)repositorio.findAll();
    }
    
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

   
}
