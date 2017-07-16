
package com.legado.grupo.srv;

import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class FacultadService {
    @Autowired
    private FacultadDAO facultadDAO;
    
    public void agregar(String nombreFacultad) throws Exception{
        Facultad facultad =new Facultad(nombreFacultad);
        facultadDAO.agregar(facultad);
    }
    
    public Facultad buscarPorID(int id){
        Facultad facultad=facultadDAO.buscarPorID(id);
        return facultad;
    }
    
    public Facultad buscarPorNombre(String nombreFacultad){
        return facultadDAO.buscarPorNombre(nombreFacultad);
    }
    
    public void actualizarFacultad(int id,String nombreFacultad){
        Facultad facultad=facultadDAO.buscarPorID(id);
        facultad.setNombre(nombreFacultad);
        facultadDAO.actualizar(facultad);
    }
    
    public void eliminarPorId(int id){
        facultadDAO.eliminarPorId(id);
    }
    
    public void eliminarTodo(){
        facultadDAO.eliminarTodo();
    }
    
    public List<Facultad> listarFacultades(){
        return facultadDAO.listar();
    }
    
    
}
