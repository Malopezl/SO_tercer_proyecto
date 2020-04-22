/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import kurokami.monitor.*;

/**
 *
 * @author marcos
 */
public class actualizador extends Thread {
    private consumidor_t consumidor;
    private Monitor monitor;
    private ArrayList<Boxeador> atendiendo;
    private ArrayList<Boxeador> pendientes;
    public actualizador(Monitor monitor, consumidor_t consumidor){
        this.consumidor = consumidor;
        this.monitor = monitor;
    }
    @Override
    public void run(){
        while(true){
            atendiendo = consumidor.getBoxeadores();
            pendientes = monitor.getPendientes();
            DefaultListModel activosID = new DefaultListModel();
            DefaultListModel activosTiempo = new DefaultListModel();
            DefaultListModel inactivos = new DefaultListModel();
            if(!atendiendo.isEmpty())
            {
                for(Boxeador p : atendiendo){
                    activosID.addElement(p.getIdent());
                    activosTiempo.addElement(p.getTiempo());
                }
                
            }
            if(!pendientes.isEmpty()){
                for(Boxeador p: pendientes){
                    inactivos.addElement(p.getIdent());
                }
            }
            Consumidor.lista_boxeadores_at.setModel(activosID);
            Consumidor.tiempoRestante.setModel(activosTiempo);
            Consumidor.colaBoxeadores.setModel(inactivos);
            try {
                this.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(actualizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
}
