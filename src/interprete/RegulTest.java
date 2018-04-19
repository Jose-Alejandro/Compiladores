package interprete;

public class RegulTest {
  public static void main(String[] args) {
<<<<<<< HEAD
    AFN_Autom regul=new AFN_Autom("(a|b)+&z*");
    regul.analizar();
    System.out.println("Acpetación de ababzz con autómata (a|b)+&z*: "+regul.result.AnalizarCadena("ababzz"));
    System.out.println("Acpetación de abab con autómata (a|b)+&z*: "+regul.result.AnalizarCadena("abab"));
=======
    AFN_Autom regul=new AFN_Autom("(a&b)+&z");
    regul.analizar();
    System.out.println("Acpetación de ababzz con autómata (a&b)+&z: "+regul.result.AnalizarCadena("ababzz"));
    System.out.println("Acpetación de ababz con autómata (a&b)+&z: "+regul.result.AnalizarCadena("ababz"));
>>>>>>> b74625a40a5480ab1b7ca31b71b9611204b839ce
  }
}
