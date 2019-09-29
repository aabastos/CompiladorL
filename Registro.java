/*
    Classe que será inserida na tabela de simbolos
*/


public class Registro {

    public Token token;
    public int tipo;
    public String endereco;

    public Registro() {
    }

    public Registro(Token token) {
        this.token = token;
    }

    public Registro(Token token, String endereco) {
        this.token = token;
        this.endereco = endereco;
    }

    public Registro(Token token, int tipo, String endereco) {
        this.token = token;
        this.tipo = tipo;
        this.endereco = endereco;
    }

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}