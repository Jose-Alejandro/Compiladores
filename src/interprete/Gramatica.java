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
        String[] simbolosRegla = regla.split(" ");
        ArrayList<String> simbolos = new ArrayList();
        //noTerminales es una lista de Strings con cada simbolo (o cadena) no terminal analizado en la gramática
        if (simbolosRegla[1].equals("€") || !Lexic.NoTerminales.contains(simbolosRegla[1])) {
            simbolos.add(simbolosRegla[1]);
            return simbolos;
        }

        ArrayList<String> reglas = new ArrayList();
        int j = 0;
        for (String s : Lexic.NoTerminales) {
            if (simbolosRegla[0].equals(s)) {
                reglas.add(Lexic.ListaReglas.get(j).get(1));
            }
            j++;
        }
        for (int i = 0; i < reglas.size(); i++) {
            simbolos.addAll(First(reglas.get(i)));
        }
        if (simbolos.contains("€")) {
            simbolos.remove("€");
            simbolos.addAll(First(regla.substring(1)));
            return simbolos;
        }
        return simbolos;
    }

    public ArrayList<String> Follow(String regla) {
        ArrayList<String> simbolos = new ArrayList();
        if (regla.equals(Lexic.simboloInicial)) {
            simbolos.add("$");
        }
        for (ArrayList<String> s : Lexic.ListaReglas) {
            if (s.get(1).contains(regla)) {
                if (s.get(1).indexOf(regla) != 0) {
                    ArrayList<String> w = First(s.get(1).substring(s.get(1).indexOf(regla) + regla.length()));
                    simbolos.addAll(w);
                    if (simbolos.contains("€")) {
                        simbolos.remove("€");
                        simbolos.addAll(Follow(s.get(0)));
                    }
                } else {
                    simbolos.addAll(Follow(s.get(0)));
                }
            }
        }
        return simbolos;
    }
    
    public void tablaLL1() {
        LL1 = new ArrayList();
        ArrayList inicial = new ArrayList();
        inicial.add("");
        for(String s : Lexic.NoTerminales) {
            inicial.add(s);
        }
        //Aquí falta agregar los terminales de la gramática
        inicial.add("$");
        LL1.add(inicial);
        for (int i = 0; i != Lexic.ListaReglas.size(); i++) {
            if(Lexic.ListaReglas.get(i).get(1).equals("€ ")) {
                ArrayList first = Follow(Lexic.ListaReglas.get(i).get(0));
            } else {
                ArrayList first = First(Lexic.ListaReglas.get(i).get(1));
            }
            //aquí falta agregar en inicial, las acciones de la tabla (ArrayList de Strings)
        }
    }
}
