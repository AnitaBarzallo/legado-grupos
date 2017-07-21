
package com.legado.grupo.srv;

import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Grupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AsignaturaService {
    @Autowired
    private AsignaturaDAO asignaturaDAO;
    
    @Autowired
    private CarreraDAO carreraDAO;
    
    public void agregar(String nombreAsignatura,String nombreCarrera) throws Exception{
        Carrera carrera=carreraDAO.buscarPorNombre(nombreCarrera);
        Asignatura asignatura=new Asignatura(nombreAsignatura);
        asignaturaDAO.agregar(asignatura, carrera);
    }
        
    public void agregar(Asignatura asignatura,Carrera carrera) throws Exception{
        asignaturaDAO.agregar(asignatura, carrera);
    }
    
    public Asignatura buscarporID(int idAsignatura){
       Asignatura asignatura=(Asignatura) asignaturaDAO.buscarPorID(idAsignatura);
       return asignatura;
   }
    
   public Asignatura buscarporNombre(String idAsignatura){
       Asignatura asignatura=(Asignatura) asignaturaDAO.buscarPorNombre(idAsignatura);
       return asignatura;
   }
   
   public void actualizar(int idAsignatura,String nombreAsignatura,String nombreCarrera){
       Asignatura asignatura=asignaturaDAO.buscarPorID(idAsignatura);
       Carrera carrera=carreraDAO.buscarPorNombre(nombreCarrera);
       asignatura.setNombre(nombreAsignatura);
       asignatura.setCarrera(carrera);
       asignaturaDAO.actualizar(asignatura);
   }
   
   public void eliminar(int id){
       asignaturaDAO.eliminarPorId(id);
   }
   
   public void eliminarTodo() {
        asignaturaDAO.eliminarTodo();
    }
   
   public List<Asignatura> listar(){
       return asignaturaDAO.listar();
   }
   
   public List<Grupo> listarGrupos(int idAsignatura){
       return buscarporID(idAsignatura).getGrupos();
   }

   public List<Grupo> listarGrupos(String nombreAsignatura){
       return buscarporNombre(nombreAsignatura).getGrupos();
   }

    public Asignatura buscarPorID(int idMateria) {
        return asignaturaDAO.buscarPorID(idMateria);
    }
   
}
