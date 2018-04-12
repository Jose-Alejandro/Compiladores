
package Pruebas;
import interprete.*;
import java.util.ArrayList;

public class prueba
{
  public static void main(String[] args)
  {
    Afn afn1=new Afn();
    Afn afn2=new Afn();
    //primer AFN de ejemplo dado en clase para convertir a AFD
    afn1.AfnBasico('s');    //Afn: 's'
    afn1.Opcional();        //Afn: s?
    afn2.AfnBasico('0');    //Afn '0'
    afn2.cerrMas();         //Afn: 0+
    afn1.ConcatenarAfn(afn2);   //Afn: s?0+ FINAL
    
    //segundo AFN
    Afn afn3 = new Afn();
    Afn afn4 = new Afn();
    afn3.AfnBasico('s');        //Afn: 's'
    afn3.Opcional();            //Afn: s?
    Afn afn9 = new Afn();
    afn9.AfnBasico('0');        //Afn: '0'
    afn9.cerrMas();             //Afn: 0+
    afn3.ConcatenarAfn(afn9);   //Afn: s?0+
    afn4.AfnBasico('.');        //Afn: '.'
    afn3.ConcatenarAfn(afn4);   //Afn: s?0+.
    Afn afn10 = new Afn();
    afn10.AfnBasico('0');       //Afn: '0'
    afn10.cerrMas();            //Afn: 0+
    afn3.ConcatenarAfn(afn10);   //Afn: s?0+.0+ FINAL
    
    //tercer AFN
    Afn afn5 = new Afn();
    Afn afn6 = new Afn();
    afn5.AfnBasico('l');        //Afn: 'l'
    afn6.AfnBasico('0');        //Afn '0'
    afn5.unirAfn(afn6);         //Afn: l|0
    afn5.cerrEstrella();        //Afn (l|0)*
    Afn afn7 = new Afn();
    afn7.AfnBasico('l');        //Afn: 'l'
    afn7.ConcatenarAfn(afn5);   //Afn: l(l|0)* FINAL
    
    //cuarto AFN
    Afn afn8 = new Afn();
    afn8.AfnBasico('e');        //Afn: 'e'
    afn8.cerrMas();             //Afn: e+ FINAL
    
    
    //Asignar tokens a cada AFN
    for (Estado edo : afn1.getEstadosAceptacion()) {
      edo.setEstadoTrue(10);
    }
    for (Estado edo : afn3.getEstadosAceptacion()) {
      edo.setEstadoTrue(20);
    }
    for (Estado edo : afn7.getEstadosAceptacion()) {
      edo.setEstadoTrue(30);
    }
    for (Estado edo : afn8.getEstadosAceptacion()) {
      edo.setEstadoTrue(40);
    }
    //Se manda llamar a la unión especial que une cualquier número de afns
    ArrayList<Afn> afns = new ArrayList();
    afns.add(afn3);
    afns.add(afn7);
    afns.add(afn8);
    afn1.unionEspecial(afns);
    Afd2 afd2=new Afd2();
    ArrayList<ArrayList<Integer>> tabla;
    tabla = afd2.AfnToAfd(afn1);
    
    System.out.println("====Alfabeto====");
    afn1.imprimeAlfabeto();
    
    System.out.println("====Tabla====" + tabla.size());
    
//    for(int i=0; i != tabla.size(); i++) {
//        for (int j = 0; j != tabla.get(i).size(); j++) {
//            System.out.print(tabla.get(i).get(j) + "  ");
//        }
//        System.out.println();
//    }
        afd2.imprimirTabla();
    
  }
  
}
