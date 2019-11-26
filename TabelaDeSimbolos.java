/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
 *
 *   Classe da tabela de simbolos
*/

import java.util.ArrayList;

public class TabelaDeSimbolos {

    public ArrayList<Registro> tabelaDeSimbolos;

    public TabelaDeSimbolos() {
        tabelaDeSimbolos = new ArrayList<Registro>();
    }

    public void adicionarRegistro(Token token) {
        tabelaDeSimbolos.add(new Registro(token));
    }

    public void adicionarRegistro(Token token, String tipo) {
        tabelaDeSimbolos.add(new Registro(token, tipo));
    }

    public void removerSimbolo(Registro registro) {
        try {
            tabelaDeSimbolos.remove(registro);
        } catch (Exception e) {
            System.out.println("Erro ao remover registro da tabela.");
        }
    }

    public int buscarSimbolo(String lexema) {
        for (Registro registro : tabelaDeSimbolos) {
            if (registro.token.lexema.equals(lexema)) {
                return tabelaDeSimbolos.indexOf(registro);
            }
        }
        return -1;
    }

    public int buscarSimbolo(Registro registro) {
        try {
            return tabelaDeSimbolos.indexOf(registro);
        } catch (Exception e) {
            return -1;
        }
    }
}