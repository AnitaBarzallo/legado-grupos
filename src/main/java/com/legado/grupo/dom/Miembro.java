
package com.legado.grupo.dom;

//lirerias
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
//fin lirerias
@Entity
@Table(name="miembros")//nombre de la tabla en la bd

public class Miembro implements Serializable {
    //atributos globales
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_usuario;
    
    @ManyToOne
    @JsonIgnore private Grupo grupo;
    
    //constructores
    public Miembro() {
    }

    public Miembro(int idMiembro) {
        this.id_usuario = idMiembro;
        this.grupo=new Grupo();
    }
    //fin constructores

    //metodo para obtener id de usuario
    public int getId_usuario() {
        return id_usuario;
    }

    //metodo para guardar id del miembro
    public void setId_usuario(int idMiembro) {
        this.id_usuario = idMiembro;
    }

    //metodo para obtener un grupo
    public Grupo getGrupo() {
        return grupo;
    }

    //metodo para guardar un grupo
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    //redefinimos el metodo toString
    @Override
    public String toString() {
        return "Miembro{" + "idMiembro=" + id_usuario + ", grupo=" + grupo.getNombre() + '}';
    }
  
}
