/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package view;

import static com.coti.tools.Esdia.*;
import controller.Controller;
import data.Datos_equipo;
import data.Equipo;
import data.Jugadora;
import data.Partido;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;

public class View {

    Controller c = new Controller();

    public void runMenu(String menu) throws Exception {
        boolean salir = false;
        boolean exitoCargaBinarios;
        boolean cargaTemporada = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4", "5", "6"};
        if(!c.binariosVacia()){
            exitoCargaBinarios = c.cargarModelo();
            if(exitoCargaBinarios){
                out.printf("%n%nBinarios cargados con éxito, ya no debe"
                    + "realizar la opción 1.- Gestión de temporada%n%n");
                cargaTemporada = true;
            }
            else{
                out.printf("%n%nERROR : no fue posible restaurar los datos%n%n");
            }
        }
        do {
            out.printf("%n%nMENÚ PRINCIPAL%n");
            option = readString(menu, availableOptions);
            switch (option) {
                case "1":
                    if (cargaTemporada) {
                        out.printf("%n%nYa se ha realizado la gestión de temporada%n%n");
                        break;
                    }
                    this.gestionTemporada("%n1.- Iniciar temporada"
                            + "%n2.- Cargar jornadas"
                            + "%n3.- Cargar equipos"
                            + "%n4.- Cargar jugadoras%n");
                    cargaTemporada = true;
                    break;
                case "2":
                    if (!cargaTemporada) {
                        out.printf("%n%nNo ha realizado la opcion 1.- Gestión de temporada%n%n");
                        break;
                    }
                    this.gestionJugadoras("%n1.- Modificar datos de una jugadora"
                            + "%n2.- Eliminar una jugadora de un equipo"
                            + "%n3.- Añadir una jugadora a un equipo"
                            + "%n4.- Volver al menú principal%n");
                    break;
                case "3":
                    if (!cargaTemporada) {
                        out.printf("%n%nNo ha realizado la opcion 1.- Gestión de temporada%n%n");
                        break;
                    }
                    this.gestionJornada("%n1.- Leer los resultados de la jornada"
                            + "%n2.- Modificar fecha de la jornada"
                            + "%n3.- Modificar fecha u hora de un partido"
                            + "%n4.- Mostrar los resultados de la jornada"
                            + "%n5.- Mostrar la clasic¡ficación de una jornada"
                            + "%n6.- Volver al menú principal%n");
                    break;
                case "4":
                    if (!cargaTemporada) {
                        out.printf("%n%nNo ha realizado la opcion 1.- Gestión de temporada%n%n");
                        break;
                    }
                    this.visualizacionResultados("%n1.- Jugadoras de un equipo"
                            + "%n2.- Relación de equipos"
                            + "%n3.- Relación de jugadoras"
                            + "%n4.- Volver al menú principal%n");
                    break;
                case "5":
                    if (!cargaTemporada) {
                        out.printf("%n%nNo ha realizado la opcion 1.- Gestión de temporada%n%n");
                        break;
                    }
                    this.almacenamientoResultados("%n1.- Jugadoras de un equipo"
                            + "%n2.- Relación de equipos"
                            + "%n3.- Clasificación de una jornada"
                            + "%n4.- Volver al menú principal%n");
                    break;
                case "6":
                    if (!cargaTemporada) {
                        out.printf("%n%nNo ha realizado la opcion 1.- Gestión de temporada%n%n");
                        break;
                    }
                    this.salida();
                    salir = true;
                    break;
                default:
                    out.printf("%n%nOpción incorrecta%n%n");
                    break;
            }
        } while (!salir);
    }

