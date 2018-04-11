package Pruebas;

import interprete.Calculadora;
import interprete.Scanner;

public class PruebaCalc {

  public static void main(String[] args) {
    Calculadora calcu=new Calculadora("LN(5)+COSENO(3.141592/4)^2+(5/9)"); //2.6649936314
    calcu.AnalizarExpr();
    System.out.println(calcu.result);
    System.out.println(calcu.prefijo);
    System.out.println(calcu.posfijo);
  }


}