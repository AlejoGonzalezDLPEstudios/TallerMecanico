package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static String ER_MARCA = "([A-ZÁÉÍÓÚ]+[a-záéíóú]*)[-]?[ ]?([A-ZÁÉÍÓÚ][a-záéíóú]+)?";// "^[Seat,Land Rover,KIA,Rolls_Royce,SsangYoung]$";
    private static String ER_MATRICULA = "\\d{4}[^\\W_\\dAEIOUa-z]{3}"; //"^[0-9]{4}[^AEIOUaeiouÁÉÍÓÚáéíóú]{3}$";

    public Vehiculo {
        validarMarca(marca);
        validarMatricula(matricula);
        validarModelo(modelo);
    }

    private void validarMarca(String marca){
        Objects.requireNonNull(marca, "Cada vehiculo debe de tener una marca, ESTA NO PUEDE SER NULA!!");
        if(!marca.matches(ER_MARCA)){
            throw new IllegalArgumentException("El formato usado para escribir la Marca NO ES VALIDO!!!");
        }
    }

    private void validarModelo(String modelo){
        Objects.requireNonNull(modelo, "Cada vehiculo debe de tener un modelo, ESTE NO PUEDE SER NULO!!!");
        if (modelo.isBlank()){
            throw new IllegalArgumentException("No puedes tener EL MODELO VACIO!!");
        }
    }

    private void validarMatricula(String matricula){
        Objects.requireNonNull(matricula, "Cada vehiculo debe de disponer de una matricula, ESTA NO PUEDE SER NULA, ES ILEGAL!!");
        if (!matricula.matches(ER_MATRICULA)){
            throw new IllegalArgumentException("El formato de la matricula usado NO ES CORRECTO!, reinice e intente nuevamente");

        }
    }

    public static Vehiculo get(String matricula) { return new Vehiculo("Peogot", "si", matricula); }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }

    /*
    private static boolean validarMarca(String marca) {

        return marca != null && marca.matches(ER_MARCA);
    }

    private static boolean validarMatricula(String matricula) {

        return matricula != null && matricula.matches(ER_MATRICULA);
    }

    public Vehiculo {
        matricula = matricula != null ? matricula.toUpperCase().trim() : null;

        if (!validarMarca(marca)) {
            throw new IllegalArgumentException("La marca especificada es invalida : No hay resultados");
        }

        if(!validarMatricula(matricula)) {
            throw new IllegalArgumentException("La matricula especificada no posee las especificaciones valdias.");
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo especificado no es valido.");
        }

        marca = marca.trim();
        modelo = modelo.trim();

    }

    public static Vehiculo get(String matricula) {
        if (!validarMatricula(matricula)) {
            throw new IllegalArgumentException("La Matricula especificada es invalida.");
        }

        return new Vehiculo("Seat", "PLACEHOLDER", matricula.toUpperCase());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return matricula.equals(((Vehiculo) o).matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    //NOTA --> En este codigo usamos ".trim()", en resumidas cuentas este funciona parecido al ".uppercase()"
    //ya que su labor es, de manera similar a como el ".uppercase()" pone todas las letras en mayuscula, eliminar
    //todo espacio o caracter invisible que posea un texto, por lo que en otras palabras, ejemplos como "Maria  ".trim()
    //pasarían a ser "Maria" a secas una vez pasados por el .trim()

     */

}
