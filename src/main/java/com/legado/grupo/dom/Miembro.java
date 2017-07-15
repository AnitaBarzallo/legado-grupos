/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="miembros")

public class Miembro implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
   
    private int id_usuario;
    @ManyToOne
    private Grupo grupo;
    
    public Miembro() {
    }

    public Miembro(int idMiembro) {
        this.id_usuario = idMiembro;
        this.grupo=new Grupo();
    }

    public int getId_miembro() {
        return id_usuario;
    }

    public void setId_miembro(int idMiembro) {
        this.id_usuario = idMiembro;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Miembro{" + "idMiembro=" + id_usuario + ", grupo=" + grupo.getNombre() + '}';
    }
    
    
}
