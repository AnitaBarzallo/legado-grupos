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
import com.legado.grupo.tmp.Institucion;
import java.util.ArrayList;
import java.util.Arrays;
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
        Institucion i=new Institucion();
	model.addAttribute("institucion", i);
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
                                @RequestParam("idAsignatura") int idAsignatura,// ingresados des el formulario
                                @RequestParam("periodoSeleccionado") int idPeriodo) throws Exception {
        grupoSrv.agregarGrupo(nombreGrupo, idPeriodo, idAsignatura);//envio de datos a GrupoService
        return new ModelAndView("redirect:/");//regreso a pagina principal
    }
    
//    Crear una carrera nueva
    @RequestMapping(value = "/agregarCarrera", method = RequestMethod.POST)
    public ModelAndView agregarCarrera(@RequestParam("agregarCarrera") String nombreCarrera,
            @RequestParam("facultadSeleccionada") int idfacultad) throws Exception {
        
        carreraSRV.agregar(nombreCarrera, idfacultad);
        return new ModelAndView("redirect:/");
    }

    
    //metodo que devuelve lista de facultades
    @ModelAttribute("facultades")
    public List<Facultad> listarFacultades(){
        return facultadSRV.listarFacultades();//retorna lista de facultades
    }
    
    @ModelAttribute("asi")
    public List<Asignatura> listarC(){
        return asignaturaSRV.listar();//retorna lista de facultades
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

    @RequestMapping("/ajax/facuCarrera")
    public String ajaxFacultades(@RequestParam("idFacultad") int idFacultad, Model model) {
        Facultad f=facultadSRV.buscarPorID(idFacultad);    
        model.addAttribute("carreras", f.getCarreras());
        return "index :: carreras";
    }
    
    @RequestMapping("/ajax/carreMateria")
    public String ajaxCarreras(@RequestParam("idCarrera") int idCarrera, Model model) {
        Carrera c=carreraSRV.buscarPorID(idCarrera);
        model.addAttribute("asignaturas", c.getAsignaturas());
        return "index :: asignaturas";
    }

}

