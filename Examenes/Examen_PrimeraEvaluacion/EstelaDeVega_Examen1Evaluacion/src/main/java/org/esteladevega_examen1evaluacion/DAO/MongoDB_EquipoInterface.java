package org.esteladevega_examen1evaluacion.DAO;

import org.esteladevega_examen1evaluacion.Model.Equipo;

public interface MongoDB_EquipoInterface {
    boolean insertEquipo(Equipo equipo);
    boolean eliminarEquipo(int id);
}
