package es.curso.springboot1.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import es.curso.springboot1.negocio.Socio;
import es.curso.springboot1.repositories.SocioRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class SocioController {

    @Autowired
    @Qualifier("jdbc")
    private SocioRepository socioRepository;
    public SocioController() {
   
    } 
    @GetMapping("/borrar")
    public String borrarSocio(@RequestParam("nombre") String nombre) {
        socioRepository.borrarSocio(nombre);
        return "redirect:listasocios";
    }

    @GetMapping("/detalle")
    public String detalleSocio(@RequestParam("nombre") String nombre,Model modelo) {
       
        Optional<Socio> oSocio=socioRepository.buscarUno(nombre);

        if (oSocio.isPresent())  {

            modelo.addAttribute("socio", oSocio.get());
        }
        return "detallesocio";
    }

    @GetMapping("/editar")
    public String editarSocio(@RequestParam("nombre") String nombre,Model modelo) {
       
        Optional<Socio> oSocio =socioRepository.buscarUno(nombre);
        if (oSocio.isPresent())  {
            modelo.addAttribute("socio", oSocio.get());
        }
        return "formularioeditarsocio";
    }

    @PostMapping("/insertarsocio")
    public String insertarSocio(@ModelAttribute Socio socio) {
        socioRepository.insertarSocio(socio);
        return "redirect:listasocios";
    }


    @PostMapping("/salvarsocio")
    public String salvarSocio(@ModelAttribute Socio socio, @RequestParam String nombreAntiguo) {
        Optional<Socio> oSocio=socioRepository.buscarUno(nombreAntiguo);
        if (oSocio.isPresent()) {

            Socio socioActual=oSocio.get();
            socioActual.setNombre(socio.getNombre());
            socioActual.setApellidos(socio.getApellidos());
            socioActual.setEdad(socio.getEdad());
        }
        return "redirect:listasocios";
    }


    @GetMapping("/formulariosocio")
    public String formulariosocio() {
       
        return "formulariosocio";
    }


    @GetMapping("/listasocios")
    public String listasocios(Model modelo) {
        modelo.addAttribute("listasocios", socioRepository.buscarTodos());
        return "listasocios";
    }
    
    @GetMapping(value="/listasocios", params="orden")
    public String listasocios(Model modelo,@RequestParam String orden) {

        List<Socio> listaOrdenada= socioRepository.buscarTodosOrdenados(orden);
        modelo.addAttribute("listasocios", listaOrdenada);
        System.out.println(orden);
        return "listasocios";
    }
}