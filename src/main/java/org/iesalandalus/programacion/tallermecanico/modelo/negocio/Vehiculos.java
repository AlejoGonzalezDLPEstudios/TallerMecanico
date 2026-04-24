package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {

    private List<Vehiculo> coleccionVehiculos;

    public Vehiculos() { coleccionVehiculos = new ArrayList<>(); }

    public List<Vehiculo> get() { return coleccionVehiculos; }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "El vehiculo tiene que existir, NO PUEDE SER NULO!");
        if (coleccionVehiculos.contains(vehiculo)){
            throw new TallerMecanicoExcepcion("No puedes clonar Vehiculos, verifica que no haya alguno que ya posea estos parametros y cambia los de este");
        }

        coleccionVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculos vehiculo){
        Objects.requireNonNull(vehiculo, "Para buscar un vehiculo, este debe de existir, NO PUEDE SER NULO!");
        int indice = coleccionVehiculos.indexOf(vehiculo);
        return (indice != -1 ? coleccionVehiculos.get(indice) : null);
    }

    public void borrar(Vehiculos vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "Para poder borrar un vehiculo, necesitas que exista, ESTE NO PUEDE SER NULO!");
        Vehiculo buscado = buscar(vehiculo);

        if (!coleccionVehiculos.contains(buscado)){
            throw new TallerMecanicoExcepcion("No tenemos un vehiculo que coincida con los parametros indicados, verifique e intente nuevamente.");
        }

        coleccionVehiculos.remove(buscado);
    }
}
