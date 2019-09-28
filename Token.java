/*
    Classe para os tokens, que possui o simbolo e o lexema
*/

public class Token {

    public Simbolo simbolo;
    public String lexema;

    public Token() {

    }

    public Token(Simbolo simbolo, String lexema) {
        this.simbolo = simbolo;
        this.lexema = lexema;
    }

    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    public Simbolo getSimbolo() {
        return this.simbolo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getLexema() {
        return this.lexema;
    }

}