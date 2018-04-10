
package interprete;

import java.util.ArrayList;
import java.util.Stack;

public class Lexic 
{

    /*int EdoActualLexema;
    int CaracterActual = 0;
    boolean EdoAcept_Previo = false;
    int IndCaracterEdoAcept = 0;
    int IndInicioLexema = 0;
    String Lexema;
    int Token;
    Stack<String> pilaToken;
    
    public Lexic(String Cadena)
    {
        EdoActualLexema = 0;
        CaracterActual = 0;
        EdoAcept_Previo = false;
        IndCaracterEdoAcept = 0;
        IndInicioLexema = 0;
        Lexema = Cadena;
        Token=0;
        pilaToken = new Stack<>();
    }
    
    public void AnalizarLexema()
    {
        char[] Lexema1 = Lexema.toCharArray();
        ArrayList<ArrayList<Integer>> tabla = new ArrayList<ArrayList<Integer>>();
        //tabla = ;  //Aquí no se como agarrar la tabla que retorna Afd2
        int i, j, longitudLexema;
        longitudLexema=Lexema.length();
        for(i=0; i<= longitudLexema; i++) //Leera toda la cadena carcter por caracter
        {
            for(j=0; j<=tabla.size(); j++) //Comparara cada carcter de la cadena con los elementos de la tabla
            {
                //Se comprueba si el caracter i del Lexema, es igual a algun caracter de la primer fila donde estan las letras y que tanga una transición =!-1
                if(Lexema1[i] == tabla[j][0] && tabla[j][0] =! -1)   
                {
                    if(tabla[j][x] =! -1) //La x es el valor de la ultima columna que es la de si es o no un estado de aceptación
                    {
                        EdoAcept_Previo=true;
                        IndCaracterEdoAcept=i;
                        Token=x; //La x es el valor de la columna de Estado de Aceptación.
                        pilaToken.push(x); //La x es el valor de la columna de Estado de Aceptación.
                    }
                    EdoActualLexema=i;                                                                                                                           
                }
            }
        }
    }
    
    public String getToken()
    {
        String Token;
        Token = pilaToken.pop();
        return Token;
    }
    
    public void returnToken(String Token)
    {
        pilaToken.push(Token);
    }*/

}
