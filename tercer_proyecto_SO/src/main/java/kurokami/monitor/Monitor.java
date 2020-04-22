/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurokami.monitor;

import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public class Monitor {
    private ArrayList<Boxeador> pendientes;
    private int totalGuantes;
    private int numeroOponentes;
    private int guantesDisponibles;
            
    public Monitor(int totalGuantes, int numeroOponentes){
        this.totalGuantes = totalGuantes;
        this.guantesDisponibles = totalGuantes;
        this.pendientes = new ArrayList();
        this.numeroOponentes = numeroOponentes;
    }

    public ArrayList<Boxeador> getPendientes() {
        return pendientes;
    }

    public int getTotalGuantes() {
        return totalGuantes;
    }

    public int getNumeroOponentes() {
        return numeroOponentes;
    }

    public void setTotalGuantes(int totalGuantes) {
        this.totalGuantes = totalGuantes;
    }

    public void setNumeroOponentes(int numeroOponentes) {
        this.numeroOponentes = numeroOponentes;
    }

    public void setGuantesDisponibles(int guantesDisponibles) {
        this.guantesDisponibles = guantesDisponibles;
    }

    
    public int getGuantesDisponibles() {
        return guantesDisponibles;
    }
    public synchronized void devolverGuantes(){
        this.guantesDisponibles += 2;
        if (guantesDisponibles == 3 || this.guantesDisponibles == 2){
            notify();
        }
    }
    public synchronized Boxeador atenderBoxeador(){
        while(this.guantesDisponibles < 2 || pendientes.isEmpty()){
            try{
                wait();
            }catch(InterruptedException ex){}
        }
        this.guantesDisponibles -= 2;
        Boxeador regresar = pendientes.remove(0);
        return regresar;
    }
    public synchronized void agregarBoxeador(Boxeador nuevoBoxeador){
        pendientes.add(nuevoBoxeador);
        if(pendientes.size() == 1){
            notify();
        }
    }
}
