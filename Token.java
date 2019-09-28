/*
    Classe para os tokens, que possui o simbolo e o lexema
*/

public class Token {

    public int simbolo;
    public String lexema;

    public Token() {

    }

    public Token(int simbolo, String lexema) {
        this.simbolo = simbolo;
        this.lexema = lexema;
    }

    public void setSimbolo(int simbolo) {
        this.simbolo = simbolo;
    }

    public int getSimbolo() {
        return this.simbolo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getLexema() {
        return this.lexema;
    }

}