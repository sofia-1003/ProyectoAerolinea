package org.example.estructuras;

import org.example.modelos.NodoReserva;
import org.example.modelos.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazadaReservas {
    private NodoReserva cabeza;

    public ListaEnlazadaReservas() {
        cabeza = null;
    }

    // Agregar una reserva
    public void agregarReserva(Reserva reserva) {
        NodoReserva nuevo = new NodoReserva(reserva);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoReserva temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    // Mostrar todas las reservas
    public void mostrarReservas() {
        NodoReserva temp = cabeza;
        while (temp != null) {
            System.out.println(temp.reserva);
            temp = temp.siguiente;
        }
    }

    // Modificar una reserva
    public boolean modificarReserva(String idReserva, String nuevoNombrePasajero, String nuevosServicios) {
        NodoReserva temp = cabeza;
        while (temp != null) {
            if (temp.reserva.idReserva.equals(idReserva)) {
                temp.reserva.nombrePasajero = nuevoNombrePasajero;
                temp.reserva.serviciosAdicionales = nuevosServicios;
                return true; // Modificación exitosa
            }
            temp = temp.siguiente;
        }
        return false; // No se encontró la reserva
    }

    // Eliminar una reserva
    public boolean eliminarReserva(String idReserva) {
        if (cabeza == null) return false; // Lista vacía

        if (cabeza.reserva.idReserva.equals(idReserva)) {
            cabeza = cabeza.siguiente;
            return true; // Eliminación exitosa
        }

        NodoReserva temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.reserva.idReserva.equals(idReserva)) {
                temp.siguiente = temp.siguiente.siguiente;
                return true; // Eliminación exitosa
            }
            temp = temp.siguiente;
        }
        return false; // No se encontró la reserva
    }
    public List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        NodoReserva temp = cabeza;
        while (temp != null) {
            reservas.add(temp.reserva);
            temp = temp.siguiente;
        }
        return reservas;
    }

    // Obtener y eliminar una reserva por su ID
    public Reserva obtenerYEliminarReserva(String idReserva) {
        if (cabeza == null) return null; // Lista vacía

        // Caso especial: la reserva está en la cabeza de la lista
        if (cabeza.reserva.idReserva.equals(idReserva)) {
            Reserva reservaEliminada = cabeza.reserva;
            cabeza = cabeza.siguiente;
            return reservaEliminada;
        }

        // Buscar la reserva en la lista
        NodoReserva temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.reserva.idReserva.equals(idReserva)) {
                Reserva reservaEliminada = temp.siguiente.reserva;
                temp.siguiente = temp.siguiente.siguiente;
                return reservaEliminada;
            }
            temp = temp.siguiente;
        }

        return null; // No se encontró la reserva
    }

}



