/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;
//librerias
import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.PeriodoDAO;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//fin librerias
/**
 *
 * @author EdwinCabrera
 */
@Service
public class PeriodoService {
    //atributos globales
    @Autowired
    private PeriodoDAO periodoDAO;
    
    @Autowired GrupoDAO grupoDAO;
    
    //busca un periodo por su id
    public Periodo buscarPorID(int idPeriodo){
        return periodoDAO.buscarPorID(idPeriodo);
    }
    
    //metodo para agregar un periodo con su periodo mediante sus ids
    public void agregarGrupo(int idPeriodo,int idGrupo){
        Grupo grupo=grupoDAO.buscarPorID(idGrupo);
        Periodo periodo=buscarPorID(idPeriodo);
        periodo.addGrupo(grupo);
        periodoDAO.actualizar(periodo);
    }
    
    //metodo para agregar un periodo con su grupo mediante id y nombre 
    public void agregarGrupo(int idPeriodo,String nombreGrupo){
        Grupo grupo=grupoDAO.buscarPorNombre(nombreGrupo);
        Periodo periodo=buscarPorID(idPeriodo);
        periodo.addGrupo(grupo);
        periodoDAO.actualizar(periodo);
    }
    
    //devuelve una lista de los periodos existentes 
    public List<Periodo> listarPeriodos(){
        return periodoDAO.listar();
    }    
    
    //lista los grupos con su respectivo periodo
    public List<Grupo> listarGruposPeriodo(int idPeriodo){
        return periodoDAO.buscarPorID(idPeriodo).getGrupos();
    }
}
