/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;
import java.util.Comparator;

public class ComparadorDeTelefono implements Comparator<Equipo>, Serializable {

    @Override
    public int compare(Equipo e1, Equipo e2) {
        
        if(e1.telefono > e2.telefono)
            return 1;
        else if(e1.telefono < e2.telefono)
            return -1;
        else
            return 0;
    }
}
