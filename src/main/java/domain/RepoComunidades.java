package domain;
import java.util.ArrayList;
import java.util.List;

public class RepoComunidades {
    private static RepoComunidades instance = null;
    private List<Comunidad> comunidades;

    public static RepoComunidades getInstance(){
        if(instance == null){
            instance = new RepoComunidades();
        }
        return instance;
    }

    public List<Comunidad> getComunidades() {
        return comunidades;
    }
}
