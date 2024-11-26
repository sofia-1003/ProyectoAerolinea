package org.example.modelos;

public class Reserva {
    public String idReserva;
    public String nombrePasajero;
    public Vuelo vuelo;
    public String serviciosAdicionales; // Ejemplo: "Equipaje extra, comida especial"

    public Reserva(String idReserva, String nombrePasajero, Vuelo vuelo, String serviciosAdicionales) {
        this.idReserva = idReserva;
        this.nombrePasajero = nombrePasajero;
        this.vuelo = vuelo;
        this.serviciosAdicionales = serviciosAdicionales;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva='" + idReserva + '\'' +
                ", nombrePasajero='" + nombrePasajero + '\'' +
                ", vuelo=" + vuelo +
                ", serviciosAdicionales='" + serviciosAdicionales + '\'' +
                '}';
    }
}

