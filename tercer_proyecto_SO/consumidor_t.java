/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

import java.util.ArrayList;
import kurokami.monitor.*;

/**
 *
 * @author marcos, sharon
 */
public class consumidor_t extends Thread {
    public static ArrayList<Boxeador> boxeadores;
    private Monitor monitor;
    private boolean estado;
    public consumidor_t(Monitor monitor){
        this.monitor = monitor;
        boxeadores = new ArrayList<>();
       estado = true;
    }
    @Override
    public void run(){
        while(estado == true ){
            
            
            Boxeador nuevo = monitor.atenderBoxeador();
            nuevo.setIndice(boxeadores.size());
            System.out.println(boxeadores.size());
            boxeadores.add(nuevo);
            nuevo.start();
        }
    }

    public ArrayList<Boxeador> getBoxeadores() {
        return boxeadores;
    }
    
    public void actualizar(int numero){
        boxeadores.remove(numero);
    } 
}
