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
   
    private int idMiembro;
    @ManyToOne
    private Grupo grupo;
    
    public Miembro() {
    }

    public Miembro(int idMiembro) {
        this.idMiembro = idMiembro;
        this.grupo=new Grupo();
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Miembro{" + "idMiembro=" + idMiembro + ", grupo=" + grupo.getNombre() + '}';
    }
    
    
}
