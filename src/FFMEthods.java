/*
import java.util.ArrayList;

public class FFMEthods {
  //Para este método se debe tener una tabla que contenga las transiciones, una por renglón
  //la tabla debería ser un arrelgo bidimensional de nx2 de Strings llamado "ListaReglas"
  public ArrayList<String> First(String regla) {
    String[] simbolosRegla = regla.split(" ");
    ArrayList<String> simbolos = new ArrayList();
    //noTerminales es una lista de Strings con cada simbolo (o cadena) no terminal analizado en la gramática
    if(simbolosRegla[1].equals("€") || !noTerminales.contains(simbolosRegla[1])) {
      simbolos.add(simbolosRegla[1]);
      return simbolos;
    }
    //Uso de ListaReglas
    ArrayList<String> reglas = new ArrayList();
    int j = 0;
    for(String s : noTerminales) {
      if(simbolosRegla[0].equals(s)) {
        reglas.add(ListaReglas[j][2]);
      }
      j++;
    }
    for(int i = 0; i < reglas.size(); i++) {
      simbolos.addAll(First(reglas.get(i)));
    }
    if(simbolos.contains("€")) {
      simbolos.remove("€");
      simbolos.addAll(First(regla.substring(1)));
      return simbolos;
    }
    return simbolos;
  }

  //Tambien se necesita la tabla "ListaReglas"
  public ArrayList<String> Follow(String regla) {
    ArrayList<String> simbolos = new ArrayList();
    if(regla.equals(Lexic.simboloInicial)) {
      simbolos.add("$");
    }
    for(String[] s : ListaReglas) {
      if(s[1].contains(regla)) {
        if(s[1].indexOf(regla) != 0) {
          ArrayList<String> w = First(s[1].substring(s[1].indexOf(regla)+regla.length()));
          simbolos.addAll(w);
          if(simbolos.contains("€")) {
            simbolos.remove("€");
            simbolos.addAll(Follow(s[0]));
          }
        } else {
          simbolos.addAll(Follow(s[0]));
        }
      }
    }
    return simbolos;
  }
}
*/
