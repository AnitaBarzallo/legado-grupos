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
import com.legado.grupo.srv.GrupoService;
import java.util.Date;
import java.util.List;
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
    
    @Autowired
    private GrupoService grupoService;
    

    public static void main(String[] args) {
        SpringApplication.run(GrupoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        /*Facultad facultadIng = new Facultad("Ingenieria");
        Facultad facultad2 = new Facultad("Arquitectura");
        
        Carrera carreraSistemas = new Carrera("Sistemas");
        Carrera carreraElectronica = new Carrera("Electronica");
        
        Asignatura asignaturaS = new Asignatura("Tecnologias Web");
        Asignatura asignaturaS2 = new Asignatura("Calidad de Software");
        Asignatura asignaturaS3 = new Asignatura("Redes de computadores");
        Asignatura asignaturaS4 = new Asignatura("Programacion Web");
        Asignatura asignaturaS5 = new Asignatura("Emprendimiento");
        Asignatura asignaturaS6 = new Asignatura("Sistemas Distribuidos");
        
        Asignatura asignaturaE = new Asignatura("Electronica de Potencia");
        Asignatura asignaturaE2 = new Asignatura("Microcontroladores Avanzados");
        
        Periodo periodo = new Periodo(new Date());
        
        Grupo grupo = new Grupo("Grupo1");
        Grupo grupo2 = new Grupo("Grupo2");
        Grupo grupo3 = new Grupo("Grupo3");
        Grupo grupo4 = new Grupo("Grupo4");
        
        Miembro miembro = new Miembro();
        Miembro miembro2 = new Miembro();
        
        facuDAO.agregar(facultadIng);
        carreDAO.agregar(carreraSistemas, facultadIng);
        carreDAO.agregar(carreraElectronica, facultadIng);
        
        asigDAO.agregar(asignaturaS, carreraSistemas);
        asigDAO.agregar(asignaturaS2, carreraSistemas);
        asigDAO.agregar(asignaturaS3, carreraSistemas);
        asigDAO.agregar(asignaturaS4, carreraSistemas);
        asigDAO.agregar(asignaturaS5, carreraSistemas);
        asigDAO.agregar(asignaturaS6, carreraSistemas);
        
        asigDAO.agregar(asignaturaE, carreraElectronica);
        asigDAO.agregar(asignaturaE2, carreraElectronica);
        
        periDAO.agregar(periodo);
        grupoDAO.agregar(grupo, asignaturaS, periodo);
        grupoDAO.agregar(grupo2, asignaturaS, periodo);
        grupoDAO.agregar(grupo3, asignaturaS2, periodo);
        
        grupoDAO.agregar(grupo4, asignaturaE, periodo);
        grupoDAO.agregar(grupo4, asignaturaE2, periodo);
        
        miemDAO.agregar(miembro,grupo);        
        miemDAO.agregar(miembro2,grupo);     
        
        System.out.println("Facultad:" + facuDAO.buscarPorNombre("Ingenieria").toString());
        System.out.println("Carrera: " + carreDAO.buscarPorNombre("Sistemas").toString());
        System.out.println("Asignatura: " + asigDAO.buscarPorNombre("Tecnologias Web").toString());
        
        /*Facultad facultad = new Facultad("Ingenieria");
        Carrera carrera = new Carrera("Sistemas");
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
        
        System.out.println("Facultad:" + facuDAO.buscarPorNombre("Ingenieria").toString());
        System.out.println("Carrera: " + carreDAO.buscarPorNombre("Sistemas").toString());
        System.out.println("Asignatura: " + asigDAO.buscarPorNombre("Tecnologias Web").toString());*/
        
        //Facultad facultad = facuDAO.buscarPorNombre("Arqui");
        //System.out.println(facultad.toString());
        
        //Grupo grupo = grupoDAO.buscarPorNombre("Grupo1");
        //System.out.println(grupo.toString());
        
        //Asignatura asignatura = asigDAO.buscarPorNombre("Dibujo");
        //System.out.println(asignatura.toString());
        
        /*List<Facultad> facultades = facuDAO.listar();
        for(Facultad f : facultades){
            System.out.println(f.toString());
        }*/
    }

}
