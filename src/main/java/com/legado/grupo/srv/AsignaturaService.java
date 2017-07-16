/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;

import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author EdwinCabrera
 */
public class AsignaturaService {
    @Autowired
    private AsignaturaDAO asignaturaDAO;
    
    @Autowired
    private CarreraDAO carreraDAO;
    
    public void agregarAsignatura(String nombreAsignatura,String nombreCarrera) throws Exception{
        Carrera carrera=carreraDAO.buscarPorNombre(nombreCarrera);
        Asignatura asignatura=new Asignatura(nombreAsignatura);
        asignaturaDAO.agregar(asignatura, carrera);
    }
        
    public void agregarAsignatura(Asignatura asignatura,Carrera carrera) throws Exception{
        asignaturaDAO.agregar(asignatura, carrera);
    }
    
   public Asignatura buscarporNombre(String idAsignatura){
       Asignatura asignatura=(Asignatura) asignaturaDAO.buscarPorNombre(idAsignatura);
       return asignatura;
   }
   
   public void actualizarAsignatura(int idAsignatura,String nombreAsignatura,String nombreCarrera){
       Asignatura asignatura=asignaturaDAO.buscarPorID(idAsignatura);
       Carrera carrera=carreraDAO.buscarPorNombre(nombreCarrera);
       asignatura.setNombre(nombreAsignatura);
       asignatura.setCarrera(carrera);
       asignaturaDAO.actualizar(asignatura);
   }
   
   public void eliminarAsignatura(int id){
       asignaturaDAO.eliminarPorId(id);
   }
   
   public void eliminarTodo() {
        asignaturaDAO.eliminarTodo();
    }
   
   public List<Asignatura> listarAsignaturas(){
       return asignaturaDAO.listar();
   }
   
}
