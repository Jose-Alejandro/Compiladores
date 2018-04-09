package interprete;

public class Calculadora {
  boolean E(float v)
  {
    if (T(v))
      if (Ep(v))
        return true;
    return false;
  }

  boolean Ep(float v)
  {
    float v1;
    int tok=Lexic.getToken();
    if (tok==Tokens.MAS || tok==Tokens.MENOS){
      if (T(v1)){
        v=v+(tok==Tokens.MAS?v1:-v1);
        if (Ep(v))
          return true;
      }
      return false;
    }
    Lexic.regresarToken();
    return true;
  }

  boolean T(float v)
  {
    if (F(v))
      if (Tp(v))
        return true;
    return  false;
  }

  boolean Tp(float v)
  {
    int tok=Lexic.getToken();
    float v1;
    if (tok==Tokens.PROD||tok==Tokens.DIV){
      if (F(v1)){
        v=v*(tok==Tokens.PROD?v1:1/v1);
        if (Tp(v))
          return true;
      }
      return false
    }
    Lexic.regresarToken();
    return true;
  }

  boolean F(float v)
  {
    int tok=Lexic.getToken();
    switch (tok){
      case Tokens.PAR_I:
        if (E(v))
        {
          tok=Lexic.getToken();
          if (tok==Tokens.PAR_D)
            return true;
        }
        return false;
      case Tokens.NUM:
        v=Float.parseFloat(Lexic.lexema);
        return true;
    }
    return false;
  }






}
