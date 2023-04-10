/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;

public class Nacimiento implements Serializable {

    // Atributos
    String fecha;
    String ciudad;
    String provincia;

    // Getters y setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    // Constructor
    public Nacimiento(String fecha, String ciudad, String provincia) {
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    public Nacimiento(String fecha) {
        this.fecha = fecha;
        this.ciudad = "Desconocida";
        this.provincia = "Desconocida";
    }

    public Nacimiento() {
        this.fecha = "Desconocida";
        this.ciudad = "Desconocida";
        this.provincia = "Desconocida";
    }

    // FactoryMethods
    static Nacimiento factoryMethod(String cadena) {
        if (cadena.endsWith(")")) {
            String fecha = cadena.substring(0, 10);
            cadena = cadena.substring(11, cadena.length());
            String[] partes = cadena.split("\\(");
            partes[1] = partes[1].substring(0, partes[1].length() - 1);
            return new Nacimiento(fecha, partes[0], partes[1]);
        } else {
            return new Nacimiento(cadena);
        }
    }

    static Nacimiento factoryMethod(String fecha, String ciudad, String provincia) {
        return new Nacimiento(fecha, ciudad, provincia);
    }
}
