
package com.legado.grupo.srv;
//librerias
import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Grupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//fin librerias

@Service
public class AsignaturaService {
    //atributos globales
    @Autowired
    private AsignaturaDAO asignaturaDAO;
    
    @Autowired
    private CarreraDAO carreraDAO;
    
    //metodo para agregar una asignatura asociado a  una carrera (nombres)
    public void agregar(String nombreAsignatura,String nombreCarrera) throws Exception{
        Carrera carrera=carreraDAO.buscarPorNombre(nombreCarrera);
        Asignatura asignatura=new Asignatura(nombreAsignatura);
        asignaturaDAO.agregar(asignatura, carrera);
    }
    
    //metodo para agregar una asignatura asociado a  una carrera (objetos)
    public void agregar(Asignatura asignatura,Carrera carrera) throws Exception{
        asignaturaDAO.agregar(asignatura, carrera);
    }
    
    //busca una asignatura por id 
    public Asignatura buscarporID(int idAsignatura){
       Asignatura asignatura=(Asignatura) asignaturaDAO.buscarPorID(idAsignatura);
       return asignatura;
   }
 
    //busca una asignatura por nombre
   public Asignatura buscarporNombre(String idAsignatura){
       Asignatura asignatura=(Asignatura) asignaturaDAO.buscarPorNombre(idAsignatura);
       return asignatura;
   }
   
   //actualizamos los atributos de asignatura y los guardamos
   public void actualizar(int idAsignatura,String nombreAsignatura,String nombreCarrera){
       Asignatura asignatura=asignaturaDAO.buscarPorID(idAsignatura);
       Carrera carrera=carreraDAO.buscarPorNombre(nombreCarrera);
       asignatura.setNombre(nombreAsignatura);
       asignatura.setCarrera(carrera);
       asignaturaDAO.actualizar(asignatura);
   }
   
   //eliminamos una asignatura por id
   public void eliminar(int id){
       asignaturaDAO.eliminarPorId(id);
   }
   
   //eliminamos la lista de asignaturas 
   public void eliminarTodo() {
        asignaturaDAO.eliminarTodo();
    }
   
   //retorna una lista con todas las asignaturas
   public List<Asignatura> listar(){
       return asignaturaDAO.listar();
   }
   
   //retorna una lista con todas los grupos mediante id de asignatura
   public List<Grupo> listarGrupos(int idAsignatura){
       return buscarporID(idAsignatura).getGrupos();
   }

   //retorna una lista con todas las asignaturas mediante el nombre
   public List<Grupo> listarGrupos(String nombreAsignatura){
       return buscarporNombre(nombreAsignatura).getGrupos();
   }

   //retorna una asignatura mediante el id
    public Asignatura buscarPorID(int idMateria) {
        return asignaturaDAO.buscarPorID(idMateria);
    }
   
}
