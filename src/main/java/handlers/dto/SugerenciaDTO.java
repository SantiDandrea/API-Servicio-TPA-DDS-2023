package handlers.dto;

import domain.comunidades.Comunidad;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
public class SugerenciaDTO {
  private int id;
  private ComunidadDTO comunidad1;
  private ComunidadDTO comunidad2;
  private LocalDate fecha;

  public SugerenciaDTO(int id, ComunidadDTO comunidad1, ComunidadDTO comunidad2, LocalDate fecha) {
    this.id = id;
    this.comunidad1 = comunidad1;
    this.comunidad2 = comunidad2;
    this.fecha = fecha;
  }
}
