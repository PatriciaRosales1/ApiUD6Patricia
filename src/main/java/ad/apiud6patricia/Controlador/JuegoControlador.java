package ad.apiud6patricia.Controlador;

import ad.apiud6patricia.Modelo.Juego;
import ad.apiud6patricia.Repositorio.JuegoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin //para que se pueda utilizar la api en modo local
@RestController //porque va a ser una api
@RequestMapping("/api/juego") //para que apunte a una ruta concreta
public class JuegoControlador {
    @Autowired
    private JuegoRepositorio juegoRepositorio;

    //Método para ver todos los juegos
    @GetMapping
    List<Juego> obtenerJuegos() {
        return juegoRepositorio.findAll();
    }

    //Obtener juego por ID
    @GetMapping("/{id}")
    Juego obtenerJuegoId(@PathVariable Long id) {
        Optional<Juego> resultado = juegoRepositorio.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("Juego no encontrado."));
    }

    //Crear un juego
    @PostMapping
    Juego crearJuego(@RequestBody Juego juego) {
        return juegoRepositorio.save(juego);
    }

    //Actualizar un juego
    @PutMapping("/{id}")
    Juego actualizarJuego(@PathVariable Long id, @RequestBody Juego juego) {
        return juegoRepositorio.findById(id).map(
                juegoTemp -> {
                    juegoTemp.setNombre((juego.getNombre() != null) ? juego.getNombre() : juegoTemp.getNombre()); //para que compruebe si el campo está vacío;
                    return juegoRepositorio.save(juegoTemp);
                }
        ).orElseThrow(() -> new RuntimeException("Juego no encontrado."));
    }

    //Eliminar un juego
    @DeleteMapping("/{id}")
    void eliminarJuego(@PathVariable Long id) {
        juegoRepositorio.deleteById(id);
    }

    //Ordenar los juegos
    @GetMapping("/api/juegosordenados")
    public List<Juego> mostrarJuegosOrdenados() {
        return juegoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "nombre"));
    }
}
