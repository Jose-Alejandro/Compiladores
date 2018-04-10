package interprete;
import java.util.HashSet;

public class AFN_Autom {
  Scanner lexic=new Scanner("(52*2)+4");

  /*boolean E(Afn f){
    if (T(f)){
      if (Ep(f))
        return true;
    }
    return false;
  }

  boolean Ep(Afn f){
    int tok;
    Afn f1;
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
    Afn f1;
    tok=lexic.getToken();
    if (tok==tokens.CONC){
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
      case tokens.MAS:
        f.cerrMas();
        break;
      case tokens.PROD:
        f.cerrEstrella();
        break;
      case tokens.OPC:
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
      case tokens.PARI:
        if (E(f)){
          tok=lexic.getToken();
          if (tok==tokens.PAR_D)
            return true;
        }
        return false;
      case tokens.SIMB:
        f.AfnBasico(lexic.lexema.charAt(0));
        return true;
    }
    return false;
  }*/
}