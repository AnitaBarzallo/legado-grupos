
package com.legado.grupo.srv;

import com.legado.grupo.dao.FacultadDAO;
import com.legado.grupo.dom.Facultad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class FacultadService {
    @Autowired
    private FacultadDAO facultadDAO;
    
    public List<Facultad> listar(){
        return facultadDAO.listar();
    }
}
