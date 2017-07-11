
package com.legado.grupo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AsignaturaDAO {
    @Autowired
    private DataSource dataSource;
    
    public void ingresar() throws Exception{

    }
}
