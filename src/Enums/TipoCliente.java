package Enums;

public enum TipoCliente {
    COMUN,
    JUBILADO,
    EMBARAZADA;

    public static Double sumarTiempoPorTipo(Double tiempo,TipoCliente tipo) {
        if(tipo==COMUN){
            tiempo*=1.15;
        }else if(tipo==JUBILADO){
            tiempo*=1.25;
        } else {
            tiempo*=1.2;
        }
        return tiempo;
    }
}