    private void gestionTemporada(String submenu) throws Exception {
        int flag = 1;
        String option;
        String[] availableOptions = {"1", "2", "3", "4"};
        do {
            out.printf("%n%nSUBMENÚ GESTIÓN DE TEMPORADA%n");
            option = readString(submenu, availableOptions);
            switch (option) {
                case "1":
                    if (flag != 1) {
                        out.printf("%n%nOpción no válida, ya ha iniciado la temporada%n%n");
                        break;
                    }
                    c.storeTemporada(readString("%nTemporada sobre la que se van a introducir datos?%n"));
                    flag = 2;
                    break;
                case "2":
                    if (flag != 2) {
                        out.printf("%n%nOpción no válida%n%n");
                        break;
                    }
                    c.cargarJornadas();
                    out.printf("%n%nJornadas cargadas%n%n");
                    flag = 3;
                    break;
                case "3":
                    if (flag != 3) {
                        out.printf("%n%nOpción no válida%n%n");
                        break;
                    }
                    c.cargarEquipos();
                    out.printf("%n%nEquipos cargadas%n%n");
                    flag = 4;
                    break;
                case "4":
                    if (flag != 4) {
                        out.printf("%n%nOpción no válida%n%n");
                        break;
                    }
                    c.cargarJugadoras();
                    out.printf("%n%nJugadoras cargadas%n%n");
                    flag = 5;
                    break;
                default:
                    out.printf("%n%nOpción incorrecta%n%n");
                    break;
            }
        } while (flag != 5);
    }

    private void gestionJugadoras(String submenu) {
        boolean salir = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4"};
        do {
            out.printf("%n%nSUBMENÚ GESTIÓN DE JUGADORAS%n");
            option = readString(submenu, availableOptions);
            switch (option) {
                case "1":
                    this.cambiarDatoJugadora();
                    break;
                case "2":
                    this.eliminarJugadora();
                    break;
                case "3":
                    this.aniadirJugadora();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    out.printf("%n%nOpción incorrecta%n%n");
                    break;
            }
        } while (!salir);
    }

    private void gestionJornada(String submenu) throws IOException {
        boolean salir = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4", "5", "6"};
        do {
            out.printf("%n%nSUBMENÚ GESTIÓN DE JORNADA%n");
            option = readString(submenu, availableOptions);
            switch (option) {
                case "1":
                    this.cargarResultadosJornada();
                    break;
                case "2":
                    this.cambiarFechaJornada();
                    break;
                case "3":
                    this.cambiarFechaHoraPartido();
                    break;
                case "4":
                    this.mostrarResultadosJornada();
                    break;
                case "5":
                    this.mostrarClasificacionJornada();
                    break;
                case "6":
                    salir = true;
                    break;
                default:
                    out.printf("%n%nOpción incorrecta%n%n");
                    break;
            }
        } while (!salir);
    }

    private void visualizacionResultados(String submenu) {
        boolean salir = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4"};
        do {
            out.printf("%n%nSUBMENÚ VISUALIZACIÓN DE RESULTADOS%n");
            option = readString(submenu, availableOptions);
            switch (option) {
                case "1":
                    this.mostrarJugadoras();
                    break;
                case "2":
                    this.mostrarEquipos();
                    break;
                case "3":
                    this.mostrarJugadorasPorInicial();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    out.printf("%n%nOpción incorrecta%n%n");
                    break;
            }
        } while (!salir);
    }

    private void almacenamientoResultados(String submenu) throws FileNotFoundException {
        boolean salir = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4"};
        do {
            out.printf("%n%nSUBMENÚ ALMACENAMIENTO DE RESULTADOS%n");
            option = readString(submenu, availableOptions);
            switch (option) {
                case "1":
                    this.guardarJugadoras();
                    break;
                case "2":
                    this.guardarEquipos();
                    break;
                case "3":
                    this.guardarClasificacion();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    out.printf("%n%nOpción incorrecta%n%n");
                    break;
            }
        } while (!salir);
    }

    private void salida() {
        boolean exito;
        exito = c.guardarModelo();
        if(exito)
            out.printf("%n%nBinarios guardados con éxito%n%n");
        else
            out.printf("%n%nERROR : no se ha creado el fichero%n%n");   
    }

