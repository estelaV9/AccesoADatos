package org.esteladevega_examen1evaluacion.DAO;

import javafx.collections.ObservableList;
import org.esteladevega_examen1evaluacion.Model.Equipo;

public interface Hibernate_EquipoInterface {
    ObservableList<Equipo> listarEquipos();
    boolean modificarEquipo(Equipo equipo, boolean sancionado);
    boolean isExistsTeam (int id);
    boolean eliminarEquipo(Equipo equipo);
}
