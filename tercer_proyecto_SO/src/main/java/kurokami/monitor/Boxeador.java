/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurokami.monitor;

/**
 *
 * @author marcos
 */
public class Boxeador implements Runnable{
     
    private Monitor monitor;
    private Thread hilo;
    private int id;
    private int guantesUsando;
    private int tiempo;

    public Boxeador(Monitor monitor, Thread hilo, int id, int guantesUsando, int tiempo) {
        this.monitor = monitor;
        this.hilo = hilo;
        this.id = id;
        this.guantesUsando = guantesUsando;
        this.tiempo = tiempo;
    }

   
    
    public synchronized void Consumir(){
        
         try{
            System.out.println("Boxeador "+ id + " esta peleando");
            int espera = (int)(Math.random()*1000);
            System.out.println("El tiempo de boxeo es de: " + (espera/1000));
            hilo.sleep(espera);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public synchronized void Esperar(){
        try{
            System.out.println("Boxeador "+ id + " esta esperando");
            int espera = (int)(Math.random()*1000);
            System.out.println("Tiempo de espera: " + (espera/1000));
            hilo.sleep(espera);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }  
 
    public void run(){
        while(true){
            monitor.agregarBoxeador();
            Esperar();
            monitor.atenderBoxeador();
            Consumir();
            monitor.devolverGuantes();
        }
    }
}
