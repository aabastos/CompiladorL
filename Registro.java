/*
    Classe que será inserida na tabela de simbolos
*/


public class Registro {

    public int simbolo;
    public int tipo;
    public String lexema;
    public String endereco;

    public Registro() {

    }

    public Registro(int simbolo, int tipo, String lexema, String endereco) {
        this.simbolo = simbolo;
        this.tipo = tipo;
        this.lexema = lexema;
        this.endereco = endereco;
    }

    public int getSimbolo() {
        return this.simbolo;
    }

    public void setSimbolo(int simbolo) {
        this.simbolo = simbolo;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}