    private void cambiarDatoJugadora() {
        String nombreEquipo, nombreJugadora, nuevoDatoString, option;
        int nuevoDatoInt;
        String[] nombresEquipos = c.nombresEquipos();
        out.printf("%nLISTADO DE EQUIPOS:%n%n");
        for (String nombresEquipo : nombresEquipos) {
            out.printf("%s%n", nombresEquipo);
        }
        do {
            nombreEquipo = readString("%n¿Nombre del equipo de la jugadora que desa modificar? ");
            nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
            if (!c.equipoValido(nombreEquipo)) {
                out.printf("%nEquipo no válido%n%n");
            }
        } while (!c.equipoValido(nombreEquipo));
        String[] nombresJugadoras = c.nombresJugadorasDelEquipo(nombreEquipo);
        out.printf("%n%nJUGADORAS DEL EQUIPO %s:%n%n", nombreEquipo);
        for (String nombresJugadora : nombresJugadoras){
            out.printf("%s%n", nombresJugadora);
        }
        do {
            nombreJugadora = readString("%n¿Nombre de la jugadora que desa modificar? ");
            nombreJugadora = nombreJugadora.toUpperCase();
            if (!c.jugadoraValida(nombreEquipo, nombreJugadora)) {
                out.printf("%nJugadora no válida%n%n");
            }
        } while (!c.jugadoraValida(nombreEquipo, nombreJugadora));
        option = readString("%n%n¿Que dato desea modificar?%n"
                + "%n1.- Posicion"
                + "%n2.- Dorsal"
                + "%n3.- Fecha de nacimiento"
                + "%n4.- Ciudad de nacimiento"
                + "%n5.- Provincia de nacimiento"
                + "%n6.- Nacionalidad"
                + "%n7.- Altura%n"
                + "%n ( 1, 2, 3, 4, 5, 6, 7 ) ? %n");
        switch (option) {
            case "1":
                do {
                    nuevoDatoString = readString("%nIntroduzca la nueva posición (Ala-Pívot, Pívot, Base, Escolta, Alero) : ");
                    if (!c.posicionValida(nuevoDatoString)) {
                        out.printf("%nPosición no válida%n%n");
                    }
                } while (!c.posicionValida(nuevoDatoString));
                c.cambiarPosicion(nombreEquipo, nombreJugadora, nuevoDatoString);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "2":
                nuevoDatoInt = readInt("Introduzca el nuevo dorsal : ");
                c.cambiarDorsal(nombreEquipo, nombreJugadora, nuevoDatoInt);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "3":
                nuevoDatoString = readString("Introduzca la nueva fecha de nacimiento (DD/MM/YYYY) : ");
                c.cambiarFechaNacimiento(nombreEquipo, nombreJugadora, nuevoDatoString);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "4":
                nuevoDatoString = readString("Introduzca la nueva ciudad de nacimiento : ");
                c.cambiarCiudadNacimiento(nombreEquipo, nombreJugadora, nuevoDatoString);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "5":
                nuevoDatoString = readString("Introduzca la nueva provincia de nacimiento : ");
                c.cambiarProvinciaNacimiento(nombreEquipo, nombreJugadora, nuevoDatoString);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "6":
                nuevoDatoString = readString("Introduzca el nuevo dorsal : ");
                c.cambiarNacionalidad(nombreEquipo, nombreJugadora, nuevoDatoString);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "7":
                nuevoDatoInt = readInt("Introduzca la nueva altura : ");
                c.cambiarAltura(nombreEquipo, nombreJugadora, nuevoDatoInt);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            default:
                out.printf("%n%nOpción incorrecta%n%n");
                break;
        }

    }

