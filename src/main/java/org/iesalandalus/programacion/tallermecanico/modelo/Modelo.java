package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar() { System.out.println("Modelo terminado");}

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "Es imposible que exista un cliente nulo, mucho menos poder insertar un cliente asi");
        Cliente cliente1 = new Cliente(cliente);
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Si no existe una revision no puedes insertarla");
        Revision revision1 = new Revision(clientes.buscar(revision.getCliente()), vehiculos.buscar(revision.getVehiculo()), revision.getFechaInicio());
        revisiones.insertar(revision1);
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "NECESITAS QUE HAYA UN CLIENTE PARA PODER BUSCARLO");
        return new Cliente(clientes.buscar(cliente));
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "SI NO EXISTE UN VEHICULO NO PUEDES BUSCARLO!!");
        return Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe ningun vehiculo como el que se especifica");
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "TIENE QUE EXISTIR UNA REVISION ANTES DE SI QUIERA BUSCARLA!");
        return new Revision(Objects.requireNonNull(revisiones.buscar(revision), "No existe ninguna revision que coincida con la especificada."));

    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "Si no existe un cliente mucho menos se va a poder modificar...");
        return clientes.modificar(cliente,nombre,telefono);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Tiene que existir una revision para poder añadirle horas!");
        return revisiones.anadirHoras(revision, horas);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Si no existe una revision no tienes a que añadirle precio de material...");
        return revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Si no tienes una revision no tienes nada que cerrar pleb");
        Objects.requireNonNull(revision, "Necesitas una fecha a la que asignar el cierre");
        return revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "Necesitas un cliente si es que quieres borrarlo en primer lugar!");
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente){
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehiculo que no existe pleb");
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Si no tienes una revision entonces no puedes borrarla pleb");
        revisiones.borrar(revision);
    }

    public List<Cliente> getCliente() {
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            Cliente cliente1 = new Cliente(cliente);
            copiaClientes.add(cliente1);
        }
        return copiaClientes;
    }

    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> coleccionVehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos.get()){
            coleccionVehiculos.add(vehiculo);
        }

        return coleccionVehiculos;
    }

    public List<Revision> getRevisiones() {
        List<Revision> copiaRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            Revision revision1 = new Revision(revision);
            copiaRevisiones.add(revision1);
        }
        return  copiaRevisiones;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            revisionesCliente.add(new Revision(revision));
        }
        return revisionesCliente;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            revisionesVehiculo.add(new Revision(revision));
        }
        return revisionesVehiculo;
    }
}
