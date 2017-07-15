
package com.legado.grupo.dom;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="periodos")

public class Periodo implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_periodo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    @OneToMany(mappedBy = "periodo")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Grupo> grupos;

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

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int idPeriodo) {
        this.id_periodo = idPeriodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
        return "Periodo{" + "idPeriodo=" + id_periodo + ", fechaInicio=" + fechaInicio + ", grupos=" + grupos + '}';
    }

}
