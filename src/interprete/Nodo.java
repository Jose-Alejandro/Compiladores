package interprete;

public class Nodo {
    
    String simbolo;
    Nodo ApAbajo = null;
    Nodo ApSiguiente = null;
    boolean fin;
    
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
    
}
