package ad.apiud6patricia.Modelo;

import jakarta.persistence.*;

@Entity(name = "puntuaciones")
public class Puntuacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String nombreJugador;
    private double puntuacion;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;

    public Puntuacion() {
    }

    public Puntuacion(long id, String nombreJugador, double puntuacion, Juego juego) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.puntuacion = puntuacion;
        this.juego = juego;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}
