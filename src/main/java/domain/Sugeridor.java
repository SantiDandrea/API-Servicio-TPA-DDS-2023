package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sugeridor {

    /*
     * Coincidan en más del 75% de los establecimientos observados
     * Coincidan en más del 75% de los servicios estándares observados
     * Tengan un mismo grado de confianza
     * Tengan un 5% de usuarios en común
     */
    double coincidenciaDeEstablecimientos = 0.75;
    double coincidenciaDeServicios = 0.75;
    double coincidenciaDeUsuarios = 0.05;


    public List<Sugerencia> sugerirFusiones (){
        List<Comunidad> comunidades = RepoComunidades.getInstance().getComunidades();
        List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
        while(comunidades.size() > 1){
            Comunidad comunidad = comunidades.remove(0);
            //compararComunidades(comunidad, comunidades, sugerencias);
            Optional<Comunidad> optionalComunidad = comunidades.stream()
                    .filter(c -> cumpleCoincidenciasFusion(comunidad, c)
                            && !existeSugerenciaMenosSeisMeses(comunidad, c))
                    .findAny();
            if(optionalComunidad.isPresent()){
                Sugerencia sugerencia = new Sugerencia(comunidad, optionalComunidad.get(), LocalDate.now());
                sugerencias.add(sugerencia);
                comunidades.remove(optionalComunidad);
            }
        }
        return sugerencias;
    }

    private boolean existeSugerenciaMenosSeisMeses(Comunidad comunidad1, Comunidad comunidad2){
        return RepoSugerencias.getInstance().existeSugerencia(comunidad1 , comunidad2, LocalDate.now());
    }

    public boolean cumpleCoincidenciasFusion (Comunidad comunidad1 , Comunidad comunidad2)  {

        return coincidenEnEstablecimientos(comunidad1,comunidad2) &&
                coincidenEnServicios(comunidad1,comunidad2) &&
                tienenMismoGradoConfianza(comunidad1,comunidad2) &&
                tienenUsuariosEnComun(comunidad1,comunidad2);
    }

    private boolean coincidenEnEstablecimientos(Comunidad comunidad1, Comunidad comunidad2) {
        double establecimientosTotales = comunidad1.getEstablecimientosObservados().size() + comunidad2.getEstablecimientosObservados().size();
        double establecimientosEnComun = comunidad1.getEstablecimientosObservados().stream().filter(s -> comunidad2.getEstablecimientosObservados().contains(s)).count();

        return establecimientosEnComun / establecimientosTotales >= coincidenciaDeEstablecimientos;
    }

    public boolean coincidenEnServicios(Comunidad comunidad1, Comunidad comunidad2) {
        double serviciosTotales = comunidad1.getServiciosEstandar().size() + comunidad2.getServiciosEstandar().size();
        double serviciosEnComun = comunidad1.getServiciosEstandar().stream().filter(s -> comunidad2.getServiciosEstandar().contains(s)).count();

        return serviciosEnComun / serviciosTotales >= coincidenciaDeServicios;
    }

    public boolean tienenMismoGradoConfianza(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.getGradoDeConfianza().equals(comunidad2.getGradoDeConfianza());
    }

    private boolean tienenUsuariosEnComun(Comunidad comunidad1, Comunidad comunidad2) {
        double usuariosTotales = comunidad1.getMiembros().size() + comunidad2.getMiembros().size();
        double usuariosEnComun = comunidad1.getMiembros().stream().filter(comunidad2.getMiembros()::contains).count();

        return usuariosEnComun / usuariosTotales >= coincidenciaDeUsuarios;
    }

}
