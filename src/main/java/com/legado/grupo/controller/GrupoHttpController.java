
package com.legado.grupo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GrupoHttpController {
    private static final Logger logger = Logger.getLogger(GrupoHttpController.class.getName());
    @RequestMapping("/")
    public ModelAndView hello(HttpServletRequest request) {
        logger.log(Level.INFO,request.getServletPath());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
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
}
