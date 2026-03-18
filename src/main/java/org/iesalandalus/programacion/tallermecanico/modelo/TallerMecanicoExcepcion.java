package org.iesalandalus.programacion.tallermecanico.modelo;

public class TallerMecanicoExcepcion extends Exception {

    public TallerMecanicoExcepcion() { //Constructor sin mensaje
        super();
    }

    public TallerMecanicoExcepcion(String message) { //constructor con mensaje
        super(message);
    }

    public TallerMecanicoExcepcion(String message, Throwable cause){ //constructor con mensaje y causa
        super(message, cause);
    }
}
