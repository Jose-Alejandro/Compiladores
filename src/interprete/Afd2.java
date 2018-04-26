package interprete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;


public class Afd2 {

    public HashSet<Estado> estados;
    public HashSet<Estado> estadosAceptacion;
    public Estado estadoInicial;
    public ArrayList<String> alfabeto ;
    public ArrayList<ArrayList<Integer>> tabla1;
    
    public Afd2() {
        this.alfabeto = new ArrayList();
        this.estados = new HashSet();
        this.estadosAceptacion = new HashSet();
        this.estadoInicial = new Estado();
        this.tabla1=new ArrayList<ArrayList<Integer>>() ;
    }

    

//pasar un AFN al que se le ha aplicado la union especial
     public ArrayList<ArrayList<Integer> > AfnToAfd(Afn a1)
     {
        int size,int_sim=0;
        HashSet<Estado> aux=new HashSet<Estado>();
        this.alfabeto.addAll(a1.alfabeto);
        ArrayList<HashSet <Estado>> C=new ArrayList<HashSet <Estado>>();
        
        C.add(a1.cerraduraEpsilon(a1.getEstadoInicial()));//este sería S0
        System.out.println("===Conjunto de Estados S0===");
        Iterator i1 = C.get(0).iterator();
        for (int j = 0; j != C.get(0).size(); j++) {
            Estado e = (Estado)i1.next();
            e.imprimirEstado();
        }
        size=C.size();
        //ArrayList<ArrayList<Integer> > tabla1 = new ArrayList<ArrayList<Integer> >();
        for (int i=0;i<size;i++){
          ArrayList<Integer> arr_tmp=new ArrayList<Integer>();
          aux=C.get(i);
          //arr_tmp.add(-1);arr_tmp.add(-1);arr_tmp.add(-1);
          for (String simbolo : this.alfabeto){
            HashSet<Estado> tmp=new HashSet<Estado>();
            tmp=a1.irA(aux, simbolo.charAt(0) );
            if ( ( !C.contains(tmp) ) && (tmp.size()>0) )
            {
              C.add(tmp); //posiblemente añadir Si a C
              System.out.println("===Conjunto de Estados===  " + size);
              Iterator it = tmp.iterator();
                for (int j = 0; j != tmp.size(); j++) {
                    Estado e = (Estado)it.next();
                    e.imprimirEstado();
                }
                System.out.println("\n");
              arr_tmp.add(C.size()-1);
              size++;
            }else if (C.contains(tmp)){
                int index=C.indexOf(tmp);
                arr_tmp.add(index);
            }else if((tmp.size()==0)){
              arr_tmp.add(-1);
            }//ELSE
          }
          boolean acep=false;
          for (Estado edo : aux) {
            if(edo.getEdoAcept()){
              arr_tmp.add(edo.token);
              acep=true;
              break;
            }
          }
          if(acep==false)
            arr_tmp.add(-1);
          tabla1.add(arr_tmp);
          
        }
        //Variable para tabla e inicializarla con -1's
        //Guardar en Tabla1 para regresala por "getTabla()"
        return tabla1;
    }
     
     public ArrayList<ArrayList<Integer>> getTabla()
     {
         return tabla1;
     }
    
    public void imprimirTabla(){
        System.out.println("======== REPRESENTACION TABULAR ========");
        
        String line = "";
        for(int i = 0; i < this.alfabeto.size(); i++) {
            line += "-----+";
        }
        line = "+----------+" + line + "-----+";
        
        System.out.println("");
        System.out.println(line);
        System.out.print("|  Estados |");
        for (String s : this.alfabeto) {
            System.out.printf(" %3s |", s);
        }
        System.out.printf("%s|", "Token");
        
        System.out.println("");
        System.out.println(line);
        for(int i=0; i != tabla1.size(); i++){
            System.out.printf("|    %2d    |", i );
            for (int j = 0; j != tabla1.get(i).size(); j++){
                System.out.printf(" %3s |", tabla1.get(i).get(j));
            }
            System.out.println("");
            System.out.println(line);
        }
    }
}
