/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="grupos")

public class Grupo implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_grupo;
    
    private String nombre;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grupo")
    private List<Miembro> miembros;
    
    @ManyToOne
    private Asignatura asignatura;
    
    @ManyToOne
    private Periodo periodo;


    public Grupo() {
    }

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.miembros=new ArrayList<>();
    }
    
    public Grupo(int idGrupo, String nombre, Periodo periodo, Asignatura asignatura) {
        this.id_grupo = idGrupo;
        this.nombre = nombre;
        this.periodo = periodo;
        this.asignatura = asignatura;
        this.miembros=new ArrayList<>();
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int idGrupo) {
        this.id_grupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }
    
    public void addMiembro(Miembro miembro){
        this.miembros.add(miembro); 
    }

    @Override
    public String toString() {
        return "Grupo{" + "idGrupo=" + id_grupo + ", nombre=" + nombre + ", miembros=" + miembros + ", asignatura=" + asignatura.getNombre() + ", periodo=" + periodo.getFechaInicio() + '}';
    }
    
    
    
}
