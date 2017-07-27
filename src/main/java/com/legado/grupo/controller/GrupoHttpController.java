/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.controller;
// librerias 
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
// fin librerias

@Controller
public class GrupoHttpController {
    //atributos globales
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
    //fin atributos globales
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
    //datos recibidos del form situado en index.html, se usa metodo POST
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public ModelAndView agregarGrupo(@RequestParam("nombreGrupo") String nombreGrupo,//creamos y asignamos variables para datos
                                @RequestParam("asignaturaSeleccionada") int idAsignatura,// ingresados des el formulario
                                @RequestParam("periodoSeleccionado") int idPeriodo) throws Exception {
        grupoSrv.agregarGrupo(nombreGrupo, idPeriodo, idAsignatura);//envio de datos a GrupoService
        return new ModelAndView("index");//regreso a pagina principal
    }
    
//    Crear una carrera nueva
    @RequestMapping(value = "/agregarCarrera", method = RequestMethod.POST)
    public ModelAndView agregarCarrera(@RequestParam("agregarCarrera") String nombreCarrera,
            @RequestParam("facultadSeleccionada") int idfacultad) throws Exception {
        
        carreraSRV.agregar(nombreCarrera, idfacultad);
        return new ModelAndView("index");
    }
//    
////    Agregar una nueva asignatura
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ModelAndView agregarAsignatura(@RequestParam("agregarAsignatura") String nombreAsignatura,
//        @RequestParam("agregarCarrera") String nombreCarrera) throws Exception {
//        asignaturaSRV.agregar(nombreAsignatura, nombreCarrera);
//        return new ModelAndView("index");
//    }
    
    //metodo que devuelve lista de facultades
    @ModelAttribute("facultades")
    public List<Facultad> listarFacultades(){
        return facultadSRV.listarFacultades();//retorna lista de facultades
    }
    
    //metodo para listar carreras
    @ModelAttribute("carreras")
    public List<Carrera> listarCarreras(){
        return carreraSRV.listar();//retorna lista de carreras
    }
    
    //metodo para listar materias
    @ModelAttribute("materias")
    public List<Asignatura> listarMaterias(){
        return asignaturaSRV.listar();//retorna lista de materias
    }
    
    //metodo para listar periodos
    @ModelAttribute("periodos")
    public List<Periodo> listarPeriodos(){
        return periodoSRV.listarPeriodos();//retorna lista de periodos lectivos
    }
    
    //metodo para listar grupos
    @ModelAttribute("listagrupos")
    public List<Grupo> listaGrupos(){
        return grupoSrv.listarGrupos();//retorna lista de grupos
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

