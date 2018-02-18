
package interprete;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author stefan
 */
public class Afn {
    
    public Afn() {
        
    }
    
    HashSet<Estado> cerraduraEpsilon(Estado e) {
        HashSet<Estado> estados = new HashSet();
        Stack<Estado> pila = new Stack();
        Estado estado;
        pila.clear();
        pila.push(e);
        while(!pila.empty()){
            estado = pila.pop();
            if(!estados.contains(estado)) {
                estados.add(estado);
            }
            //ciclo para verificar si existen transiciones epsilon en el estado
        }
        return estados;
    }
    
    HashSet<Estado> cerraduraEpsilon(HashSet<Estado> conjunto) {
        HashSet<Estado> estados = new HashSet();
        for(int i = 0; i < conjunto.size(); i++) {
            estados.addAll(cerraduraEpsilon(conjunto.iterator().next()));
        }
        return estados;
    }
    
    HashSet<Estado> mover(Estado e, char c) {
        HashSet<Estado> estados = new HashSet();
        //Ciclo para obtener transisiones epsilon de los estados
        return estados;
    }
    
    HashSet<Estado> irA(HashSet<Estado> conjunto, char c) {
        HashSet<Estado> estados = new HashSet();
        for(int i = 0; i < conjunto.size(); i++) {
            estados.addAll(mover(conjunto.iterator().next(), c));
        }
        return cerraduraEpsilon(estados);
    }
}
