/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import java.util.HashSet;

/**
 *
 * @author Fernando
 */
public class Estado 
{
    static int IdEdoAct=0;
    int IdEdo;
    boolean EdoAcept;
    int token=-1;
    public HashSet<Transicion> Transiciones;
    
    public Estado(char c, Estado e)
    {
        IdEdo=IdEdoAct+1;
        EdoAcept=false;
        Transiciones = new HashSet();
        Transiciones.clear();
        this.setTransicion(c, e);
    }
    
    public Estado()
    {
        IdEdo = IdEdoAct + 1;
        EdoAcept = false;
        Transiciones = new HashSet();
        IdEdoAct++;
    }
    
    public int getIdEdo() //Regresa el Id del Estado en el que nos encontramos.
    {
        return this.IdEdo;        
    }
    
    public boolean getEdoAcept() //Regresa True o False para saber si es o no estado de aceptación.
    {
        return this.EdoAcept;
    }
    
    public HashSet<Transicion> getTransiciones() //Regresa las transiciones que tenga este estado.
    {
        return this.Transiciones;
    }
    
    //Agrega la transición enviada al estado
    public void setTransicion(Transicion transicion)
    {
        Transiciones.add(transicion);
    }
    
    //Agrega una transición al Estado creándola primero
    public void setTransicion(char c, Estado e)
    {
        Transicion transicion = new Transicion(c, e);
        Transiciones.add(transicion);
    }
    
    //Agrega una transición al Estado creándola primero con un rango de carac
    public void setTransicion(char c, char c2, Estado e)
    {
        Transicion transicion = new Transicion(c, c2, e);
        Transiciones.add(transicion);
    }
    
    //Cambia el estado de aceptación a true
    public void setEstadoTrue()
    {
        this.EdoAcept=true;
        
    }
    public void setEstadoTrue(int token)
    {
        this.EdoAcept=true;
        this.token=token;
    }
    
    //Imprime en consola el estado
    public void imprimirEstado() {
        System.out.println("ID: " + this.IdEdo);
        System.out.println("Estado Aceptación: " + this.EdoAcept);
        System.out.println("Número de Transiciones: " + this.Transiciones.size());
        for(int i = 0; i < this.Transiciones.size(); i++) {
            Transiciones.iterator().next().imprimirTransicion();
        }
    }
    
}
