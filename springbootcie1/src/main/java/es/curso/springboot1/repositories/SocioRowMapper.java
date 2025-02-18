package es.curso.springboot1.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.curso.springboot1.negocio.Socio;

public class SocioRowMapper implements RowMapper<Socio>{

    @Override
    public Socio mapRow(ResultSet rs, int posicion) throws SQLException {
        // TODO Auto-generated method stub
      return new Socio(rs.getString("nombre"),
      rs.getString("apellidos"),
      rs.getInt("edad"));

    }

}
