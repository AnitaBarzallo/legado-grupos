
package com.legado.grupo.dom;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int id_carrera;
    
    private String nombre;
    
    @OneToMany(mappedBy = "carrera")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore private List<Asignatura> asignaturas;
    
    @ManyToOne
    @JsonIgnore private Facultad facultad;

    public Carrera() {
    }
    
    public Carrera(int idCarrera, String nombre) {
        this.id_carrera = idCarrera;
        this.nombre = nombre;
        this.asignaturas = new ArrayList<>();
        this.facultad=new Facultad();
    }
    
    public Carrera(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new ArrayList<>();
        this.facultad=new Facultad();
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int idCarrera) {
        this.id_carrera = idCarrera;
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

    @Override
    public String toString() {
        return "Carrera{" + "idCarrera=" + id_carrera + ", nombre=" + nombre + ", asignaturas=" + asignaturas + ", facultad=" + facultad.getNombre() + '}';
    }
    
}
