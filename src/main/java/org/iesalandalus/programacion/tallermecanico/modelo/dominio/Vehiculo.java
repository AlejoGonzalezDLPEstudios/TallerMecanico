package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static String ER_MARCA = "^[Seat,Land Rover,KIA,Rolls_Royce,SsangYoung]$";
    private static String ER_MATRICULA = "^[0-9]{4}[^AEIOUaeiouÁÉÍÓÚáéíóú]{3}$";

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

}
