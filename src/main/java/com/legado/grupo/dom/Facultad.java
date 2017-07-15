
package com.legado.grupo.dom;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="facultades")

public class Facultad implements Serializable{    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_facultad; 
    
    private String nombre;
    
    @OneToMany(mappedBy = "facultad")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Carrera> carreras;

    public Facultad() {
    }

    public Facultad(String nombre) {
        this.nombre = nombre;
        this.carreras = new ArrayList<>();
    }

    public Facultad(int idFacultad, String nombre) {
        this.id_facultad = idFacultad;
        this.nombre = nombre;
        this.carreras = new ArrayList<>();
    }

    public Facultad(int idFacultad, String nombre, List<Carrera> carreras) {
        this.id_facultad = idFacultad;
        this.nombre = nombre;
        this.carreras = carreras;
    }

    public int getId_facultad() {
        return id_facultad;
    }

    public void setId_facultad(int idFacultad) {
        this.id_facultad = idFacultad;
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

    @Override
    public String toString() {
        return "Facultad{" + "idFacultad=" + id_facultad + ", nombre=" + nombre + ", carreras=" + carreras + '}';
    }
            
    
}
