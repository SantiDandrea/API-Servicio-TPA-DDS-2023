package domain;

import Utils.BDUtils;
import domain.comunidades.Comunidad;
import domain.comunidades.Incidente;
import domain.comunidades.Miembro;
import domain.establecimientos.Establecimiento;
import domain.servicios.Servicio;
import domain.sugerenciasFusion.GradoDeConfianza;
import domain.sugerenciasFusion.Sugerencia;
import domain.sugerenciasFusion.Sugeridor;
import handlers.GetSugerenciasHandler;
import handlers.PostAceptacionSugerencia;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

import static io.javalin.apibuilder.ApiBuilder.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    /*EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);

    List<Servicio> servicios = em.createQuery("FROM Servicio", Servicio.class).getResultList();
    servicios.forEach(s->System.out.println(s.getNombre()));
    List<Establecimiento> establecimientos = em.createQuery("FROM Establecimiento ", Establecimiento.class).getResultList();
    establecimientos.forEach(e->System.out.println(e.getId()));
    List<Miembro> miembros = em.createQuery("FROM Miembro ", Miembro.class).getResultList();
    miembros.forEach(m->em.remove(m));
    em.flush();
    //List<Comunidad> comunidades = em.createQuery("FROM Comunidad ", Comunidad.class).getResultList();
    List<Comunidad> comunidades = crearInstancias(establecimientos, servicios, em);
    comunidades.forEach(c-> em.merge(c));
    em.flush();
    Sugeridor sugeridor = new Sugeridor();
    List<Sugerencia> sugerencias = sugeridor.sugerirFusiones();
    System.out.println("\n Cantidad de sugerencias: \n"+sugerencias.size());
    sugerencias.forEach(s->em.persist(s));
    BDUtils.commit(em);
    em.close();
    */
    Javalin app = Javalin.create(config -> {
      config.plugins.register(new OpenApiPlugin(new OpenApiConfiguration()));
      config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
    }).start(7002);

    app.get("/api/sugerencias", new GetSugerenciasHandler());
    app.post("/api/sugerencias/{id}", new PostAceptacionSugerencia());


    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Programa la tarea para que se ejecute cada 7 dias
    scheduler.scheduleAtFixedRate(new Sugerir(), 0, 7, TimeUnit.DAYS);

  }

  private static List<Comunidad> crearInstancias(List<Establecimiento> est, List<Servicio> servicios, EntityManager em){

    Miembro miembro1 = new Miembro("Blue", "Label", "blue@label");
    Miembro miembro2 = new Miembro("Azul","Lebal", "azul@lebal");
    Miembro miembro3 = new Miembro("Rodolfo", "Gonzalez","rodo@gmail.com");
    Miembro miembro4 = new Miembro("Chef", "Curry", "sthep@hotmail.com");
    Miembro miembro5 = new Miembro("Lucho", "ohcul", "lucho@gmail");
    Miembro miembro6 = new Miembro("Lionel","Mezzi", "lio@gmail");
    em.merge(miembro1);
    em.merge(miembro2);
    em.merge(miembro3);
    em.merge(miembro4);
    em.merge(miembro5);
    em.merge(miembro6);


    // Comunidad 1
    List<Establecimiento> establecimientosObservados1 = List.of(est.get(0), est.get(1));
    List<Servicio> serviciosEstandar1 = List.of(servicios.get(0));
    List<Miembro> miembros1 = List.of(miembro1, miembro2, miembro3, miembro4, miembro5);

    //Comunidad 2
    List<Establecimiento> establecimientosObservados2 = List.of(est.get(0), est.get(1));
    List<Servicio> serviciosEstandar2 = List.of(servicios.get(0));
    List<Miembro> miembros2 = List.of(miembro1, miembro2, miembro5);

    //Comunidad 3
    List<Establecimiento> establecimientosObservados3 = List.of(est.get(3));
    List<Servicio> serviciosEstandar3 = List.of(servicios.get(1));
    List<Miembro> miembros3 = List.of(miembro1, miembro3, miembro4, miembro6);

    //Comunidad 4
    List<Establecimiento> establecimientosObservados4 = List.of(est.get(3));
    List<Servicio> serviciosEstandar4 = List.of(servicios.get(1));
    List<Miembro> miembros4 = List.of(miembro1, miembro3, miembro6);

    Incidente inc1 = new Incidente();
    Incidente inc2 = new Incidente();
    Incidente inc3 = new Incidente();
    Incidente inc4 = new Incidente();

    List<Incidente> incidentes1 = List.of(inc1,inc2);
    List<Incidente> incidentes2 = List.of(inc3,inc4);

    Comunidad comunidad1 = new Comunidad("com1",establecimientosObservados1, serviciosEstandar1, GradoDeConfianza.CON_RESERVAS, miembros1, incidentes1);
    Comunidad comunidad2 = new Comunidad("com2",establecimientosObservados2, serviciosEstandar2, GradoDeConfianza.CON_RESERVAS, miembros2, incidentes2);
    Comunidad comunidad3 = new Comunidad("com3",establecimientosObservados3, serviciosEstandar3, GradoDeConfianza.CON_RESERVAS, miembros3, incidentes1);
    Comunidad comunidad4 = new Comunidad("com4",establecimientosObservados4, serviciosEstandar4, GradoDeConfianza.CON_RESERVAS, miembros4, incidentes2);

    return List.of(comunidad1, comunidad2, comunidad3, comunidad4);
  }

  static class Sugerir implements Runnable {

    @Override
    public void run() {
      EntityManager em = BDUtils.getEntityManager();
      List<Sugerencia> sugerencias = Sugeridor.getInstance().sugerirFusiones();
      BDUtils.comenzarTransaccion(em);
      sugerencias.forEach(s->em.persist(s));
      BDUtils.commit(em);
      em.close();
    }
  }

  }

