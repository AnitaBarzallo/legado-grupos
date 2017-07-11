/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dom;

import java.util.Set;
import java.util.TreeSet;
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
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }
    
    

    
}
