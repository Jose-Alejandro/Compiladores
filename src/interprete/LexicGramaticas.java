/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;
import java.util.ArrayList;
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
    public ArrayList<String> NoTerminales; //Simbolos No Terminales
    public ArrayList<ArrayList<String>> ListaReglas; //LIsta de Reglas
    boolean FF; //Flecha Flag
    String simboloInicial = "";
    
    public LexicGramaticas (String cadena)
    {
        this.Lexema=cadena;
        this.Token=1000; //Error
        pilaToken = new Stack<>();
        this.FF = false;
        this.NoTerminales = new ArrayList<>();
        this.NoTerminales.clear();
        this.ListaReglas = new ArrayList();
        this.ListaReglas.clear();
    }
    
    public void EscanerLexic()
    {
        int i;
        char cadenaLexema[];
        int longitudLexema=Lexema.length();
        cadenaLexema=Lexema.toCharArray();
        ArrayList<String> regla = new ArrayList();
        
        for(i=0; i<longitudLexema-1; i++)
        {
            Lexema="";
            while (i < longitudLexema &&(cadenaLexema[i] == ' ' || cadenaLexema[i] == '\n')) 
            {
                i++;
            }
            if (EsLetra(cadenaLexema[i])) 
            {
                while (EsLetra(cadenaLexema[i]) || EsDigito(cadenaLexema[i]))   
                {
                    if(FF == false) //Verificamos si ya pasamos por una Flecha, si no, leemos el Lexema
                    {
                        Lexema = Lexema + cadenaLexema[i];
                    }
                    i++;
                }
                Token = TokensGramaticas.SIMB;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
            }
            if(PC(cadenaLexema[i]))
            {
                Token=TokensGramaticas.PC;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
                FF=false;
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
                FF=true;
                
                //Agregar lado derecho a regla
                String der = "";
                String nuevo;
                for (int j = i+2; cadenaLexema[j] != ';'; j++) {
                    if(cadenaLexema[j] == '|') {
                        regla.add(1, der);
                        nuevo = regla.get(0);
                        ListaReglas.add(regla);
                        regla = new ArrayList();
                        regla.add(0, nuevo);
                        der = "";
                    } else {
                        if(cadenaLexema[j] != ' ' || !der.equals("")) {
                            der += cadenaLexema[j];
                        }
                    }
                }
                regla.add(1, der);
                ListaReglas.add(regla);
            }
            else if(cadenaLexema[i] == '€')
            {
                Token=TokensGramaticas.SIMB;
                pilaToken.push(Integer.toString(Token)); //Agregamos a la pila el Token.
            }
            //Reconocer un simbolo no terminal
            if(!Lexema.isEmpty()) //Si nuestro Lexema no esta vacio, lo agregamos a los NoTerminales.
            {
                NoTerminales.add(Lexema);
                regla = new ArrayList();
                regla.add(0, Lexema);
                if(simboloInicial.equals("")) {
                    simboloInicial = Lexema;
                }
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
    
    public ArrayList<String> GetNoTerminales ()
    {
        return NoTerminales;
    }

    public ArrayList<ArrayList<String>> getListaReglas() {
        return ListaReglas;
    }
    
    public void imprimeTabla() {
        System.out.println("Simbolo Inicial: " + simboloInicial);
        //System.out.println("Tamaño: " + ListaReglas.size());
        for (int i = 0; i != ListaReglas.size(); i++) {
            //System.out.println("Tamaño: " + ListaReglas.get(i).size());
            for (int j = 0; j != ListaReglas.get(i).size(); j++) {
                System.out.print(ListaReglas.get(i).get(j) + "-");
                //System.out.println("Tamaño: " + ListaReglas.get(i).size());
            }
            System.out.println();
        }
    }
}
