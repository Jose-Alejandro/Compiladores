package interprete;

class Mfloat{
 public float v=0;
}

class MString{
  public String s="";
}


public class Calculadora {
  Scanner Lexic;
  public float result;
  public String posfijo="",prefijo="";
  public MString pos=new MString(),pre=new MString();


  public Calculadora(String cadena){
    this.Lexic=new Scanner(cadena);
  }

  public boolean AnalizarExpr()
  {
    boolean R;
    Mfloat v=new Mfloat();
    R=E(v,pos,pre);
    if (R){
      if (Lexic.getToken()==Tokens.FIN){
        result=v.v;
        prefijo=pre.s;
        posfijo=pos.s;
        return R;
      }
      else
        return false;
    }
    return R;
  }

  boolean E(Mfloat v,MString pos, MString pre)
  {
    if (T(v,pos,pre))
      if (Ep(v,pos,pre))
        return true;
    return false;
  }

  boolean Ep(Mfloat v,MString pos, MString pre)
  {
    Mfloat v1=new Mfloat();
    MString pre2=new MString(),pos2=new MString();
    int tok=Lexic.getToken();
    if (tok==Tokens.MAS || tok==Tokens.MENOS){
      if (T(v1,pos2, pre2)){
        switch (tok){
          case Tokens.MAS:
            v.v=v.v+v1.v;
            pos.s+=" "+pos2.s+" "+"+";
            pre.s="+ "+pre.s+" "+pre2.s;
            break;
          case Tokens.MENOS:
            v.v=v.v-v1.v;
            pos.s+=" "+pos2.s+" "+"-";
            pre.s="- "+pre.s+" "+pre2.s;
            break;
        }
        if (Ep(v,pos,pre))
          return true;
      }
      return false;
    }
    Lexic.regresarToken();
    return true;
  }

  boolean T(Mfloat v,MString pos, MString pre)
  {
    if (F(v,pos,pre))
      if (Tp(v,pos,pre))
        return true;
    return  false;
  }

  boolean Tp(Mfloat v,MString pos,MString pre)
  {
    int tok=Lexic.getToken();
    Mfloat v1=new Mfloat();
    MString pre2=new MString(),pos2=new MString();
    if (tok==Tokens.PROD||tok==Tokens.DIV || tok==Tokens.POTENCIA){
      if (F(v1,pos2,pre2)){
        switch (tok){
          case Tokens.PROD:
            v.v*=v1.v;
            pos.s+=" "+pos2.s+" "+"*";
            pre.s="* "+pre.s+" "+pre2.s;
            break;
          case Tokens.DIV:
            v.v/=v1.v;
            pos.s+=" "+pos2.s+" "+"/";
            pre.s="/ "+pre.s+" "+pre2.s;
            break;
          case  Tokens.POTENCIA:
            v.v= (float) Math.pow(v.v,v1.v);
            pos.s+=" "+pos2.s+" "+"^";
            pre.s="^ "+pre.s+" "+pre2.s;
            break;
        }
        if (Tp(v,pos,pre))
          return true;
      }
      return false;
    }
    Lexic.regresarToken();
    return true;
  }

  boolean F(Mfloat v,MString pos,MString pre)
  {
    int tok=Lexic.getToken();
    switch (tok){
      case Tokens.SENO:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v,pos,pre))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.sin(v.v);
                pos.s+=" "+"seno";
                pre.s="seno "+pre.s;
                return true;
              }
            }
            return false;
        }
      case Tokens.COSENO:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v,pos,pre))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.cos(v.v);
                pos.s+=" "+"coseno";
                pre.s="coseno "+pre.s;
                return true;
              }
            }
            return false;
        }
      case Tokens.TANGENTE:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v,pos,pre))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.tan(v.v);
                pos.s+=" "+"tangente";
                pre.s="tangente "+pre.s;
                return true;
              }
            }
            return false;
        }
      case Tokens.EXP:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v,pos,pre))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.exp(v.v);
                pos.s+=" "+"exp";
                pre.s="exp "+pre.s;
                return true;
              }
            }
            return false;
        }
      case Tokens.LN:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v,pos,pre))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.log(v.v);
                pos.s+=" "+"ln";
                pre.s="ln "+pre.s;
                return true;
              }
            }
            return false;
          default:
            return false;
        }
      case Tokens.LOG:
        tok=Lexic.getToken();
        switch (tok){
          case Tokens.PAR_I:
            if (E(v,pos,pre))
            {
              tok=Lexic.getToken();
              if (tok==Tokens.PAR_D){
                v.v= (float) Math.log10(v.v);
                pos.s+=" "+"log";
                pre.s="log "+pre.s;
                return true;
              }
            }
            return false;
        }
      case Tokens.PAR_I:
        if (E(v,pos,pre))
        {
          tok=Lexic.getToken();
          if (tok==Tokens.PAR_D)
            return true;
        }
        return false;
      case Tokens.NUM:
        v.v=Float.parseFloat(Lexic.getLexema());
        pos.s+=Lexic.getLexema();
        pre.s+=Lexic.getLexema();
        return true;
    }
    return false;
  }
}
