
package com.legado.grupo.dao;
// librerias
import com.legado.grupo.dao.I.Crud;
import com.legado.grupo.dao.I.GrupoRepositorio;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Periodo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// fin librerias
@Repository
public class GrupoDAO implements Crud<Grupo> {
    //instancia repositorio
    @Autowired
    private GrupoRepositorio repositorio;

    //Agregar grupo a base de datos
    public void agregar(Grupo grupo, Asignatura asignatura, Periodo periodo) throws Exception {
        //control de campos repetidos
        if (asignatura == null) {//control de asignaturas
            throw new Exception("No se ha indicado la asignatura del grupo " + grupo.getNombre());
        }
        if (periodo == null) {//control de periodos
            throw new Exception("No se ha indicado el periodo del grupo " + grupo.getNombre());
        }
        if (existe(grupo, asignatura, periodo)) {//control de grupo + periodo
            throw new Exception("Ya existe un grupo " + grupo.getNombre() + " para la asignatura " + asignatura.getNombre() + " en el período " + periodo.getFechaInicio() + "!.");
        }
        //fin control de campos repetidos
        //Bidireccional: Un Grupo tiene una Asinatura
        grupo.setAsignatura(asignatura);
        asignatura.addGrupo(grupo);

        //Bidireccional: Un Grupo tiene un periodo
        grupo.setPeriodo(periodo);
        periodo.addGrupo(grupo);

        repositorio.save(grupo);//guardamos el grupo en la base de datos
    }

    @Override
    public Grupo buscarPorID(int id) {
        return repositorio.findOne(id);//devuelve el grupo mediante el id de grupo
    }

    public Grupo buscarPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);//devuelve el grupo mediante el nombre del grupo
    }

    @Override
    public void actualizar(Grupo o) {
        repositorio.save(o);//actualiza el grupo
        //guarda el grupo enviado por parametro
    }

    //elimina el grupo mediante el id enviado por parametro
    @Override
    public void eliminarPorId(int id) {
        if (existe(id)) {
            repositorio.delete(id);
        }
    }

    //borra todos los grupos existente de la base de datos
    @Override
    public void eliminarTodo() {
        repositorio.deleteAll();
    }

    //devuelve un listado de los grupos existentes
    @Override
    public List<Grupo> listar() {
        return (List<Grupo>) repositorio.findAll();
    }

    //en caso de existir un grupo devolvera el valor de verdadero
    @Override
    public boolean existe(int id) {
        return repositorio.exists(id);
    }

    //en caso de existir un grupo devolvera el valor de verdadero
    private boolean existe(Grupo grupo, Asignatura asignatura, Periodo periodo) {
        for (Grupo g : asignatura.getGrupos()) {
            if (g.getNombre().equals(grupo.getNombre()) && g.getPeriodo().getFechaInicio().equals(periodo.getFechaInicio())) {
                return true;//devuelve verdadero si existen coincidencias en la base de datos en  la tabla grupo
            }
        }
        return false;//devuelve falso si no encuentra coincidencias en la base de datos en  la tabla grupo
    }

}
