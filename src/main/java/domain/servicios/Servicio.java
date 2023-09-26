package domain.servicios;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "servicio")
public class Servicio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "servicio_id")
  private int id;
  public String nombre;
  public String getNombre() {
    return nombre;
  }
}
