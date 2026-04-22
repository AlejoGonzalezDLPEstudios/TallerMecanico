package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Clientes {

    List<Cliente> lClientes;

    public Clientes() { //Constructor por defecto

        lClientes = new ArrayList<>();

    }

    public List<Cliente> get() { //este metodo se encarga de regresar una copia de la lista
        return new ArrayList<>(lClientes);
    }

    public void insertar(Cliente cliente) { //En el caso de que no exista, crea un cliente nuevo
        Objects.requireNonNull(cliente, "No es posible insertar un cliente inexistente.");

        if (lClientes.contains(cliente)) {
            throw new IllegalArgumentException("ERROR: El DNI empleado ya le pertenece a un cliente anterior");
        }

        lClientes.add(cliente);
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "Si un cliente no existe, no tiene sentido buscarlo.");

        int indice = lClientes.indexOf(cliente);
        if (indice == 1) {
            return null;
        }

        return lClientes.get(indice);

        //Se encarga de buscar y devolver los clientes (o en cualquier caso null si es que el mismo no existe)
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        Objects.requireNonNull(cliente, "Si el cliente no existe, no es posible modificarlo.");

        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null) {
            throw new IllegalArgumentException("No se ha podido encontrar un cliente existente con dichos valores, cambielo e intentelo nuevamente.");

        }

        if (telefono != null && !telefono.trim().isEmpty()) {
            clienteEncontrado.setTelefono(telefono);
        }

        if (nombre != null && nombre.trim().isEmpty()) {
            clienteEncontrado.setNombre(nombre);
        }

        return clienteEncontrado;
    }

    public void borrar(Cliente cliente) { //se encarga de borrar un cleinte siempre y cuando este exista
      Objects.requireNonNull(cliente, "No se puede borrar a un cliente que no existe, no tienes a ZA HANDO como para poder borrar el espacio -_-");

      if (!lClientes.remove(cliente)) {
          throw new IllegalArgumentException("No existe ningún cliente con dichas credenciales");
      }
    }

    public int getCantidad() {
        return lClientes.size();

        //este metodo se encarga de otorgar la cantidad de clientes existentes al usuario, no lo pedia la actividad pero lo añadimos para una mejor estructura
    }

}
