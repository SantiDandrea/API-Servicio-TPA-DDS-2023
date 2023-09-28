package domain.comunidades;

import domain.establecimientos.Establecimiento;
import domain.sugerenciasFusion.GradoDeConfianza;
import domain.servicios.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "comunidad")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comunidad_id")
    private int id;
    private String nombre;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "comunidades_establecimientos_observados",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "establecimiento_id")
    )
    List<Establecimiento> establecimientosObservados;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "comunidades_servicios_observados",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    List<Servicio> serviciosEstandar;
    @Enumerated(value = EnumType.STRING)
    GradoDeConfianza gradoDeConfianza;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "comunidades_afectados",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> afectados;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "comunidades_observadores",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> observadores;
    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Administrador> administradores;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "comunidades_incidentes",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "incidente_id")
    )
    private List<Incidente> incidentesAbiertos;

    public Comunidad(){ }

    public Comunidad(String nombre, List<Miembro> afectados, List<Miembro> observadores, List<Administrador> admins,
                     List<Establecimiento> establecimientosObservados, List<Servicio> serviciosEstandar,
                     GradoDeConfianza gradoDeConfianza, List<Incidente> incidentes) {
        this.nombre = nombre;
        this.establecimientosObservados = establecimientosObservados;
        this.serviciosEstandar = serviciosEstandar;
        this.gradoDeConfianza = gradoDeConfianza;
        this.afectados = afectados;
        this.observadores = observadores;
        this.incidentesAbiertos = incidentes;
        this.administradores = admins;
    }

    public List<Servicio> getServiciosEstandar() {
        return serviciosEstandar;
    }

    public List<Establecimiento> getEstablecimientosObservados() {
        return establecimientosObservados;
    }

    public GradoDeConfianza getGradoDeConfianza() {
        return gradoDeConfianza;
    }

    public List<Miembro> getMiembros() {
        HashSet<Miembro> miembros = new HashSet<>(this.afectados);
        miembros.addAll(this.observadores);
        return miembros.stream().collect(Collectors.toList());
    }
    public void agregarIncidente(Incidente incidente){
        incidentesAbiertos.add(incidente);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comunidad comunidad = (Comunidad) o;
        return id == comunidad.id && Objects.equals(nombre, comunidad.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
