package interprete;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author stefan
 */
public class Afd {
    
    HashSet<Estado> estados;
    HashSet<Estado> estadosAceptacion;
    Estado estadoInicial;
    ArrayList<String> alfabeto ;
    
    public Afd() {
        this.alfabeto = new ArrayList();
        this.estados = new HashSet();
        this.estadosAceptacion = new HashSet();
        this.estadoInicial = new Estado();
    }
    
    public Afd AfnToAfd(Afn a1, Afn a2, Afn a3) {
        this.alfabeto.addAll(a1.alfabeto);
        this.alfabeto.addAll(a2.alfabeto);
        this.alfabeto.addAll(a3.alfabeto);
        a1.unionEspecial(a2, a3);
        //Variable para tabla e inicializarla con -1's
        
        
        return this;
    }
}
