/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;
//librerias
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//fin librerias
/**
 *
 * @author EdwinCabrera
 */
@Service
public class CarreraService {
    //atributos globales
    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private FacultadDAO facultadDAO;

    //agregamos una carrera pero primero obtenemos la carrera y facultad mediante id
    public void agregar(String nombreCarrera, int idFacultad) throws Exception {
        Carrera carrera = new Carrera(nombreCarrera);
        Facultad facultad = facultadDAO.buscarPorID(idFacultad);
        carreraDAO.agregar(carrera, facultad);
    }

    //agregamos una carrera pero primero obtenemos la carrera y facultad mediante nombres
    public void agregar(String nombreCarrera, String nombreFacultad) throws Exception {
        Carrera carrera = new Carrera(nombreCarrera);
        Facultad facultad = facultadDAO.buscarPorNombre(nombreFacultad);
        carreraDAO.agregar(carrera, facultad);
    }

    //lista las carreras
    public List<Carrera> listar() {
        return carreraDAO.listar();
    }

    //busca una carrera mediante el id
    public Carrera buscarPorID(int idCarrera) {
        return carreraDAO.buscarPorID(idCarrera);
    }

    //busca una carrera mediante el nombre
    public Carrera buscarPorNombre(String nombreCarrera) {
        return carreraDAO.buscarPorNombre(nombreCarrera);
    }

    /* 
     Recupera una lista de asignaturas que posee una Carrera 
     */
    //devuelve una lista con las asignaturas de una carrera mediante un id de carrera
    public List<Asignatura> listarAsignaturasDeUnaCarrera(int idCarrera) {
        return buscarPorID(idCarrera).getAsignaturas();
    }

    //devuelve una lista con las asignaturas de una carrera mediante un nombre de carrera
    public List<Asignatura> listarAsignaturasDeUnaCarrera(String nombreCarrera) {
        return buscarPorNombre(nombreCarrera).getAsignaturas();
    }
}
