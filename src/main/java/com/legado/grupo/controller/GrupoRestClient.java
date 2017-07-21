package com.legado.grupo.controller;

import com.legado.grupo.dom.Usuario;
import org.springframework.web.client.RestTemplate;

public class GrupoRestClient {

    private static String URL_LEGADO_USUARIOS = "localhost:9091";
    private static String URL_LEGADO_GRUPOS = "localhost:9092";

    /*
    Obtiene un usuario de un microservicio externo
     */
    public static Usuario get_usuario(int idUsuario) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + URL_LEGADO_USUARIOS + "/get_usuario?id_usuario=" + idUsuario;
        Usuario usuario = restTemplate.getForObject(url, Usuario.class);
        return usuario;
    }

}
