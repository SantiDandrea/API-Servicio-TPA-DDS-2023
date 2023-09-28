package domain.sugerenciasFusion;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.comunidades.RepoComunidades;
import domain.establecimientos.Establecimiento;
import domain.servicios.Servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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

    private static Sugeridor instance = null;

    public static Sugeridor getInstance(){
        if(instance == null){
            instance = new Sugeridor();
        }
        return instance;
    }
    public List<Sugerencia> sugerirFusiones (){
        List<Comunidad> comunidades = RepoComunidades.getInstance().getComunidades();
        System.out.println("\n Cantidad de comunidades :\n" + comunidades.size());
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
        System.out.println("\n Cantidad de sugerencias :" + sugerencias.size());
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
        //junto la lista de establecimientos de las dos comunidades sin repetidos
        HashSet<Establecimiento> establecimientosTotales = new HashSet<>();
        establecimientosTotales.addAll(comunidad1.getEstablecimientosObservados());
        establecimientosTotales.addAll(comunidad2.getEstablecimientosObservados());
        double establecimientosEnComun = comunidad1.getEstablecimientosObservados().stream().filter(s -> comunidad2.getEstablecimientosObservados().contains(s)).count();

        return establecimientosEnComun / establecimientosTotales.size() >= coincidenciaDeEstablecimientos;
    }

    public boolean coincidenEnServicios(Comunidad comunidad1, Comunidad comunidad2) {
        HashSet<Servicio> serviciosTotales = new HashSet<>();
        serviciosTotales.addAll(comunidad1.getServiciosEstandar());
        serviciosTotales.addAll(comunidad2.getServiciosEstandar());
        double serviciosEnComun = comunidad1.getServiciosEstandar().stream().filter(s -> comunidad2.getServiciosEstandar().contains(s)).count();

        return serviciosEnComun / serviciosTotales.size() >= coincidenciaDeServicios;
    }

    public boolean tienenMismoGradoConfianza(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.getGradoDeConfianza().equals(comunidad2.getGradoDeConfianza());
    }

    private boolean tienenUsuariosEnComun(Comunidad comunidad1, Comunidad comunidad2) {
        HashSet<Miembro> usuariosTotales = new HashSet<>();
        usuariosTotales.addAll(comunidad1.getMiembros());
        usuariosTotales.addAll(comunidad2.getMiembros());
        double usuariosEnComun = comunidad1.getMiembros().stream().filter(comunidad2.getMiembros()::contains).count();

        return usuariosEnComun / usuariosTotales.size() >= coincidenciaDeUsuarios;
    }

}
