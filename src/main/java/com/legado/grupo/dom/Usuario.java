
package com.legado.grupo.dom;
//librerias
import java.io.Serializable;
//fin librerias

public class Usuario implements Serializable {
    //atributos globales
    private int id_usuario;
    private String alias;
    private String nombre;
    private String correo;

    //constructores
    public Usuario(int id_usuario, String alias, String nombre, String correo) {
        this.id_usuario = id_usuario;
        this.alias = alias;
        this.nombre = nombre;
        this.correo = correo;
    }
    
    public Usuario(){
        
    }
    //fin constructores
    
    //metodo para obtener
    public int getId_usuario() {
        return id_usuario;
    }

    //metodo para guardar id de usuario
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    //metodo para obtener alias de usuario
    public String getAlias() {
        return alias;
    }

    //metodo para guardar alias de usuario
    public void setAlias(String alias) {
        this.alias = alias;
    }

    //metodo para obtener nombre de usuario
    public String getNombre() {
        return nombre;
    }

    //metodo para guardar nombre de usuario
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //metodo para obtener correo de usuario
    public String getCorreo() {
        return correo;
    }

    //metodo para guardar correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //refefinimod el metodo toString
    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", alias=" + alias + ", nombre=" + nombre + ", correo=" + correo + '}';
    }
    
    

}
