package com.legado.grupo;


import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.MiembroDAO;
import com.legado.grupo.dao.PeriodoDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import com.legado.grupo.dom.Periodo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrupoApplication implements CommandLineRunner {
    
    @Autowired
    private FacultadDAO facuDAO;
    @Autowired
    private CarreraDAO carreDAO;
    @Autowired
    private AsignaturaDAO asigDAO;
    @Autowired
    private PeriodoDAO periDAO;
    @Autowired
    private MiembroDAO miemDAO;
    @Autowired
    private GrupoDAO grupoDAO;
    
    public static void main(String[] args) {
            SpringApplication.run(GrupoApplication.class, args);
    }
    
    @Override
    public void run(String... strings) throws Exception {
//        Facultad f=new Facultad("Arqui");
//        Carrera c=new Carrera("Carrera100");
//        Asignatura a=new Asignatura("Dibujo");
//        f.addCarrera(c);
//        c.addAsignatura(a);
//        
//        facuDAO.agregar(f);
//        carreDAO.agregar(c, f);
//        asigDAO.agregar(a, c);
//        
//        Periodo periodo =new Periodo(new Date());
//        periDAO.agregar(periodo);
//        
//        Miembro miembro = new Miembro();
//        miemDAO.agregar(miembro);
//        
//        Grupo grupo=new Grupo("Grupo1", periodo, a);
//        grupo.addMiembro(miembro);
//        
//        grupoDAO.agregar(grupo, a, periodo);
        System.out.println(asigDAO.buscarPorNombre("Dibujo").getIdAsignatura());
        System.out.println(carreDAO.buscarPorNombre("Civil").getIdCarrera());
        System.out.println(facuDAO.buscarPorNombre("Arqui").getIdFacultad());
    }
        
}
