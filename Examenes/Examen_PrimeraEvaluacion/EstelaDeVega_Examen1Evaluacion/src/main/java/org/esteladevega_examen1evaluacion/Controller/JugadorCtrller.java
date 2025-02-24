package org.esteladevega_examen1evaluacion.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.esteladevega_examen1evaluacion.DAO.Hibernate_EquipoDAO;
import org.esteladevega_examen1evaluacion.DAO.Hibernate_JugadorDAO;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Model.Jugador;

import javafx.scene.input.MouseEvent;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class JugadorCtrller implements Initializable {

    @FXML
    private TextField aliasJugadorTF;

    @FXML
    private Button cambiarEquipoBtt;

    @FXML
    private Button cumpleañosBtt;

    @FXML
    private TextField fechaNacimientoTF;

    @FXML
    private TextField idEquipoTF;

    @FXML
    private ListView<Jugador> listView;

    Hibernate_JugadorDAO hibernateJugadorDAO = new Hibernate_JugadorDAO();
    Hibernate_EquipoDAO hibernateEquipoDAO = new Hibernate_EquipoDAO();
    private Equipo equipoGeneral = new Equipo();

    LocalDate localDate = LocalDate.now(); // ATRIBUTO PARA SABER LA FECHA DE HOY

    public void displayController(Equipo equipo) {
        idEquipoTF.setText(String.valueOf(equipo.getIdEquipo())); // SE SETTEA EL ID DEL EQUIPO
        equipoGeneral = equipo; // SE SETTEA PARA PODER ACCEDER A ESE ATRIBUTO
        refreshList(); // R¡SE REFRESCA LA LISTA
    } // METODO PARA PASAR EL ID DEL EQUIPO SELECCIONADO

    @FXML
    void onClickList(MouseEvent event) {
        Jugador jugadorSeleccionado = listView.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL JUGADOR SELECCIONADO
        if (jugadorSeleccionado != null) {
            aliasJugadorTF.setText(jugadorSeleccionado.getAliasJugador());
            fechaNacimientoTF.setText(String.valueOf(jugadorSeleccionado.getFechaNacimiento()));
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // CUANDO PULSA UN JUGADOR, SE SETTEAN LOS VALORES AL TEXTFIELD


    /*Cambiar a un jugador de equipo, para lo cual modificando el identificador de equipo y pulsando del
    botón Cambiar de equipo a un jugador, al jugador seleccionado se le habrá cambiado de equipo, por tanto,
    deberá desaparecer del ListView y los campos se ponen en blanco. Si el equipo al que se intenta cambiar
    no existiera nos debería mostrar un mensaje de ERROR.*/
    @FXML
    void onCambiarEquipoJugadorAction(ActionEvent event) {
        Jugador jugadorSeleccionado = listView.getSelectionModel().getSelectedItem(); // OBTENER LOS DATOS DEL JUGADOR SELECCIONADO
        if (jugadorSeleccionado != null) {
            if (hibernateEquipoDAO.isExistsTeam(Integer.parseInt(idEquipoTF.getText()))) {
                // CREAR EQUIPO AUXILIAR CON EL NUEVO ID
                Equipo equipoModificar = new Equipo(Integer.parseInt(idEquipoTF.getText()), equipoGeneral.getNombreEquipo(), equipoGeneral.getPatrocinado(), equipoGeneral.getCategoria(), equipoGeneral.isSancionado());
                if (hibernateJugadorDAO.modificarJugador(jugadorSeleccionado, equipoModificar)) {
                    refreshList(); // REFRESCAR LA TABLA
                    // CAMPOS EN BLANCO
                    idEquipoTF.clear();
                    aliasJugadorTF.clear();
                    fechaNacimientoTF.clear();
                } else {
                    // SI NO SE MODIFICO, SE MUESTRA UN ERROR
                    StaticCode.Alerts("ERROR", "Modificación de jugador",
                            "Modificación fallida", "No se ha podido modificar al jugador.");
                } // SI SE MODIFICA BIEN, SE REFRESCA LA TABLA, Y SI NO, SE MUESTRA UN ERROR
            } else {
                StaticCode.Alerts("ERROR", "Equipo no existente",
                        "Equipo no existente", "Ese equipo no existe.");
            } // SI EL EQUIPO EXISTE, SE REALIZA LA MODIFICACION, SINO, SALTA UNA ALERTA
        } // SI SELECCIONADO NO ES NULO, SE PONEN LOS VALORES AL TEXTFIELD
    } // BOTON PARA CAMBIAR A UN JUGADOR A UN EQUIPO, VALIDANDO SI ESE EQUIPO EXISTE

    /*II.	(1 Punto) Cumpleaños, al pulsar sobre este botón me mostrara en un alert el nombre de los jugadores
    que es su cumpleaños en el día de hoy y cuantos años cumplen.*/
    @FXML
    void onCumpleañosAction(ActionEvent event) {
        String cadenaInfoJugadorCumple = ""; // ATRIBUTO PARA GUARDAR LOS NOMBRES Y LA EDAD DE LOS JUGADORES

        for (Jugador jugador : hibernateJugadorDAO.listarJugadoresCumpleaños(localDate)) {
            // SE RESTA LA FECHA DE HOY CON LA FECHA DE CUANDO NACIO Y NOS DARA LOS AÑOS QUE TIENE
            int añosActuales = localDate.compareTo(jugador.getFechaNacimiento());
            cadenaInfoJugadorCumple += jugador.getAliasJugador() + " Cumpleaños: " + añosActuales + "\n";
        } // SE RECORREN LOS JUGADORES DEL CUMPLEAÑOS

        // SE MUESTRAN
        StaticCode.Alerts("INFORMATION", "Cumpleaños", "Cumpleaños", cadenaInfoJugadorCumple);
    } // BOTON PARA MOSTRAR LOS NOMBRES DE LOS JUGADORES Y LOS AÑOS QUE CUMPLAN AÑOS HOY

    private void refreshList() {
        // SE LLAMA AL METODO DE LISTAR JUGADORES CON EL ID DEL EQUIPO
        listView.setItems(hibernateJugadorDAO.listarJugadores(equipoGeneral.getIdEquipo()));
    } // METODO PARA REFRESCAR LA LISTA

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshList();
    } // AL INICIALIZAR, SE PONEN LOS DATOS EN EL LISTVIEW
}