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
  @Column(name = "incidente_id")
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
  private Servicio servicio;

  /*@ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "agrupacion_id", referencedColumnName = "id")
  private Agrupacion agrupacion;*/
  private String observacion;

  /*@ManyToMany(mappedBy = "incidentesAbiertos", cascade = CascadeType.ALL)
  private List<Comunidad> comunidades;*/

  private LocalDateTime horarioApertura;
  private LocalDateTime horarioCierre;
  private EstadoIncidente estadoIncidente;

  public Incidente(Servicio servicio, String observacion, LocalDateTime horarioApertura,
                   LocalDateTime horarioCierre, EstadoIncidente estadoIncidente) {
    this.servicio = servicio;
    this.observacion = observacion;
    this.horarioApertura = horarioApertura;
    this.horarioCierre = horarioCierre;
    this.estadoIncidente = estadoIncidente;
  }

  public Incidente() {

  }

  public Duration obtenerTiempoCierre(){
    return Duration.between(horarioApertura, horarioCierre);
  }

  public boolean tieneEstado(EstadoIncidente estado){
    return this.estadoIncidente.equals(estado);
  }

  public boolean cerradoUltimaSemana(){
    return Duration.between(this.getHorarioCierre() , LocalDateTime.now()).minusDays(7).isNegative();
  }

  @Override
  public String toString() {
    return "Incidente en el servicio: "+servicio.nombre+ ", abierto:"+
        DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss").format(horarioApertura);
  }
}
