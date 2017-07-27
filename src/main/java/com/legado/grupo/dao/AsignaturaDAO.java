
package com.legado.grupo.dao;
//librerias
import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.AsignaturaRepositorio;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//fin librerias
@Repository
public class AsignaturaDAO implements Crud<Asignatura> {

    // atributos globales
    @Autowired
    private AsignaturaRepositorio repositorio;

    //se agrega una asignatura
    public void agregar(Asignatura asignatura, Carrera carrera) throws Exception {
        if (carrera == null) {//valida carrera vacia
            throw new Exception("No se ha indicado la carrera de la asignatura " + asignatura.getNombre());
        }
        if (existe(asignatura, carrera)) {//valida si existe
            throw new Exception("Ya existe la asignatura " + asignatura.getNombre() + " para la carrera " + carrera.getNombre());
        }
        //Una asignatura pertenece a una carrera
        asignatura.setCarrera(carrera);
        carrera.addAsignatura(asignatura);
        repositorio.save(asignatura);
    }

    //busca asignatura por id
    @Override
    public Asignatura buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    //busca asignatura por nombre
    public Asignatura buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    //acualiza una asignatura
    @Override
    public void actualizar(Asignatura o) {
        repositorio.save((Asignatura) o);
    }

    //elimina una asignatura mediante id
    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {
            repositorio.delete(id);
        }
    }

    //elimina todas las asignaturas existentes
    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    //devuelve una lisra de asignaturas
    @Override
    public List<Asignatura> listar() {
        return (List<Asignatura>) repositorio.findAll();
    }

    //metodo para comprobar si existe una asignatura mediante id
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    //metodo para comprobar si existe una asignatura dentro de una carrera
    private boolean existe(Asignatura asignatura, Carrera carrera) {
        for(Asignatura a : carrera.getAsignaturas()){
            if(a.getNombre().equals(asignatura.getNombre())){
                return true;
            }
        }
        return false;
    }

}
