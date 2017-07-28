
package com.legado.grupo.dao;
//inicio librerias
import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.CarreraRepositorio;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//fin librerias
@Repository
public class CarreraDAO implements Crud<Carrera> {
    //atributos globales
    @Autowired
    private CarreraRepositorio repositorio;

    //agregar carrera
    public void agregar(Carrera carrera, Facultad facultad) throws Exception {
        if (facultad == null) {// valida facultad vacia
            throw new Exception("No se ha indicado la facultad de la carrera " + carrera.getNombre());
        }
        if (existe(carrera.getNombre())) {//valida carreras existentes
            throw new Exception("La carrera " + carrera.getNombre() + " ya existe!");
        }

        //Una carrera pertenece a una Facultad
        carrera.setFacultad(facultad);
        facultad.addCarrera(carrera);
        
        repositorio.save(carrera);
    }

    // busca carrera por id
    @Override
    public Carrera buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    //busca carrera por nombre
    public Carrera buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    //acualiza una carrera existente
    @Override
    public void actualizar(Carrera o) {
        repositorio.save(o);
    }

    //elimina carrera mediante id
    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {
            repositorio.delete(id);
        }
    }

    //elimina toda la lista de carreras
    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    //lista todas las carreras existentes
    @Override
    public List<Carrera> listar() {
        return (List<Carrera>) repositorio.findAll();
    }

    //comprueba si exite una carrera por id
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    //comprueba si exite una carrera por nombre
    public boolean existe(String nombre) {
        if (repositorio.findByNombre(nombre) != null) {
            return true;
        }
        return false;
    }
}
