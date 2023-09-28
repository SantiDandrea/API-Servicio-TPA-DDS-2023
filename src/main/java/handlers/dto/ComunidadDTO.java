package handlers.dto;

import domain.comunidades.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
@Setter
@Getter
public class ComunidadDTO {
  private int id;
  private String nombre;
  private List<MiembroDTO> afectados;
  private List<MiembroDTO> observadores;

  public ComunidadDTO(){}

  public ComunidadDTO(int id, String nombre, List<MiembroDTO> afectados, List<MiembroDTO> observadores) {
    this.id = id;
    this.nombre = nombre;
    this.afectados = afectados;
    this.observadores = observadores;
  }
}
