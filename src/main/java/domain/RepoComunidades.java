package domain;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class RepoComunidades {
    private static RepoComunidades instance = null;
    private List<Comunidad> comunidades = new ArrayList<>();

    public static RepoComunidades getInstance(){
        if(instance == null){
            instance = new RepoComunidades();
        }
        return instance;
    }

    public List<Comunidad> getComunidades() {
        return comunidades;
    }

    public void agregarComunidad(Comunidad comu) {
        comunidades.add(comu);
    }
}
