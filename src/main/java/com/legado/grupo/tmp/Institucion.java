/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.tmp;


public class Institucion {
    	private String idFacultad;
	private String idCarrera;

    public Institucion() {
    }

    public Institucion(String idFacultad, String idCarrera) {
        this.idFacultad = idFacultad;
        this.idCarrera = idCarrera;
    }
    
    

    public String getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(String idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }
    
    


    
    
        
}
