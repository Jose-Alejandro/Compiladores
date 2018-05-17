package interprete;
import java.util.Stack;

public class Gramatica {
  LexicGramaticas Lexic;

  public Gramatica(String cadena){
    this.Lexic=new LexicGramaticas(cadena);
    this.Lexic.EscanerLexic();
  }
  public boolean AnalizarExpr()
  {
    boolean R;
    R=G();
    if (R){
      int tok=Lexic.getToken();
      if (tok==TokensGramaticas.FIN){
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
      if (tok==TokensGramaticas.PC)
        if(ListaReglasP())
          return true;
    }
    return false;
  }

  boolean ListaReglasP()
  {
    Stack<String> edo;
    edo=(Stack<String>) Lexic.getEdo().clone();
    if (Regla()){
      int tok=Lexic.getToken();
      if (tok==TokensGramaticas.PC)
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
      if (tok==TokensGramaticas.FLECHA)
        if (ListaLadosDerechos())
          return true;
    }
    return false;
  }

  boolean LadoIzquierdo(){
    int tok=Lexic.getToken();
    if (tok==TokensGramaticas.SIMB)
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
    if (tok==TokensGramaticas.OR) {
      if (LadoDerecho())
        if (ListaLadosDerechosP())
          return true;
      return false;
    }
    Lexic.returnToken(tok);
    return true;
  }

  boolean LadoDerecho()
  {
    int tok=Lexic.getToken();
    if (tok==TokensGramaticas.SIMB)
      if (LadoDerechoP())
        return true;
    return false;
  }

  boolean LadoDerechoP()
  {
    int tok=Lexic.getToken();
    if (tok==TokensGramaticas.SIMB) {
      if (LadoDerechoP())
        return true;
      return false;
    }
    Lexic.returnToken(tok);
    return true;
  }
}


