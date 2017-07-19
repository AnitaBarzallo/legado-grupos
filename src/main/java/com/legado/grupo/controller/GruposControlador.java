/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.controller;

/**
 *
 * @author elinoe
 */

import javax.validation.Valid;
import org.springframework.boot.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*; 

@Controller
public class GruposControlador {
    @RequestMapping("/processForm")
    public String process(Model model){
        model.addAttribute("usuario", new grupo());
        return "userForm";
    }
    
    @RequestMapping("/create")
    public ModelAndView createUser(@Valid grupo user, BindingResult result) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", user);
        model.setViewName(result.hasErrors() ? "userForm" : "userReady");
        
        return model;
    }
    

}
