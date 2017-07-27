package com.legado.grupo.controller;
//librerias
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//fin librerias
@RestController
public class GrupoRestController {
    // aributos globales
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
    // fin atributos globales
    /*
    Obtener Lista de todas las carreras que pertenecen a una facultad
     */
    @RequestMapping(value = "/get_lista_facultad", method = RequestMethod.GET)
    public List<Facultad> get_lista_facultad() {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_lista_facultad");
        List<Facultad> facultades = facultadSRV.listar();//se carga lista de facultades
        return facultades;// retorna lista de facultades
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
            Carrera carrera = carreraSRV.buscarPorID(idCarrera);//se busca una carrera por id
            if (carrera != null) {// si no es vacio se cargan las materias
                materias = carrera.getAsignaturas();
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return materias;//devuelve lista de materias
        }
    }

    /*
    Obtener Lista de miebros de un grupo
     */
    @RequestMapping(value = "/get_miembros", method = RequestMethod.GET)
    public List<Usuario> get_miembros(@RequestParam String id_grupo) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_miembros?id_grupo=" + id_grupo);

        List<Usuario> usuarios = new ArrayList<>();//creamos lista de usarios tipo Usuario
        try {
            int idGrupo = Integer.parseInt(id_grupo);//casting a id grupo
            Grupo grupo = grupoSRV.buscarPorID(idGrupo);
            if (grupo != null) {// si existe el grupo entonces buscara a sus integrantes
                for (Miembro m : grupo.getMiembros()) {
                    try {//manejode errores
                        Usuario u = GrupoRestClient.get_usuario(m.getId_usuario());
                        if (u != null) {//si el usuario existe es agregado
                            usuarios.add(u);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return usuarios;//retorna la lista de usuarios
        }
    }

    /*
    Obtener Lista de grupos en los que está un usuario
     */
    @RequestMapping(value = "/get_grupos_usuario", method = RequestMethod.GET)
    public String get_grupos_usuario(@RequestParam String id_usuario) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/get_grupos_usuario?id_usuario=" + id_usuario);

        String jsonRespuesta = "[]";
        try {//control de excepciones
            int idUsuario = Integer.parseInt(id_usuario);

            //Se buscan los grupos y se prepara el JSON de respuesta
            JsonArray jsonArray = new JsonArray();//array de objetos json
            for (Grupo g : grupoSRV.listarGruposDeUnUsuario(idUsuario)) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id_grupo", g.getId_grupo());//se agregan propiedades al objeto json
                jsonObject.addProperty("nombre", g.getNombre());
                jsonObject.addProperty("materia", g.getAsignatura().getNombre());
                jsonObject.addProperty("carrera", g.getAsignatura().getCarrera().getNombre());
                jsonObject.addProperty("facultad", g.getAsignatura().getCarrera().getFacultad().getNombre());
                jsonObject.addProperty("periodo_academico", g.getPeriodo().getFechaInicio().toGMTString());

                jsonArray.add(jsonObject);//se agrega el objeto json a un array
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

        String jsonRespuesta = "[{}]";
        try {//control de excepciones
            int idCarrera = Integer.parseInt(id_carrera);//castings
            int idMateria = Integer.parseInt(id_materia);

            //Se buscan los grupos y se prepara el JSON de respuesta
            JsonArray jsonArray = new JsonArray();
            for (Grupo g : grupoSRV.listarGruposSegunAsignaturaYCarrera(idMateria, idCarrera)) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id_grupo", g.getId_grupo());//se agrega propiedades del grupo
                jsonObject.addProperty("nombre", g.getNombre());
                jsonObject.addProperty("materia", g.getAsignatura().getNombre());
                jsonObject.addProperty("carrera", g.getAsignatura().getCarrera().getNombre());
                jsonObject.addProperty("facultad", g.getAsignatura().getCarrera().getFacultad().getNombre());
                jsonObject.addProperty("periodo_academico", g.getPeriodo().getFechaInicio().toGMTString());

                jsonArray.add(jsonObject);//agregamos el objeo al array
            }

            jsonRespuesta = jsonArray.toString();//tranformamos a string el array
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesita un parametro numerico.");
        } finally {
            return jsonRespuesta;// retornamos el array en forma de string
        }
    }

    /*
    Ingresar un usuario en un grupo, metodo GET
     */
    @RequestMapping(value = "/add_miembro", method = RequestMethod.GET)
    public String add_miembro(@RequestParam String id_usuario, String id_grupo) {
        logger.log(Level.INFO, "GET: " + URL_LEGADO_GRUPOS + "/add_miembro?id_usuario=" + id_usuario + "&id_grupo=" + id_grupo);

        JsonObject jsonRespuesta = new JsonObject();// creamos un objeto json
        try {//control de excepciones
            int idUsuario = Integer.parseInt(id_usuario);//casting a los parametros
            int idGrupo = Integer.parseInt(id_grupo);

            Grupo grupo = grupoSRV.buscarPorID(idGrupo);//instancia de grupo y miembro
            Miembro miembro = new Miembro(idUsuario);

            //Existe el usuario?
            if (grupo != null) {//si existe un grupo ingresa 
                if (!grupoSRV.existeMiembroEnGrupo(idUsuario, idGrupo)) {
                    miembroSRV.agregar(miembro.getId_usuario(), grupo.getId_grupo());//agregamos el miembro en caso de no existir
                    jsonRespuesta.addProperty("estado", "ok");//mensaje de confirmacion
                } else {
                    jsonRespuesta.addProperty("estado", "existe");//mensaje de usuario enconrado
                }
            } else{
                jsonRespuesta.addProperty("estado", "error");//respuesta de error
            }
        } catch (NumberFormatException ex) {//validacion de numeros
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
        try {//control de excepciones
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

                jsonArray.add(jsonObject);//se agrega el objeto al array
            }

            jsonRespuesta = jsonArray.toString();//se trandorma a texto
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, ex.getClass().getSimpleName() + ": Se necesitan parametros numericos.");
        } finally {
            return jsonRespuesta; //retorna la respuesta
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
