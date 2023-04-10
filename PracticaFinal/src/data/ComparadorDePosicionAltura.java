/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;
import java.util.Comparator;

public class ComparadorDePosicionAltura implements Comparator<Jugadora>, Serializable{
    
    @Override
    public int compare(Jugadora j1, Jugadora j2) {
        
        if(j1.posicion.compareTo(j2.posicion) == 0){
            if(j1.altura > j2.altura)
                return -1;
            else if(j1.altura < j2.altura)
                return 1;
            else
                return 0;
        }
        else
            return j1.posicion.compareTo(j2.posicion);
    }
}
