
package com.legado.grupo.dom;
//librerias
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//fin librerias
@Entity
@Table(name="asignaturas")//relacion con la tabla asignaturas en la bd

public class Asignatura implements Serializable{
    //variables globales
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_materia;
    
    private String nombre;
    
    @ManyToOne
    @JsonIgnore private Carrera carrera;//ignora atributo en peticion rest

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "asignatura")//tipo de relacion con clase asignatura
    @JsonIgnore private List<Grupo> grupos;//ignora atributo en peticion rest
    //fin variables globales
    //constructor
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
     //fin constructores

    //metodo para obtener el id de materia
    public int getId_materia() {
        return id_materia;
    }

    //metodo para guardar el id de materia
    public void setId_materia(int idAsignatura) {
        this.id_materia = idAsignatura;
    }

    //metodo para obtener el nombre de materia
    public String getNombre() {
        return nombre;
    }

    //metodo para guardar el nombre de materia
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //metodo para obtener el objeto carrera
    public Carrera getCarrera() {
        return carrera;
    }

    //metodo para guardar el objeto carrera
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    //metodo get lista de grupos
    public List<Grupo> getGrupos() {
        return grupos;
    }

    //metodo set grupos
    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    public void addGrupo(Grupo grupo){
        this.grupos.add(grupo);
    }
    
    //redefinimos el metodo toString
    @Override
    public String toString() {
        return "Asignatura{" + "idAsignatura=" + id_materia + ", nombre=" + nombre + ", carrera=" + carrera.getNombre() + ", grupos=" + grupos + '}';
    }
    
}
