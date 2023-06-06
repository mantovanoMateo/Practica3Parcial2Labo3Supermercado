package Models;

import Enums.MetodoDePago;
import Enums.TipoCliente;

import java.io.Serializable;

public class Cliente implements Serializable {
    private MetodoDePago metodo;
    private TipoCliente tipo;
    private Integer cantArticulos;

    public Cliente(){

    }
    public Cliente(MetodoDePago metodoDePago, TipoCliente tipo) {
    }

    public void setMetodo(MetodoDePago metodo) {
        this.metodo = metodo;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public Integer getCantArticulos() {
        return cantArticulos;
    }

    public void setCantArticulos(Integer cantArticulos) {
        this.cantArticulos = cantArticulos;
    }

    public Cliente(MetodoDePago metodo, TipoCliente tipo, Integer cantArticulos) {
        this.metodo = metodo;
        this.tipo = tipo;
        this.cantArticulos = cantArticulos;
    }

    public Double calcularTiempo(){
        Double tiempo=0.0;
        tiempo=(double) cantArticulos*30.0;
        tiempo=MetodoDePago.tiempoSegunMetodoDePago(tiempo,this.metodo);
        tiempo=TipoCliente.sumarTiempoPorTipo(tiempo,this.tipo);

        return tiempo;
    }

    public MetodoDePago getMetodo() {
        return metodo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "metodo=" + metodo +
                ", tipo=" + tipo +
                ", cantArticulos=" + cantArticulos +
                '}'+"\n";
    }
}
