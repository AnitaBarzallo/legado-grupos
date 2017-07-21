
package com.legado.grupo.dom;


public class Usuario {
    private int id_usuario;
    private String alias;
    private String nombre;
    private String correo;

    public Usuario(int id_usuario, String alias, String nombre, String correo) {
        this.id_usuario = id_usuario;
        this.alias = alias;
        this.nombre = nombre;
        this.correo = correo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", alias=" + alias + ", nombre=" + nombre + ", correo=" + correo + '}';
    }
    
    

}
