/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Jornada implements Serializable {
    
    // Atributos
    int numJornada;
    String fecha;
    ArrayList<Partido> partidos;
    ArrayList<Datos_equipo> clasificacion;
    
    // Getters y Setters
    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<Datos_equipo> getClasifiacion() {
        return clasificacion;
    }

    public void setClasificacion(ArrayList<Datos_equipo> clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    // Constructors
    public Jornada(int numJornada, String fecha, ArrayList<Partido> partidos, ArrayList<Datos_equipo> clasificacion) {
        this.numJornada = numJornada;
        this.fecha = fecha;
        this.partidos = partidos;
        this.clasificacion = clasificacion;
    }
    
    public Jornada(int numJornada, String fecha, ArrayList<Partido> partidos) {
        this.numJornada = numJornada;
        this.fecha = fecha;
        this.partidos = partidos;
        this.clasificacion = new ArrayList();
    }
    
    public Jornada() {
        this.numJornada = 0;
        this.fecha = "XX/XX/XXXX";
        this.partidos = new ArrayList();
        this.clasificacion = new ArrayList();
    }
    
    //FactoryMethod
    public static Jornada factoryMethod(String cadena){
        ArrayList <Partido> p = new ArrayList();
        String[] jornadas= cadena.split("\\+");
        int numeroJornada = Integer.parseInt(jornadas[0]);
        String[] partidos = jornadas[2].split("#");
        for (String partido : partidos) {
            String[] datosPartido = partido.split("\\$");
            Partido p1 = new Partido(datosPartido[0],datosPartido[1],0,0,datosPartido[2],datosPartido[3]);
            p.add(p1);
        }
        return new Jornada(numeroJornada,jornadas[1],p);
    }
    
    //Ordenacion por Pclas
    public void sortByPclas(){
        Comparator<Datos_equipo> c = new ComparadorDeJornada();
        Collections.sort(this.clasificacion, c);
    }
}
