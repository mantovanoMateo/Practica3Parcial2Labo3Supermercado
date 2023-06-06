import Enums.MetodoDePago;
import Enums.TipoCliente;
import Models.Caja;
import Models.Cliente;
import Models.FilaEspera;
import Excepciones.masDeTresProblemasException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //Un supermercado necesita un módulo de simulación de las filas de las cajas. Necesita
        //calcular el tiempo medio de espera de cada cliente. Cada cliente tiene un medio de
        //pago, un tipo de cliente y la cantidad de artículos. Cada atributo influye en el tiempo que
        //permanecerá en caja.

        FilaEspera fila=new FilaEspera<>();
        TipoCliente[] tipos=TipoCliente.values();
        MetodoDePago[] metodoDePagos=MetodoDePago.values();
        Caja[] cajas = new Caja[5];
        Random rand=new Random();

        for(Integer i=0;i<30;i++){
            fila.agregar(new Cliente(metodoDePagos[rand.nextInt(0,metodoDePagos.length)],tipos[rand.nextInt(0,tipos.length)],rand.nextInt(0,20)));
        }

        for (int i=0;i<5;i++){
            cajas[i]=new Caja();
        }

        int index=0;

        System.out.println(fila.toString());

        //Los clientes se agrupan en una colección de cajas y cada caja tiene un
        //comportamiento de fila. Se agrega al final, se extrae al principio. Desarrolle los métodos
        //de la colección y de cada caja para brindar las funciones necesarias para el
        //comportamiento básico.
        //Los clientes deben pasar desde una colección genérica llamada “fila_espera”
        //hacia el arreglo de cajas. Para agrupar los clientes en las cajas se debe buscar un
        //equilibrio tanto en la cantidad de clientes como en los tiempos de espera de cada uno.

        Cliente cliente=null;
        while (!fila.vacia()){
            cliente=(Cliente)fila.desEncolar();
            cajas[index].agregar(cliente);
            if(index==cajas.length-1){
                index=0;
            }else {
                index++;
            }
        }

        System.out.println("Mostramos las cajas despues de pasarlas de la fila");
        for (Caja caja: cajas){
            System.out.println(caja.toString());
        }

        //Exportar el contenido de cada una de las cajas en un jsonarray.
        File file=new File("archivoDeCajitas.json");
        ObjectMapper mapper=new ObjectMapper();
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,cajas);
        }catch (IOException e){
            System.out.println("No lo pudo escribir");
        }

        System.out.println(atender(cajas));

        System.out.println("Comprobamos que se hayan vaciados las cajas luego de atender");
        for (Caja caja: cajas){
            System.out.println(caja.toString());
        }


        System.out.println("Ahora procederemos a intentar leer el archivo");
        try {
            cajas=mapper.readValue(file, Caja[].class);
        }catch (IOException e){
          System.out.println("no lo pudo leer rey");
          e.printStackTrace();
        }

        System.out.println("Si fue leido bien aqui deberia mostrarse el contenido del mismo");
        for (Caja caja: cajas){
           System.out.println(caja.toString());
        }


    }

    //Desarrolle un método atender() que elimine a todos los clientes de todas las
    //cajas calculando los tiempos. Tenga en cuenta que el tiempo del segundo cliente es
    //igual a su tiempo + el tiempo del primero. y así sucesivamente. arrojar resultados por
    //caja y en general.
    public static String atender(Caja[] cajas){
        String resultados="";
        int i=0;
        for(Caja caja: cajas){
            try{
                if (caja.getCantDeProbelmas()>3){
                    throw new masDeTresProblemasException();
                }
                Double tiempoTotalDeLaCaja=0.0;
                for (Double tiempo: caja.getTiempos()){
                    tiempoTotalDeLaCaja+=tiempo;
                }
                Double cantidad=(double)caja.getTiempos().stream().count();
                resultados+="La caja "+i+" tiene un tiempo promedio de "+String.format("%.2f",tiempoTotalDeLaCaja/cantidad)+" segundos\n";
                caja.vaciarCaja();

            } catch (masDeTresProblemasException e) {
                System.out.println("La caja "+ i+" supero los tres problemas");
            }finally {
                i++;
            }
        }

        return resultados;
    }

}