    private void eliminarJugadora() {
        String nombreEquipo, nombreJugadora;
        String[] nombresEquipos = c.nombresEquipos();
        out.printf("%nLISTADO DE EQUIPOS:%n%n");
        for (String nombresEquipo : nombresEquipos) {
            out.printf("%s%n", nombresEquipo);
        }
        do {
            nombreEquipo = readString("%n¿Nombre del equipo de la jugadora que desa modificar? ");
            nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
            if (!c.equipoValido(nombreEquipo)) {
                out.printf("%nEquipo no válido%n%n");
            }
        } while (!c.equipoValido(nombreEquipo));
        String[] nombresJugadoras = c.nombresJugadorasDelEquipo(nombreEquipo);
        out.printf("%n%nJUGADORAS DEL EQUIPO %s:%n%n", nombreEquipo);
        for (String nombresJugadora : nombresJugadoras){
            out.printf("%s%n", nombresJugadora);
        }
        do {
            nombreJugadora = readString("%n¿Nombre de la jugadora que desa eliminar? ");
            nombreJugadora = nombreJugadora.toUpperCase();
            if (!c.jugadoraValida(nombreEquipo, nombreJugadora)) {
                out.printf("%nJugadora no válida%n%n");
            }
        } while (!c.jugadoraValida(nombreEquipo, nombreJugadora));
        c.eliminarJugadora(nombreEquipo, nombreJugadora);
        out.printf("%n%nCambios aplicados%n%n");
    }

    private void aniadirJugadora() {
        String nombreEquipo, nombreJugadora, posicion, fecha, ciudad, provincia, nacionalidad;
        int dorsal, altura;
        String[] nombresEquipos = c.nombresEquipos();
        out.printf("%nLISTADO DE EQUIPOS:%n%n");
        for (String nombresEquipo : nombresEquipos) {
            out.printf("%s%n", nombresEquipo);
        }
        do {
            nombreEquipo = readString("%n¿Nombre del equipo al que quiere añadir una jugadora? ");
            nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
            if (!c.equipoValido(nombreEquipo)) {
                out.printf("%nEquipo no válido%n%n");
            }
        } while (!c.equipoValido(nombreEquipo));
        nombreJugadora = readString("Nombre de la jugadora : ");
        nombreJugadora = nombreJugadora.toUpperCase();
        do {
            posicion = readString("Posición de la jugadora : ");
            if (!c.posicionValida(posicion)) {
                out.printf("%nPosición no válida%n%n");
            }
        } while (!c.posicionValida(posicion));
        dorsal = readInt("Dorsal de la jugadora : ");
        fecha = readString("Fecha de nacimiento de la jugadora : ");
        ciudad = readString("Ciudad de nacimiento de la jugadora : ");
        provincia = readString("Provincia de nacimiento de la jugadora : ");
        nacionalidad = readString("Nacionalidad de la jugadora : ");
        nacionalidad = nacionalidad.toUpperCase();
        altura = readInt("Altura de la jugadora : ");
        c.aniadirJugadora(nombreEquipo, nombreJugadora, posicion, dorsal, fecha, ciudad, provincia, nacionalidad, altura);
        out.printf("%n%nCambios aplicados%n%n");
    }

    private void cargarResultadosJornada() throws IOException {
        int numJornada;
        do {
            numJornada = readInt("%nIntroduzca el número de la jornada que desea cargar (1,2,3,...) : ");
            if (!c.existenResulJornada(numJornada)) {
                out.printf("%nLa jornada %s no es válida%n%n", numJornada);
            }
        } while (!c.existenResulJornada(numJornada));
        //c.cargarResulJornada(numJornada);
        c.calcularClasificacion(numJornada);
        out.printf("%n%nResultados de la jornada %d cargados correctamente%n", numJornada);
        out.printf("Calculos de la clasificacion de la jornada %d realizados correctamente%n%n", numJornada);
    }

    private void cambiarFechaJornada() {
        int numJornada;
        String nuevaFecha;
        do {
            numJornada = readInt("%nIntroduce el número de la jornada de la cual quiere cambiar su fecha (1,2,3,...) : ");
            if(c.estaCargada(numJornada) == -1){
                out.printf("%n%nTodavía no hay ninguna joranda cargada%n%n");
                return;
            }
            if (c.estaCargada(numJornada) == -2){
                out.printf("%n%nLa jornada %d aún no se ha cargado, intente con otra jornada%n%n", numJornada);
            }
        } while (c.estaCargada(numJornada) != 0);
        nuevaFecha = readString("%nNueva fecha de la jornada (DD/MM/YYYY)? : ");
        c.cambiarFechaJornada(numJornada, nuevaFecha);
        out.printf("%n%nCambios aplicados%n%n");
    }

