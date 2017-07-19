/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.controller;

import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author elinoe
 */
public class grupo {
    @NotBlank
    @Size(min = 3, max = 30)
    private String nombreGrupo;
    
    @NotBlank
    @Size(min = 3, max = 30)
    private String nombreFacultad;

    @NotBlank
    @Size(min = 3, max = 30)
    private String nombreCarrera;
    
    @NotBlank
    @Size(min = 3, max = 30)
    private String nombreAsignatura;
    
    @NotBlank
    @Size(min = 3, max = 30)
    private String periodLectivo;

    public grupo() {
    }

    
    public grupo(String nombreGrupo, String nombreFacultad, String nombreCarrera, String nombreAsignatura, String periodLectivo) {
        
        this.nombreGrupo = nombreGrupo;
        this.nombreFacultad = nombreFacultad;
        this.nombreCarrera = nombreCarrera;
        this.nombreAsignatura = nombreAsignatura;
        this.periodLectivo = periodLectivo;
    }

    public String sayHellow(){
        return "Hello";
    }
    
    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getPeriodLectivo() {
        return periodLectivo;
    }

    public void setPeriodLectivo(String periodLectivo) {
        this.periodLectivo = periodLectivo;
    }
        
}
