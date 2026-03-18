package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.util.Locale;

public class Cliente {

    public static final String ER_NOMBRE = "^[A-ZÁÉÍÓÚÑ][a-za-áéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$";
    public static final String ER_DNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]*$";
    public static final String ER_TELEFONO = "^[0-9]{9}$";

    private String nombre;
    private String dni;
    private String telefono;

    public static boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches(ER_NOMBRE);
    }

    public static boolean validarDNI(String dni) {

        return dni != null && dni.toUpperCase().matches(ER_DNI);
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches(ER_TELEFONO);
    }

    public void setDni(String dni) {

        if (validarDNI(dni)) {
            throw new IllegalArgumentException("El DNI otorgado no debe ni puede ser NULO");
        }
        this.dni = dni.toUpperCase(); //El "ToUpperCase" se encarga de que los caracteres de DNI estén siempre en mayúscula, asi evitamos
                                      // problemas futuros relacionados a las letras del DNI en minúscula.
    }

    public void setNombre(String nombre) {

        if (!validarNombre(nombre)) {
            throw new IllegalArgumentException("El nombre del cliente ni puede ni debe de ser NULO");
        }

        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        if (validarTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono del cliente ni puede ni debe de ser NULO");
        }

        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        //if (validarTelefono(this.telefono) == false) {
        //    throw new TallerMecanicoExcepcion("El telefono asignado NO SIGUE LOS PARAMETROS CORRECTOS!");
        // }

        return telefono;
    }

    public Cliente(String dni, String nombre, String telefono) {

        setDni(dni);
        setNombre(nombre);
        setTelefono(telefono);

    }

    // public Cliente (String nombre, String dni, String telefono) { //Constructor copia
    //    this.nombre = nombre;
    //    this.dni = dni;
    //    this.telefono = telefono;
    // }

    public static Cliente get(String dni) throws TallerMecanicoExcepcion{

        if (dni == null || !validarDNI(dni)) {
            throw new TallerMecanicoExcepcion("El DNI otorgado no es valido, el proceso no puede continuar");
        }

        return new Cliente(dni.toUpperCase(), "P", "000000000");
    }

    public Cliente(Cliente cliente) {
        this( cliente.dni, cliente.nombre, cliente.telefono);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return dni.equals(cliente.dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }


}
