
package interprete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author stefan
 */
public class Afn {
    
    HashSet<Estado> estados;
    HashSet<Estado> estadosAceptacion;
    Estado estadoInicial;
    ArrayList<String> alfabeto ;
    
    public Afn() {
        this.alfabeto = new ArrayList();
        this.estados = new HashSet();
        this.estadosAceptacion = new HashSet();
        this.estadoInicial = new Estado();
    }
    
    public Afn(char c) {
        estados = new HashSet();
        estadosAceptacion = new HashSet();
        alfabeto = new ArrayList();
        alfabeto.add("" + c);
        this.setEstadoAceptacion();
    }
    
    public HashSet<Estado> cerraduraEpsilon(Estado e) {
        HashSet<Estado> estados1 = new HashSet();
        HashSet<Transicion> transiciones;
        Stack<Estado> pila = new Stack();
        Estado estado;
        pila.clear();
        pila.push(e);
        while(!pila.empty()){
            estado = pila.pop();
            if(!estados1.contains(estado)) {
                estados1.add(estado);
            }
            transiciones = estado.getTransiciones();
            //ciclo para verificar si existen transiciones epsilon en el estado
            for(int i = 0; i < transiciones.size(); i++) {
                System.out.println("Estado" + transiciones.iterator().next().getEstado());
                if(transiciones.iterator().next().getMaxSimb() == '|') {
                   pila.push(transiciones.iterator().next().getEstado());
                }
            }
        }
        return estados1;
    }
    
    public HashSet<Estado> cerraduraEpsilon(HashSet<Estado> conjunto) {
        HashSet<Estado> estados = new HashSet();
        for(int i = 0; i < conjunto.size(); i++) {
            estados.addAll(cerraduraEpsilon(conjunto.iterator().next()));
        }
        return estados;
    }
    
    public HashSet<Estado> mover(Estado e, char c) {
        HashSet<Estado> estados = new HashSet();
        HashSet<Transicion> transiciones = e.getTransiciones();
        //Ciclo para obtener transisiones epsilon de los estados
        for(int  i = 0; i < transiciones.size(); i++) {
            if(transiciones.iterator().next().getMinSimb() >= c && transiciones.iterator().next().getMaxSimb() <= c) {
                estados.add(transiciones.iterator().next().getEstado());
            }
        }
        return estados;
    }
    
    public HashSet<Estado> irA(HashSet<Estado> conjunto, char c) {
        HashSet<Estado> estados = new HashSet();
        for(int i = 0; i < conjunto.size(); i++) {
            estados.addAll(mover(conjunto.iterator().next(), c));
        }
        return cerraduraEpsilon(estados);
    }
    
    public boolean AnalizarCadena(String s) {
        HashSet<Estado> estados1, conjunto;
        int longitud;
        estados1 = cerraduraEpsilon(this.estadoInicial);
        System.out.println("Estado Inicial: ");
        this.estadoInicial.imprimirEstado();
        longitud = s.length();
        //System.out.println("Longitud: " + longitud);
        for(int i = 0; i < longitud; i++) {
            System.out.println("Símbolo a analizar: " + s.charAt(i));
            conjunto = irA(estados1, s.charAt(i));
            if(conjunto.isEmpty()) {
                System.out.println("Analizar 1");
                return false;
            }
        }
        for(int j = 0; j < estadosAceptacion.size(); j++) {
            if(estados1.contains(estadosAceptacion.iterator().next())) {
                System.out.println("Analizar 2");
                return true;
            }
        }
        System.out.println("Analizar 3");
        return false;
    }
    
    public Estado getEstadoInicial() { //Retorna el estado inicial del afn
        return this.estadoInicial;
    }
    
    public HashSet<Estado> getEstadosAceptacion() { //Retorna el conjunto de estados de aceptación del afn
        return this.estadosAceptacion;
    }
    
    public void setEstadoAceptacion(Estado e) {
        this.estadosAceptacion.add(e);
    }
    
    public void setEstadoAceptacion() {
        Estado eAcept = new Estado();
        this.estadosAceptacion.add(eAcept);
    }
    
    public Afn AfnBasico(char s) {
        Estado edoFin;
        //this.estadoInicial = new Estado();
        edoFin = new Estado();
        edoFin.setEstadoTrue();
        this.estadoInicial.setTransicion(s, edoFin);
        //this.alfabeto = new ArrayList();
        this.alfabeto.add("" + s);
        this.estados.add(this.estadoInicial);
        this.estados.add(edoFin);
        this.estadosAceptacion.add(edoFin);
        
        return this;
    }
    
    public Afn AfnBasico(char s, char s2) {
        Estado edoFin;
        this.estadoInicial = new Estado();
        edoFin = new Estado();
        edoFin.setEstadoTrue();
        this.estadoInicial.setTransicion(s, s2, edoFin);
        for(int i = s; i != s2; i++) {
            this.alfabeto.add("" + i);
        }
        this.estados.add(this.estadoInicial);
        this.estados.add(edoFin);
        this.estadosAceptacion.add(edoFin);
        
        return this;
    }
}
