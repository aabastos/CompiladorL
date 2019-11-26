/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
 *
 *  Classe que será inserida na tabela de simbolos
*/

public class Registro {

    public Token token;
    public String tipo;
    public String endereco;
    public String classe;

    public Registro() {
        this.classe = Classe.tipo_vazio;
    }

    public Registro(Token token) {
        this.token = token;
    }

    public Registro(Token token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    // public Registro(Token token, int tipo, String endereco) {
    // this.token = token;
    // this.tipo = tipo;
    // this.endereco = endereco;
    // }

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

}