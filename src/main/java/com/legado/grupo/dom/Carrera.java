/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="carreras")
public class Carrera implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCarrera;
    
    private String nombre;
    
    @OneToMany(mappedBy = "carrera")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Asignatura> asignaturas;
    
    @ManyToOne
    private Facultad facultad;

    public Carrera() {
    }
    
    public Carrera(int idCarrera, String nombre) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.asignaturas = new ArrayList<>();
        this.facultad=new Facultad();
    }
    
    public Carrera(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new ArrayList<>();
        this.facultad=new Facultad();
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    public void addAsignatura(Asignatura asignatura){
        this.asignaturas.add(asignatura);
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    
    


}
