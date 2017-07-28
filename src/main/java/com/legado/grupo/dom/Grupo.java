/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;
//librerias

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//fin librerias

@Entity
@Table(name = "grupos")//nombre de la clase en la bd

public class Grupo implements Serializable {

    //atributos globales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_grupo;

    private String nombre;

    @ManyToMany(cascade = {CascadeType.MERGE}, mappedBy = "grupos", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Miembro> miembros;

    @ManyToOne//relacion de muchos a uno con la clase asignatura
    private Asignatura asignatura;

    @ManyToOne//relacion de muchos a uno con la clase periodo
    private Periodo periodo;

    //constructores
    public Grupo() {
        this.miembros = new ArrayList<>();
    }

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
    }

    public Grupo(int idGrupo, String nombre, Periodo periodo, Asignatura asignatura) {
        this.id_grupo = idGrupo;
        this.nombre = nombre;
        this.periodo = periodo;
        this.asignatura = asignatura;
        this.miembros = new ArrayList<>();
    }
    //fin constructores

    //metodo para obtener id de grupo
    public int getId_grupo() {
        return id_grupo;
    }

    //metodo para guardar id de grupo
    public void setId_grupo(int idGrupo) {
        this.id_grupo = idGrupo;
    }

    //metodo para obtener nombre de grupo
    public String getNombre() {
        return nombre;
    }

    //metodo para guardar nombre de grupo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //metodo para obtener periodo
    public Periodo getPeriodo() {
        return periodo;
    }

    //metodo para guardar periodo
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    //metodo para obtener asignatura
    public Asignatura getAsignatura() {
        return asignatura;
    }

    //metodo para guardar asignatura
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    //metodo para obtener lista de miembros
    public List<Miembro> getMiembros() {
        return miembros;
    }

    //metodo para guardar lsita de miembros
    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    //agregamos un miembro a la lista
    public void addMiembro(Miembro miembro) {
        this.miembros.add(miembro);
    }

    //redeminimos el metodo toString
    @Override
    public String toString() {
        return "Grupo{" + "idGrupo=" + id_grupo + ", nombre=" + nombre + ", miembros="+ miembros + ", asignatura=" + asignatura.getNombre() + ", periodo=" + periodo.getFechaInicio() + '}';
    }

}
