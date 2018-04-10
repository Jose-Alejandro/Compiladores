package interprete;

class Mfloat{
 public float v=0;
}


public class Calculadora {
  Scanner Lexic;
  public float result;
  public Calculadora(String cadena){
    this.Lexic=new Scanner(cadena);
  }

  public boolean AnalizarExpr()
  {
    boolean R;
    Mfloat v=new Mfloat();
    R=E(v);
    if (R){
      if (Lexic.getToken()==Tokens.FIN){
        result=v.v;
        return R;
      }
      else
        return false;
    }
    return R;
  }

  boolean E(Mfloat v)
  {
    if (T(v))
      if (Ep(v))
        return true;
    return false;
  }

  boolean Ep(Mfloat v)
  {
    Mfloat v1=new Mfloat();
    int tok=Lexic.getToken();
    if (tok==Tokens.MAS || tok==Tokens.MENOS){
      if (T(v1)){
        v.v=v.v+(tok==Tokens.MAS?v1.v:-v1.v);
        if (Ep(v))
          return true;
      }
      return false;
    }
    Lexic.regresarToken();
    return true;
  }

  boolean T(Mfloat v)
  {
    if (F(v))
      if (Tp(v))
        return true;
    return  false;
  }

  boolean Tp(Mfloat v)
  {
    int tok=Lexic.getToken();
    Mfloat v1=new Mfloat();
    if (tok==Tokens.PROD||tok==Tokens.DIV || tok==Tokens.POTENCIA){
      if (F(v1)){
        switch (tok){
          case Tokens.PROD:
            v.v*=v1.v;
            break;
          case Tokens.DIV:
            v.v/=v1.v;
            break;
          case  Tokens.POTENCIA:
            v.v= (float) Math.pow(v.v,v1.v);
            break;
        }
        if (Tp(v))
          return true;
      }
      return false;
    }
    Lexic.regresarToken();
    return true;
  }

  boolean F(Mfloat v)
  {
    int tok=Lexic.getToken();
    switch (tok){
      case Tokens.SENO:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.sin(v.v);
                return true;
              }
            }
            return false;
        }
      case Tokens.COSENO:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.cos(v.v);
                return true;
              }
            }
            return false;
        }
      case Tokens.TANGENTE:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.tan(v.v);
                return true;
              }
            }
            return false;
        }
      case Tokens.EXP:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.exp(v.v);
                return true;
              }
            }
            return false;
        }
      case Tokens.LN:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.log(v.v);
                return true;
              }
            }
            return false;
        }
      case Tokens.LOG:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.log10(v.v);
                return true;
              }
            }
            return false;
        }
      case Tokens.PAR_I:
        if (E(v))
        {
          tok=Lexic.getToken();
          if (tok==Tokens.PAR_D)
            return true;
        }
        return false;
      case Tokens.NUM:
        v.v=Float.parseFloat(Lexic.getLexema());
        return true;
    }
    return false;
  }
}
