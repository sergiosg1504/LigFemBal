/*
 * Sergio Sánchez García    70961594Q
 * sergiosg@usal.es
 */

package data;

import java.io.Serializable;
import java.util.Comparator;

public class ComparadorDeNacimiento implements Comparator<Jugadora>, Serializable {

    @Override
    public int compare(Jugadora j1, Jugadora j2) {
        
        String[] fecha1 = j1.nac.fecha.split("/");
        String[] fecha2 = j2.nac.fecha.split("/");
        fecha1[2] = fecha1[2].substring(0,4);
        fecha2[2] = fecha2[2].substring(0,4);
        int anio1 = Integer.parseInt(fecha1[2]);
        int anio2 = Integer.parseInt(fecha2[2]);
        int mes1 = Integer.parseInt(fecha1[1]);
        int mes2 = Integer.parseInt(fecha2[1]);
        int dia1 = Integer.parseInt(fecha1[0]);
        int dia2 = Integer.parseInt(fecha2[0]);
        if(anio1 > anio2)
            return -1;
        else if(anio1 < anio2)
            return 1;
        else{   // si los años son iguales comparo por mes
            if(mes1 > mes2)
                return -1;
            else if(mes1 < mes2)
                return 1;
            else{   // si los meses son iguales comparo por dia
                if(dia1 > dia2)
                    return -1;
                else if(dia1 < dia2)
                    return 1;
                else
                    return 0;
            }
        }
    }
}
