package com.legado.grupo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legado.grupo.dom.Miembro;
import com.legado.grupo.dom.Usuario;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.web.client.RestTemplate;

public class GrupoRestClient {

    private static String URL_LEGADO_USUARIOS = "172.16.147.108:9091";
    private static String URL_LEGADO_GRUPOS = "localhost:9092";

    /*
    Obtiene un usuario de un microservicio externo
     */
    public static Usuario get_usuario(int idUsuario) throws MalformedURLException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://" + URL_LEGADO_USUARIOS + "/get_usuario?id_usuario=" + idUsuario;
        Usuario[] usuarios = mapper.readValue(new URL(url), Usuario[].class);
        return usuarios[0];
    }
}
