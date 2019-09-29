public class AnalisadorLexico {
  public Registro registroAtual;

  private TabelaDeSimbolos tabelaDeSimbolos;
  private Leitor leitor;
  private Caracteres verificadorCaracteres;
  private int estadoInicial = 0;
  private int estadoFinal = 8;
  private boolean devolve = false;
  char proximo;

  public AnalisadorLexico(Leitor leitor, TabelaDeSimbolos tabelaDeSimbolos) {
    this.leitor = leitor;
    this.tabelaDeSimbolos = tabelaDeSimbolos;
    this.verificadorCaracteres = new Caracteres();
  }

  public void getProximoRegistro() throws Exception {
    String lexema = "";
    Tipo tipoConstante = null;
    boolean identificador = false;
    Token token = null;

    int estado = estadoInicial;
    while (estado != estadoFinal) {

      if (!devolve) {
        proximo = leitor.getProximoCaractere();
      }
      devolve = false;

      switch (estado) {
      case 0:
        if (verificadorCaracteres.isBranco(proximo)) {
          estado = estadoInicial;
        } else if (proximo == '0') {
          estado = 1;
          lexema = lexema + proximo;
        } else if (proximo == '_') {
          estado = 4;
          lexema = lexema + proximo;
        } else if (verificadorCaracteres.isLetra(proximo)) {
          estado = 3;
          lexema = lexema + proximo;
        } else if (proximo == '+' || proximo == '*' || proximo == '(' || proximo == ')' || proximo == ','
            || proximo == ';') {
          estado = estadoFinal;
          lexema = lexema + proximo;
        } else if (proximo == '-') {
          estado = 13;
          lexema = lexema + proximo;
        } else if (verificadorCaracteres.isDigito(proximo)) {
          estado = 12;
          lexema = lexema + proximo;
        } else if (proximo == '\'') {
          estado = 14;
          lexema = lexema + proximo;
        } else if (proximo == '!' || proximo == '=' || proximo == '>' || proximo == '<') {
          estado = 7;
          lexema = lexema + proximo;
        } else if (proximo == '/') {
          estado = 9;
        }
        break;
      case 1:
        if (proximo == 'h') {
          estado = 5;
          lexema = lexema + proximo;
        } else if (verificadorCaracteres.isDigito(proximo)) {
          estado = 12;
          lexema = lexema + proximo;
        } else {
          throw new Exception("Caractere invalido [" + proximo + "]");
        }
        break;
      case 2:
        break;
      case 3:
        if (verificadorCaracteres.isLetra(proximo) || verificadorCaracteres.isDigito(proximo) || proximo == '_') {
          estado = 3;
          lexema = lexema + proximo;
        } else {
          estado = estadoFinal;
          identificador = true;
          devolve = true;
        }
        break;
      case 4:
        if (proximo == '_') {
          estado = 4;
          lexema = lexema + proximo;
        } else if (verificadorCaracteres.isLetra(proximo) || verificadorCaracteres.isDigito(proximo)) {
          estado = 3;
          lexema = lexema + proximo;
        } else {
          throw new Exception("Caractere invalido [" + proximo + "]");
        }
        break;
      case 5:
        if (verificadorCaracteres.isHexa(proximo)) {
          estado = 6;
          lexema = lexema + proximo;
        } else {
          throw new Exception("Caractere invalido [" + proximo + "]");
        }
        break;
      case 6:
        if (verificadorCaracteres.isHexa(proximo)) {
          estado = estadoFinal;
          lexema = lexema + proximo;
        } else {
          throw new Exception("Caractere invalido [" + proximo + "]");
        }
        break;
      case 7:
        if (proximo == '=') {
          estado = estadoFinal;
          lexema = lexema + proximo;
        } else {
          estado = estadoFinal;
          devolve = true;
        }
        break;
      case 9:
        if (proximo == '*') {
          estado = 10;
        } else {
          estado = estadoFinal;
          lexema = lexema + '/';
          devolve = true;
        }
        break;
      case 10:
        if (proximo == '*') {
          estado = 11;
        } else {
          estado = 10;
        }
        break;
      case 11:
        if (proximo == '*') {
          estado = 11;
        } else if (proximo == '/') {
          estado = estadoInicial;
        } else {
          estado = 10;
        }
        break;
      case 12:
        if (verificadorCaracteres.isDigito(proximo)) {
          estado = 12;
          lexema = lexema + proximo;
        } else {
          estado = estadoFinal;
          tipoConstante = Tipo.inteiro;
        }
        break;
      case 13:
        if (verificadorCaracteres.isDigito(proximo)) {
          estado = 12;
          lexema = lexema + proximo;
        } else {
          estado = estadoFinal;
          devolve = true;
        }
        break;
      case 14:
        break;
      }
    }

    if (tipoConstante == null && identificador == false) {
      registroAtual = tabelaDeSimbolos.tabelaDeSimbolos.get(tabelaDeSimbolos.buscarSimbolo(lexema));
    } else if (identificador == true && tipoConstante == null) {
      if (tabelaDeSimbolos.buscarSimbolo(lexema) == -1) {
        tabelaDeSimbolos.adicionarRegistro(new Token(Simbolos.identificador, lexema));
      }

      registroAtual = tabelaDeSimbolos.tabelaDeSimbolos.get(tabelaDeSimbolos.buscarSimbolo(lexema));
    } else if (tipoConstante != null) {
      if (tabelaDeSimbolos.buscarSimbolo(lexema) == -1) {
        tabelaDeSimbolos.adicionarRegistro(new Token(Simbolos.constante, lexema), tipoConstante);
      }

      registroAtual = tabelaDeSimbolos.tabelaDeSimbolos.get(tabelaDeSimbolos.buscarSimbolo(lexema));
    }

    System.out.println("Simbolo: " + registroAtual.token.getSimbolo() + "/ Lexema: " + registroAtual.token.getLexema());
  }
}