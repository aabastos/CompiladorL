/*
    Classe para os tokens, que possui o simbolo e o lexema
*/

public class Token {

    public byte simbolo;
    public String lexema;

    public Token() {

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