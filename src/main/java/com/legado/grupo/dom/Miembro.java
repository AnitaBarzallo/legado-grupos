
package com.legado.grupo.dom;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="miembros")

public class Miembro implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_usuario;
    
    @ManyToOne
    @JsonIgnore private Grupo grupo;
    
    public Miembro() {
    }

    public Miembro(int idMiembro) {
        this.id_usuario = idMiembro;
        this.grupo=new Grupo();
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int idMiembro) {
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