    private void cambiarFechaHoraPartido() {
        int numJornada;
        String nombreEquipo, nuevoDato, option;
        String[] nombresEquipos = c.nombresEquipos();
        do {
            numJornada = readInt("%nIntroduce el número de la jornada de la cual quiere cambiar el dato (1,2,3,...) : ");
            if(c.estaCargada(numJornada) == -1){
                out.printf("%n%nTodavía no hay ninguna joranda cargada%n%n");
                return;
            }
            if (c.estaCargada(numJornada) == -2){
                out.printf("%n%nLa jornada %d aún no se ha cargado, intente con otra jornada%n%n", numJornada);
            }
        } while (c.estaCargada(numJornada) != 0);
        out.printf("%nLISTADO DE EQUIPOS:%n%n");
        for (String nombresEquipo : nombresEquipos) {
            out.printf("%s%n", nombresEquipo);
        }
        do {
            nombreEquipo = readString("%nNombre del equipo del cual quiere modificar el dato : ");
            if (!c.existeEquipoClasificacion(numJornada - 1, nombreEquipo)) {
                out.printf("%nEl equipo %s no se encuentra en la jornada %d%n%n", nombreEquipo, numJornada);
            }
        } while (!c.existeEquipoClasificacion(numJornada - 1, nombreEquipo));
        option = readString("%n¿Que dato desea modificar?"
                + "%n1.- Fecha del partido%n"
                + "%n2.- Hora del partido%n"
                + "%n ( 1, 2 ) ? %n");
        switch (option) {
            case "1":
                nuevoDato = readString("%nNueva fecha del partido (DD/MM/YYYY) ? : ");
                c.cambiarFechaPartido(numJornada - 1, nombreEquipo, nuevoDato);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            case "2":
                nuevoDato = readString("%nNueva hora del partido (HH:MM) ? : ");
                c.cambiarHoraPartido(numJornada - 1, nombreEquipo, nuevoDato);
                out.printf("%n%nCambios aplicados%n%n");
                break;
            default:
                out.printf("%nOpción no válida%n%n");
                break;
        }
    }

    private void mostrarResultadosJornada() {
        int numJornada;
        do {
            numJornada = readInt("%nIntroduce el número de la jornada de la cual quiere mostar su resultado (1,2,3,...) : ");
            if(c.estaCargada(numJornada) == -1){
                out.printf("%n%nTodavía no hay ninguna joranda cargada%n%n");
                return;
            }
            if (c.estaCargada(numJornada) == -2){
                out.printf("%n%nLa jornada %d aún no se ha cargado, intente con otra jornada%n%n", numJornada);
            }
        } while (c.estaCargada(numJornada) != 0);
        ArrayList<Partido> partidos = c.mostrarPartidosJornada(numJornada - 1);
        out.printf("%n%n------------------------------------------------------------------------------------------------------------%n");
        out.printf("|                                      RESULTADOS DE LA JORNADA %2d                                         |%n", numJornada);
        out.printf("------------------------------------------------------------------------------------------------------------%n");
        out.printf("| Equipo local                             | Puntos  || Equipo visitante                         | Puntos  |%n");
        out.printf("------------------------------------------------------------------------------------------------------------%n");
        for (int i = 0; i < partidos.size(); i++) {
            out.printf("| %-40s |   %3d   || %-40s |   %3d   |%n",
                    partidos.get(i).getLocal(),
                    partidos.get(i).getPuntosLocal(),
                    partidos.get(i).getVisitante(),
                    partidos.get(i).getPuntosVisitante());
        }
        out.printf("------------------------------------------------------------------------------------------------------------%n%n");
    }

