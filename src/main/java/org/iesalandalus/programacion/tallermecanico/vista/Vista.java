package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "ERROR: El controlador no posee la capacidad de ser una entidad NULA.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
    }

    public void terminar() { System.out.println("¡¡¡Hasta luego Alejoooo!!!"); }

    public void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BUSCAR_REVISION -> buscarRevision();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_CLIENTES -> listarClientes();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesClientes();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculos();
                case SALIR -> salir();
            }
        } catch (Exception e) {
            System.out.printf("ERROR: %s%n", e.getMessage());
        }
    }

    private void insertarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente");
    }

    private void insertarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Vehiculo");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Vehiculo insertado correctamente");
    }

    private void insertarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Revision");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Revision insertada correctamente");
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar Cliente");
        Cliente cliente = controlador.buscar(Consola.leerClienteDNI());
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con el DNI otorgado");
    }

    private void buscarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Buscar Vehiculo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehiculo con la matricula otorgada");
    }

    private void buscarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Buscar Revision");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println((revision != null) ? revision : "No existe ninguna revisión vigente para dicho elemento");
    }

    private void modificarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Modificar Cliente");
        controlador.modificar(Consola.leerClienteDNI(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        System.out.println("El cliente se ha modificado correctamente.");
    }

    private void anadirHoras() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir Horas Revisión");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        System.out.println("Horas añadidas correctamente");
    }

    private void anadirPrecioMaterial() throws TallerMecanicoExcepcion {
        System.out.println("Precio material añadido exitosamente.");
    }

    private void cerrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Cerrar Revisión");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        System.out.println("Revision Cerrada exitosamente.");
    }

    private void borrarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Cliente");
        controlador.borrar(Consola.leerClienteDNI());
        System.out.println("Cliente borrado exitosamente.");
    }

    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Vehiculo");
        controlador.borrar(Consola.leerVehiculoMatricula());
        System.out.println("Vehiculo borrado exitosamente.");
    }

    private void borrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Revisión");
        controlador.borrar(Consola.leerRevision());
        System.out.println("Revision borrada exitosamente");
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Leer Clientes");
        List<Cliente> clientes = controlador.getClientes();
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay ningun cliente que coincida, por ende no se muestra nada.");
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Leer Vehiculos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay ningun Vehiculo que coincida, por ende no se muestra nada.");
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Leer Revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revisiones);
            }
        } else {
            System.out.println("No hay ningún Revisiones que coincida, por ende no se muestra nada.");
        }
    }

    private void listarRevisionesClientes() {
        Consola.mostrarCabecera("Leer Revisiones de los Clientes");
        List<Revision> revisionesClientes = controlador.getRevisionesClientes(Consola.leerClienteDNI());
        if (!revisionesClientes.isEmpty()) {
            for (Revision revision : revisionesClientes) {
                System.out.println(revisionesClientes);
            }
        } else {
            System.out.println("No hay ninguna Revision con respecto a los clientes que coincida, por ende no se muestra nada");
        }
    }

    private void listarRevisionesVehiculos() {
        Consola.mostrarCabecera("Leer Revisiones de los Vehículos");
        List<Revision> revisionesVehiculos = controlador.getRevisionesVehiculos(Consola.leerVehiculoMatricula());
        if (!revisionesVehiculos.isEmpty()) {
            for (Revision revision : revisionesVehiculos) {
                System.out.println(revisionesVehiculos);
            }
        } else {
            System.out.println("No hay ninguna Revisión con respecto a los Vehículos que coincida, por ende no se muestra nada");
        }
    }

    private void salir() {
        //Literalmente no hacemos mada, se frena el programa
    }
}
