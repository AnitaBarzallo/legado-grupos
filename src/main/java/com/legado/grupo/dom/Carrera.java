/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;

/**
 *
 * @author Anita
 */
public class Carrera {
    int id_carrera;
	String nombre;
	int id_facultad;
	
	public int getId_carrera() {
		return id_carrera;
	}
	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId_facultad() {
		return id_facultad;
	}
	public void setId_facultad(int id_facultad) {
		this.id_facultad = id_facultad;
	}
}
