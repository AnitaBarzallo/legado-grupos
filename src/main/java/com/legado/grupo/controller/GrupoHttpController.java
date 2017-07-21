/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.controller;

import com.legado.grupo.srv.*;
import com.legado.grupo.dom.*;
import com.legado.grupo.srv.GrupoService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GrupoHttpController {
    @Autowired
    private FacultadService facultadSRV;     
    @Autowired
    private CarreraService carreraSRV; 
    @Autowired
    private AsignaturaService asignaturaSRV; 
    @Autowired
    private PeriodoService periodoSRV; 
    @Autowired
    private GrupoService grupoSrv;
    
    private static final Logger logger = Logger.getLogger(GrupoHttpController.class.getName());
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }
    
    @RequestMapping("/grupos")
    //RequestParam permite obtener un atributo de la url, debe tener el mismo nombre
    public @ResponseBody ModelAndView redireccion(){
        logger.log(Level.INFO, "Hola");
        ModelAndView mv = new ModelAndView();
        //Esto carga parámetros en el jsp
        mv.addObject("nombre", "Anita");
        //Esto carga el jsp correspondiente como la vista ante la solicitud del Usuario
        mv.setViewName("grupos");
	return mv;
    }
    
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public ModelAndView agregarGrupo(@RequestParam("nombreGrupo") String nombreGrupo,
                                @RequestParam("asignaturaSeleccionada") int idAsignatura,
                                @RequestParam("periodoSeleccionado") int idPeriodo) throws Exception {
        grupoSrv.agregarGrupo(nombreGrupo, idPeriodo, idAsignatura);
        return new ModelAndView("index");
    }
    
    @ModelAttribute("facultades")
    public List<Facultad> listarFacultades(){
        return facultadSRV.listarFacultades();
    }
    
    @ModelAttribute("carreras")
    public List<Carrera> listarCarreras(){
        return carreraSRV.listar();
    }
    
    @ModelAttribute("materias")
    public List<Asignatura> listarMaterias(){
        return asignaturaSRV.listar();
    }
    
    @ModelAttribute("periodos")
    public List<Periodo> listarPeriodos(){
        return periodoSRV.listarPeriodos();
    }
    
    
    @ModelAttribute("listagrupos")
    public List<Grupo> listaGrupos(){
        return grupoSrv.listarGrupos();
    }

//    Aplicando ajax

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String refreshItem(@RequestParam("agregar") String orderId, Model model) {
////    List<Grupo> list= Grupo.findItemsByOrder(idgrupo).getResull
////        List<Item> itemList = Item.findItemsByOrderId(orderId).getResultList();
////
////    model.addAttribute("itemList", itemList);
//
//    return "myView :: #item";

}

