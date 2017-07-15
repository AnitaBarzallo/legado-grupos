
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

    public void agregar(Grupo grupo, Asignatura asignatura, Periodo periodo) throws Exception {
        if (asignatura == null) {
            throw new Exception("No se ha indicado la asignatura del grupo " + grupo.getNombre());
        }
        if (periodo == null) {
            throw new Exception("No se ha indicado el periodo del grupo " + grupo.getNombre());
        }
        if (existe(grupo, asignatura, periodo)) {
            throw new Exception("Ya existe un grupo " + grupo.getNombre() + " para la asignatura " + asignatura.getNombre() + " en el período " + periodo.getFechaInicio() + "!.");
        }

        //Bidireccional: Un Grupo tiene una Asinatura
        grupo.setAsignatura(asignatura);
        asignatura.addGrupo(grupo);

        //Bidireccional: Un Grupo tiene un periodo
        grupo.setPeriodo(periodo);
        periodo.addGrupo(grupo);

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
        if (existe(id)) {
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

    private boolean existe(Grupo grupo, Asignatura asignatura, Periodo periodo) {
        for (Grupo g : asignatura.getGrupos()) {
            if (g.getNombre().equals(grupo.getNombre()) && g.getPeriodo().getFechaInicio().equals(periodo.getFechaInicio())) {
                return true;
            }
        }
        return false;
    }

}
