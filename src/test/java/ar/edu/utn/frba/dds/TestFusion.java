package ar.edu.utn.frba.dds;

import domain.Comunidad;
import domain.Fusionador;
import domain.Sugerencia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TestFusion extends InstanciasIniciales{

    Fusionador fusionador = new Fusionador();
    Sugerencia sugerencia = new Sugerencia(comunidad3, comunidad4, LocalDate.now());
    @Test
    void fusionadorFusionaMiembros(){
        Comunidad comunidadFusionada = fusionador.fusionarComunidades(sugerencia);
        Assertions.assertEquals(5, comunidadFusionada.getMiembros().size());
    }

    @Test
    void fusionadorFusionaEstablecimientos(){
        Comunidad comunidadFusionada = fusionador.fusionarComunidades(sugerencia);
        Assertions.assertEquals(3, comunidadFusionada.getEstablecimientosObservados().size());
    }

    @Test
    void fusionadorFusionaServicios(){
        Comunidad comunidadFusionada = fusionador.fusionarComunidades(sugerencia);
        Assertions.assertEquals(5, comunidadFusionada.getServiciosEstandar().size());
    }
}
