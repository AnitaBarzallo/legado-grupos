/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;
//librerias 

import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.MiembroDAO;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//fin librerias

/**
 *
 * @author EdwinCabrera
 */
@Service
public class MiembroService {

    //atribuoos globales
    @Autowired
    private MiembroDAO miembroDAO;
    @Autowired
    private GrupoDAO grupoDAO;

    //agregamos a un miembro dentro de un grupo por su id
    public void agregar(int idUsuario, int idGrupo) throws Exception {
        Miembro miembro;
        if (existe(idUsuario)) {
            miembro = buscarPorID(idUsuario);
        } else {
            miembro = new Miembro(idUsuario);
        }

        Grupo grupo = grupoDAO.buscarPorID(idGrupo);
        miembroDAO.agregar(miembro, grupo);
    }

    //agregamos a un miembro dentro de un grupo por su nombre
    public void agregar(int idUsuario, String nombreGrupo) throws Exception {
        Miembro miembro;
        if (existe(idUsuario)) {
            miembro = buscarPorID(idUsuario);
        } else {
            miembro = new Miembro(idUsuario);
        }
        Grupo grupo = grupoDAO.buscarPorNombre(nombreGrupo);
        miembroDAO.agregar(miembro, grupo);
    }

    //busca un miembro por su id
    public Miembro buscarPorID(int ID) {
        return miembroDAO.buscarPorID(ID);
    }

    //metodo que lista los miembros existentes
    public List<Miembro> listar() {
        return miembroDAO.listar();
    }

    //verifica si existe un miembro
    public boolean existe(int idUsuario) {
        return miembroDAO.existe(idUsuario);
    }

}
