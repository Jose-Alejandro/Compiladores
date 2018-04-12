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
    ArrayList<String> alfabeto;

    public Afn() {
        this.alfabeto = new ArrayList();
        this.estados = new HashSet();
        this.estadosAceptacion = new HashSet();
        this.estadoInicial = new Estado();
    }

    /*public Afn(char c) {
        estados = new HashSet();
        estadosAceptacion = new HashSet();
        alfabeto = new ArrayList();
        alfabeto.add("" + c);
        this.setEstadoAceptacion();
    }*/
    public HashSet<Estado> cerraduraEpsilon(Estado e) {
//        System.out.println("\n=== Método cerraduraEpsilon ===");
        HashSet<Estado> estados1 = new HashSet();
        HashSet<Transicion> transiciones;
        Stack<Estado> pila = new Stack();
        Estado estado;
        pila.clear();
        pila.push(e);
        while (!pila.empty()) {
            estado = pila.pop();
//            System.out.println("\nEstado en la pila: ");
//            estado.imprimirEstado();
            if (!estados1.contains(estado)) {
//                System.out.println("\nAgrega estado");
                estados1.add(estado);
            }
            transiciones = estado.getTransiciones();
//            System.out.println("Transiciones: " + transiciones.size());

            //ciclo para verificar si existen transiciones epsilon en el estado
            for (Transicion t : transiciones) {
                if (t.getMaxSimb() == '|') {
//                    System.out.println("\nAgrega por Epsilon");
                    pila.push(t.getEstado());
                }
            }
        }

//        for (Estado es : estados1) {
//            System.out.println("\nEstadoCeEp ");
//            es.imprimirEstado();
//        }
        return estados1;
    }

    public HashSet<Estado> cerraduraEpsilon(HashSet<Estado> conjunto) {
//        System.out.println("\n=== Método cerradura Epsilon Conjunto ===");
        HashSet<Estado> estados1 = new HashSet();

        for (Estado e : conjunto) {
            estados1.addAll(cerraduraEpsilon(e));
        }

//        for (Estado es : estados1) {
//            System.out.println("\nEstadoCeEpConj ");
//            es.imprimirEstado();
//        }
        return estados1;
    }

    public HashSet<Estado> mover(Estado e, char c) {
//        System.out.println("\n=== Método mover ===");
//        e.imprimirEstado();
        HashSet<Estado> estados1 = new HashSet();
        HashSet<Transicion> transiciones = e.getTransiciones();

        //Ciclo para obtener transisiones epsilon de los estados
        for (Transicion t : transiciones) {
            if (t.getMinSimb() <= c && t.getMaxSimb() >= c) {
                estados1.add(t.getEstado());
            }
        }

//        for (Estado es : estados1) {
//            System.out.println("\nEstadoMov ");
//            es.imprimirEstado();
//        }
        return estados1;
    }

    public HashSet<Estado> irA(HashSet<Estado> conjunto, char c) {
//        System.out.println("\n=== Método irA ===");
        HashSet<Estado> estados1 = new HashSet();
//        System.out.println("\nEstados en conjunto: " + conjunto.size());

        for (Estado e : conjunto) {
            estados1.addAll(mover(e, c));
        }

//        for (Estado e : estados1) {
//            System.out.println("\nEstadoIrA ");
//            e.imprimirEstado();
//        }
        return cerraduraEpsilon(estados1);
    }

    public boolean AnalizarCadena(String s) {
        HashSet<Estado> estados1, conjunto;
        int longitud;
//        System.out.println("\nEstado Inicial: ");
        this.estadoInicial.imprimirEstado();
        estados1 = cerraduraEpsilon(this.estadoInicial);
        longitud = s.length();
        //System.out.println("Longitud: " + longitud);

        for (int i = 0; i != longitud; i++) {
//            System.out.println("\nSímbolo a analizar: " + s.charAt(i));
            conjunto = irA(estados1, s.charAt(i));
            if (conjunto.isEmpty()) {
//                System.out.println("Analizar 1");
                return false;
            }
            estados1 = conjunto;
        }

        for (Estado e : estadosAceptacion) {
            if (estados1.contains(e)) {
//                System.out.println("Analizar 2");
                return true;
            }
        }
//        System.out.println("Analizar 3");
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
        for (int i = s; i <= s2; i++) {
            System.out.println("" + ((char) i));
            this.alfabeto.add("" + ((char) i));
        }
        this.estados.add(this.estadoInicial);
        this.estados.add(edoFin);
        this.estadosAceptacion.add(edoFin);

        return this;
    }

    public Afn Opcional() {
        Estado edoIni = new Estado();
        Estado edoFin = new Estado();

        edoIni.Transiciones.add(new Transicion(Epsilon.epsilon, this.estadoInicial));
        edoIni.Transiciones.add(new Transicion(Epsilon.epsilon, edoFin));
        edoIni.imprimirEstado();
        edoFin.imprimirEstado();

        for (Estado e : this.estadosAceptacion) {
            e.Transiciones.add(new Transicion(Epsilon.epsilon, edoFin));
            e.EdoAcept = false;
        }

        edoFin.setEstadoTrue();
        this.estadoInicial = edoIni;
        this.estadosAceptacion.clear();
        this.estadosAceptacion.add(edoFin);
        this.estados.add(edoIni);
        this.estados.add(edoFin);

        return this;
    }

    public Afn unirAfn(Afn f2) {
        Estado nuevoIni = new Estado();
        Estado nuevoFin = new Estado();

        nuevoIni.setTransicion(Epsilon.epsilon, this.estadoInicial);
        nuevoIni.setTransicion(Epsilon.epsilon, f2.estadoInicial);

        for (Estado e : this.estadosAceptacion) {
            e.setTransicion(Epsilon.epsilon, nuevoFin);
            e.EdoAcept = false;
        }

        for (Estado e : f2.estadosAceptacion) {
            e.setTransicion(Epsilon.epsilon, nuevoFin);
            e.EdoAcept = false;
        }

        nuevoFin.setEstadoTrue();
        this.estados.add(nuevoIni);
        this.estados.add(nuevoFin);
        this.estadoInicial = nuevoIni;
        this.estadosAceptacion.clear();
        this.estadosAceptacion.add(nuevoFin);

        return this;
    }

    public Afn cerrMas() {
        Estado edoIni = new Estado();
        Estado edoFin = new Estado();

        edoIni.Transiciones.add(new Transicion(Epsilon.epsilon, this.estadoInicial));

        for (Estado e : this.estadosAceptacion) {
            e.Transiciones.add(new Transicion(Epsilon.epsilon, edoFin));
            e.Transiciones.add(new Transicion(Epsilon.epsilon, this.estadoInicial));
            e.EdoAcept = false;
        }

        edoFin.setEstadoTrue();
        this.estadosAceptacion.clear();
        this.estadosAceptacion.add(edoFin);
        this.estadoInicial = edoIni;
        this.estados.add(edoIni);
        this.estados.add(edoFin);

        return this;
    }

    public Afn cerrEstrella() {
        Estado edoIni = new Estado();
        Estado edoFin = new Estado();

        edoIni.Transiciones.add(new Transicion(Epsilon.epsilon, this.estadoInicial));
        edoIni.Transiciones.add(new Transicion(Epsilon.epsilon, edoFin));

        for (Estado e : this.estadosAceptacion) {
            e.Transiciones.add(new Transicion(Epsilon.epsilon, edoFin));
            e.Transiciones.add(new Transicion(Epsilon.epsilon, this.estadoInicial));
            e.EdoAcept = false;
        }

        edoFin.setEstadoTrue();
        this.estadosAceptacion.clear();
        this.estadosAceptacion.add(edoFin);
        this.estadoInicial = edoIni;
        this.estados.add(edoIni);
        this.estados.add(edoFin);

        return this;
    }

    public Afn ConcatenarAfn(Afn f2) {

        for (Estado e : this.estadosAceptacion) {
            for (Transicion t : f2.estadoInicial.Transiciones) {
                e.setTransicion(t.minSimb, t.maxSimb, t.getEstado());
            }
        }
        f2.estados.remove(f2.estadoInicial);

        for (Estado e : this.estadosAceptacion) {
            e.EdoAcept = false;
        }
        this.estadosAceptacion.clear();

        this.estadosAceptacion.addAll(f2.estadosAceptacion);
        this.alfabeto.addAll(f2.alfabeto);
        this.estados.addAll(f2.estados);
        f2 = null;

        return this;
    }

    public Afn unionEspecial(Afn f2, Afn f3) {
        Estado nuevoIni = new Estado();

        nuevoIni.setTransicion(Epsilon.epsilon, this.estadoInicial);
        nuevoIni.setTransicion(Epsilon.epsilon, f2.estadoInicial);
        nuevoIni.setTransicion(Epsilon.epsilon, f3.estadoInicial);

        this.estados.add(nuevoIni);
        this.estadoInicial = nuevoIni;
        //this.alfabeto.addAll(a1.alfabeto);
        this.alfabeto.addAll(f2.alfabeto);
        this.alfabeto.addAll(f3.alfabeto);

        return this;
    }
    
    public Afn unionEspecial(Afn f1) {
        Estado nuevoIni = new Estado();

        nuevoIni.setTransicion(Epsilon.epsilon, f1.estadoInicial);
        nuevoIni.setTransicion(Epsilon.epsilon, this.estadoInicial);

        this.estados.add(nuevoIni);
        this.estadoInicial = nuevoIni;
        //this.alfabeto.addAll(f1.alfabeto);
        for(String s : f1.alfabeto) {
            if(!this.alfabeto.contains(s)) {
                this.alfabeto.add(s);
            }
        }
        for (Estado edo: f1.getEstadosAceptacion() ) {
            this.estadosAceptacion.add(edo);
        }
        for (Estado edo: f1.estados ) {
            this.estados.add(edo);
        }

        return this;
    }
    
    public Afn unionEspecial(ArrayList<Afn> afns) {
        Estado nuevoIni = new Estado();

        for (int i = 0; i != afns.size(); i++) {
            nuevoIni.setTransicion(Epsilon.epsilon, afns.get(i).estadoInicial);
        }
        nuevoIni.setTransicion(Epsilon.epsilon, this.estadoInicial);

        this.estados.add(nuevoIni);
        this.estadoInicial = nuevoIni;
        for (int i = 0; i != afns.size(); i++) {
            for(String s: afns.get(i).alfabeto) {
                if(!this.alfabeto.contains(s)) {
                    this.alfabeto.add(s);
                }
            }
        }
        for (int i = 0; i != afns.size(); i++) {
            for (Estado edo: afns.get(i).getEstadosAceptacion() ) {
                this.estadosAceptacion.add(edo);
            }
            for (Estado edo: afns.get(i).estados ) {
                this.estados.add(edo);
            }
        }

        return this;
    }
    
    public void imprimeAlfabeto() {
        System.out.println(this.alfabeto.toString());
    }
}
