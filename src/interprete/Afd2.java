package interprete;

import java.util.ArrayList;
import java.util.HashSet;


public class Afd2 {

    HashSet<Estado> estados;
    HashSet<Estado> estadosAceptacion;
    Estado estadoInicial;
    ArrayList<String> alfabeto ;

    public Afd2() {
        this.alfabeto = new ArrayList();
        this.estados = new HashSet();
        this.estadosAceptacion = new HashSet();
        this.estadoInicial = new Estado();
    }

    

//pasar un AFN al que se le ha aplicado la union especial
     public ArrayList<ArrayList<Integer> > AfnToAfd(Afn a1)
     {
        int size,int_sim=0;
        HashSet<Estado> aux=new HashSet<Estado>();
        this.alfabeto.addAll(a1.alfabeto);
        ArrayList<HashSet <Estado>> C=new ArrayList<HashSet <Estado>>();
        
        C.add(a1.cerraduraEpsilon(a1.getEstadoInicial()));//este sería S0
        size=C.size();
       ArrayList<ArrayList<Integer> > tabla = new ArrayList<ArrayList<Integer> >();
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
          tabla.add(arr_tmp);
          
        }
       
      
       
        
        //Variable para tabla e inicializarla con -1's
        return tabla;
    }
}
