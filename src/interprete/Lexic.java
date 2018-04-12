
package interprete;

import java.util.Stack;

/**
 *
 * @author Alxd001
 */
public class Lexic {

    int EdoActualLexema;
    int CaracterActual = 0;
    boolean EdoAcept_Previo = false;
    int IndCaracterEdoAcept = 0;
    int IndInicioLexema = 0;
    String Lexema;
    int Token;
    Stack<String> pilaToken;

    Lexic() {
        EdoActualLexema = 0;
        CaracterActual = 0;
        EdoAcept_Previo = false;
        IndCaracterEdoAcept = 0;
        IndInicioLexema = 0;
        this.Lexema = "";
        Token = 10000; //10000 = Error
        pilaToken = new Stack<>();
    }

    public boolean AnalizarLexema(String inCadena, char[] inAlf, int inTab[][]) {

        //char alfabeto[] = {'S', 'D', 'L', '.', 'E', ' '};
        char alfabeto[]=inAlf;
        char cadena[];
        Lexema = inCadena;
        cadena = Lexema.toCharArray();
        int tabla[][]=inTab;
        /*int tabla[][] = {
        {1, 2, 3, -1, 4, -1}, //0
        {-1, 2, -1, -1, -1, -1}, //1
        {-1, 2, -1, 5, -1, 10}, //2 
        {-1, 6, 7, -1, -1, 30}, //3 
        {-1, -1, -1, -1, 4, 40}, //4
        {-1, 8, -1, -1, -1, -1}, //5
        {-1, 6, 7, -1, -1, 30}, //6
        {-1, 6, 7, -1, -1, 30}, //7
        {-1, 8, -1, -1, -1, 20} //8
        }; //[Fila][Columna]
        */
        int i, j, k = 0, longitudLexema; //i=Carater actual que se esta analizando.
        int longitudColumna = tabla[EdoActualLexema].length - 1;
        longitudLexema = Lexema.length();
        
        for (i = 0; i < longitudLexema; i++) //Revisara toda la cadena de entrada
        {
            for (j = 0; j < alfabeto.length; j++) //Buscamos el indice del alfabeto donde esta el caracter de la cadena de entrada.
            {
                if (cadena[i] == alfabeto[j]) //Si el carcter de entrada es igual al del alfabeto
                {
                    k = j; //Guardamos el valor del indice.
                }
            }
            if (tabla[EdoActualLexema][k] != -1) //Revisamos si hay transición del estado actual a algun otro.
            {
                IndInicioLexema = i; //Aqui empieza el lexema que si es reconocido por el AFD
                EdoActualLexema = tabla[EdoActualLexema][k]; //El estado actual en el que nos encontramos cambia a donde hay transición.
                if ((tabla[EdoActualLexema][longitudColumna]) != -1) //Revisamos si es estado de aceptación
                {
                    EdoAcept_Previo = true; //Existe un estado de aceptacion previo
                    IndCaracterEdoAcept = i;
                    Token = (tabla[EdoActualLexema][longitudColumna]); //Nos vamos a la ultima columna
                } 
            }
            
            else //Si no hay transición de estados. 
            {
                if (EdoAcept_Previo == true) //Si ya habiamos pasado por algun estado de aceptación
                {
                    EdoActualLexema = 0; //Regresamos al estado inicial.
                    EdoAcept_Previo = false; //El Estado de Aceptación Previo lo regresamos a falso.
                    IndInicioLexema = IndCaracterEdoAcept + 1; //Establecemos donde empezaría el nuevo Lexema reconocido por el AFD.
                    pilaToken.push(Integer.toString(Token)); //Agregamos a nuestra pila de Tokens el Token Previo.  
                    Token = 10000; //Token 10000=Error.
                    i = IndCaracterEdoAcept; //El caracter actual a analizar es el ultimo caracter de aceptacion
                    IndCaracterEdoAcept = -1; //El indice del carater que tiene estado de aceptación lo regresamos a -1.
                } 
                else //Si no hubo un estado de aceptación Previo.
                {
                    System.out.println("La cadena no es Valida.");
                    return false; //El Lexema de entrada es incorrecto "Cadena NO valida".
                }
            }           
        }//Fin for() que lee la cadena de entrada
        //Se hace una ultima validación.
        if (EdoAcept_Previo == true) //Si ya habiamos pasado por algun estado de aceptación
        {
            EdoActualLexema = 0; //Regresamos al estado inicial.
            EdoAcept_Previo = false; //El Estado de Aceptación Previo lo regresamos a falso.
            IndInicioLexema = IndCaracterEdoAcept + 1; //Establecemos donde empezaría el nuevo Lexema reconocido por el AFD.
            pilaToken.push(Integer.toString(Token)); //Agregamos a nuestra pila de Tokens el Token Previo.                   
            Token = 10000; //Token 10000=Error.
            i = IndCaracterEdoAcept + 1; //El caracter actual a analizar es el ultimo caracter de aceptacion + 1
            IndCaracterEdoAcept = -1; //El indice del carater que tiene estado de aceptación lo regresamos a -1.
        } 
        else //Si no hubo un estado de aceptación Previo.
        {
            System.out.println("La cadena no es Valida.");
            return false; //El Lexema de entrada es incorrecto "Cadena NO valida".
        }
        return true; //El Lexema de Entrada es Correcto "Cadena Valida"
    }

    public String getToken() {
        String Token1;
        Token1=pilaToken.pop();
        return Token1;
    }

    public void returnToken(String Token) {
        pilaToken.push(Token);
    }

    public void ImprimeTokens() {
        Stack<String> pilaTokencopia;
        pilaTokencopia = pilaToken;
        while (!pilaTokencopia.empty()) {
            System.out.println(pilaTokencopia.pop() + ", ");
        }
    }

}
