/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
 *  Classe para os tokens, que possui o simbolo e o lexema
*/

public class Token {

    public byte simbolo;
    public String lexema;
    public String classe;
    public String tipo;

    public Token() {

    }

    public Token(byte simbolo, String lexema, String classe, String tipo) {
        this.simbolo = simbolo;
        this.lexema = lexema;
        this.classe = classe;
        this.tipo = tipo;
    }

    public Token(byte simbolo, String lexema, String tipo) {
        this.simbolo = simbolo;
        this.lexema = lexema;
        this.tipo = tipo;
    }

    public Token(byte simbolo, String lexema) {
        this.simbolo = simbolo;
        this.lexema = lexema;
    }

    public void setSimbolo(byte simbolo) {
        this.simbolo = simbolo;
    }

    public byte getSimbolo() {
        return this.simbolo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getLexema() {
        return this.lexema;
    }

}