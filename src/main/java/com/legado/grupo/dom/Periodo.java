
package com.legado.grupo.dom;
//librerias
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
//fin librerias
@Entity
@Table(name="periodos")//nombre de la tabla en la bd

public class Periodo implements Serializable {
    //atributos globales
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_periodo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    @OneToMany(mappedBy = "periodo")//relacion de uno a muchos con la tabla periodos
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Grupo> grupos;

    //constructores
    public Periodo() {
    }
    
    public Periodo(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.grupos=new ArrayList<>();
    }
    
    public Periodo(int idPeriodo, Date fechaInicio) {
        this.id_periodo = idPeriodo;
        this.fechaInicio = fechaInicio;
    }
    //fin constructores
    
    //metodo para obtener id del periodo
    public int getId_periodo() {
        return id_periodo;
    }

    //metodo para guardar id del periodo
    public void setId_periodo(int idPeriodo) {
        this.id_periodo = idPeriodo;
    }

    //metodo para obtener fecha de inicio
    public Date getFechaInicio() {
        return fechaInicio;
    }

    //metodo para guardar fecha de inicio
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    //metodo para obtener lista de grupos
    public List<Grupo> getGrupos() {
        return grupos;
    }

    //metodo para guardar lista de grupos
    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    //agrega un grupo a una lista de grupos
    public void addGrupo(Grupo grupo){
        this.grupos.add(grupo);
    }

    //redefinimos el metodo to String
    @Override
    public String toString() {
        return "Periodo{" + "idPeriodo=" + id_periodo + ", fechaInicio=" + fechaInicio + ", grupos=" + grupos + '}';
    }

}
