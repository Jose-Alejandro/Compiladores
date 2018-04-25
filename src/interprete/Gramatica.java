package interprete;

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
}
