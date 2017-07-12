/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.dao;

import com.legado.grupo.dom.Asignatura;
import java.util.List;


public interface Crud {
      
    public Object buscarPorID(int id);
  
    public void actualizar(Object o);
 
    public void eliminarPorId(int id);
 
    public void eliminarTodo();
 
    public List<Object> listar();
    
    public boolean existe(int id);
    
}
