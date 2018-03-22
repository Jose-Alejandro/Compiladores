/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

/**
 *
 * @author Alxd001
 */
public class Lexic 
{

    Estado EdoActual;
    int CaracterActual = 0;
    boolean EdoAcept_Previo = false;
    int IndCaracterEdoAcept = 0;
    int IndInicioLexema = 0;
    String Lexema;
    
    public Lexic(String Cadena)
    {
        Estado EdoActual;
        CaracterActual = 0;
        EdoAcept_Previo = false;
        IndCaracterEdoAcept = 0;
        IndInicioLexema = 0;
        Lexema = Cadena;
    }
    
    public void AnalizarLexema()
    {
        int i, longitudLexema;
        longitudLexema=Lexema.length();
        for(i=0; i<= longitudLexema; i++)
        {
            if(Lexema[i]==)
        }
        
    }

}
