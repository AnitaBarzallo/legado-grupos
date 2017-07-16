/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;

import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.PeriodoDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author EdwinCabrera
 */

public class GrupoService {
    @Autowired
    private PeriodoDAO periodoDAO;
    @Autowired
    private AsignaturaDAO asignaturaDAO;
    @Autowired
    private GrupoDAO grupoDAO;
    
    
    public void agregarGrupo(String nombreGrupo,int idPeriodo, int idAsignatura) throws Exception{
        Periodo periodo=periodoDAO.buscarPorID(idPeriodo);
        Asignatura asignatura=asignaturaDAO.buscarPorID(idAsignatura);
        Grupo grupo =new Grupo(nombreGrupo);
        grupo.setPeriodo(periodo);
        grupo.setAsignatura(asignatura);
        grupoDAO.agregar(grupo, asignatura, periodo);
    }
    
    public Grupo buscarPorID(int id){
        return grupoDAO.buscarPorID(id);
    }
    
    public void actualizar(int idGrupo,String nombreGrupo,int idPeriodo){
        Grupo grupo= grupoDAO.buscarPorID(idGrupo);
        Periodo periodo=periodoDAO.buscarPorID(idPeriodo);
        grupo.setNombre(nombreGrupo);
        grupo.setPeriodo(periodo);
    }
    
    public void actualizar(int idGrupo,String nombreGrupo,int idPeriodo,int idAsignatura ){
        Grupo grupo= grupoDAO.buscarPorID(idGrupo);
        Periodo periodo=periodoDAO.buscarPorID(idPeriodo);
        Asignatura asignatura=asignaturaDAO.buscarPorID(idAsignatura);
        grupo.setNombre(nombreGrupo);
        grupo.setPeriodo(periodo);
        grupo.setAsignatura(asignatura);
    }
    
    public void actualizar(int idGrupo,String nombreGrupo,int idPeriodo,String nombreAsignatura ){
        Grupo grupo= grupoDAO.buscarPorID(idGrupo);
        Periodo periodo=periodoDAO.buscarPorID(idPeriodo);
        Asignatura asignatura=asignaturaDAO.buscarPorNombre(nombreAsignatura);
        grupo.setNombre(nombreGrupo);
        grupo.setPeriodo(periodo);
        grupo.setAsignatura(asignatura);
    }
    
    public void eliminarPorID(int id){
        grupoDAO.eliminarPorId(id);
    }
    
    public List<Grupo> listarGrupos(){
        return grupoDAO.listar();
    }
    
}
