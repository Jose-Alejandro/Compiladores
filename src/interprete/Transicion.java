/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

/**
 *
 * @author Fernando
 */
public class Transicion {
    
    char minSimb;
    char maxSimb;
    Estado estado;
    
    public Transicion(char s, Estado estado){
        minSimb = s;
        maxSimb = s;
        this.estado = estado;
    }
    
    public Transicion(char minSimb, char maxSimb, Estado estado){
        this.minSimb = minSimb;
        this.maxSimb = maxSimb;
        this.estado = estado;
    }

    public char getMinSimb() {
        return minSimb;
    }

    public void setMinSimb(char minSimb) {
        this.minSimb = minSimb;
    }

    public char getMaxSimb() {
        return maxSimb;
    }

    public void setMaxSimb(char maxSimb) {
        this.maxSimb = maxSimb;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
