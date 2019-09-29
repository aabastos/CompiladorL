public class AnalisadorLexico {
  private Leitor leitor;
  private Caracteres verificadorCaracteres;
  private int estadoInicial = 0;
  private int estadoFinal = 8;

  public AnalisadorLexico(Leitor leitor) {
    this.leitor = leitor;
    this.verificadorCaracteres = new Caracteres();
  }

  public Registro getProximoRegistro() {
    String lexema = "";
    Tipo tipoConstante = null;
    int estado = estadoInicial;

    while (estado != estadoFinal) {
      char proximo = leitor.getProximoCaractere();

      switch (estado) {
      case 0:
        if (verificadorCaracteres.isLetra(proximo)) {
          estado = 3;
          lexema = lexema + proximo;
        }

        break;
      }
    }
  }
}