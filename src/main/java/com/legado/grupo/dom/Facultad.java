
package com.legado.grupo.dom;

//librerias
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
//fin librerias
@Entity
@Table(name="facultades")//nombre asignado en la bd

public class Facultad implements Serializable{    
    //atributos globales
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_facultad; 
    
    private String nombre;
    
    @OneToMany(mappedBy = "facultad")//relacion una a muchos de facultad con carreras
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Carrera> carreras;

    //constructores
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
    //fin constructores
    
    //metodo para obtener id de facultad
    public int getId_facultad() {
        return id_facultad;
    }

    //metodo para guardar id de facultad
    public void setId_facultad(int idFacultad) {
        this.id_facultad = idFacultad;
    }

    //metodo para obtener nombre de facultad
    public String getNombre() {
        return nombre;
    }

    //metodo para guardar nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //metodo para obtener lista de carreras
    public List<Carrera> getCarreras() {
        return carreras;
    }

    //metodo para guardar carreras
    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
    
    //se agrga una carrera a la lista exisente
    public void addCarrera(Carrera carrera){
        carreras.add(carrera);
    }

    //redefinimos el metod toString
    @Override
    public String toString() {
        return "Facultad{" + "idFacultad=" + id_facultad + ", nombre=" + nombre + ", carreras=" + carreras + '}';
    }
            
    
}
