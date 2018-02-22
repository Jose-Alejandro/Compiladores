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
    public HashSet<Transicion> Transiciones;
    
    public Estado()
    {
        IdEdo=IdEdoAct+1;
        EdoAcept=false;
        Transiciones = new HashSet<Transicion>();
        Transiciones.clear();
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
    
    public void setTransicion(Transicion transicion)
    {
        Transiciones.add(transicion);
    }
    
    public void setEstadoTrue()
    {
        this.EdoAcept=true;
    }
    
    
}
