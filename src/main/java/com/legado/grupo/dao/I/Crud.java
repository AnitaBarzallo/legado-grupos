/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao.I;
//librerias
import java.util.List;
//fin librerias

//creamos los metodos para realizar un CRUD
public interface Crud<T> {
      
    public T buscarPorID(int id);
      
    public void actualizar(T o);
 
    public void eliminarPorId(int id);
 
    public void eliminarTodo();
 
    public List<T> listar();
    
    public boolean existe(int id);
    
}
