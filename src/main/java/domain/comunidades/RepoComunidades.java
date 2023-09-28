package domain.comunidades;
import Utils.BDUtils;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Setter
public class RepoComunidades {
    private EntityManager em = BDUtils.getEntityManager();
    private static RepoComunidades instance = null;
    private List<Comunidad> comunidades = new ArrayList<>();

    public static RepoComunidades getInstance(){
        if(instance == null){
            instance = new RepoComunidades();
        }
        return instance;
    }
    @SuppressWarnings("unchecked")
    public List<Comunidad> getComunidades() {
        System.out.println("\n\nhola2\n\n");
        return em
            .createQuery("from Comunidad")
            .getResultList();
    }

    public void agregarComunidad(Comunidad comu) {
        comunidades.add(comu);
    }
}
