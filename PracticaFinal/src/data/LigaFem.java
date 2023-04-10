/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LigaFem implements Serializable {
    
    final int NUM_DATOS_JORNADA = 15;
    int temporadasCargadas = 0;
    // Atributos
    String temporada;
    ArrayList<Jornada> jornadas;
    ArrayList<Equipo> equipos;
    
    // Getters y Setters
    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public ArrayList<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(ArrayList<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    public int getNUM_DATOS_JORNADA() {
        return NUM_DATOS_JORNADA;
    }
    
    public int getTemporadasCargadas() {
        return temporadasCargadas;
    }

    public void setTemporadasCargadas(int temporadasCargadas) {
        this.temporadasCargadas = temporadasCargadas;
    }
    
    // Constructors
    public LigaFem(String temporada, ArrayList<Jornada> jornadas, ArrayList<Equipo> equipos) {
        this.temporada = temporada;
        this.jornadas = jornadas;
        this.equipos = equipos;
    }
    
    public LigaFem() {
        this.temporada = "XXXX/XXXX";
        this.jornadas = new ArrayList();
        this.equipos = new ArrayList();
    }

    // Ordenación por números de teléfono
    public void sortByTelefono(){
        Comparator <Equipo> j = new ComparadorDeTelefono();
        Collections.sort(this.equipos, j);
    }

}
