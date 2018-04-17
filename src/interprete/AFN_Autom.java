package interprete;
import java.util.HashSet;

public class AFN_Autom {
  Scanner lexic;
  Afn f=new Afn();
  public Afn result=new Afn();

  public AFN_Autom(String expresion)
  {
    this.lexic=new Scanner(expresion);
  }

  public boolean analizar()
  {
    boolean R=E(this.f);
    if (R){
      if (lexic.getToken()==Tokens.FIN){
        result=f;
        return R;
      }else
        return false;
    }
    return R;
  }



  boolean E(Afn f){
    if (T(f)){
      if (Ep(f))
        return true;
    }
    return false;
  }

  boolean Ep(Afn f){
    int tok;
    Afn f1=new Afn();
    tok=lexic.getToken();
    if (tok==Tokens.OR){
      if (T(f1)){
        f.unirAfn(f1);
        if (Ep(f))
          return true;
      }
      return false;
    }
    lexic.regresarToken();
    return true;
  }

  boolean T(Afn f){
    if (C(f)){
      if (Tp(f))
        return true;
    }
    return false;
  }

  boolean Tp(Afn f){
    int tok;
    Afn f1=new Afn();
    tok=lexic.getToken();
    if (tok==Tokens.CONC){
      if (C(f1)){
        f.ConcatenarAfn(f1);
        if (Tp(f))
          return true;
      }
      return false;
    }
    lexic.regresarToken();
    return true;
  }

  boolean C(Afn f){
    if (F(f)){
      if (Cp(f)){
        return true;
      }

    }
    return false;
  }

  boolean Cp(Afn f){
    int tok=lexic.getToken();
    switch (tok){
      case Tokens.MAS:
        f.cerrMas();
        break;
      case Tokens.PROD:
        f.cerrEstrella();
        break;
      case Tokens.OPC:
        f.Opcional();
        break;
      default:
        lexic.regresarToken();
        return true;
    }
    if (Cp(f)){
      return true;
    }
    return false;
  }

  boolean F(Afn f){
    int tok=lexic.getToken();
    switch (tok){
      case Tokens.PAR_I:
        if (E(f)){
          tok=lexic.getToken();
          if (tok==Tokens.PAR_D)
            return true;
        }
        return false;
      case Tokens.SIMB:
        f.AfnBasico(lexic.getLexema().charAt(0));
        return true;
    }
    return false;
  }
}