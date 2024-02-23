package ad.apiud6patricia.Repositorio;

import ad.apiud6patricia.Modelo.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntuacionRepositorio extends JpaRepository<Puntuacion, Long> {
    List<Puntuacion> findByJuegoId(Long idJuego);
}

