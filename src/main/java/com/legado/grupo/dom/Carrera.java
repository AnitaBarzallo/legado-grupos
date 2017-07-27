
package com.legado.grupo.dom;
//librerias
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
//fin librerias

@Entity
@Table(name="carreras")//relacion explicita tabla carreras en bd
public class Carrera implements Serializable{
    //atributos globales
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_carrera;
    
    private String nombre;
    
    @OneToMany(mappedBy = "carrera")//relacion de uno a muchos con tabla asignaturas en bd
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore private List<Asignatura> asignaturas;
    
    @ManyToOne//relacion muchos a uno con facultad
    @JsonIgnore private Facultad facultad;

    //constructores
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
    //fin de constructores
    
    //metodo para obtener id decarreras
    public int getId_carrera() {
        return id_carrera;
    }

    //metodo para agregar id de carrera
    public void setId_carrera(int idCarrera) {
        this.id_carrera = idCarrera;
    }

    //metodo obtiene nomobre de carrera
    public String getNombre() {
        return nombre;
    }

    //metodo guarda el nombre de carrera
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //devuelve lista de asignaturas
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    //se agrega una lista de asignaturas
    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    //metodo que agrega una asignatura a la lista de asignaturas
    public void addAsignatura(Asignatura asignatura){
        this.asignaturas.add(asignatura);
    }

    //metodo que devuelve la facultad
    public Facultad getFacultad() {
        return facultad;
    }

    //metodo para guardar una faculad
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    //redefinimos el metodo toString
    @Override
    public String toString() {
        return "Carrera{" + "idCarrera=" + id_carrera + ", nombre=" + nombre + ", asignaturas=" + asignaturas + ", facultad=" + facultad.getNombre() + '}';
    }
    
}
