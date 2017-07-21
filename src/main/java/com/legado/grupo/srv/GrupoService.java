/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legado.grupo.srv;

import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.PeriodoDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import com.legado.grupo.dom.Periodo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwinCabrera
 */

@Service
public class GrupoService {

    @Autowired
    private PeriodoDAO periodoDAO;
    @Autowired
    private AsignaturaDAO asignaturaDAO;
    @Autowired
    private GrupoDAO grupoDAO;
    @Autowired
    private CarreraDAO carreraDAO;

    public void agregarGrupo(String nombreGrupo, int idPeriodo, int idAsignatura) throws Exception {
        Periodo periodo = periodoDAO.buscarPorID(idPeriodo);
        Asignatura asignatura = asignaturaDAO.buscarPorID(idAsignatura);
        Grupo grupo = new Grupo(nombreGrupo);
        grupo.setPeriodo(periodo);
        grupo.setAsignatura(asignatura);
        grupoDAO.agregar(grupo, asignatura, periodo);
    }

    public Grupo buscarPorID(int id) {
        return grupoDAO.buscarPorID(id);
    }

    public void actualizar(int idGrupo, String nombreGrupo, int idPeriodo) {
        Grupo grupo = grupoDAO.buscarPorID(idGrupo);
        Periodo periodo = periodoDAO.buscarPorID(idPeriodo);
        grupo.setNombre(nombreGrupo);
        grupo.setPeriodo(periodo);
    }

    public void actualizar(int idGrupo, String nombreGrupo, int idPeriodo, int idAsignatura) {
        Grupo grupo = grupoDAO.buscarPorID(idGrupo);
        Periodo periodo = periodoDAO.buscarPorID(idPeriodo);
        Asignatura asignatura = asignaturaDAO.buscarPorID(idAsignatura);
        grupo.setNombre(nombreGrupo);
        grupo.setPeriodo(periodo);
        grupo.setAsignatura(asignatura);
    }

    public void actualizar(int idGrupo, String nombreGrupo, int idPeriodo, String nombreAsignatura) {
        Grupo grupo = grupoDAO.buscarPorID(idGrupo);
        Periodo periodo = periodoDAO.buscarPorID(idPeriodo);
        Asignatura asignatura = asignaturaDAO.buscarPorNombre(nombreAsignatura);
        grupo.setNombre(nombreGrupo);
        grupo.setPeriodo(periodo);
        grupo.setAsignatura(asignatura);
    }

    public void eliminarPorID(int id) {
        grupoDAO.eliminarPorId(id);
    }

    public List<Grupo> listarGrupos() {
        return grupoDAO.listar();
    }

    public List<Miembro> buscarMiembroGrupo(int idGrupo) {
        return buscarPorID(idGrupo).getMiembros();
    }

    public List<Grupo> listar() {
        return grupoDAO.listar();
    }
    
    public List<Grupo> buscar(int... idGrupos) {
        List<Grupo> lista = new ArrayList<>();
        Grupo grupo;
        for(int id : idGrupos){
            grupo = buscarPorID(id);
            if(grupo != null){
                lista.add(grupo);
            }
        }
        return lista;
    }

    //Se buscan los grupos en los que esta el usuario
    public List<Grupo> listarGruposDeUnUsuario(int idUsuario) {
        List<Grupo> gruposRespuesta = new ArrayList<>();
        List<Grupo> grupos = grupoDAO.listar();
        for (Grupo g : grupos) {
            for (Miembro m : g.getMiembros()) {
                if (m.getId_usuario() == idUsuario) {    // Se verifica si el usuario esta en el grupo
                    gruposRespuesta.add(g);
                    break;
                }
            }
        }
        return gruposRespuesta;
    }

    //Se buscan los grupos que pertenecen a una carrera y una materia especifica
    public List<Grupo> listarGruposSegunAsignaturaYCarrera(int idAsignatura, int idCarrera) {
        List<Grupo> gruposRespuesta = new ArrayList<>();
        Carrera carrera = carreraDAO.buscarPorID(idCarrera);
        Asignatura asignatura = asignaturaDAO.buscarPorID(idAsignatura);
        gruposRespuesta = asignatura.getGrupos();
        return gruposRespuesta;
    }
    
    //Se verifica si un usuario esta en un grupo
    public boolean existeMiembroEnGrupo(int idUsuario, int idGrupo){
        Grupo grupo = grupoDAO.buscarPorID(idGrupo);
        for(Miembro m : grupo.getMiembros()){
            if(m.getId_usuario() == idUsuario){
                return true;
            }
        }
        return false;
    }
}
