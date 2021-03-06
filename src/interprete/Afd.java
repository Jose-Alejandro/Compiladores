
package interprete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Afd {
    /*
    HashSet<Estado> estados;
    HashSet<Estado> estadosAceptacion;
    Estado estadoInicial;
    ArrayList<String> alfabeto;
    ArrayList< ArrayList<Integer>> tabla;
    */
    ArrayList<EstadoS> estados;
    ArrayList<EstadoS> estadosAceptacion;
    EstadoS estadoInicial;
    ArrayList<String> alfabeto;
    ArrayList<ArrayList<Integer>> tabla;

    public Afd() {
        this.alfabeto = new ArrayList();
        this.estados = new ArrayList();
        this.estadosAceptacion = new ArrayList();
        this.estadoInicial = new EstadoS();
        this.tabla = new ArrayList<>();
    }
    /*
    public Afd AfnToAfd(Afn f1){
        this.alfabeto.addAll(f1.alfabeto);
        EstadoS Si = new EstadoS(f1.cerraduraEpsilon(f1.estadoInicial));
        //Si.estados = f1.cerraduraEpsilon(f1.estadoInicial);
        if(!this.estados.contains(Si)){
            isAcept : for(Estado e : Si.estados){
                if(e.EdoAcept){
                    Si.EdoAcept = true;
                    Si.token = e.token;
                    break isAcept;
                }
            }
            this.estados.add(Si);
        }
        ArrayList<Integer> fila = new ArrayList();
        //Iterator iteradorEstados = this.estados.iterator();
        while(this.estados.iterator().hasNext()){
            EstadoS S = (EstadoS) this.estados.iterator().next();
        //for(EstadoS S : this.estados){
            System.out.println("length: " + this.estados.size());
            fila.add(S.IdEdo);
            //for(String caracter : alfabeto){
            while(this.alfabeto.iterator().hasNext()){
                String caracter = this.alfabeto.iterator().next();
                //Si.estados = f1.irA(S.estados, caracter.charAt(0));
                Si = new EstadoS(f1.irA(S.estados, caracter.charAt(0)));
                System.out.println("Si");
                System.out.println(Si);
                System.out.println(this.estados.contains(Si));
                System.out.println(Si.estados.isEmpty());
                if(!this.estados.contains(Si) && !Si.estados.isEmpty()){
                    isAcept : for(Estado e : Si.estados){
                        if(e.EdoAcept){
                            Si.EdoAcept = true;
                            Si.token = e.token;
                            break isAcept;
                        }
                    }
                    fila.add(Si.IdEdo);
                    this.estados.add(Si);
                    System.out.println("length this.estados: " + this.estados.size());
                }else if(this.estados.contains(Si)){
                    fila.add(Si.IdEdo);
                }else if(Si.estados.isEmpty()){
                    fila.add(-1);
                }
                fila.add(Si.token);
            }
            this.tabla.add(fila);
        }
        
        
        return this;
    }
    */
    public Afd AfnToAfd(Afn a1, Afn a2, Afn a3) {
        this.alfabeto.addAll(a1.alfabeto);
        this.alfabeto.addAll(a2.alfabeto);
        this.alfabeto.addAll(a3.alfabeto);
        a1.unionEspecial(a2, a3);
        //Variable para tabla e inicializarla con -1's
        ArrayList<Integer> fila = new ArrayList();
        int i = 0;
        while (i < this.alfabeto.size()) {
            fila.add(-1);
        }
        this.tabla.add(fila);

        return this;
    }

//pasar un AFN al que se le ha aplicado la union especial

    public Afd AfnToAfd(Afn a1) {
        this.alfabeto.addAll(a1.alfabeto);
        estadoInicial = new EstadoS(a1.cerraduraEpsilon(a1.getEstadoInicial()));
        estados.add(estadoInicial);//este sería S0
        for(int i=0; i != estados.size(); i++) {    // este for es el que se cicla
            for(int j=0; j != this.alfabeto.size(); j++) {
                HashSet<Estado> tmp = a1.irA(estados.get(i).getEstados(), this.alfabeto.get(j).charAt(0));
                int c = 0;  //bandera para saber si ya existe temp en los conjuntos Si
                if(tmp != null) {
                    for(int l=0; l != estados.size(); l++) {    //Este ciclo es el que hay que acomodar para que compare con los conjuntos ya existentes
                        if(estados.iterator().next().getEstados() == tmp) {
                            c++;
                            break;
                        }
                    }
                } else {
                    c++;
                }
                if(c == 0) {
                    EstadoS si = new EstadoS(tmp);
                    estados.add(si);
                    System.out.println("agrega si");
                    ArrayList<Integer> fila = new ArrayList();
                    for(int k = 0; k != this.alfabeto.size(); k++) {
                        fila.add(-1);
                    }
                    this.tabla.add(fila);
                }
            }
        }
        
//        for (HashSet<Estado> aux : C) {
//            for (String simbolo : this.alfabeto) {
//                HashSet<Estado> tmp = new HashSet();
//                tmp = a1.irA(aux, simbolo.charAt(0));
//                if (!C.contains(tmp) && tmp != null) {
//                    C.add(tmp); //posiblemente añadir Si a C
//                    int i = 0;
//                    ArrayList<Integer> fila = new ArrayList();
//                    while (i < this.alfabeto.size()) {
//                        fila.add(-1);   //Se inicializa un nuevo renglón para Si
//                    }
//                    this.tabla.add(fila);
//                }
//            }
//        }
        return this;
    }

    public void printTable(){
        System.out.println("Entra imprime");
        for (int i = 0; i < tabla.size(); i++) {
            System.out.print("size i: " + tabla.size());
            for (int j = 0; j < tabla.get(i).size(); j++) {
                System.out.print("size j: " + tabla.get(i).size());
                System.out.print(tabla.get(i).get(j));
                System.out.print("\t");
            }
            System.out.println("\n");
        }
    }
}
