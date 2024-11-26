package org.example;

import org.example.estructuras.ArbolDeVuelos;
import org.example.estructuras.ListaEnlazadaReservas;
import org.example.modelos.Reserva;
import org.example.modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArbolDeVuelos arbolDeVuelos = new ArbolDeVuelos();
        ListaEnlazadaReservas listaDeReservas = new ListaEnlazadaReservas();
        Scanner sc = new Scanner(System.in);

        // Vuelos creados con datos quemados
        Vuelo vuelo1 = new Vuelo("A001", "Bogotá", "Cali", "2024-11-25", 1);
        Vuelo vuelo2 = new Vuelo("A002", "Medellín", "Cartagena", "2024-11-26", 50);
        Vuelo vuelo3 = new Vuelo("A003", "Pereira", "San Andrés", "2024-11-30", 75);
        Vuelo vuelo4 = new Vuelo("A004", "Barranquilla", "Leticia", "2024-12-01", 80);
        Vuelo vuelo5 = new Vuelo("A005", "Manizales", "Armenia", "2024-12-08", 130);

        arbolDeVuelos.insertarVuelo(vuelo1);
        arbolDeVuelos.insertarVuelo(vuelo2);
        arbolDeVuelos.insertarVuelo(vuelo3);
        arbolDeVuelos.insertarVuelo(vuelo4);
        arbolDeVuelos.insertarVuelo(vuelo5);

        // Convertir vuelos del árbol a una lista para mostrarlos al usuario
        List<Vuelo> vuelosDisponibles = arbolDeVuelos.obtenerVuelosEnLista();

        int opcion;
        do {
            menu();
            opcion = sc.nextInt();
            sc.nextLine(); // Generar nuevo espacio

            switch (opcion) {
                case 1: // Hacer reserva
                    System.out.println("\n=== Hacer Reserva ===");
                    System.out.println("Vuelos disponibles:");
                    for (int i = 0; i < vuelosDisponibles.size(); i++) {
                        System.out.println((i + 1) + ". " + vuelosDisponibles.get(i));
                    }

                    System.out.print("\nSeleccione el número del vuelo en el que desea reservar: ");
                    int vueloSeleccionado = sc.nextInt();
                    sc.nextLine(); // Generar nuevo espacio

                    if (vueloSeleccionado > 0 && vueloSeleccionado <= vuelosDisponibles.size()) {
                        Vuelo vuelo = vuelosDisponibles.get(vueloSeleccionado - 1);

                        if (vuelo.asientosDisponibles > 0) {
                            System.out.print("Ingrese el nombre del pasajero: ");
                            String nombrePasajero = sc.nextLine();
                            System.out.print("Ingrese servicios adicionales (opcional): ");
                            String serviciosAdicionales = sc.nextLine();
                            String idReserva = "R" + (int) (Math.random() * 150); // Generar ID único
                            Reserva nuevaReserva = new Reserva(idReserva, nombrePasajero, vuelo, serviciosAdicionales);

                            listaDeReservas.agregarReserva(nuevaReserva);
                            vuelo.reservarAsiento();
                            System.out.println("Reserva creada con éxito. ID de la reserva: " + idReserva);
                        } else {
                            System.out.println("No hay asientos disponibles en este vuelo.");
                        }
                    } else {
                        System.out.println("Selección inválida.");
                    }
                    break;

                case 2: // Consultar reserva
                    System.out.println("\n=== Consultar Reserva ===");
                    System.out.print("Ingrese el ID de la reserva: ");
                    String idConsulta = sc.nextLine();
                    boolean encontrada = false;

                    for (Reserva reserva : listaDeReservas.obtenerReservas()) {
                        if (reserva.idReserva.equalsIgnoreCase(idConsulta)) {
                            System.out.println("Reserva encontrada:");
                            System.out.println(reserva);
                            encontrada = true;
                            break;
                        }
                    }

                    if (!encontrada) {
                        System.out.println("No se encontró una reserva con ese ID.");
                    }
                    break;

                case 3: // Modificar reserva
                    System.out.println("\n=== Modificar Reserva ===");
                    System.out.print("Ingrese el ID de la reserva: ");
                    String idModificar = sc.nextLine();
                    System.out.print("Ingrese el nuevo nombre del pasajero: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Ingrese los nuevos servicios adicionales: ");
                    String nuevosServicios = sc.nextLine();

                    if (listaDeReservas.modificarReserva(idModificar, nuevoNombre, nuevosServicios)) {
                        System.out.println("Reserva modificada con éxito.");
                    } else {
                        System.out.println("No se encontró una reserva con ese ID.");
                    }
                    break;

                case 4: // Cancelar reserva
                    System.out.println("\n=== Cancelar Reserva ===");
                    System.out.print("Ingrese el ID de la reserva: ");
                    String idCancelar = sc.nextLine();

                    // Recuperar y eliminar la reserva
                    Reserva reservaEliminada = listaDeReservas.obtenerYEliminarReserva(idCancelar);
                    if (reservaEliminada != null) {
                        // Liberar el asiento en el vuelo asociado
                        reservaEliminada.vuelo.asientosDisponibles++;
                        System.out.println("Reserva cancelada con éxito. Asiento liberado en el vuelo: " + reservaEliminada.vuelo.codigoVuelo);
                    } else {
                        System.out.println("No se encontró una reserva con ese ID.");
                    }
                    break;

                case 5: //Salir
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

        sc.close();
    }

    public static void menu() {
        System.out.println("""
                *** BIENVENIDO AL SISTEMA DE RESERVAS DE VUELOS ***
                1. Hacer reserva
                2. Consultar reserva
                3. Modificar reserva
                4. Cancelar reserva
                5. Salir
                Por favor escoja una opción:
                """);
    }
}
