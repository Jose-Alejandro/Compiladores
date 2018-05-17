/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;
import java.util.Stack;

/**
 *
 * @author Alxd001
 */

public class LexicGramaticas 
{
    public String Lexema;
    public int Token;
    Stack<String> pilaToken;
    
    public LexicGramaticas (String cadena)
    {
        this.Lexema=cadena;
        this.Token=1000; //Error
        pilaToken = new Stack<>();
    }
    
    public void EscanerLexic()
    {
        int i;
        char cadenaLexema[];
        int longitudLexema=Lexema.length();
        cadenaLexema=Lexema.toCharArray();
        
        for(i=0; i<longitudLexema-1; i++)
        {
            while (i < longitudLexema &&(cadenaLexema[i] == ' ' || cadenaLexema[i] == '\n')) 
            {
                i++;
            }
            if (EsLetra(cadenaLexema[i])) 
            {
                Lexema = "";
                while (EsLetra(cadenaLexema[i]) || EsDigito(cadenaLexema[i]))   
                {
                    i++;
                }
                Token = TokensGramaticas.SIMB;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
            }
            if(PC(cadenaLexema[i]))
            {
                Token=TokensGramaticas.PC;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
            }
            else if(OR(cadenaLexema[i]))
            {
                Token=TokensGramaticas.OR;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
            }
            else if((cadenaLexema[i] == '-') && cadenaLexema[i+1] == '>')
            {
                Token=TokensGramaticas.FLECHA;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
                i+=1;
            }
            else if(cadenaLexema[i] == '€')
            {
                Token=TokensGramaticas.SIMB;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
            }
        }
        Stack<String> pila2=new Stack<>();
        while (!pilaToken.empty()){
            pila2.push(pilaToken.pop());
        }
        pilaToken=pila2;
    }
    
    public boolean PC(char c)
    {
        return c == ';'; //Regresa TRUE si el caracter es ;
    }
    
    public boolean OR(char c)
    {
        return c == '|'; //Regresa True si el caracter es |
    }
    
    public boolean Simb(char c)
    {
        return ((c>='A' && c<='Z'));
    }
    
    boolean EsDigito(char c)
    {
        return (c>='0' && c<='9' );
    }

    boolean EsLetra(char c)
    {
        return (c>='a' && c<='z' ) || (c>='A' && c<='Z' );
    }
    
    public Stack<String> getEdo()
    {
        return pilaToken;
    }
    
    public void setEdo(Stack<String> nuevaPila)
    {
        pilaToken=(Stack<String>) nuevaPila.clone();
    }
    
    public int getToken()
    {
        int TokenReturn;
        if (pilaToken.empty())
          return TokensGramaticas.FIN;
        TokenReturn = Integer.parseInt(pilaToken.pop());
        return TokenReturn;
    }
    
    public void returnToken(int TokenReturn)
    {
        pilaToken.push(Integer.toString(TokenReturn));
    }
    
    public void ImprimeTokens() 
    {
        Stack<String> pilaTokencopia;
        pilaTokencopia = pilaToken;
        while (!pilaTokencopia.empty()) 
        {
            System.out.println(pilaTokencopia.pop() + ",");
        }
    }
}
