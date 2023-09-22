package domain;


import java.util.ArrayList;
import java.util.List;


public class Fusionador {

    Comunidad fusionarComunidades(Sugerencia sugerencia){
        Comunidad comunidad1 = sugerencia.getComunidad1();
        Comunidad comunidad2 = sugerencia.getComunidad2();

        //fusiono establecimientos
        List<Establecimiento> establecimientosObservados = new ArrayList<>();
        establecimientosObservados.addAll(comunidad1.getEstablecimientosObservados());
        establecimientosObservados.addAll(comunidad2.getEstablecimientosObservados());
        //fusiono servicios
        List<Servicio> serviciosEstandar = new ArrayList<>();
        serviciosEstandar.addAll(comunidad1.getServiciosEstandar());
        serviciosEstandar.addAll(comunidad2.getServiciosEstandar());
        //agrego grado de confianza (es el mismo en las dos comunidades
        GradoDeConfianza gradoDeConfianza = comunidad1.getGradoDeConfianza();
        //fusiono miembros
        List<Miembro> miembros = new ArrayList<>();
        miembros.addAll(comunidad1.getMiembros());
        miembros.addAll(comunidad2.getMiembros());

        return new Comunidad(establecimientosObservados, serviciosEstandar, gradoDeConfianza, miembros);
    }
}
