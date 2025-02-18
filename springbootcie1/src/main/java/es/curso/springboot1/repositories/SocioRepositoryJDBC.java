package es.curso.springboot1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.curso.springboot1.negocio.Socio;

@Repository
@Qualifier("jdbc")
public class SocioRepositoryJDBC  implements SocioRepository{

    @Autowired
    private JdbcTemplate plantilla;

    @Override
    public void borrarSocio(String nombre) {
        
        plantilla.update("delete from socios where nombre=?",nombre);
    }
    @Override
    public void insertarSocio(Socio socio) { 
        plantilla.update("insert into socios values (?,?,?)",
        socio.getNombre(),socio.getApellidos(),socio.getEdad());
    }
    @Override
    public List<Socio> buscarTodos() {
        return plantilla.query("select * from socios", new SocioRowMapper());
    }

    @Override
    public Optional<Socio> buscarUno(String nombre) {
      
     return null;
    }

    @Override
    public List<Socio> buscarTodosOrdenados(String orden) {
       
      
        return null;
    
    }
}
