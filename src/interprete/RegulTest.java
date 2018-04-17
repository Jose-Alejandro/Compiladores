package interprete;

public class RegulTest {
  public static void main(String[] args) {
    AFN_Autom regul=new AFN_Autom("(a|b)+&z*");
    regul.analizar();
    System.out.println("Acpetación de ababzz con autómata (a|b)+&z*: "+regul.result.AnalizarCadena("ababzz"));
    System.out.println("Acpetación de abab con autómata (a|b)+&z*: "+regul.result.AnalizarCadena("abab"));
  }
}
