/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;

public class Datos_equipo implements Serializable {
    
    // Atributos
    String nomEquipo;
    int pj;
    int pg;
    int pp;
    int pf;
    int pc;
    int pclas;
    
    // Getters y Setters
    public String getNomEquipo() {
        return nomEquipo;
    }

    public void setNomEquipo(String nomEquipo) {
        this.nomEquipo = nomEquipo;
    }

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj = pj;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPclas() {
        return pclas;
    }

    public void setPclas(int pclas) {
        this.pclas = pclas;
    }
    
    // Constructors
    public Datos_equipo(String nomEquipo, int pj, int pg, int pp, int pf, int pc, int pclas) {
        this.nomEquipo = nomEquipo;
        this.pj = pj;
        this.pg = pg;
        this.pp = pp;
        this.pf = pf;
        this.pc = pc;
        this.pclas = pclas;
    }
    
    public Datos_equipo(String nomEquipo) {
        this.nomEquipo = nomEquipo;
        this.pj = 0;
        this.pg = 0;
        this.pp = 0;
        this.pf = 0;
        this.pc = 0;
        this.pclas = 0;
    }
    
    public Datos_equipo() {
        this.nomEquipo = "Desconocido";
        this.pj = 0;
        this.pg = 0;
        this.pp = 0;
        this.pf = 0;
        this.pc = 0;
        this.pclas = 0;
    }
    
    
}
