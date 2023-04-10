/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */
package controller;

import static com.coti.tools.Rutas.*;
import data.Datos_equipo;
import data.Equipo;
import data.Jornada;
import data.Jugadora;
import data.LigaFem;
import data.Partido;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller {

    LigaFem m = new LigaFem();

    public boolean binariosVacia() {
        File f = new File(pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "binarios" + File.separator + "model.bin");
        return !f.exists();
    }

    public boolean cargarModelo() {
        File f = new File(pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "binarios" + File.separator + "model.bin");
        FileInputStream fis;
        BufferedInputStream bis;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            m = (LigaFem) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException | ClassNotFoundException ex) {
            return false;
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    return false;
                }
            }
        }
        return true;
    }

    public void storeTemporada(String temporada) {
        m.setTemporada(temporada);
    }

    public void cargarJornadas() throws IOException {
        List<String> datos;
        File datosJornada = fileToFileInFolderOnDesktop("LigFemBal", "datosjornadas.txt");
        datos = Files.readAllLines(datosJornada.toPath());
        for (String s : datos) {
            m.getJornadas().add(Jornada.factoryMethod(s));
        }
    }

    public void cargarEquipos() throws IOException {
        List<String> datos;
        File datosEquipo = fileToFileInFolderOnDesktop("LigFemBal", "datosequipos.txt");
        datos = Files.readAllLines(datosEquipo.toPath());
        for (String s : datos) {
            m.getEquipos().add(Equipo.factoryMethod(s));
        }
    }

    public void cargarJugadoras() throws IOException {
        List<String> datos;
        ArrayList<Equipo> equipos = m.getEquipos();
        String ruta = pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "jugadoras" + File.separator;
        File f;
        String[] nombresEquipos = new String[equipos.size()];
        for (int i = 0; i < equipos.size(); i++) {
            nombresEquipos[i] = equipos.get(i).getNombre().toUpperCase().replace('Á', 'A').replace('Í', 'I');
            f = new File(ruta + nombresEquipos[i] + ".txt");
            datos = Files.readAllLines(f.toPath());
            for (String s : datos) {
                m.getEquipos().get(i).getJugadoras().add(Jugadora.factoryMethod(s));
            }
        }
    }

    public String[] nombresEquipos() {
        String[] nombresEquipos = new String[m.getEquipos().size()];
        for (int i = 0; i < m.getEquipos().size(); i++) {
            nombresEquipos[i] = m.getEquipos().get(i).getNombre();
        }
        return nombresEquipos;
    }

    public boolean equipoValido(String nombreEquipo) {
        String[] nombresEquipos = this.nombresEquipos();
        for (int i = 0; i < nombresEquipos.length; i++) {
            if (nombreEquipo.equals(nombresEquipos[i])) {
                return true;
            }
        }
        return false;
    }

    public String[] nombresJugadorasDelEquipo(String nombreEquipo) {
        ArrayList<Equipo> equipos = m.getEquipos();
        int i = 0;
        while (!nombreEquipo.equals(equipos.get(i).getNombre())) {
            i++;
        }
        String[] nombresJugadoras = new String[equipos.get(i).getJugadoras().size()];
        for (int j = 0; j < equipos.get(i).getJugadoras().size(); j++) {
            nombresJugadoras[j] = equipos.get(i).getJugadoras().get(j).getNombre();
        }
        return nombresJugadoras;
    }

    public boolean jugadoraValida(String nombreEquipo, String nombreJugadora) {
        String[] nombresJugadoras = this.nombresJugadorasDelEquipo(nombreEquipo);
        for (String nombresJugadora : nombresJugadoras) {
            if (nombreJugadora.equals(nombresJugadora)) {
                return true;
            }
        }
        return false;
    }

    public boolean posicionValida(String posicion) {
        posicion = posicion.toUpperCase().replace('Í', 'I');
        return (posicion.equals("ALA-PIVOT") || posicion.equals("PIVOT")
                || posicion.equals("BASE") || posicion.equals("ESCOLTA")
                || posicion.equals("ALERO"));
    }

    public void cambiarPosicion(String nombreEquipo, String nombreJugadora, String nuevaPosicion) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).setPosicion(nuevaPosicion);
                    }
                }
            }
        }
    }

    public void cambiarDorsal(String nombreEquipo, String nombreJugadora, int nuevoDorsal) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).setDorsal(nuevoDorsal);
                    }
                }
            }
        }
    }

    public void cambiarFechaNacimiento(String nombreEquipo, String nombreJugadora, String nuevaFechaNacimiento) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).getNacimiento().setFecha(nuevaFechaNacimiento);
                    }
                }
            }
        }
    }

    public void cambiarCiudadNacimiento(String nombreEquipo, String nombreJugadora, String nuevaCiudadNacimiento) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).getNacimiento().setCiudad(nuevaCiudadNacimiento);
                    }
                }
            }
        }
    }

    public void cambiarProvinciaNacimiento(String nombreEquipo, String nombreJugadora, String nuevaProvinciaNacimiento) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).getNacimiento().setProvincia(nuevaProvinciaNacimiento);
                    }
                }
            }
        }
    }

    public void cambiarNacionalidad(String nombreEquipo, String nombreJugadora, String nuevaNacionalidad) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).setNacionalidad(nuevaNacionalidad);
                    }
                }
            }
        }
    }

    public void cambiarAltura(String nombreEquipo, String nombreJugadora, int nuevaAltura) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().get(i).setAltura(nuevaAltura);
                    }
                }
            }
        }
    }

    public void eliminarJugadora(String nombreEquipo, String nombreJugadora) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                for (int i = 0; i < e.getJugadoras().size(); i++) {
                    if (nombreJugadora.equals(e.getJugadoras().get(i).getNombre())) {
                        e.getJugadoras().remove(i);
                    }
                }
            }
        }
    }

    public void aniadirJugadora(String nombreEquipo, String nombreJugadora,
            String posicion, int dorsal, String fecha, String ciudad,
            String provincia, String nacionalidad, int altura) {
        for (Equipo e : m.getEquipos()) {
            if (nombreEquipo.equals(e.getNombre())) {
                e.getJugadoras().add(Jugadora.factoryMethod(nombreJugadora,
                        posicion, dorsal, fecha, ciudad, provincia, nacionalidad, altura));
            }
        }
    }

    private String numeroIntToString(int numJornada) {
        String[] numerosString = {"uno", "dos", "tres", "cuatro", "cinco",
            "seis", "siete", "ocho", "nueve", "diez",
            "once", "doce", "trece", "catorce", "quince"};
        return numerosString[numJornada - 1];
    }

    public boolean existenResulJornada(int numJornada) {
        return !(numJornada < 1 || numJornada > m.getNUM_DATOS_JORNADA());
    }

    public int estaCargada(int numJornada) {
        if (m.getTemporadasCargadas() <= 0) {
            return -1;
        }
        if (numJornada <= m.getTemporadasCargadas() && numJornada > 0) {
            return 0;
        } else {
            return -2;
        }
    }

    public void cargarResulJornada(int numJornada) throws IOException {
        List<String> datos;
        m.setTemporadasCargadas(numJornada);
        String ruta = pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "resul_jornadas" + File.separator
                + this.numeroIntToString(numJornada) + ".txt";
        File datosJornada = new File(ruta);
        datos = Files.readAllLines(datosJornada.toPath());
        Jornada j = m.getJornadas().get(numJornada - 1);
        for (String s : datos) {
            String[] partes = s.split("=");
            for (int i = 0; i < j.getPartidos().size(); i++) {
                if (partes[0].equals(j.getPartidos().get(i).getLocal())
                        && partes[1].equals(j.getPartidos().get(i).getVisitante())) {
                    j.getPartidos().get(i).setPuntosLocal(Integer.parseInt(partes[2]));
                    j.getPartidos().get(i).setPuntosVisitante(Integer.parseInt(partes[3]));
                }
            }
        }
    }

    public void calcularClasificacion(int numJornada) throws IOException {
        // Vacío la clasificación de cada jornada (por si se usa la opción mas de una vez)
        for(int i = 0 ; i < m.getJornadas().size() ; i++){
            m.getJornadas().get(i).getClasifiacion().clear();
        }
        for (int i = 0; i < numJornada; i++) {
            // Cargo los puntos de los partidos de la jornada
            this.cargarResulJornada(i + 1);
            // Si es la primera jornada cargo los nombres de los equipos en clasifiación
            if (i == 0) {
                this.cargarClasifiacion(i);
            } 
            // Si no es la primera jornada copio la clasifiación de la jornada anterior en la actual
            else {
                this.clonarClasificacion(i);            
            }
            for (int j = 0; j < m.getJornadas().get(i).getPartidos().size(); j++) {
                Partido p = m.getJornadas().get(i).getPartidos().get(j);
                if (p.getPuntosLocal() > p.getPuntosVisitante()) {
                    // si gana el local
                    for (int k = 0; k < m.getJornadas().get(i).getClasifiacion().size(); k++) {
                        Datos_equipo clas = m.getJornadas().get(i).getClasifiacion().get(k);
                        if (clas.getNomEquipo().equals(p.getLocal())) {
                            // Se encuentra el equipo local, que ha ganado
                            clas.setPj(clas.getPj() + 1);
                            clas.setPg(clas.getPg() + 1);
                            clas.setPf(clas.getPf() + p.getPuntosLocal());
                            clas.setPc(clas.getPc() + p.getPuntosVisitante());
                            clas.setPclas(2 * clas.getPg() + clas.getPp());
                        }
                        if (clas.getNomEquipo().equals(p.getVisitante())) {
                            // Sencuentra el equipo visitante, que ha perdido
                            clas.setPj(clas.getPj() + 1);
                            clas.setPp(clas.getPp() + 1);
                            clas.setPf(clas.getPf() + p.getPuntosVisitante());
                            clas.setPc(clas.getPc() + p.getPuntosLocal());
                            clas.setPclas(2 * clas.getPg() + clas.getPp());
                        }
                    }
                }
                if (p.getPuntosVisitante() > p.getPuntosLocal()) {
                    // si gana el visitante
                    for (int k = 0; k < m.getJornadas().get(i).getClasifiacion().size(); k++) {
                        Datos_equipo clas = m.getJornadas().get(i).getClasifiacion().get(k);
                        if (clas.getNomEquipo().equals(p.getLocal())) {
                            // Se encuentra el equipo local, que ha perdido
                            clas.setPj(clas.getPj() + 1);
                            clas.setPp(clas.getPp() + 1);
                            clas.setPf(clas.getPf() + p.getPuntosLocal());
                            clas.setPc(clas.getPc() + p.getPuntosVisitante());
                            clas.setPclas(2 * clas.getPg() + clas.getPp());
                        }
                        if (clas.getNomEquipo().equals(p.getVisitante())) {
                            // Se encuentra el equipo visitante, que ha ganado
                            clas.setPj(clas.getPj() + 1);
                            clas.setPg(clas.getPg() + 1);
                            clas.setPf(clas.getPf() + p.getPuntosVisitante());
                            clas.setPc(clas.getPc() + p.getPuntosLocal());
                            clas.setPclas(2 * clas.getPg() + clas.getPp());
                        }
                    }
                }
            }
        }
    }

    private void cargarClasifiacion(int numJornada) {
        for (int i = 0; i < m.getEquipos().size(); i++) {
            Datos_equipo eq = new Datos_equipo(m.getEquipos().get(i).getNombre().toUpperCase().replace('Á', 'A').replace('Í', 'I'));
            m.getJornadas().get(numJornada).getClasifiacion().add(eq);
        }
    }

    private void clonarClasificacion(int i) {
        for(int j = 0 ; j < m.getJornadas().get(i-1).getClasifiacion().size() ; j++){
                    Datos_equipo anterior = m.getJornadas().get(i-1).getClasifiacion().get(j);
                    m.getJornadas().get(i).getClasifiacion().add(new Datos_equipo(anterior.getNomEquipo(),
                            anterior.getPj(), anterior.getPg(), anterior.getPp(), 
                            anterior.getPf(), anterior.getPc(), anterior.getPclas()));
                } 
    }
    
    public void cambiarFechaJornada(int numJornada, String nuevaFecha) {
        m.getJornadas().get(numJornada - 1).setFecha(nuevaFecha);
    }

    public boolean existeEquipoClasificacion(int numJornada, String nombreEquipo) {
        nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
        for (int i = 0; i < m.getJornadas().get(numJornada).getPartidos().size(); i++) {
            if ((nombreEquipo.equals(m.getJornadas().get(numJornada).getPartidos().get(i).getLocal())
                    || (nombreEquipo.equals(m.getJornadas().get(numJornada).getPartidos().get(i).getVisitante())))) {
                return true;
            }
        }
        return false;
    }

    public void cambiarFechaPartido(int numJornada, String nombreEquipo, String nuevoDato) {
        nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
        for (int i = 0; i < m.getJornadas().get(numJornada).getPartidos().size(); i++) {
            if ((nombreEquipo.equals(m.getJornadas().get(numJornada).getPartidos().get(i).getLocal())
                    || (nombreEquipo.equals(m.getJornadas().get(numJornada).getPartidos().get(i).getVisitante())))) {
                m.getJornadas().get(numJornada).getPartidos().get(i).setFecha(nuevoDato);
            }
        }
    }

    public void cambiarHoraPartido(int numJornada, String nombreEquipo, String nuevoDato) {
        nombreEquipo = nombreEquipo.toUpperCase().replace('Á', 'A').replace('Í', 'I');
        for (int i = 0; i < m.getJornadas().get(numJornada).getPartidos().size(); i++) {
            if ((nombreEquipo.equals(m.getJornadas().get(numJornada).getPartidos().get(i).getLocal())
                    || (nombreEquipo.equals(m.getJornadas().get(numJornada).getPartidos().get(i).getVisitante())))) {
                m.getJornadas().get(numJornada).getPartidos().get(i).setHora(nuevoDato);
            }
        }
    }

    public ArrayList<Partido> mostrarPartidosJornada(int numJornada) {
        return m.getJornadas().get(numJornada).getPartidos();
    }

    public ArrayList<Datos_equipo> mostrarClasificacionJornada(int numJornada) {
        m.getJornadas().get(numJornada).sortByPclas();
        return m.getJornadas().get(numJornada).getClasifiacion();
    }

    public ArrayList<Jugadora> mostrarJugadorasPosicionAltura(String nombreEquipo) {
        for (int i = 0; i < m.getEquipos().size(); i++) {
            if (nombreEquipo.equals(m.getEquipos().get(i).getNombre())) {
                m.getEquipos().get(i).sortByPosicionAltura();
                return m.getEquipos().get(i).getJugadoras();
            }
        }
        return null;
    }

    public ArrayList<Equipo> mostrarEquipos() {
        //m.sortByTelefono();
        m.getEquipos().sort(Comparator.comparing(Equipo::getTelefono));
        return m.getEquipos();
    }

    public ArrayList<Jugadora> mostrarJugadorasInicial(String inicial) {
        Equipo e = new Equipo();
        for (int i = 0; i < m.getEquipos().size(); i++) {
            for (int j = 0; j < m.getEquipos().get(i).getJugadoras().size(); j++) {
                if (m.getEquipos().get(i).getJugadoras().get(j).getNombre().startsWith(inicial)) {
                    e.getJugadoras().add(m.getEquipos().get(i).getJugadoras().get(j));
                }
            }
        }
        e.getJugadoras().sort(Comparator.comparing(Jugadora::getPosicion).reversed().thenComparing(Jugadora::getAltura));
        return e.getJugadoras();
    }

    public boolean guardarJugadoras(String nombreEquipo) throws FileNotFoundException {
        String ruta = pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "fichsalida" + File.separator + nombreEquipo
                + ".enc";
        ArrayList<Jugadora> jugadoras = this.mostrarJugadorasPosicionAltura(nombreEquipo);
        try ( PrintWriter pw = new PrintWriter(ruta)) {
            pw.printf("%nJUGADORAS DEL %s%n%n", nombreEquipo);
            pw.printf("%-35s%-14s%-10s%-60s%-16s%-6s%n%n",
                    "NOMBRE", "POSICIÓN", "DORSAL",
                    "NACIMIENTO", "NACIONALIDAD", "ALTURA");
            for (int i = 0; i < jugadoras.size(); i++) {
                String nacimiento = jugadoras.get(i).getNacimiento().getFecha()
                        + " " + jugadoras.get(i).getNacimiento().getCiudad()
                        + " (" + jugadoras.get(i).getNacimiento().getProvincia() + ")";
                pw.printf("%-35s%-14s%-10d%-60s%-16s%-6d%n",
                        jugadoras.get(i).getNombre(),
                        jugadoras.get(i).getPosicion(),
                        jugadoras.get(i).getDorsal(),
                        nacimiento,
                        jugadoras.get(i).getNacionalidad(),
                        jugadoras.get(i).getAltura());
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }

    public boolean guardarEquipos() throws FileNotFoundException {
        String ruta = pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "fichsalida" + File.separator + "equipos.enc";
        ArrayList<Equipo> equipos = this.mostrarEquipos();
        try ( PrintWriter pw = new PrintWriter(ruta)) {
            pw.printf("%nLISTA DE EQUIPOS:%n%n");
            pw.printf("%-40s%-12s%-45s%-20s%n%n", "NOMBRE", "TELÉFONO", "WEB", "E-MAIL");
            for (int i = 0; i < equipos.size(); i++) {
                pw.printf("%-40s%-12d%-45s%-20s%n",
                        equipos.get(i).getNombre(), equipos.get(i).getTelefono(),
                        equipos.get(i).getWeb(), equipos.get(i).getEmail());
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }

    public boolean guardarClasificacion(int numJornada) throws FileNotFoundException {
        String ruta = pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "fichsalida" + File.separator
                + "fich_html_" + numJornada + ".html";
        ArrayList<Datos_equipo> clasificacion = this.mostrarClasificacionJornada(numJornada - 1);
        try ( PrintWriter pw = new PrintWriter(ruta)) {
            pw.printf("<!DOCTYPE html>%n"
                    + "<HTML>%n"
                    + "<HEAD>%n"
                    + "<meta charset =\"UTF-8\">%n"
                    + "<H1>CLASIFICACION</H1>%n"
                    + "</HEAD>%n"
                    + "<BODY>");
            pw.printf("<TABLE BORDER=1>%n");
            String cabecera = String.format("<TR>"
                    + "<TD>Puesto</TD>"
                    + "<TD>EQUIPO</TD>"
                    + "<TD>PJ</TD>"
                    + "<TD>PG</TD>"
                    + "<TD>PP</TD>"
                    + "<TD>PF</TD>"
                    + "<TD>PC</TD>"
                    + "<TD>PTS</TD>"
                    + "</TR>");
            pw.printf(cabecera);
            for (int i = 0; i < clasificacion.size(); i++) {
                String fila = String.format("<TR>"
                        + "<TD>%d</TD>" // puesto
                        + "<TD>%s</TD>" // nombre del equipo
                        + "<TD>%d</TD>" // PJ
                        + "<TD>%d</TD>" // PG
                        + "<TD>%d</TD>" // PP
                        + "<TD>%d</TD>" // PF
                        + "<TD>%d</TD>" // PS
                        + "<TD>%d</TD>" // PTS
                        + "</TR>",
                        i + 1,
                        clasificacion.get(i).getNomEquipo(),
                        clasificacion.get(i).getPj(),
                        clasificacion.get(i).getPg(),
                        clasificacion.get(i).getPp(),
                        clasificacion.get(i).getPf(),
                        clasificacion.get(i).getPc(),
                        clasificacion.get(i).getPclas());
                pw.printf("%s", fila);
            }
            pw.printf("</TABLE>%n</BODY>%n</HTML>%n");
            pw.close();
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }

    public boolean guardarModelo() {
        File f = new File(pathToDesktop().toString() + File.separator + "LigFemBal"
                + File.separator + "binarios" + File.separator + "model.bin");
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(m);
            oos.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } finally {
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    return false;
                }
            }
        }
        return true;
    }
}
