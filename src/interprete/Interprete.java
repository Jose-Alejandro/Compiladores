/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

/**
 *
 * @author Alejandro
 */
public class Interprete {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      System.out.println("Hola mundo");
  }
  
  public Afn crearBasico(char c) {
      Afn afn = new Afn(c);
      //afn.estadoInicial.
      return afn;
  }
}
