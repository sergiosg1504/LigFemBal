/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;

public class Jugadora implements Serializable {

    // Atributos
    String nombre;
    String posicion;
    int dorsal;
    Nacimiento nac;
    String nacionalidad;
    int altura;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Nacimiento getNacimiento() {
        return nac;
    }

    public void setNacimiento(Nacimiento nac) {
        this.nac = nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    // Constructors
    public Jugadora(String nombre, String posicion, int dorsal, Nacimiento nac, 
            String nacionalidad, int altura) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.nac = nac;
        this.nacionalidad = nacionalidad;
        this.altura = altura;
    }

    public Jugadora() {
        this.nombre = "Desconocido";
        this.posicion = "Desconocida";
        this.dorsal = 0;
        this.nac = new Nacimiento();
        this.nacionalidad = "Desconocida";
        this.altura = 0;
    }

    // FactoryMethods
    public static Jugadora factoryMethod(String cadena) {
        int identificador, height;
        String[] partes = cadena.split("\t");
        if (partes[1].equals("")) {
            partes[1] = "Desconocida";
        }
        if (partes[2].equals("-")) {
            identificador = 0;
        } else {
            identificador = Integer.parseInt(partes[2]);
        }
        if (partes.length == 6) {
            if (partes[5].equals("-")) {
                height = 0;
            } else {
                height = Integer.parseInt(partes[5]);
            }
        } else {
            height = 0;
        }
        Nacimiento nac = Nacimiento.factoryMethod(partes[3]);
        return new Jugadora(partes[0], partes[1], identificador, nac, partes[4], height);
    }

    public static Jugadora factoryMethod(String nombreJugadora, String posicion,
            int dorsal, String fecha, String ciudad, String provincia, 
            String nacionalidad, int altura) {
        Nacimiento nac = Nacimiento.factoryMethod(fecha, ciudad, provincia);
        return new Jugadora(nombreJugadora, posicion, dorsal, nac, nacionalidad, altura);
    }
}
