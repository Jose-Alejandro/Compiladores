package interprete;

public class Scanner
{
  private int ApAct, ApIniLexema,ApAnt;
  private String Lexema, CadenaEntrada;
  private int Token;

  public Scanner(String CadenaEntrada) 
  {
    this.ApAct = 0;
    this.ApIniLexema = 0;
    this.ApAnt = -1;
    this.Lexema = "";
    this.CadenaEntrada = CadenaEntrada;
  }

  public Scanner() 
  {
    this.ApAct = 0;
    this.ApIniLexema = 0;
    this.ApAnt = -1;
    this.Lexema = "";
    this.CadenaEntrada = "";
  }

  boolean EsDigito(char c)
  {
    if ( (c>='0' && c<='9' ) ) {
      return true;
    }
    return false;
  }

  boolean EsLetra(char c)
  {
    if ( (c>='a' && c<='z' ) || (c>='A' && c<='Z' ) ){
      return true;
    }
    return false;
  }

  public int GetToken() {
    ApAnt=ApAct;
    while( ApAct<CadenaEntrada.length() && (CadenaEntrada.charAt(ApAct)==' ' || CadenaEntrada.charAt(ApAct)=='\n' || CadenaEntrada.charAt(ApAct)=='a') )
    {
      ApAct++;     
    }
    if (ApAct==CadenaEntrada.length())
    {
      Lexema="";
      Token=Tokens.FIN;
      return Tokens.FIN;
    }
    if (EsDigito( CadenaEntrada.charAt(ApAct) ) ) 
    {
      Lexema="";
      while (ApAct<CadenaEntrada.length()&& EsDigito(CadenaEntrada.charAt(ApAct)))
      {
        Lexema=Lexema+CadenaEntrada.charAt(ApAct);
        ApAct++;
      }
      if (ApAct<CadenaEntrada.length() && CadenaEntrada.charAt(ApAct)=='.' )
      {
        Lexema=Lexema+CadenaEntrada.charAt(ApAct);
        ApAct++;
        while (ApAct<CadenaEntrada.length()&& EsDigito(CadenaEntrada.charAt(ApAct) ) )
        {
          Lexema=Lexema+CadenaEntrada.charAt(ApAct);
          ApAct++;
        }
      }
      Token=Tokens.NUM;
      return Tokens.NUM;
    }
    if ( EsLetra(CadenaEntrada.charAt(ApAct) ) )
    {
      Lexema="";
      while (EsLetra(CadenaEntrada.charAt(ApAct)) || EsDigito(CadenaEntrada.charAt(ApAct) ) )
      {
        Lexema=Lexema+CadenaEntrada.charAt(ApAct);
        ApAct++;
      }
      if (Lexema.toUpperCase().equals("SENO") )
      {
        Token=Tokens.SENO;
        return Tokens.SENO;
      }
      if (Lexema.toUpperCase().equals("COSENO") )
      {
        Token=Tokens.COSENO;
        return Tokens.COSENO;
      }
      if (Lexema.toUpperCase().equals("TANGENTE") )
      {
        Token=Tokens.TANGENTE;
        return Tokens.TANGENTE;
      }
      if (Lexema.toUpperCase().equals("EXP") )
      {
        Token=Tokens.EXP;
        return Tokens.EXP;
      }
      if (Lexema.toUpperCase().equals("LN") )
      {
        Token=Tokens.LN;
        return Tokens.LN;
      }
      if (Lexema.toUpperCase().equals("LOG") )
      {
        Token=Tokens.LOG;
        return Tokens.LOG;
      }
      if (Lexema.toUpperCase().equals("VAR") )
      {
        Token=Tokens.VAR;
        return Tokens.VAR;
      }
      Token=Tokens.ERROR;
    }
    switch (CadenaEntrada.charAt(ApAct))
    {
      case '+':
        Lexema="+";
        ApAct++;
        Token=Tokens.MAS;
        return Tokens.MAS;
      case '-':
        Lexema="-";
        ApAct++;
        Token=Tokens.MENOS;
        return Tokens.MENOS;
      case '*':
        Lexema="*";
        ApAct++;
        Token=Tokens.PROD;
        return Tokens.PROD;
      case '/':
        Lexema="/";
        ApAct++;
        Token=Tokens.DIV;
        return Tokens.DIV;
      case '(':
        Lexema="(";
        ApAct++;
        Token=Tokens.PAR_I;
        return Tokens.PAR_I;
      case ')':
        Lexema=")";
        ApAct++;
        Token=Tokens.PAR_D;
        return Tokens.PAR_D;
      case '^':
        Lexema="^";
        ApAct++;
        Token=Tokens.POTENCIA;
        return Tokens.POTENCIA;
    }
    Lexema=""+CadenaEntrada.charAt(ApAct);
    ApAct++;
    Token=Tokens.ERROR;
    return Tokens.ERROR;
  }
  public String CadenaToken()
  {
    switch(Token)
    {
      case Tokens.FIN:
        return "FIN";
      case Tokens.ERROR:
        return "ERROR";
      case Tokens.MAS:
        return "MAS";
      case Tokens.MENOS:
        return "MENOS";
      case Tokens.PROD:
        return "PROD";
      case Tokens.DIV:
        return "DIV";
      case Tokens.PAR_I:
        return "PAR_I";
      case Tokens.PAR_D:
        return "PAR_D";
      case Tokens.NUM:
        return "NUM";
      case Tokens.POTENCIA:
        return "POTENCIA";
      case Tokens.SENO:
        return "SENO";
      case Tokens.COSENO:
        return "COSENO";
      case Tokens.TANGENTE:
        return "TANGENTE";
      case Tokens.EXP:
        return "EXP";
      case Tokens.LN:
        return "LN";
      case Tokens.LOG:
        return "LOG";
      case Tokens.VAR:
        return "VAR";
    }
    return "ERROR";
  }  
}