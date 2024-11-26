package org.example.estructuras;

import org.example.modelos.NodoVuelo;
import org.example.modelos.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class ArbolDeVuelos {
    private NodoVuelo raiz;

    public ArbolDeVuelos() {
        raiz = null;
    }

    // Insertar un vuelo en el árbol
    public void insertarVuelo(Vuelo vuelo) {
        raiz = insertarRecursivo(raiz, vuelo);
    }

    private NodoVuelo insertarRecursivo(NodoVuelo nodo, Vuelo vuelo) {
        if (nodo == null) {
            return new NodoVuelo(vuelo);
        }
        if (vuelo.fecha.compareTo(nodo.vuelo.fecha) < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, vuelo);
        } else if (vuelo.fecha.compareTo(nodo.vuelo.fecha) > 0) {
            nodo.derecha = insertarRecursivo(nodo.derecha, vuelo);
        }
        return nodo;
    }

    // Buscar un vuelo por su código
    public Vuelo buscarVuelo(String codigoVuelo) {
        return buscarRecursivo(raiz, codigoVuelo);
    }

    private Vuelo buscarRecursivo(NodoVuelo nodo, String codigoVuelo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.vuelo.codigoVuelo.equals(codigoVuelo)) {
            return nodo.vuelo;
        }
        return codigoVuelo.compareTo(nodo.vuelo.codigoVuelo) < 0
                ? buscarRecursivo(nodo.izquierda, codigoVuelo)
                : buscarRecursivo(nodo.derecha, codigoVuelo);
    }

    // Mostrar todos los vuelos en orden
    public void mostrarVuelos() {
        mostrarRecursivo(raiz);
    }

    private void mostrarRecursivo(NodoVuelo nodo) {
        if (nodo != null) {
            mostrarRecursivo(nodo.izquierda);
            System.out.println(nodo.vuelo);
            mostrarRecursivo(nodo.derecha);
        }
    }

    // Obtener los vuelos como una lista en orden ascendente
    public List<Vuelo> obtenerVuelosEnLista() {
        List<Vuelo> vuelos = new ArrayList<>();
        llenarListaRecursiva(raiz, vuelos);
        return vuelos;
    }

    private void llenarListaRecursiva(NodoVuelo nodo, List<Vuelo> vuelos) {
        if (nodo != null) {
            llenarListaRecursiva(nodo.izquierda, vuelos);
            vuelos.add(nodo.vuelo);
            llenarListaRecursiva(nodo.derecha, vuelos);
        }
    }
}
