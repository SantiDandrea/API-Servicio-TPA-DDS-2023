package domain;

import java.util.List;

public class Comunidad {
    List<Establecimiento> establecimientosObservados;
    List<Servicio> serviciosEstandar;
    GradoDeConfianza gradoDeConfianza;
    List<Miembro> miembros;
//    List<Usuario> administradores;
//    List<Incidente> incidentesAbiertos;


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
}
