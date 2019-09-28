/*
    Classe que será inserida na tabela de simbolos
*/


public class Simbolo {

    public int simbolo;
    public int tipo;
    public String lexema;

    public Simbolo() {

    }

    public Simbolo(int simbolo, int tipo) {
        this.simbolo = simbolo;
        this.tipo = tipo;
    }

    public void setSimbolo(int simbolo) {
        this.simbolo = simbolo;
    }

    public int getSimbolo() {
        return this.simbolo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return this.tipo;
    }

}