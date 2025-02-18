package es.curso.springboot1.repositories;

import java.util.List;
import java.util.Optional;

import es.curso.springboot1.negocio.Socio;

public interface SocioRepository {

    void borrarSocio(String nombre);

    void insertarSocio(Socio socio);

    List<Socio> buscarTodos();

    Optional<Socio> buscarUno(String nombre);

    List<Socio> buscarTodosOrdenados(String orden);

}