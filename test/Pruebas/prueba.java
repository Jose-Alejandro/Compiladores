
package Pruebas;
import interprete.*;
import jdk.nashorn.internal.objects.NativeArray;

public class prueba
{
  public static void main(String[] args)
  {
    Afn afn1=new Afn();
    Afn afn2=new Afn();
    afn1.AfnBasico('z');
    for (Estado edo : afn1.getEstadosAceptacion()) {
      edo.setEstadoTrue(10);
    }
    afn2.AfnBasico('b');
    for (Estado edo : afn2.getEstadosAceptacion()) {
      edo.setEstadoTrue(20);
    }
    afn1.unionEspecial(afn2);
    Afd2 afd2=new Afd2();
    afd2.AfnToAfd(afn1);
    
  }
  
}
