
package com.legado.grupo.dom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="asignaturas")

public class Asignatura implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_materia;
    
    private String nombre;
    
    @ManyToOne
    @JsonIgnore private Carrera carrera;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "asignatura")
    @JsonIgnore private List<Grupo> grupos;
    
    public Asignatura() {
    }
    
    public Asignatura(String nombre) {
        this.nombre = nombre;
        this.grupos=new ArrayList<>();
    }
    
    public Asignatura(int idAsignatura, String nombre) {
        this.id_materia = idAsignatura;
        this.nombre = nombre;
        this.grupos=new ArrayList<>();

    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int idAsignatura) {
        this.id_materia = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    public void addGrupo(Grupo grupo){
        this.grupos.add(grupo);
    }

    @Override
    public String toString() {
        return "Asignatura{" + "idAsignatura=" + id_materia + ", nombre=" + nombre + ", carrera=" + carrera.getNombre() + ", grupos=" + grupos + '}';
    }
    
}
