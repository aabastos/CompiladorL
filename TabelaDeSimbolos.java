import java.util.ArrayList;

/*
    Classe da tabela de simbolos
*/

public class TabelaDeSimbolos {
    
    public ArrayList<Registro> tabelaDeSimbolos;

    public TabelaDeSimbolos() {
        tabelaDeSimbolos = new ArrayList<Registro>();
    }

    public void adicionarSimbolo(int simbolo, int tipo, String lexema, String endereco) {
        tabelaDeSimbolos.add(new Registro(simbolo, tipo, lexema, endereco));
    }

    public void removerSimbolo(Registro registro) {
        try{
            tabelaDeSimbolos.remove(registro);
        } catch (Exception e) {
            System.out.println("Erro ao remover registro da tabela.");
        }
    }

    public int buscarSimbolo(String lexema) {
        for (Registro registro : tabelaDeSimbolos) {
            if(registro.lexema.equals(lexema)){
                return tabelaDeSimbolos.indexOf(registro);
            }
        }
        return -1;
    }

    public int buscarSimbolo(Registro registro) {
        try{
            return tabelaDeSimbolos.indexOf(registro);
        } catch (Exception e) {
            return -1;
        }
    }

}