
package com.legado.grupo.srv;
//librerias
import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//fin librerias
@Service
public class FacultadService {
    //atributis globales
    @Autowired
    private FacultadDAO facultadDAO;
    
    //se agrega una facultad 
    public void agregar(String nombreFacultad) throws Exception{
        Facultad facultad =new Facultad(nombreFacultad);
        facultadDAO.agregar(facultad);
    }
    
    //busca una facultad por su id
    public Facultad buscarPorID(int id){
        Facultad facultad=facultadDAO.buscarPorID(id);
        return facultad;
    }
    
    //busca una facultad por su nombre
    public Facultad buscarPorNombre(String nombreFacultad){
        return facultadDAO.buscarPorNombre(nombreFacultad);
    }
    
    //se modifica y actualiza una facultad
    public void actualizarFacultad(int id,String nombreFacultad){
        Facultad facultad=facultadDAO.buscarPorID(id);
        facultad.setNombre(nombreFacultad);
        facultadDAO.actualizar(facultad);
    }
    
    //elimina una facultad por id
    public void eliminarPorId(int id){
        facultadDAO.eliminarPorId(id);
    }
    
    //elimina las facultades existenes
    public void eliminarTodo(){
        facultadDAO.eliminarTodo();
    }
    
    //metodo que nos devuelve una lista con las facultades
    public List<Facultad> listarFacultades(){
        return facultadDAO.listar();
    }
    
    //metodo que nos devuelve una lista con las carreras
    public List<Carrera> carrerasFacultad(int idFacultad){
        return buscarPorID(idFacultad).getCarreras();
    }
    
    //metodo que nos devuelve una lista con las carreras de una facultades
    public List<Carrera> carrerasFacultad(String nombreFacultad){
        return buscarPorNombre(nombreFacultad).getCarreras();
    }

    //metodo que nos devuelve una lista con las facultades (r)
    public List<Facultad> listar() {
        return facultadDAO.listar();
    }
}
