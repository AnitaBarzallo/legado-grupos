/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private int idPeriodo;
    
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
        this.idPeriodo = idPeriodo;
        this.fechaInicio = fechaInicio;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
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
    
    
    
}
