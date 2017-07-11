/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="facultades")

public class Facultad implements Serializable{    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idFacultad; 
    
    private String nombre;
    @OneToMany(mappedBy = "facultad")
    private List<Carrera> carreras;

    public Facultad() {
    }

    public Facultad(String nombre) {
        this.nombre = nombre;
        this.carreras = new ArrayList<>();
    }

    public Facultad(int idFacultad, String nombre) {
        this.idFacultad = idFacultad;
        this.nombre = nombre;
        this.carreras = new ArrayList<>();
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
    
    public void addCarrera(Carrera carrera){
        carreras.add(carrera);
    }
    
    
            
}
