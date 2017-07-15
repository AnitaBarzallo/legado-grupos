package com.legado.grupo.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.legado.grupo.dao.AsignaturaDAO;
import com.legado.grupo.dao.CarreraDAO;
import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dao.GrupoDAO;
import com.legado.grupo.dao.MiembroDAO;
import com.legado.grupo.dao.PeriodoDAO;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrupoRestController {

    @Autowired
    private FacultadDAO facultadDAO;
    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private AsignaturaDAO asignaturaDAO;
    @Autowired
    private PeriodoDAO periodoDAO;
    @Autowired
    private MiembroDAO miembroDAO;
    @Autowired
    private GrupoDAO grupoDAO;

    private static final Logger logger = Logger.getLogger(RestController.class.getName());

    /*
    Obtener Lista de todas las carreras que pertenecen a una facultad
     */
    @RequestMapping(value = "/get_lista_facultad", method = RequestMethod.GET)
    public List<Facultad> get_lista_facultad() {
        logger.log(Level.INFO, "GET: localhost:9092/get_lista_facultad");
        List<Facultad> facultades = facultadDAO.listar();
        return facultades;
    }

    /*
    Obtener Lista de todas las materias que pertenecen a una carrera
     */
    @RequestMapping(value = "/get_materia_carrera", method = RequestMethod.GET)
    public List<Asignatura> get_materias_carrera(@RequestParam String id_carrera) {
        logger.log(Level.INFO, "GET: localhost:9092/get_materia_carrera?id_carrera=" + id_carrera);

        List<Asignatura> materias = new ArrayList<>();
        try {
            int idCarrera = Integer.parseInt(id_carrera);
            Carrera carrera = carreraDAO.buscarPorID(idCarrera);
            if (carrera != null) {
                materias = carrera.getAsignaturas();
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return materias;
        }
    }

    /*
    Obtener Lista de miebros de un grupo
     */
    @RequestMapping(value = "/get_miembros", method = RequestMethod.GET)
    public List<Miembro> get_miembros(@RequestParam String id_grupo) {
        logger.log(Level.INFO, "GET: localhost:9092/get_miembros?id_grupo=" + id_grupo);

        List<Miembro> miembros = new ArrayList<>();
        try {
            int idGrupo = Integer.parseInt(id_grupo);
            Grupo grupo = grupoDAO.buscarPorID(idGrupo);
            if (grupo != null) {
                miembros = grupo.getMiembros();
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return miembros;
        }
    }

    /*
    Obtener Lista de ggrupos en los que está un usuario
     */
    @RequestMapping(value = "/get_grupos_usuario", method = RequestMethod.GET)
    public String get_grupos_usuario(@RequestParam String id_usuario) {
        logger.log(Level.INFO, "GET: localhost:9092/get_grupos_usuario?id_usuario=" + id_usuario);

        String jsonRespuesta = "[]";
        try {
            int idUsuario = Integer.parseInt(id_usuario);

            //Se buscan los grupos en los que esta el usuario
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

            //Con los grupos encontrados, se prepara el JSON de respuesta
            JsonArray jsonArray = new JsonArray();
            for (Grupo g : gruposRespuesta) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id_grupo", g.getId_grupo());
                jsonObject.addProperty("nombre", g.getNombre());
                jsonObject.addProperty("materia", g.getAsignatura().getNombre());
                jsonObject.addProperty("carrera", g.getAsignatura().getCarrera().getNombre());
                jsonObject.addProperty("facultad", g.getAsignatura().getCarrera().getFacultad().getNombre());
                jsonObject.addProperty("periodo_academico", g.getPeriodo().getFechaInicio().toGMTString());

                jsonArray.add(jsonObject);
            }

            jsonRespuesta = jsonArray.toString();
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return jsonRespuesta;
        }
    }

    /*
    Obtener Lista de grupos que pertenecen a una carrera y una materia especific
     */
    @RequestMapping(value = "/buscar_grupos", method = RequestMethod.GET)
    public String get_grupos_usuario(@RequestParam String id_carrera, String id_materia) {
        logger.log(Level.INFO, "GET: localhost:9092/buscar_grupos?id_carrera=" + id_carrera + "&id_materia=" + id_materia);

        String jsonRespuesta = "[]";
        try {
            int idCarrera = Integer.parseInt(id_carrera);
            int idMateria = Integer.parseInt(id_materia);

            //Se buscan los grupos que pertenecen a una carrera y una materia especifica
            List<Grupo> gruposRespuesta = new ArrayList<>();
            Carrera carrera = carreraDAO.buscarPorID(idCarrera);
            Asignatura asignatura = asignaturaDAO.buscarPorID(idMateria);
            if (carrera.getAsignaturas().contains(asignatura)) {
                gruposRespuesta = asignatura.getGrupos();

                //Con los grupos encontrados, se prepara el JSON de respuesta
                JsonArray jsonArray = new JsonArray();
                for (Grupo g : gruposRespuesta) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id_grupo", g.getId_grupo());
                    jsonObject.addProperty("nombre", g.getNombre());
                    jsonObject.addProperty("materia", g.getAsignatura().getNombre());
                    jsonObject.addProperty("carrera", g.getAsignatura().getCarrera().getNombre());
                    jsonObject.addProperty("facultad", g.getAsignatura().getCarrera().getFacultad().getNombre());
                    jsonObject.addProperty("periodo_academico", g.getPeriodo().getFechaInicio().toGMTString());

                    jsonArray.add(jsonObject);
                }

                jsonRespuesta = jsonArray.toString();
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return jsonRespuesta;
        }
    }

    /*
    Ingresar un usuario en un grupo
     */
    @RequestMapping(value = "/add_miembro", method = RequestMethod.GET)
    public String add_miembro(@RequestParam String id_usuario, String id_grupo) {
        logger.log(Level.INFO, "GET: localhost:9092/add_miembro?id_usuario=" + id_usuario + "&id_grupo=" + id_grupo);

        JsonObject jsonRespuesta = new JsonObject();
        try {
            int idUsuario = Integer.parseInt(id_usuario);
            int idGrupo = Integer.parseInt(id_grupo);

            Grupo grupo = grupoDAO.buscarPorID(idGrupo);
            Miembro miembro = new Miembro(idUsuario);

            //Existe el usuario?
            if (!miembroDAO.existe(idUsuario)) {
                miembroDAO.agregar(miembro, grupo);
                jsonRespuesta.addProperty("estado", "ok");
            } else {
                jsonRespuesta.addProperty("estado", "existe");
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
            jsonRespuesta.addProperty("estado", "error");
        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": " + ex.getMessage());
            jsonRespuesta.addProperty("estado", "error");
        } finally {
            return jsonRespuesta.toString();
        }
    }
}
