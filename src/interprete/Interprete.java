/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Interprete {

    public Afn afn;
    public ArrayList<Afn> afns;
  /**
   * @param args the command line arguments
   */
  /*public static void main(String[] args) {
      System.out.println("Hola mundo");
  }*/  
    
  public Interprete() {
        afns = new ArrayList();
  }

    public void crearBasico(char c) {
        afns.add(new Afn(c));
    }
}
