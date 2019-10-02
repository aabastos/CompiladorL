public class AnalisadorLexico {
  public Registro registroAtual;
  public Leitor leitor;

  private TabelaDeSimbolos tabelaDeSimbolos;
  private Caracteres verificadorCaracteres;
  private int estadoInicial = 0;
  private int estadoFinal = 8;
  private boolean devolve = false;
  private char proximo;

  public AnalisadorLexico(Leitor leitor, TabelaDeSimbolos tabelaDeSimbolos) {
    this.leitor = leitor;
    this.tabelaDeSimbolos = tabelaDeSimbolos;
    this.verificadorCaracteres = new Caracteres();
  }

  public void getProximoRegistro() throws Exception {
    String lexema = "";
    Tipo tipoConstante = null;
    boolean identificador = false;

    int estado = estadoInicial;
    while (estado != estadoFinal) {

      if (!devolve) {
        proximo = leitor.getProximoCaractere();
        if (!verificadorCaracteres.isValido(proximo) && proximo != Caracteres.EOF) {
          System.out.println(leitor.numeroLinha + ":caractere invalido.[" + (int) proximo + "]");
          System.exit(1);
        }
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
        } else if (proximo == '+' || proximo == '-' || proximo == '*' || proximo == '(' || proximo == ')'
            || proximo == ',' || proximo == ';') {
          estado = estadoFinal;
          lexema = lexema + proximo;
        } else if (verificadorCaracteres.isDigito(proximo)) {
          estado = 12;
          lexema = lexema + proximo;
        } else if (proximo == '\'') {
          estado = 2;
          lexema = lexema + proximo;
        } else if (proximo == '!' || proximo == '=' || proximo == '>' || proximo == '<') {
          estado = 7;
          lexema = lexema + proximo;
        } else if (proximo == '/') {
          estado = 9;
        } else if (proximo == Caracteres.EOF) {
          estado = estadoFinal;
          lexema = lexema + proximo;
        } else {
          if (proximo != Caracteres.EOF) {
            System.out.println(leitor.numeroLinha + ":lexema nao identificado [" + lexema + "].");
            System.exit(1);
          } else {
            System.out.println(leitor.numeroLinha + ":fim de arquivo nao esperado.");
            System.exit(1);
          }
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
          estado = estadoFinal;
          tipoConstante = Tipo.inteiro;
          devolve = true;
        }
        break;
      case 2:
        if (proximo == '\'') {
          estado = 14;
          lexema = lexema + proximo;
        } else {
          estado = 2;
          lexema = lexema + proximo;
        }
        break;
      case 3:
        if (verificadorCaracteres.isLetra(proximo) || verificadorCaracteres.isDigito(proximo) || proximo == '_') {
          estado = 3;
          lexema = lexema + proximo;
        } else {
          if (lexema.equals("true") || lexema.equals("false"))
            tipoConstante = Tipo.booleano;
          else
            identificador = true;

          estado = estadoFinal;
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
          if (proximo != Caracteres.EOF) {
            System.out.println(leitor.numeroLinha + ":lexema nao identificado [" + lexema + "].");
            System.exit(1);
          } else {
            System.out.println(leitor.numeroLinha + ":fim de arquivo nao esperado.");
            System.exit(1);
          }
        }
        break;
      case 5:
        if (verificadorCaracteres.isHexa(proximo)) {
          estado = 6;
          lexema = lexema + proximo;
        } else {
          if (proximo != Caracteres.EOF) {
            System.out.println(leitor.numeroLinha + ":lexema nao identificado [" + lexema + "].");
            System.exit(1);
          } else {
            System.out.println(leitor.numeroLinha + ":fim de arquivo nao esperado.");
            System.exit(1);
          }
        }
        break;
      case 6:
        if (verificadorCaracteres.isHexa(proximo)) {
          estado = estadoFinal;
          lexema = lexema + proximo;
          tipoConstante = Tipo.bit;
        } else {
          if (proximo != Caracteres.EOF) {
            System.out.println(leitor.numeroLinha + ":lexema nao identificado [" + lexema + "].");
            System.exit(1);
          } else {
            System.out.println(leitor.numeroLinha + ":fim de arquivo nao esperado.");
            System.exit(1);
          }
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
        } else if (verificadorCaracteres.isLetra(proximo)) {
          System.out.println(leitor.numeroLinha + ":lexema nao identificado [" + lexema + "].");
          System.exit(1);
        } else {
          estado = estadoFinal;
          tipoConstante = Tipo.inteiro;
          devolve = true;
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
        if (proximo == '\'') {
          estado = 2;
        } else {
          estado = estadoFinal;
          tipoConstante = Tipo.string;
          devolve = true;
        }
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
        tabelaDeSimbolos.adicionarRegistro(new Token(Simbolos.value, lexema), tipoConstante);
      }

      registroAtual = tabelaDeSimbolos.tabelaDeSimbolos.get(tabelaDeSimbolos.buscarSimbolo(lexema));
    }

    // System.out
    // .println("Simbolo: " + registroAtual.token.getSimbolo() + " / Lexema: " +
    // registroAtual.token.getLexema());
  }
}