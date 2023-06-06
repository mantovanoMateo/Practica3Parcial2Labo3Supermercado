package Models;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEspera <T>{
    Queue<T> filaGenerica;

    public FilaEspera() {
        filaGenerica=new LinkedList<>();
    }

    public void agregar(T nuevo){
        this.filaGenerica.add(nuevo);
    }

    public T desEncolar(){
        return this.filaGenerica.poll();
    }

    @Override
    public String toString() {
        return "FilaEspera{" +
                "filaGenerica=" + filaGenerica +
                '}';
    }

    public Boolean vacia(){
        if(filaGenerica.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
