package com.legado.grupo.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.legado.grupo.dom.Asignatura;
import com.legado.grupo.dom.Carrera;
import com.legado.grupo.dom.Facultad;
import com.legado.grupo.dom.Grupo;
import com.legado.grupo.dom.Miembro;
import com.legado.grupo.dom.Usuario;
import com.legado.grupo.srv.AsignaturaService;
import com.legado.grupo.srv.CarreraService;
import com.legado.grupo.srv.FacultadService;
import com.legado.grupo.srv.GrupoService;
import com.legado.grupo.srv.MiembroService;
import com.legado.grupo.srv.PeriodoService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GrupoRestController {

    @Autowired
    private FacultadService facultadSRV;
    @Autowired
    private CarreraService carreraSRV;
    @Autowired
    private AsignaturaService asignaturaSRV;
    @Autowired
    private PeriodoService periodoSRV;
    @Autowired
    private GrupoService grupoSRV;
    @Autowired
    private MiembroService miembroSRV;

    private String URL_LEGADO_GRUPOS = "localhost:9092";

    private static final Logger logger = Logger.getLogger(RestController.class.getName());

    /*
    Obtener Lista de todas las carreras que pertenecen a una facultad
     */
    @RequestMapping(value = "/get_lista_facultad", method = RequestMethod.GET)
    public List<Facultad> get_lista_facultad() {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_lista_facultad");
        List<Facultad> facultades = facultadSRV.listar();
        return facultades;
    }

    /*
    Obtener Lista de todas las materias que pertenecen a una carrera
     */
    @RequestMapping(value = "/get_materia_carrera", method = RequestMethod.GET)
    public List<Asignatura> get_materias_carrera(@RequestParam String id_carrera) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_materia_carrera?id_carrera=" + id_carrera);

        List<Asignatura> materias = new ArrayList<>();
        try {
            int idCarrera = Integer.parseInt(id_carrera);
            Carrera carrera = carreraSRV.buscarPorID(idCarrera);
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
    public List<Usuario> get_miembros(@RequestParam String id_grupo) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_miembros?id_grupo=" + id_grupo);

        List<Usuario> usuarios = new ArrayList<>();
        try {
            int idGrupo = Integer.parseInt(id_grupo);
            Grupo grupo = grupoSRV.buscarPorID(idGrupo);
            if (grupo != null) {
                for (Miembro m : grupo.getMiembros()) {
                    usuarios.add(GrupoRestClient.get_usuario(m.getId_usuario()));
                }
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return usuarios;
        }
    }

    /*
    Obtener Lista de grupos en los que está un usuario
     */
    @RequestMapping(value = "/get_grupos_usuario", method = RequestMethod.GET)
    public String get_grupos_usuario(@RequestParam String id_usuario) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_grupos_usuario?id_usuario=" + id_usuario);

        String jsonRespuesta = "[]";
        try {
            int idUsuario = Integer.parseInt(id_usuario);

            //Se buscan los grupos y se prepara el JSON de respuesta
            JsonArray jsonArray = new JsonArray();
            for (Grupo g : grupoSRV.listarGruposDeUnUsuario(idUsuario)) {
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
    Obtener Lista de grupos que pertenecen a una carrera y una materia especifica
     */
    @RequestMapping(value = "/buscar_grupos", method = RequestMethod.GET)
    public String get_grupos_usuario(@RequestParam String id_carrera, String id_materia) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/buscar_grupos?id_carrera=" + id_carrera + "&id_materia=" + id_materia);

        String jsonRespuesta = "[]";
        try {
            int idCarrera = Integer.parseInt(id_carrera);
            int idMateria = Integer.parseInt(id_materia);

            //Se buscan los grupos y se prepara el JSON de respuesta
            JsonArray jsonArray = new JsonArray();
            for (Grupo g : grupoSRV.listarGruposSegunAsignaturaYCarrera(idMateria, idCarrera)) {
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
    Ingresar un usuario en un grupo
     */
    @RequestMapping(value = "/add_miembro", method = RequestMethod.GET)
    public String add_miembro(@RequestParam String id_usuario, String id_grupo) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/add_miembro?id_usuario=" + id_usuario + "&id_grupo=" + id_grupo);

        JsonObject jsonRespuesta = new JsonObject();
        try {
            int idUsuario = Integer.parseInt(id_usuario);
            int idGrupo = Integer.parseInt(id_grupo);

            Grupo grupo = grupoSRV.buscarPorID(idGrupo);
            Miembro miembro = new Miembro(idUsuario);

            //Existe el usuario?
            if (!miembroSRV.existe(idUsuario)) {
                miembroSRV.agregar(miembro.getId_usuario(), grupo.getId_grupo());
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

    /*
    Obtener Lista de grupos segun los ids que se indiquen como parametro
     */
    @RequestMapping(value = "/get_grupo", method = RequestMethod.GET)
    public String get_grupos(@RequestParam String id_grupo) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_grupo?id_grupo=" + id_grupo);

        String jsonRespuesta = "[]";
        try {
            //Se separan los ids pasados como parametro
            String[] s_ids = id_grupo.split(",");
            int[] ids = new int[s_ids.length];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = Integer.parseInt(s_ids[i]);
            }

            //Se buscan los grupos y se prepara el JSON de respuesta
            JsonArray jsonArray = new JsonArray();
            for (Grupo g : grupoSRV.buscar(ids)) {
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
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesitan parametros numericos.");
        } finally {
            return jsonRespuesta;
        }
    }

    /*
    Método para provar invocación de servicios REST externos
     */
    @RequestMapping(value = "/get_usuario", method = RequestMethod.GET)
    public Usuario get_usuario(@RequestParam String id_usuario) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_usuario?id_usuario=" + id_usuario);
        return new Usuario(1, "fxaviergb", "Xavier Garnica", "xg@ucuenca.ec");
    }

}
