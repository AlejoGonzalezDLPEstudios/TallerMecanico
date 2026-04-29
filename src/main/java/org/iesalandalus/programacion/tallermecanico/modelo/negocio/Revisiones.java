package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {

    private final List<Revision> coleccionRevisiones;

    public Revisiones() {coleccionRevisiones = new ArrayList<>();}

    public List<Revision> get() {return new ArrayList<>(coleccionRevisiones);}

    public List<Revision> get (Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public List<Revision> get(Vehiculos vehiculo) {
        List<Revision> revisionesVehiculos = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculos().equals(new Vehiculos())) {
                revisionesVehiculos.add(revision);
            }
        }
        return  revisionesVehiculos;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No puedes insertar una revision que en primer lugar ni existe");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        for (Revision revision : coleccionRevisiones) {
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("No puedes sacarle una revision a un cliente que ya esta en una revision!.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehiculo seleccionado ya se encuentra en medio de una revision");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente ya tiene una revision posteriormente.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehiculo ya posee una revision posteriormente.");
                }
            }
        }
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirHoras(horas);
        return revisionEncontrada;
    }

    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Si no existe una revision no tiene sentido que puedas operar en ella.");
        Revision revisionEncontrada = buscar(revision);
        if (revisionEncontrada == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revision que concida con la seleccionada.");
        }

        return revisionEncontrada;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirPrecioMaterial(precioMaterial);
        return revisionEncontrada;
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.cerrar(fechaFin);
        return revisionEncontrada;
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "Si una revision no existe, no tiene sentido buscarla.");
        int indice = coleccionRevisiones.indexOf(revision);
        return (indice == 1) ? null : coleccionRevisiones.get(indice);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "Si una revision no existe, no puedes borrarla, no tienes a ZA HANDO como para poder borrar su espacio inexistente -_-");
        if (!coleccionRevisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("No hay ninguna revision que concuerde con la seleccionada.");
        }
        coleccionRevisiones.remove(revision);
    }


}
