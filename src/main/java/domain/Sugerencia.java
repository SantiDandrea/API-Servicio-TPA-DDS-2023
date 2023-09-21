package domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Sugerencia {
    private Comunidad comunidad1;
    private Comunidad comunidad2;
    private LocalDate fecha;


    public Sugerencia(Comunidad comunidad1, Comunidad comunidad2, LocalDate fecha) {
        this.comunidad1 = comunidad1;
        this.comunidad2 = comunidad2;
        this.fecha = fecha;
    }
}
