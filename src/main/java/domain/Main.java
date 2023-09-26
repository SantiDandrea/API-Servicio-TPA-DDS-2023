package domain;

import Utils.BDUtils;
import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.establecimientos.Establecimiento;
import domain.servicios.Servicio;
import domain.sugerenciasFusion.GradoDeConfianza;
import domain.sugerenciasFusion.Sugerencia;
import domain.sugerenciasFusion.Sugeridor;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);

    List<Servicio> servicios = em.createQuery("FROM Servicio", Servicio.class).getResultList();
    servicios.forEach(s->System.out.println(s.getNombre()));
    List<Establecimiento> establecimientos = em.createQuery("FROM Establecimiento ", Establecimiento.class).getResultList();
    establecimientos.forEach(e->System.out.println(e.getId()));
    /*List<Miembro> miembros = em.createQuery("FROM Miembro ", Miembro.class).getResultList();
    miembros.forEach(m->em.remove(m));
    em.flush();
    //List<Comunidad> comunidades = em.createQuery("FROM Comunidad ", Comunidad.class).getResultList();
    List<Comunidad> comunidades = crearInstancias(establecimientos, servicios, em);
    comunidades.forEach(c-> em.merge(c));
    em.flush();*/
    Sugeridor sugeridor = new Sugeridor();
    List<Sugerencia> sugerencias = sugeridor.sugerirFusiones();
    System.out.println("\n Cantidad de sugerencias: \n"+sugerencias.size());
    sugerencias.forEach(s->em.persist(s));

    /*
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Programa la tarea para que se ejecute cada 7 dias
    scheduler.scheduleAtFixedRate(new Sugerir(), 0, 7, TimeUnit.DAYS);
*/
    BDUtils.commit(em);
    em.close();
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

    Comunidad comunidad1 = new Comunidad(establecimientosObservados1, serviciosEstandar1, GradoDeConfianza.CON_RESERVAS, miembros1);
    Comunidad comunidad2 = new Comunidad(establecimientosObservados2, serviciosEstandar2, GradoDeConfianza.CON_RESERVAS, miembros2);
    Comunidad comunidad3 = new Comunidad(establecimientosObservados3, serviciosEstandar3, GradoDeConfianza.CON_RESERVAS, miembros3);
    Comunidad comunidad4 = new Comunidad(establecimientosObservados4, serviciosEstandar4, GradoDeConfianza.CON_RESERVAS, miembros4);

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

