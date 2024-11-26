package org.example.modelos;

    public class Vuelo {
        public String codigoVuelo;
        String origen;
        String destino;
        public String fecha;
        public int asientosDisponibles;
        int asientosTotales;
        String estado; // "A tiempo", "Retrasado", "Cancelado"

        public Vuelo(String codigoVuelo, String origen, String destino, String fecha, int asientosTotales) {
            this.codigoVuelo = codigoVuelo;
            this.origen = origen;
            this.destino = destino;
            this.fecha = fecha;
            this.asientosTotales = asientosTotales;
            this.asientosDisponibles = asientosTotales;
            this.estado = "A tiempo";
        }

        public void actualizarEstado(String nuevoEstado) {
            this.estado = nuevoEstado;
        }

        public boolean reservarAsiento() {
            if (asientosDisponibles > 0) {
                asientosDisponibles--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Vuelo{" +
                    "codigoVuelo='" + codigoVuelo + '\'' +
                    ", origen='" + origen + '\'' +
                    ", destino='" + destino + '\'' +
                    ", fecha='" + fecha + '\'' +
                    ", asientosDisponibles=" + asientosDisponibles +
                    ", estado='" + estado + '\'' +
                    '}';
        }
    }
