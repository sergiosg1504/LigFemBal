/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;
import java.util.Comparator;

public class ComparadorDeJornada implements Comparator<Datos_equipo>, Serializable {

    @Override
    public int compare(Datos_equipo clas1, Datos_equipo clas2) {
        
        if(clas1.pclas > clas2.pclas)
            return -1;
        else if(clas1.pclas < clas2.pclas)
            return 1;
        else{
            if((clas1.pf-clas1.pc) > (clas2.pf-clas2.pc))
                return -1;
            else if((clas1.pf-clas1.pc) < (clas2.pf-clas2.pc))
                return 1;
            else
                return 0;
        }
    }
}
