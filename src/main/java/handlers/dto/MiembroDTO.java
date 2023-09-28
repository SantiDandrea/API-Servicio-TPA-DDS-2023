package handlers.dto;

import lombok.Getter;

@Getter
public class MiembroDTO {
  private int id;
  private String nombre;
  private String apellido;
  private String correoElectronico;

  public MiembroDTO(int id, String nombre, String apellido, String correoElectronico) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
  }
}
