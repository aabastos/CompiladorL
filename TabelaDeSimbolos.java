import java.util.ArrayList;

/*
    Classe da tabela de simbolos
*/

public class TabelaDeSimbolos {
    
    public ArrayList<Simbolo> tabelaDeSimbolos;

    public TabelaDeSimbolos() {
        tabelaDeSimbolos = new ArrayList<Simbolo>();
    }

    public void adicionarSimbolo(Simbolo simbolo) {
        tabelaDeSimbolos.add(simbolo);
    }

    public void removerSimbolo(Simbolo simbolo) {
        try{
            tabelaDeSimbolos.remove(simbolo);
        } catch (Exception e) {
            System.out.println("Erro ao remover símbolo.");
        }
    }

    public int buscarSimbolo(String lexema) {
        for (Simbolo simbolo : tabelaDeSimbolos) {
            if(simbolo.lexema.equals(lexema)){
                return tabelaDeSimbolos.indexOf(simbolo);
            }
        }
        return -1;
    }

    public int buscarSimbolo(Simbolo simbolo) {
        try{
            return tabelaDeSimbolos.indexOf(simbolo);
        } catch (Exception e) {
            return -1;
        }
    }

}