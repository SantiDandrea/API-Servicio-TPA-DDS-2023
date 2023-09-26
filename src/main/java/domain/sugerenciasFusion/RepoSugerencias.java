package domain.sugerenciasFusion;

import Utils.BDUtils;
import domain.comunidades.Comunidad;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepoSugerencias {
    private EntityManager em = BDUtils.getEntityManager();
    private static RepoSugerencias instance = null;

    public static RepoSugerencias getInstance(){
        if(instance == null){
            instance = new RepoSugerencias();
        }
        return instance;
    }

    public List<Sugerencia> getSugerencias() {
        return em
            .createQuery("from Sugerencia ")
            .getResultList();
    }

    public boolean existeSugerencia(Comunidad comunidad1 , Comunidad comunidad2, LocalDate fechaActual){
        return this.getSugerencias().stream().anyMatch(s ->
                (s.getComunidad1().equals(comunidad1) && s.getComunidad2().equals(comunidad2) ||
                s.getComunidad1().equals(comunidad2) && s.getComunidad2().equals(comunidad1))
                        && s.getFecha().isAfter(fechaActual.minusMonths(6)));
    }
}
