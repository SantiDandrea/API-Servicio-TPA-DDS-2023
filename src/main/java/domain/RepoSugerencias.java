package domain;

import java.time.LocalDate;
import java.util.List;

public class RepoSugerencias {

    private static RepoSugerencias instance = null;
    private List<Sugerencia> sugerencias;

    public static RepoSugerencias getInstance(){
        if(instance == null){
            instance = new RepoSugerencias();
        }
        return instance;
    }

    public List<Sugerencia> getSugerencias() {
        return sugerencias;
    }

    public boolean existeSugerencia(Comunidad comunidad1 , Comunidad comunidad2, LocalDate fechaActual){
        return sugerencias.stream().anyMatch(s ->
                (s.getComunidad1().equals(comunidad1) && s.getComunidad2().equals(comunidad2) ||
                s.getComunidad1().equals(comunidad2) && s.getComunidad2().equals(comunidad1))
                        && s.getFecha().isAfter(fechaActual.minusMonths(6)));
    }
}
