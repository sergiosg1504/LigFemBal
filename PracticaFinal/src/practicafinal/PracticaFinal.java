/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package practicafinal;

import view.View;

public class PracticaFinal {

    public static void main(String[] args) throws Exception {
        View v = new View();
        
        v.runMenu("%n1.- Gestión de temporada"
                + "%n2.- Gestión de jugadoras"
                + "%n3.- Gestión de Jornada"
                + "%n4.- Visualización de resultados"
                + "%n5.- Almacenamiento de resultados"
                + "%n6.- Salida de la aplicación%n");
    }
}
