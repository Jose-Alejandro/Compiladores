package interprete;

import java.util.ArrayList;

public class Gramatica {
  Scanner Lexic;

  public Gramatica(String cadena){
    this.Lexic=new Scanner(cadena);
  }
  public boolean AnalizarExpr()
  {
    boolean R;
    R=G();
    if (R){
      if (Lexic.getToken()==Tokens.FIN){
        return R;
      }
      else
        return false;
    }
    return R;
  }


  boolean G()
  {
    if (ListaReglas())
      return true;
    return false;
  }

  boolean ListaReglas()
  {
    if (Regla()){
      int tok=Lexic.getToken();
      if (tok==Tokens.PC)
        if(ListaReglasP())
          return true;
    }
    return false;
  }

  boolean ListaReglasP()
  {
    LexicEdo edo=new LexicEdo;
    edo=Lexic.getEdo();
    if (Regla()){
      int tok=Lexic.getToken();
      if (ListaReglasP())
        return true;
      return false;
    }
    Lexic.setEdo(edo);
    return true;
  }

  boolean Regla(){
    if (LadoIzquierdo()){
      int tok=Lexic.getToken();
      if (tok==Tokens.FLECHA)
        if (ListaLadosDerechos())
          return true;
    }
    return false;
  }

  boolean LadoIzquierdo(){
    int tok=Lexic.getToken();
    if (tok==Tokens.SIMB)
      return true;
    return false;
  }

  boolean ListaLadosDerechos(){
    if (LadoDerecho())
      if (ListaLadosDerechosP())
        return true;
    return false;
  }

  boolean ListaLadosDerechosP(){
    int tok=Lexic.getToken();
    if (tok==Tokens.OR) {
      if (LadoDerecho())
        if (ListaLadosDerechosP())
          return true;
      return false;
    }
    Lexic.regresarToken();
    return true;
  }

  boolean LadoDerecho()
  {
    int tok=Lexic.getToken();
    if (tok==Tokens.SIMB)
      if (LadoDerechoP())
        return true;
    return false;
  }

  boolean LadoDerechoP()
  {
    int tok=Lexic.getToken();
    if (tok==Tokens.SIMB) {
      if (LadoDerechoP())
        return true;
      return false;
    }
    Lexic.regresarToken();
    return true;
  }
  //Para este método se debe tener una tabla que contenga las transiciones, una por renglón
  //la tabla debería ser un arrelgo bidimensional de nx2 de Strings llamado "ListaReglas"
  public ArrayList<String> First(String regla) {
      String[] simbolosRegla = regla.split(" ");
      ArrayList<String> simbolos = new ArrayList();
      //noTerminales es una lista de Strings con cada simbolo (o cadena) no terminal analizado en la gramática
      if(simbolosRegla[1].equals("€") || !noTerminales.contains(simbolosRegla[1])) {
          simbolos.add(simbolosRegla[1]);
          return simbolos;
      }
      //Uso de ListaReglas
      ArrayList<String> reglas = new ArrayList();
      int j = 0;
      for(String s : noTerminales) {
          if(simbolosRegla[0].equals(s)) {
              reglas.add(ListaReglas[j][2]);
          }
          j++;
      }
      for(int i = 0; i < reglas.size(); i++) {
        simbolos.addAll(First(reglas.get(i)));
      }
      if(simbolos.contains("€")) {
          simbolos.remove("€");
          simbolos.addAll(First(regla.substring(1)));
          return simbolos;
      }
      return simbolos;
  }
  
  //Tambien se necesita la tabla "ListaReglas"
  public ArrayList<String> Follow(String regla) {
      ArrayList<String> simbolos = new ArrayList();
      if(regla.equals(Lexic.simboloInicial)) {
          simbolos.add("$");
      }
      for(String[] s : ListaReglas) {
          if(s[1].contains(regla)) {
            if(s[1].indexOf(regla) != 0) {  
                ArrayList<String> w = First(s[1].substring(s[1].indexOf(regla)+regla.length()));
                simbolos.addAll(w);
                if(simbolos.contains("€")) {
                    simbolos.remove("€");
                    simbolos.addAll(Follow(s[0]));
                }
            } else {
                simbolos.addAll(Follow(s[0]));
            }
          }
      }
      return simbolos;
  }
}


