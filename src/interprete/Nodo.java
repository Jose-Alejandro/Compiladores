package interprete;

import java.util.ArrayList;

public class Nodo {
    
    String simbolo;
    Nodo ApAbajo = null;
    Nodo ApSiguiente = null;
    boolean fin;

    public Nodo getApAbajo() {
        return ApAbajo;
    }

    public void setApAbajo(Nodo ApAbajo) {
        this.ApAbajo = ApAbajo;
    }

    public Nodo getApSiguiente() {
        return ApSiguiente;
    }

    public void setApSiguiente(Nodo ApSiguiente) {
        this.ApSiguiente = ApSiguiente;
    }
    
    public Nodo(String simbolo, boolean fin) {
        this.simbolo = simbolo;
        this.fin = fin;
    }
    
    public Nodo(String simbolo, Nodo abajo, Nodo siguiente, boolean fin) {
        this.simbolo = simbolo;
        this.ApAbajo = abajo;
        this.ApSiguiente = siguiente;
        this.fin = fin;
    }
    
    public ArrayList<Nodo> getNodosAbajo() {
        ArrayList nodos = new ArrayList();
        Nodo n = this;
        nodos.add(n);
        while(n.ApAbajo != null) {
            nodos.add(n.ApAbajo);
            n = n.ApAbajo;
        }
        return nodos;
    }
    
}
