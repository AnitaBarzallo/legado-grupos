
package com.legado.grupo.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="asignaturas")

public class Asignatura implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idAsignatura;
    
    private String nombre;
    @ManyToOne
    private Carrera carrera;

    @OneToMany(mappedBy = "asignatura")
    private List<Grupo> grupos;
    public Asignatura() {
    }
    
    public Asignatura(String nombre) {
        this.nombre = nombre;
        this.grupos=new ArrayList<>();
    }
    
    public Asignatura(int idAsignatura, String nombre) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.grupos=new ArrayList<>();

    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
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
    
    
    
    
}
