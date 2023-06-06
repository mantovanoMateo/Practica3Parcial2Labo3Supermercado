package Models;

import Enums.MetodoDePago;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Caja implements Serializable {
    private Queue<Cliente> clientesEnCaja;
    private ArrayList<Double> tiempos;
    private Integer cantDeProbelmas;

    public Caja() {
        clientesEnCaja=new LinkedList<>();;
        tiempos=new ArrayList<>();
        cantDeProbelmas=0;
    }

    public void setClientesEnCaja(Queue<Cliente> clientesEnCaja) {
        this.clientesEnCaja = clientesEnCaja;
    }

    public void setTiempos(ArrayList<Double> tiempos) {
        this.tiempos = tiempos;
    }

    public void setCantDeProbelmas(Integer cantDeProbelmas) {
        this.cantDeProbelmas = cantDeProbelmas;
    }

    public void agregar(Cliente nuevo){
        if (clientesEnCaja.isEmpty()){
            clientesEnCaja.add(nuevo);
            tiempos.add(nuevo.calcularTiempo());
        }else {
            clientesEnCaja.add(nuevo);
            tiempos.add(nuevo.calcularTiempo()+tiempos.get(tiempos.size()-1));
        }
        if(nuevo.getMetodo()== MetodoDePago.TARJETACONPROBLEMAS){
            this.cantDeProbelmas+=1;
        }
    }

    public Cliente salirDeCaja(){
        return clientesEnCaja.poll();
    }

    public Integer getCantDeProbelmas() {
        return cantDeProbelmas;
    }

    public Queue<Cliente> getClientesEnCaja() {
        return clientesEnCaja;
    }

    public ArrayList<Double> getTiempos() {
        return tiempos;
    }

    public void vaciarCaja(){
        this.cantDeProbelmas=0;
        this.clientesEnCaja.clear();
        this.tiempos.clear();
    }

    @Override
    public String toString() {
        return "Caja{" +
                "clientesEnCaja=" + clientesEnCaja +
                ", tiempos=" + tiempos +
                ", cantDeProbelmas=" + cantDeProbelmas +
                '}'+"\n\n";
    }
}
