/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;

import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author EdwinCabrera
 */
public class CarreraService {
    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private FacultadDAO facultadDAO;

    public void agregarCarrera (String nombreCarrera,int idFacultad) throws Exception{
        Carrera carrera=new Carrera(nombreCarrera);
        Facultad facultad=facultadDAO.buscarPorID(idFacultad);
        carreraDAO.agregar(carrera, facultad);
    }
    
    public void agregarCarrera (String nombreCarrera,String nombreFacultad) throws Exception{
        Carrera carrera=new Carrera(nombreCarrera);
        Facultad facultad=facultadDAO.buscarPorNombre(nombreFacultad);
        carreraDAO.agregar(carrera, facultad);
    }
    
    public List<Carrera> listarCarrera(){
        return carreraDAO.listar();
    }
    
    public Carrera buscarPorID(int idCarrera){
        return carreraDAO.buscarPorID(idCarrera);
    }
    
    public Carrera buscarPorNombre(String nombreCarrera){
        return carreraDAO.buscarPorNombre(nombreCarrera);
    }
    
    /** 
     *Recupera una lista de asignaturas que posee una Carrera 
     */
    public List<Asignatura> asignaturasCarrera(int idCarrera){
        return buscarPorID(idCarrera).getAsignaturas();
    }
    
    public List<Asignatura> asignaturasCarrera(String nombreCarrera){
        return buscarPorNombre(nombreCarrera).getAsignaturas();
    }
}
