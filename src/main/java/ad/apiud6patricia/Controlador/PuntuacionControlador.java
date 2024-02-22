package ad.apiud6patricia.Controlador;

import ad.apiud6patricia.Modelo.Puntuacion;
import ad.apiud6patricia.Repositorio.JuegoRepositorio;
import ad.apiud6patricia.Repositorio.PuntuacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/puntuacion")
public class PuntuacionControlador {
    @Autowired
    private PuntuacionRepositorio puntuacionRepositorio;
    @Autowired
    private JuegoRepositorio juegoRepositorio;

    //Método para ver todas las puntuaciones
    @GetMapping
    List<Puntuacion> obtenerPuntuacion() {
        return puntuacionRepositorio.findAll();
    }

    //Obtener puntuacion por ID
    @GetMapping("/{id}")
    Puntuacion obtenerPuntuacionesId(@PathVariable Long id) {
        Optional<Puntuacion> resultado = puntuacionRepositorio.findById(id);
        return resultado.orElseThrow(() -> new RuntimeException("Puntuaciones no encontradas."));
    }

    //Alta de una puntuacion
    @PostMapping("/juego/{id}")  //categoría donde se va a dar de alta
    Puntuacion crearPuntuacion(@PathVariable Long id, @RequestBody Puntuacion puntuacion) {
        //Busca la categoría por id y la añade
        Puntuacion p = juegoRepositorio.findById(id).map(
                categoria -> {
                    puntuacion.setJuego(categoria);
                    return puntuacionRepositorio.save(puntuacion);
                }
        ).orElseThrow(() -> new RuntimeException("Juego no encontrado."));
        return p;
    }

    //Actualizar una puntuacion
    @PutMapping("/{id}")
    Puntuacion actualizarPuntuacion(@PathVariable Long id, @RequestBody Puntuacion puntuacion) {
        return puntuacionRepositorio.findById(id).map(puntuacionTemp -> {
            puntuacionTemp.setNombreJugador((puntuacion.getNombreJugador() != null) ? puntuacion.getNombreJugador() : puntuacionTemp.getNombreJugador()); //para que compruebe si el campo está vacío
            puntuacionTemp.setPuntuacion(Double.parseDouble((puntuacion.getPuntuacion() != 0) ? puntuacion.getNombreJugador() : puntuacionTemp.getNombreJugador())); //para que compruebe si el campo está vacío)
                    return puntuacionRepositorio.save(puntuacionTemp);
                }
        ).orElseThrow(() -> new RuntimeException("Puntuacion no encontrada."));
    }

    //Eliminar una puntuacion
    @DeleteMapping("/{id}")
    void eliminarPuntuacion(@PathVariable Long id) {
        puntuacionRepositorio.deleteById(id);
    }
}