    private void mostrarClasificacionJornada() {
        int numJornada;
        do {
            numJornada = readInt("%nIntroduce el número de la jornada de la cual quiere mostar su clasifiación (1,2,3,...) : ");
            if(c.estaCargada(numJornada) == -1){
                out.printf("%n%nTodavía no hay ninguna joranda cargada%n%n");
                return;
            }
            if (c.estaCargada(numJornada) == -2){
                out.printf("%n%nLa jornada %d aún no se ha cargado, intente con otra jornada%n%n", numJornada);
            }
        } while (c.estaCargada(numJornada) != 0);   
        ArrayList<Datos_equipo> clasificacion = c.mostrarClasificacionJornada(numJornada - 1);
        out.printf("%n%n|-------------------------------------------------------------------------------|%n");
        out.printf("|                        CLASIFICACIÓN DE LA JORNADA %2d                         |%n", numJornada);
        out.printf("|-------------------------------------------------------------------------------|%n");
        out.printf("| Puesto  EQUIPO                                    PJ  PG  PP   PF    PC   PTS |%n");
        out.printf("|-------------------------------------------------------------------------------|%n");
        for (int i = 0; i < clasificacion.size(); i++) {
            out.printf("|   %2d    %-40s  %2d  %2d  %2d  %4d  %4d  %2d  |%n",
                    i + 1,
                    clasificacion.get(i).getNomEquipo(),
                    clasificacion.get(i).getPj(),
                    clasificacion.get(i).getPg(),
                    clasificacion.get(i).getPp(),
                    clasificacion.get(i).getPf(),
                    clasificacion.get(i).getPc(),
                    clasificacion.get(i).getPclas());
        }
        out.printf("|-------------------------------------------------------------------------------|%n");  
    }

    private void mostrarJugadoras() {
        String nombreEquipo;
        String[] nombresEquipos = c.nombresEquipos();
        out.printf("%nLISTADO DE EQUIPOS:%n%n");
        for (String nombresEquipo : nombresEquipos) {
            out.printf("%s%n", nombresEquipo);
        }
        do {
            nombreEquipo = readString("%n¿Nombre del equipo del cual quiere obener la información de las jugadoras? ");
            nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
            if (!c.equipoValido(nombreEquipo)) {
                out.printf("%nEquipo no válido%n%n");
            }
        } while (!c.equipoValido(nombreEquipo));
        ArrayList<Jugadora> jugadoras = c.mostrarJugadorasPosicionAltura(nombreEquipo);
        out.printf("%n%nJUGADORAS DEL %s%n%n", nombreEquipo);
        out.printf("%-35s%-14s%-10s%-60s%-16s%-6s%n%n",
                "NOMBRE", "POSICIÓN", "DORSAL",
                "NACIMIENTO", "NACIONALIDAD", "ALTURA");
        for(int i=0 ; i < jugadoras.size() ; i++){
            String nacimiento = jugadoras.get(i).getNacimiento().getFecha()
                    + " " + jugadoras.get(i).getNacimiento().getCiudad()
                    + " (" + jugadoras.get(i).getNacimiento().getProvincia() + ")";
            out.printf("%-35s%-14s%-10d%-60s%-16s%-6d%n", 
                    jugadoras.get(i).getNombre(),
                    jugadoras.get(i).getPosicion(),
                    jugadoras.get(i).getDorsal(),
                    nacimiento,
                    jugadoras.get(i).getNacionalidad(),
                    jugadoras.get(i).getAltura());
        }
        out.printf("%n%n");
    }

    private void mostrarEquipos() {
        ArrayList<Equipo> equipos = c.mostrarEquipos();
        out.printf("%nLISTA DE EQUIPOS:%n%n");
        out.printf("%-40s%-90s%-12s%-45s%-20s%n%n", "NOMBRE", "DIRECCIÓN", "TELÉFONO", "WEB", "E-MAIL");
        for(int i=0 ; i<equipos.size() ; i++){
            out.printf("%-40s%-90s%-12d%-45s%-20s%n",
                    equipos.get(i).getNombre(), equipos.get(i).getDireccion(),
                    equipos.get(i).getTelefono(), equipos.get(i).getWeb(),
                    equipos.get(i).getEmail());
        }
        out.printf("%n%n");
    }

