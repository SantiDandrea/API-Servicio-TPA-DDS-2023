package domain.sugerenciasFusion;


import domain.comunidades.Comunidad;
import domain.comunidades.Incidente;
import domain.comunidades.Miembro;
import domain.establecimientos.Establecimiento;
import domain.servicios.Servicio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Fusionador {

    public Comunidad fusionarComunidades(Sugerencia sugerencia, String nombreNuevaComunidad){
        Comunidad comunidad1 = sugerencia.getComunidad1();
        Comunidad comunidad2 = sugerencia.getComunidad2();


        List<Establecimiento> establecimientosObservados = fusionarEstablecimientos(comunidad1, comunidad2);
        List<Servicio> serviciosEstandar = fusionarServicios(comunidad1, comunidad2);
        GradoDeConfianza gradoDeConfianza = comunidad1.getGradoDeConfianza();
        List<Miembro> miembros = fusionarMiembros(comunidad1, comunidad2);
        //List<Usuario> admins = fusionarAdministradores(comunidad1, comunidad2);
        List<Incidente> incidentesAbiertos = fusionarIncidentes(comunidad1, comunidad2);

        return new Comunidad(nombreNuevaComunidad,establecimientosObservados, serviciosEstandar, gradoDeConfianza, miembros, incidentesAbiertos);
    }

    private List<Incidente> fusionarIncidentes(Comunidad comunidad1, Comunidad comunidad2) {
        HashSet<Incidente> incidentesAbiertosCopia = new HashSet<>();
        incidentesAbiertosCopia.addAll(comunidad1.getIncidentesAbiertos());
        incidentesAbiertosCopia.addAll(comunidad2.getIncidentesAbiertos());
        return new ArrayList<>(incidentesAbiertosCopia);
    }

    private List<Establecimiento> fusionarEstablecimientos(Comunidad comunidad1, Comunidad comunidad2) {
        HashSet<Establecimiento> establecimientosObservadosCopia = new HashSet<>();
        establecimientosObservadosCopia.addAll(comunidad1.getEstablecimientosObservados());
        establecimientosObservadosCopia.addAll(comunidad2.getEstablecimientosObservados());
        return new ArrayList<>(establecimientosObservadosCopia);
    }

    private List<Servicio> fusionarServicios(Comunidad comunidad1, Comunidad comunidad2){
        HashSet<Servicio> serviciosEstandarCopia = new HashSet<>();
        serviciosEstandarCopia.addAll(comunidad1.getServiciosEstandar());
        serviciosEstandarCopia.addAll(comunidad2.getServiciosEstandar());
        return new ArrayList<>(serviciosEstandarCopia);
    }

    private List<Miembro> fusionarMiembros(Comunidad comunidad1, Comunidad comunidad2){
        HashSet<Miembro> miembrosCopia = new HashSet<>();
        miembrosCopia.addAll(comunidad1.getMiembros());
        miembrosCopia.addAll(comunidad2.getMiembros());
        return new ArrayList<>(miembrosCopia);
    }
}
