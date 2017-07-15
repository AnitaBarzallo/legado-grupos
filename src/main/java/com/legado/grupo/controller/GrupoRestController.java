
package com.legado.grupo.controller;

import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrupoRestController {
    private static final Logger logger = Logger.getLogger(RestController.class.getName());
    //Solicitud del usuario y método
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public Asignatura respuesta(@RequestParam String id_grupo) {
        logger.log(Level.INFO, "Peticion", id_grupo);
        Asignatura a = new Asignatura("Mate");
        //La lib json-smart se encarga de convertir automaticamente en json al retornar en un restcontroller
        //Alternativamente pueden armar con gson
        return a;
    }
    
    //Obtener carrera
    @RequestMapping(value = "/get_materia_carrera", method = RequestMethod.GET)
    public Carrera get_materia_carrera() {
        logger.log(Level.INFO, "Peticion");
        Carrera c = new Carrera();
//        c.setId_carrera(1);
//        c.setNombre("Ingenieria de Sistemas");
//        c.setId_facultad(1);
        return c;/**/
    }
    
    
    //Obtener Lista de todas las carreras que pertenecen a una facultad
    @RequestMapping(value = "/get_lista_facultad", method = RequestMethod.GET)
    public Carrera get_lista_facultad() {
        logger.log(Level.INFO, "GET: localhost:9092/get_lista_facultad");
        
        return null;
    }
}
