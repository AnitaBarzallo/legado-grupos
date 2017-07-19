/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;

import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.MiembroDAO;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author EdwinCabrera
 */
public class MiembroService {
    @Autowired
    private MiembroDAO miembroDAO;
    @Autowired
    private GrupoDAO grupoDAO;
    
    public void agregarMiembro(int idUsuario,int idGrupo) throws Exception{
        Miembro miembro=new Miembro(idUsuario);
        Grupo grupo=grupoDAO.buscarPorID(idGrupo);
        miembroDAO.agregar(miembro, grupo);
    }
    
    public void agregarMiembro(int idUsuario,String nombreGrupo) throws Exception{
        Miembro miembro=new Miembro(idUsuario);
        Grupo grupo=grupoDAO.buscarPorNombre(nombreGrupo);
        miembroDAO.agregar(miembro, grupo);
    }
    
    public Miembro buscarPorID(int ID){
        return miembroDAO.buscarPorID(ID);
    }
    
    public List<Miembro> listarMiembros(){
        return miembroDAO.listar();
    }
    
}