/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurokami.monitor;

import consumidor.consumidor_t;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class Boxeador extends Thread{
     
    private Monitor monitor;
    private int id;
    private int guantesUsando;
    private int tiempo;
    private consumidor_t c;
    private int indice;

    public Boxeador(int id, int tiempo, Monitor monitor, consumidor_t c) {
        this.monitor = monitor;
        this.id = id;
        this.tiempo = tiempo;
        this.c = c;
        indice = 0;
    }
    public void run(){
//            int espera = (int)(Mathrandom()*1000);
            int espera = (int)(tiempo*1000);
            try {
                this.sleep(espera);
            } catch (InterruptedException ex) {
                Logger.getLogger(Boxeador.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.monitor.devolverGuantes();
            consumidor_t.boxeadores.remove(this);
    }

    public int getIdent() {
        return id;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
}