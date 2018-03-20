/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import static interprete.Estado.IdEdoAct;
import java.util.HashSet;

/**
 *
 * @author Fernando
 */
public class EstadoS {
    static int IdEdoAct = 0;
    public int IdEdo;
    public boolean EdoAcept;
    public int token = -1;
    public HashSet<Estado> estados;
    public HashSet<Transicion> transiciones;
    
    public EstadoS() {
        this.IdEdo = IdEdoAct + 1;
        this.EdoAcept = false;
        this.estados = new HashSet();
        this.transiciones = new HashSet();
        this.IdEdoAct++;
    }
    
    public EstadoS(HashSet<Estado> estados) {
        this.IdEdo = IdEdoAct + 1;
        this.EdoAcept = false;
        this.estados = estados;
        this.transiciones = new HashSet();
        this.IdEdoAct++;
    }
    
    public HashSet<Estado> getEstados() {
        return this.estados;
    }
        
}
