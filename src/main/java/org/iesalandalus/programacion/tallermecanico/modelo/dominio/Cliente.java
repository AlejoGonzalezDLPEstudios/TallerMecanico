package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.util.Locale;
import java.util.Objects;

public class Cliente {

    public static final String ER_NOMBRE = "^[A-ZÁÉÍÓÚÑ][a-za-áéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$";
    public static final String ER_DNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]*$";
    public static final String ER_TELEFONO = "^[0-9]{9}$";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente){
        Objects.requireNonNull(cliente, "ERROR: Un cliente ni debe ni puede ser NULO");
        nombre = cliente.nombre;
        dni = cliente.dni;
        telefono = cliente.telefono;
    }

    public String getNombre() { return nombre;}

    public void setNombre(String nombre){
        Objects.requireNonNull(nombre, "El cliente debe de tener un nombre, asi que NO PUEDE SER NULO!");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El formato usado con el NOMBRE del cliente no es correcto.");
        }
        this.nombre = nombre;
    }

    public String getDni() {  return dni;}

    private void setDni(String dni){
        Objects.requireNonNull(dni, "El cliente debe de tener un DNI, ESTE NO PUEDE SER NULO!");
        if (!dni.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El formato utilizado en el DNI es INVALIDO");
        }
        if (!comprobarLetraDNI(dni)){
            throw new IllegalArgumentException("La letra del DNI asignado es imposible, verifique y reintente por favor.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDNI(String dni) {
        String letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = Integer.parseInt(dni.substring(0,8)) % 23;
        return (dni.charAt(8) == letraCalculada.charAt(resto));
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El cliente debe de tener un telefono, ESTE NO PUEDE ESTAR EN NULO!");
        if (telefono.matches(ER_TELEFONO)) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El formato usado para agregar el telefono es INVALIDO, reincie e intente nuevamente por favor.");
        }
    }

    public static Cliente get(String dni) { return new Cliente("Joshu", dni, "150620118"); }

    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }

    /*

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


     */


}
