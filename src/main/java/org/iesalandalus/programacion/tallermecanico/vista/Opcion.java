package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Opcion {
    INSERTAR_CLIENTE(11, "Insertar Cliente"),
    BUSCAR_CLIENTE(12, "Buscar Cliente"),
    BORRAR_CLIENTE(13, "Borrar Cliente"),
    LISTAR_CLIENTES(14, "Borrar Cliente"),
    MODIFICAR_CLIENTE(15, "Modificar Cliente"),
    INSERTAR_VEHICULO(21, "Insertar Vehiculo"),
    BUSCAR_VEHICULO(22, "Bucar Vehiculo"),
    BORRAR_VEHICULO(23, "Borrar Vehiculo"),
    LISTAR_VEHICULOS(24, "Listar Vehiculo"),
    INSERTAR_REVISION(31, "Insertar Revision"),
    BUSCAR_REVISION(32, "Buscar Revision"),
    BORRAR_REVISION(33, "Borrar Revision"),
    LISTAR_REVISIONES(34, "Listar Revision"),
    LISTAR_REVISIONES_CLIENTE(35, "Listar Revisiones De Un Cliente"),
    LISTAR_REVISIONES_VEHICULO(36, "Listar Revisiones De Un Vehiculo"),
    ANADIR_HORAS_REVISION(37, "Añadir Horas a una Revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(38, "Añadir Precio del Material a una Revision"),
    CERRAR_REVISION(39, "Cerrar Revision"),
    SALIR(0, "Salir");

    private final int numeroOpcion;
    private final String texto;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();

    static {
        for(Opcion opcion : values()) {
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    private Opcion(int numeroOpcion, String texto) {
        this.numeroOpcion = numeroOpcion;
        this.texto = texto;
    }

    public static boolean esValida(int numeroOpcion) {return opciones.containsKey(numeroOpcion);}

    public static Opcion get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("El número otorgado no corresponde a ninguna opción valida");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {return String.format("%d. - %s%n", numeroOpcion, texto);}
}


