package domain.comunidades;

import domain.servicios.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Entity
public class Incidente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
  private Servicio servicio;

  /*@ManyToOne TODO:implementar agrupacion
  @JoinColumn(name = "agrupacion_id", referencedColumnName = "id")
  private Agrupacion agrupacion;*/
  private String observacion;

  @ManyToMany(mappedBy = "incidentesAbiertos")
  private List<Comunidad> comunidades;

  private LocalDateTime horarioApertura;
  @Getter
  private LocalDateTime horarioCierre;
  private EstadoIncidente estadoIncidente;
  public Incidente() {  }

  public Duration obtenerTiempoCierre(){
    return Duration.between(horarioApertura, horarioCierre);
  }

  public boolean cerradoUltimaSemana(){
    return Duration.between(this.horarioCierre , LocalDateTime.now()).minusDays(7).isNegative();
  }

  @Override
  public String toString() {
    return "Incidente en el servicio: "+servicio.nombre+ ", abierto:"+
        DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss").format(horarioApertura);
  }
}
