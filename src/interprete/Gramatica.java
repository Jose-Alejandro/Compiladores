package interprete;

import java.util.ArrayList;
import java.util.Stack;

public class Gramatica {

    public LexicGramaticas Lexic;
    public ArrayList<ArrayList<String>> LL1;
    public ArrayList<String> terminales;

    public Gramatica(String cadena) {
        this.Lexic = new LexicGramaticas(cadena);
        this.Lexic.EscanerLexic();
    }

    public boolean AnalizarExpr() {
        boolean R;
        R = G();
        if (R) {
            int tok = Lexic.getToken();
            if (tok == TokensGramaticas.FIN) {
                return R;
            } else {
                return false;
            }
        }
        //Lexic.imprimeTabla();
        return R;
    }

    boolean G() {
        if (ListaReglas()) {
            return true;
        }
        return false;
    }

    boolean ListaReglas() {
        if (Regla()) {
            int tok = Lexic.getToken();
            if (tok == TokensGramaticas.PC) {
                if (ListaReglasP()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean ListaReglasP() {
        Stack<String> edo;
        edo = (Stack<String>) Lexic.getEdo().clone();
        if (Regla()) {
            int tok = Lexic.getToken();
            if (tok == TokensGramaticas.PC) {
                if (ListaReglasP()) {
                    return true;
                }
            }
            return false;
        }
        Lexic.setEdo(edo);
        return true;
    }

    boolean Regla() {
        if (LadoIzquierdo()) {
            int tok = Lexic.getToken();
            if (tok == TokensGramaticas.FLECHA) {
                if (ListaLadosDerechos()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean LadoIzquierdo() {
        int tok = Lexic.getToken();
        if (tok == TokensGramaticas.SIMB) {
            return true;
        }
        return false;
    }

    boolean ListaLadosDerechos() {
        if (LadoDerecho()) {
            if (ListaLadosDerechosP()) {
                return true;
            }
        }
        return false;
    }

    boolean ListaLadosDerechosP() {
        int tok = Lexic.getToken();
        if (tok == TokensGramaticas.OR) {
            if (LadoDerecho()) {
                if (ListaLadosDerechosP()) {
                    return true;
                }
            }
            return false;
        }
        Lexic.returnToken(tok);
        return true;
    }

    boolean LadoDerecho() {
        int tok = Lexic.getToken();
        if (tok == TokensGramaticas.SIMB) {
            if (LadoDerechoP()) {
                return true;
            }
        }
        return false;
    }

    boolean LadoDerechoP() {
        int tok = Lexic.getToken();
        if (tok == TokensGramaticas.SIMB) {
            if (LadoDerechoP()) {
                return true;
            }
            return false;
        }
        Lexic.returnToken(tok);
        return true;
    }

    public ArrayList<String> First(String regla) {
//        System.out.println("Regla: " + regla);
        String[] simbolosRegla = regla.split(" ");
        ArrayList<String> simbolos = new ArrayList();
        //noTerminales es una lista de Strings con cada simbolo (o cadena) no terminal analizado en la gramática
        if (simbolosRegla[0].equals("€")) {
//            System.out.println("entra €");
            simbolos.add("€");
            return simbolos;
        }
        if(Lexic.Terminales.contains(simbolosRegla[0])) {
//            System.out.println("entra Term: " + simbolosRegla[0]);
            simbolos.add(simbolosRegla[0]);
            return simbolos;
        }
        ArrayList<String> reglas = new ArrayList();
        for (String s : Lexic.NoTerminales) {
            //System.out.println("NT: " + s);
            if (s.equals(simbolosRegla[0] + " ")) {
//                System.out.println("entra s: " + s);
                //reglas.add(Lexic.ListaReglas.get(j).get(1));
                for(int j = 0; j != Lexic.ListaReglas.size(); j++) {
                    if(s.equals(Lexic.ListaReglas.get(j).get(0))) {
                        reglas.add(Lexic.ListaReglas.get(j).get(1));
                        //System.out.println("add regla" + j);
                    }
                }
                break;
            }
        }
        for (int i = 0; i != reglas.size(); i++) {
            for(String s : First(reglas.get(i))) {
                if(!simbolos.contains(s)) {
                    //System.out.println("add simb");
                    simbolos.add(s);
                }
            }
            //simbolos.addAll(First(reglas.get(i)));
        }
        if (simbolos.contains("€")) {
            simbolos.remove("€");
            String cadena = regla.substring(1);
            simbolos.addAll(First(cadena));
//            for(String s : simbolos) {
//                System.out.print(s + ", ");
//            }
//            System.out.println("entra € final");
            return simbolos;
        }
//        for(String s : simbolos) {
//            System.out.print(s + ",");
//        }
//        System.out.println("\nTermina");
        return simbolos;
    }

    public ArrayList<String> Follow(String regla) {
//        System.out.println("No terminal: " + regla);
        ArrayList<String> simbolos = new ArrayList();
        if (regla.equals(Lexic.simboloInicial)) {
            simbolos.add("$");
//            System.out.println("incial");
        }
        for (ArrayList<String> s : Lexic.ListaReglas) {
            if (s.get(1).contains(regla)) {
                try {
                    if(s.get(1).charAt(s.get(1).indexOf(regla) - 1) >= 'A'
                       && s.get(1).charAt(s.get(1).indexOf(regla) - 1) <= 'z') {
//                        System.out.println("ENTER");
                        continue;
                    }
                } catch(Exception e) {}
//                System.out.println("regla: " + s.get(1));
//                System.out.println("indice: " + s.get(1).indexOf(regla));
//                System.out.println("length: " + regla.length());
//                System.out.println("indice2: " + (s.get(1).length() - regla.length()));
                if (s.get(1).indexOf(regla) != (s.get(1).length() - regla.length())) {
//                    System.out.println("entra2");
                    String extract = s.get(1).substring(s.get(1).indexOf(regla) + regla.length());
//                    System.out.println("extract: " + extract);
                    ArrayList<String> w = First(extract);
//                    for(String W : w) {
//                        System.out.println("W: " + W);
//                    }
                    simbolos.addAll(w);
                    if (simbolos.contains("€")) {
//                        System.out.println("aqui");
                        simbolos.remove("€");
                        simbolos.addAll(Follow(s.get(0)));
                    }
                } else {
//                    System.out.println("entra");
                    if(!s.get(0).equals(regla)) {
                        simbolos.addAll(Follow(s.get(0)));
                    }
                }
            }
        }
//        System.out.println("return");
        return simbolos;
    }
    
    public void tablaLL1() {
        LL1 = new ArrayList();
        ArrayList<String> inicial = new ArrayList();
        inicial.add("");
        for(String s : Lexic.Terminales) {      //Agregar los Terminales a LL1
            inicial.add(s);
        }
        inicial.add("$");
        System.out.print("Fila: ");
        for(String s : inicial) {
            System.out.print(s + "   ");
        }
        System.out.println();
        LL1.add(inicial);
        for (int i = 0; i != Lexic.ListaReglas.size(); i++) {
            ArrayList<String> fila = new ArrayList();
            ArrayList<String> first = new ArrayList();
            fila.add(Lexic.ListaReglas.get(i).get(0));
            if(Lexic.ListaReglas.get(i).get(1).equals("€ ")) {
//                System.out.println("Follow");
                first.addAll(Follow(Lexic.ListaReglas.get(i).get(0)));
//                for(String s : first) {
//                    System.out.println("s = " + s);
//                }
                //break;
            } else {
//                System.out.println("First");
                first.addAll(First(Lexic.ListaReglas.get(i).get(1)));
//                for(String s : first) {
//                    System.out.println("f = " + s);
//                }
                //break;
            }
//            for(String s : first) {
//                System.out.println("final = " + s);
//            }
            int x = 0;
            for (int j = 1; j != inicial.size(); j++) {
                if(inicial.get(j).equals(first.get(x))) {
                    fila.add(Lexic.ListaReglas.get(i).get(1) + "-" + i);
                } else {
                    fila.add("-");
                }
            }
//            System.out.print("Fila: ");
//            for(String s : fila) {
//                System.out.print(s + "   ");
//            }
//            System.out.println();
            LL1.add(fila);
        }
        for (int i = 0; i != Lexic.Terminales.size() + 1; i++) {
            ArrayList<String> fila = new ArrayList();
            fila.add(inicial.get(i + 1));
            for (int j = 1; j != inicial.size(); j++) {
                if(inicial.get(j).equals(fila.get(0))) {
                    fila.add("pop");
                } else {
                    fila.add("-");
                }
            }
//            System.out.print("Fila: ");
//            for(String s : fila) {
//                System.out.print(s + "   ");
//            }
//            System.out.println();
            LL1.add(fila);
        }
        for (int i = 0; i != LL1.size(); i++) {
            System.out.print("Fila: ");
            for(String s : LL1.get(i)) {
                System.out.print(s + "   ");
            }
            System.out.println();
        }
    }
}
