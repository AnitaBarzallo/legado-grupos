package com.legado.grupo.dao;
//librerias
import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.PeriodoRepositorio;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//fin librerias
@Repository
public class PeriodoDAO implements Crud<Periodo> {
    //atributos globales
    @Autowired
    private PeriodoRepositorio repositorio;

    //metodo para agregar un periodo
    public void agregar(Periodo periodo) throws Exception {
        if (existe(periodo)) {//si el periodo existe creamos un mensaje indicando que ya existe
            throw new Exception("El periodo " + periodo.getFechaInicio().toGMTString() + " ya existe!");
        }
        repositorio.save(periodo);
    }

    //busca un periodo por id
    @Override
    public Periodo buscarPorID(int id) {
        return repositorio.findOne(id);
    }

    //actualiza un periodo, lo modifica
    @Override
    public void actualizar(Periodo o) {
        repositorio.save(o);
    }

    //elimina un periodo mediante id
    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {//si el periodo existe lo elimina
            repositorio.delete(id);
        }
    }

     //elimina todos los periodos existentes
    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    //retornamos una lista con los periodos existentes
    @Override
    public List<Periodo> listar() {
        return (List<Periodo>) repositorio.findAll();
    }

    //comprobamos si existe un periodo por el id
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);//retorna verdadero si existe 
    }

    //comprobamos si existe un periodo por fecha de inicio
    private boolean existe(Periodo periodo) {
        for(Periodo p : listar()){
            if(p.getFechaInicio().equals(periodo.getFechaInicio())){
                return true;//si lo encuentra retorna verdadero
            }
        }
        return false;//si no lo encuentra retorna falso
    }

}
