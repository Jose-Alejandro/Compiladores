/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import interprete.Afd2;
import interprete.Afn;
import interprete.Estado;
import interprete.Lexic;
import java.util.ArrayList;

/**
 *
 * @author FernandoEsteban
 */
public class PruebaLexic {
    public static void main(String[] args) {
        Afn afn1 = new Afn();
        afn1.AfnBasico('a');
        Afn afn2 = new Afn();
        afn2.AfnBasico('b');
        
        //Asignar tokens a cada AFN
        for (Estado edo : afn1.getEstadosAceptacion()) {
          edo.setEstadoTrue(10);
        }
        for (Estado edo : afn2.getEstadosAceptacion()) {
          edo.setEstadoTrue(20);
        }
        
        ArrayList<Afn> afns = new ArrayList();
        afns.add(afn1);
        afns.add(afn2);
        
        Afn afnUnionEspecial = new Afn();
        afnUnionEspecial.unionEspecial(afns);
        
        Afd2 afd2 = new Afd2();
        ArrayList<ArrayList<Integer>> tabla;
        tabla = afd2.AfnToAfd(afnUnionEspecial);
    
        System.out.println("====Alfabeto====");
        afnUnionEspecial.imprimeAlfabeto();

        System.out.println("====Tabla====" + tabla.size());
        afd2.imprimirTabla();
        
        Lexic lexic = new Lexic();
        boolean ok = lexic.AnalizarLexema("baab", afd2.alfabeto , afd2.getTabla() );
        System.out.println("===== Tokens =====");
        System.out.println("ok: " + ok);
        String tokens = lexic.ImprimeTokens("");
        System.out.println("tokens: " + tokens);
    }
}
