package Pruebas;

import interprete.Calculadora;
import interprete.Scanner;

public class PruebaCalc {

  public static void main(String[] args) {
    Calculadora calcu=new Calculadora("(52/2)+4*4");
    calcu.AnalizarExpr();
    System.out.println(calcu.result);
  }


}
