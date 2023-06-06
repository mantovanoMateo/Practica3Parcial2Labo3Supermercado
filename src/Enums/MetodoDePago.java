package Enums;

public enum MetodoDePago {
    EFECTIVO,
    TARJETASINPROBLEMAS,
    TARJETACONPROBLEMAS;

    public  static Double tiempoSegunMetodoDePago(Double tiempo, MetodoDePago metodo){
        if(metodo==EFECTIVO){
            tiempo*=1.1;
        } else if (metodo==TARJETASINPROBLEMAS) {
            tiempo*=1.15;
        }else {
            tiempo*=1.5;
        }
        return tiempo;
    }
}
