/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Equipo implements Serializable {
    
    // Atributos
    String nombre;
    String direccion;
    int telefono;
    String web;
    String email;
    ArrayList<Jugadora> jugadoras;
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Jugadora> getJugadoras() {
        return jugadoras;
    }

    public void setJugadoras(ArrayList<Jugadora> jugadoras) {
        this.jugadoras = jugadoras;
    }
    
    // Constructors
    public Equipo(String nombre, String direccion, int telefono, String web, String email, ArrayList<Jugadora> jugadoras) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
        this.jugadoras = jugadoras;
    }
    
    public Equipo(String nombre, String direccion, int telefono, String web, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
        this.jugadoras = new ArrayList();
    }
    
    public Equipo() {
        this.nombre = "Desconocido";
        this.direccion = "Desconocida";
        this.telefono = 0;
        this.web = "Desconocida";
        this.email = "Desconocido";
        this.jugadoras = new ArrayList();
    }
    
    // FactoryMehtod
    public static Equipo factoryMethod(String cadena){
        String[] partes= cadena.split("#");
        int tel = Integer.parseInt(partes[2]);
        partes[0] = partes[0].toUpperCase().replace('Á', 'A').replace('Í', 'I');
        return new Equipo(partes[0],partes[1],tel,partes[3],partes[4]);
    }
    
    // Ordenación por posición y altura
    public void sortByPosicionAltura(){
        Comparator<Jugadora> j = new ComparadorDePosicionAltura();
        Collections.sort(this.jugadoras, j);
    }
    
    // Ordencación por fecha de nacimiento
    public void sortByNacimiento(){
        Comparator<Jugadora> j = new ComparadorDeNacimiento();
        Collections.sort(this.jugadoras, j);
    }
}
