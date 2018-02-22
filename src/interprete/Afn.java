
package interprete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author stefan
 */
public class Afn {
    
    HashSet<Estado> estadosAceptacion;
    Estado estadoInicial;
    ArrayList<String> alfabeto ;
    
    public Afn(char c) {
        estadosAceptacion = new HashSet();
        alfabeto = new ArrayList();
        alfabeto.add("" + c);
        estadoInicial = new Estado();
        estadosAceptacion.add(new Estado());
        estadosAceptacion.iterator().next().setEstadoTrue();
    }
    
    public HashSet<Estado> cerraduraEpsilon(Estado e) {
        HashSet<Estado> estados = new HashSet();
        HashSet<Transicion> transiciones;
        Stack<Estado> pila = new Stack();
        Estado estado;
        pila.clear();
        pila.push(e);
        while(!pila.empty()){
            estado = pila.pop();
            if(!estados.contains(estado)) {
                estados.add(estado);
            }
            transiciones = estado.getTransiciones();
            //ciclo para verificar si existen transiciones epsilon en el estado
            for(int i = 0; i < transiciones.size(); i++) {
                if(transiciones.iterator().next().getMaxSimb() == '\00') {
                   pila.push(transiciones.iterator().next().getEstado());
                }
            }
        }
        return estados;
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
        HashSet<Estado> estados, conjunto;
        int longitud;
        estados = cerraduraEpsilon(this.estadoInicial);
        longitud = s.length();
        for(int i = 0; i < longitud; i++) {
            conjunto = irA(estados, s.charAt(i));
            if(conjunto.isEmpty()) {
                return false;
            }
        }
        for(int j = 0; j < estadosAceptacion.size(); j++) {
            if(estados.contains(estadosAceptacion.iterator().next())) {
                return true;
            }
        }
        return false;
    }
    
    public Estado getEstadoInicial() { //Retorna el estado inicial del afn
        return this.estadoInicial;
    }
    
    public HashSet<Estado> getEstadosAceptacion() { //Retorna el conjunto de estados de aceptación del afn
        return this.estadosAceptacion;
    }
}
