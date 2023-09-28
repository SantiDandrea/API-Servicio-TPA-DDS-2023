package domain.comunidades;

import domain.clasesTecnicas.Usuario;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "miembro")
public class Miembro extends Usuario {

  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "miembro_id")
  private int id;

  @Transient
  private static int cantidadMiembros=100;
  private String nombre;
  private String apellido;
  private String correoElectronico;

  public Miembro(String nombre, String apellido, String correoElectronico) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correoElectronico = correoElectronico;
    this.id = ++cantidadMiembros;
  }

  public Miembro() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Miembro miembro = (Miembro) o;
    return id == miembro.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
