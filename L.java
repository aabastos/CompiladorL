/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
*/

public class L {

  public static TabelaDeSimbolos tabela = new TabelaDeSimbolos();

  public static void main(String[] args) throws Exception {
    inicializarTabela();

    Leitor leitorFonte = new Leitor(args[0]);
    AnalisadorLexico al = new AnalisadorLexico(leitorFonte, tabela);
    AnalisadorSintatico as = new AnalisadorSintatico(al, tabela);

    // al.getProximoRegistro();
    as.S();
  }

  public static void inicializarTabela() {
    tabela.adicionarRegistro(new Token(Simbolos.constante, "const"));
    tabela.adicionarRegistro(new Token(Simbolos.inteiro, "integer"));
    tabela.adicionarRegistro(new Token(Simbolos.bit, "byte"));
    tabela.adicionarRegistro(new Token(Simbolos.string, "string"));
    tabela.adicionarRegistro(new Token(Simbolos.enquanto, "while"));
    tabela.adicionarRegistro(new Token(Simbolos.se, "if"));
    tabela.adicionarRegistro(new Token(Simbolos.senao, "else"));
    tabela.adicionarRegistro(new Token(Simbolos.e, "and"));
    tabela.adicionarRegistro(new Token(Simbolos.ou, "or"));
    tabela.adicionarRegistro(new Token(Simbolos.nao, "not"));
    tabela.adicionarRegistro(new Token(Simbolos.igual, "="));
    tabela.adicionarRegistro(new Token(Simbolos.igualIgual, "=="));
    tabela.adicionarRegistro(new Token(Simbolos.abreParenteses, "("));
    tabela.adicionarRegistro(new Token(Simbolos.fechaParenteses, ")"));
    tabela.adicionarRegistro(new Token(Simbolos.diferente, "!="));
    tabela.adicionarRegistro(new Token(Simbolos.maiorOuIgual, ">="));
    tabela.adicionarRegistro(new Token(Simbolos.menorOuIgual, "<="));
    tabela.adicionarRegistro(new Token(Simbolos.menor, "<"));
    tabela.adicionarRegistro(new Token(Simbolos.maior, ">"));
    tabela.adicionarRegistro(new Token(Simbolos.virgula, ","));
    tabela.adicionarRegistro(new Token(Simbolos.mais, "+"));
    tabela.adicionarRegistro(new Token(Simbolos.menos, "-"));
    tabela.adicionarRegistro(new Token(Simbolos.multiplicacao, "*"));
    tabela.adicionarRegistro(new Token(Simbolos.divisao, "/"));
    tabela.adicionarRegistro(new Token(Simbolos.pontoEVirgula, ";"));
    tabela.adicionarRegistro(new Token(Simbolos.inicio, "begin"));
    tabela.adicionarRegistro(new Token(Simbolos.fim, "end"));
    tabela.adicionarRegistro(new Token(Simbolos.entao, "then"));
    tabela.adicionarRegistro(new Token(Simbolos.lerLinha, "readln"));
    tabela.adicionarRegistro(new Token(Simbolos.principal, "main"));
    tabela.adicionarRegistro(new Token(Simbolos.escrever, "write"));
    tabela.adicionarRegistro(new Token(Simbolos.escreverLinha, "writeln"));
    tabela.adicionarRegistro(new Token(Simbolos.value, "true"));
    tabela.adicionarRegistro(new Token(Simbolos.value, "false"));
    tabela.adicionarRegistro(new Token(Simbolos.booleano, "boolean"));
    tabela.adicionarRegistro(new Token(Simbolos.eof, Caracteres.EOF + ""));

  }

}