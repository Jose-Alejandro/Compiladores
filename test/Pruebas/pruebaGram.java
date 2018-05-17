package Pruebas;

import interprete.Gramatica;

public class pruebaGram {
  public static void main(String[] args) {
    String cadena="G -> LR ; " +
                  "LR -> R PC LRP ; "+
                  "LRP -> R PC LRP | €; "+
                  "R -> LI FLECHA LLD ; "+
                  "LI -> S ; "+
                  "LLD -> LD LLDP ; "+
                  "LLDP -> OR LD LLDP | € ; "+
                  "LD -> S LDP ; "+
                  "LDP -> S LDP | € ;" ;
    Gramatica gram=new Gramatica(cadena);
    if (gram.AnalizarExpr())
      System.out.println("Aceptada");
    else
      System.out.println("Rechazada");

  }





}
