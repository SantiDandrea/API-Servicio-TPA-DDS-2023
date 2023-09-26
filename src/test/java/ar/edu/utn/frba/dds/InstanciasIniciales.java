package ar.edu.utn.frba.dds;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.comunidades.RepoComunidades;
import domain.establecimientos.Establecimiento;
import domain.servicios.Servicio;
import domain.sugerenciasFusion.GradoDeConfianza;

import java.util.List;

public abstract class InstanciasIniciales {
    Establecimiento est1 = new Establecimiento();
    Establecimiento est2 = new Establecimiento();
    Establecimiento est3 = new Establecimiento();
    Establecimiento est4 = new Establecimiento();
    Establecimiento est5 = new Establecimiento();
    Establecimiento est6 = new Establecimiento();
    Establecimiento est7 = new Establecimiento();

    Servicio serv1 = new Servicio();
    Servicio serv2 = new Servicio();
    Servicio serv3 = new Servicio();
    Servicio serv4 = new Servicio();
    Servicio serv5 = new Servicio();
    Servicio serv6 = new Servicio();

    Miembro miembro1 = new Miembro();
    Miembro miembro2 = new Miembro();
    Miembro miembro3 = new Miembro();
    Miembro miembro4 = new Miembro();
    Miembro miembro5 = new Miembro();
    Miembro miembro6 = new Miembro();
    Miembro miembro7 = new Miembro();

    // Comunidad 1
    List<Establecimiento> establecimientosObservados1 = List.of(est1, est2, est3, est4, est5);
    List<Servicio> serviciosEstandar1 = List.of(serv1, serv2, serv3, serv4, serv5);
    List<Miembro> miembros1 = List.of(miembro1, miembro2, miembro3, miembro4, miembro5);

    //Comunidad 2
    List<Establecimiento> establecimientosObservados2 = List.of(est1, est3, est4, est5);
    List<Servicio> serviciosEstandar2 = List.of(serv1, serv2, serv3, serv4, serv5);
    List<Miembro> miembros2 = List.of(miembro1, miembro2, miembro5);

    //Comunidad 3
    List<Establecimiento> establecimientosObservados3 = List.of(est5, est6, est7);
    List<Servicio> serviciosEstandar3 = List.of(serv1, serv2, serv4, serv5);
    List<Miembro> miembros3 = List.of(miembro1, miembro3, miembro4, miembro6);

    //Comunidad 4
    List<Establecimiento> establecimientosObservados4 = List.of(est5, est6, est7);
    List<Servicio> serviciosEstandar4 = List.of(serv1, serv2, serv3, serv4, serv5);
    List<Miembro> miembros4 = List.of(miembro1, miembro2);

    // Comunidad 5
    List<Establecimiento> establecimientosObservados5 = List.of(est4, est5);
    List<Servicio> serviciosEstandar5 = List.of(serv1, serv2, serv3, serv4, serv5);
    List<Miembro> miembros5 = List.of(miembro1, miembro3, miembro5);

    // Comunidad 6
    List<Establecimiento> establecimientosObservados6 = List.of(est1, est2, est4, est5);
    List<Servicio> serviciosEstandar6 = List.of(serv1, serv2, serv4, serv5);
    List<Miembro> miembros6 = List.of(miembro2, miembro3, miembro4);

    Comunidad comunidad1 = new Comunidad(establecimientosObservados1, serviciosEstandar1, GradoDeConfianza.CON_RESERVAS, miembros1);
    Comunidad comunidad2 = new Comunidad(establecimientosObservados2, serviciosEstandar2, GradoDeConfianza.CON_RESERVAS, miembros2);
    Comunidad comunidad3 = new Comunidad(establecimientosObservados3, serviciosEstandar3, GradoDeConfianza.CON_RESERVAS, miembros3);
    Comunidad comunidad4 = new Comunidad(establecimientosObservados4, serviciosEstandar4, GradoDeConfianza.CON_RESERVAS, miembros4);
    Comunidad comunidad5 = new Comunidad(establecimientosObservados5, serviciosEstandar5, GradoDeConfianza.CON_RESERVAS, miembros5);
    Comunidad comunidad6 = new Comunidad(establecimientosObservados6, serviciosEstandar6, GradoDeConfianza.CON_RESERVAS, miembros6);


    // Agrego comunidades al repo
    RepoComunidades repo = RepoComunidades.getInstance();
    {
        repo.agregarComunidad(comunidad1);
        repo.agregarComunidad(comunidad2);
        repo.agregarComunidad(comunidad3);
        repo.agregarComunidad(comunidad4);
        repo.agregarComunidad(comunidad5);
        repo.agregarComunidad(comunidad6);
    }
}
