/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;

import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.PeriodoDAO;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwinCabrera
 */
@Service
public class PeriodoService {
    @Autowired
    private PeriodoDAO periodoDAO;
    
    @Autowired GrupoDAO grupoDAO;
    
    public Periodo buscarPorID(int idPeriodo){
        return periodoDAO.buscarPorID(idPeriodo);
    }
    
    public void agregarGrupo(int idPeriodo,int idGrupo){
        Grupo grupo=grupoDAO.buscarPorID(idGrupo);
        Periodo periodo=buscarPorID(idPeriodo);
        periodo.addGrupo(grupo);
        periodoDAO.actualizar(periodo);
    }
    
    public void agregarGrupo(int idPeriodo,String nombreGrupo){
        Grupo grupo=grupoDAO.buscarPorNombre(nombreGrupo);
        Periodo periodo=buscarPorID(idPeriodo);
        periodo.addGrupo(grupo);
        periodoDAO.actualizar(periodo);
    }
    
       
    public List<Periodo> listarPeriodos(){
        return periodoDAO.listar();
    }    
    
    public List<Grupo> listarGruposPeriodo(int idPeriodo){
        return periodoDAO.buscarPorID(idPeriodo).getGrupos();
    }
}
