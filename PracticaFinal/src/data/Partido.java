/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;

public class Partido implements Serializable {
    
    // Atributos
    String local;
    String visitante;
    int puntosLocal;
    int puntosVisitante;
    String fecha;
    String hora;
    
    // Getters and Setters
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    // Constructors
    public Partido(String local, String visitante, int puntosLocal, int puntosVisitante, String fecha, String hora) {
        this.local = local;
        this.visitante = visitante;
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    public Partido() {
        this.local = "Desconocido";
        this.visitante = "Desconocido";
        this.puntosLocal = 0;
        this.puntosVisitante = 0;
        this.fecha = "XX/XX/XXXX";
        this.hora = "XX:XX";
    }
    
}
