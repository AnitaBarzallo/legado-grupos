package com.legado.grupo.dom;

//lirerias
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//fin lirerias

@Entity
@Table(name = "miembros")//nombre de la tabla en la bd

public class Miembro implements Serializable {

    //atributos globales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "MiembroGrupo", joinColumns = {
        @JoinColumn(name = "IdMiembro")}, inverseJoinColumns = {
        @JoinColumn(name = "IdGrupo")})
    @JsonIgnore
    private List<Grupo> grupos;

    //constructores
    public Miembro() {
        this.grupos = new ArrayList<>();
    }

    public Miembro(int idMiembro) {
        this.id_usuario = idMiembro;
        this.grupos = new ArrayList<>();
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
    public List<Grupo> getGrupo() {
        return grupos;
    }

    //metodo para guardar un grupo
    public void setGrupo(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    public void addGrupo(Grupo grupo) {
        this.grupos.add(grupo);
    }

    //redefinimos el metodo toString
    @Override
    public String toString() {
        return "Miembro{" + "idMiembro=" + id_usuario + ", grupo=..." + '}';
    }

}
