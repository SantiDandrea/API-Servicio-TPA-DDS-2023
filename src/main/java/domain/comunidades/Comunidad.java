package domain.comunidades;

import domain.establecimientos.Establecimiento;
import domain.sugerenciasFusion.GradoDeConfianza;
import domain.servicios.Servicio;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comunidad_id")
    private int id;
    private String nombre;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_establecimientos_observados",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "establecimiento_id")
    )
    List<Establecimiento> establecimientosObservados;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_servicios_observados",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    List<Servicio> serviciosEstandar;
    GradoDeConfianza gradoDeConfianza;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Miembro> miembros;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Administrador> administradores;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comunidades_incidentes",
        joinColumns = @JoinColumn(name = "comunidad_id"),
        inverseJoinColumns = @JoinColumn(name = "incidente_id")
    )
    List<Incidente> incidentesAbiertos;

    public Comunidad(){ }

    public Comunidad(String nombre, List<Establecimiento> establecimientosObservados, List<Servicio> serviciosEstandar, GradoDeConfianza gradoDeConfianza, List<Miembro> miembros, List<Incidente> incidentes) {
        this.nombre = nombre;
        this.establecimientosObservados = establecimientosObservados;
        this.serviciosEstandar = serviciosEstandar;
        this.gradoDeConfianza = gradoDeConfianza;
        this.miembros = miembros;
        this.incidentesAbiertos = incidentes;
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
        return miembros;
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
