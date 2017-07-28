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
import com.legado.grupo.srv.FacultadService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
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
        /*Facultad facultad = new Facultad("Ingenieria");
        Carrera carrera = new Carrera("Ingenieria de sistemas");
        Asignatura asignatura = new Asignatura("Tecnologias Web");     
        Periodo periodo = new Periodo(new Date());
        Grupo grupo = new Grupo("Grupo1");
        Miembro miembro = new Miembro();
        
        facuDAO.agregar(facultad);
        carreDAO.agregar(carrera, facultad);
        asigDAO.agregar(asignatura, carrera);
        periDAO.agregar(periodo);
        grupoDAO.agregar(grupo, asignatura, periodo);
        miemDAO.agregar(miembro,grupo);
        
        grupoDAO.agregar(new Grupo("Grupo2"), asigDAO.buscarPorNombre("Tecnologias Web"), periDAO.buscarPorID(1));
                       
        Miembro m1 = miemDAO.buscarPorID(1);
        miemDAO.agregar(m1, grupoDAO.buscarPorNombre("Grupo2")); 
        
        Miembro m2 = new Miembro();
        miemDAO.agregar(m2, grupoDAO.buscarPorNombre("Grupo2")); */
    }

}
