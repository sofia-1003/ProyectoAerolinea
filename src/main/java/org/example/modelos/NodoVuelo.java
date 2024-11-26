package org.example.modelos;

public class NodoVuelo {
    public Vuelo vuelo;
    public NodoVuelo izquierda, derecha;

    public NodoVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        izquierda = null;
        derecha = null;
    }
}