    private void mostrarJugadorasPorInicial() {
        String inicial;
        do{
            inicial = readString("Inicial del nombre de la jugadora? : %n");
            inicial = inicial.toUpperCase();
            if(inicial.length()!=1 || inicial.equals(" ")){
                out.printf("%n%nInicial no válida%n%n");
            }
        } while (inicial.length()!=1 || inicial.equals(" "));
        ArrayList<Jugadora> jugadoras = c.mostrarJugadorasInicial(inicial);
        if(jugadoras.isEmpty()){
            out.printf("%n%nNinguna jugadora comienza por el caracter '%s'%n%n", inicial);
            return;
        }
        out.printf("%n%nJUGADORAS CUYO NOMBRE EMPIEZA POR %s%n%n", inicial);
        out.printf("%-35s%-14s%-10s%-60s%-16s%-6s%n%n",
                "NOMBRE", "POSICIÓN", "DORSAL",
                "NACIMIENTO", "NACIONALIDAD", "ALTURA");
        for(int i=0 ; i < jugadoras.size() ; i++){
            String nacimiento = jugadoras.get(i).getNacimiento().getFecha()
                    + " " + jugadoras.get(i).getNacimiento().getCiudad()
                    + " (" + jugadoras.get(i).getNacimiento().getProvincia() + ")";
            out.printf("%-35s%-12s %-10d%-60s%-14s%-6d%n",
                    jugadoras.get(i).getNombre(),
                    jugadoras.get(i).getPosicion(),
                    jugadoras.get(i).getDorsal(),
                    nacimiento,
                    jugadoras.get(i).getNacionalidad(),
                    jugadoras.get(i).getAltura());
        }
        out.printf("%n%n");
    }

    private void guardarJugadoras() throws FileNotFoundException {
        boolean exito;
        String nombreEquipo;
        String[] nombresEquipos = c.nombresEquipos();
        out.printf("%nLISTADO DE EQUIPOS:%n%n");
        for (String nombresEquipo : nombresEquipos) {
            out.printf("%s%n", nombresEquipo);
        }
        do {
            nombreEquipo = readString("%n¿Nombre del equipo del cual quiere obener la información de las jugadoras? ");
            nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
            if (!c.equipoValido(nombreEquipo)) {
                out.printf("%nEquipo no válido%n%n");
            }
        } while (!c.equipoValido(nombreEquipo));
        exito = c.guardarJugadoras(nombreEquipo);
        if(exito)
            out.printf("%n%nEquipo %s guardado%n%n", nombreEquipo);
        else
            out.printf("%n%nERROR : no se ha creado el fichero%n%n");
    }

    private void guardarEquipos() throws FileNotFoundException {
        boolean exito;
        exito = c.guardarEquipos();
        if(exito)
            out.printf("%n%nEquipos guardados%n%n");
        else
            out.printf("%n%nERROR : no se ha creado el fichero%n%n");
    }

    private void guardarClasificacion() throws FileNotFoundException {
        boolean exito;
        int numJornada;
        do {
            numJornada = readInt("%nIntroduce el número de la jornada de la cual quiere mostar su clasifiación (1,2,3,...) : ");
            if(c.estaCargada(numJornada) == -1){
                out.printf("%n%nTodavía no hay ninguna joranda cargada%n%n");
                return;
            }
            if (c.estaCargada(numJornada) == -2){
                out.printf("%n%nLa jornada %d aún no se ha cargado, intente con otra jornada%n%n", numJornada);
            }
        } while (c.estaCargada(numJornada) != 0);
        exito = c.guardarClasificacion(numJornada);
        if(exito)
            out.printf("%n%nClasificación de la jornada %d guardada%n%n", numJornada);
        else
            out.printf("%n%nERROR : no se ha creado el fichero%n%n");     
    }

}
