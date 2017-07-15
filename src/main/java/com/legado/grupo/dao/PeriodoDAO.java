package com.legado.grupo.dao;

import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.PeriodoRepositorio;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodoDAO implements Crud<Periodo> {

    @Autowired
    private PeriodoRepositorio repositorio;

    public void agregar(Periodo periodo) throws Exception {
        if (existe(periodo)) {
            throw new Exception("El periodo " + periodo.getFechaInicio().toGMTString() + " ya existe!");
        }
        repositorio.save(periodo);
    }

    @Override
    public Periodo buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    @Override
    public void actualizar(Periodo o) {
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
    public List<Periodo> listar() {
        return (List<Periodo>) repositorio.findAll();
    }

    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    private boolean existe(Periodo periodo) {
        for(Periodo p : listar()){
            if(p.getFechaInicio().equals(periodo.getFechaInicio())){
                return true;
            }
        }
        return false;
    }

}
