public class Caracteres {
  public static char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i',
      'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
      'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };
  public static char digitos[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
  public static char simbolos[] = { '<', '>', '=', '!', '+', '-', '/', '*', '(', ')', '\'', ';', ':', '_', ',', '?' };
  public static char hexas[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
  public static char brancos[] = { 10, 13, 65535, '\n', ' ' };
  public static char EOF = '\u001a';

  public boolean isLetra(char ch) {
    for (char letra : letras) {
      if (ch == letra) {
        return true;
      }
    }

    return false;
  }

  public boolean isSimbolo(char ch) {
    for (char simbolo : simbolos) {
      if (ch == simbolo) {
        return true;
      }
    }

    return false;
  }

  public boolean isDigito(char ch) {
    for (char digito : digitos) {
      if (ch == digito) {
        return true;
      }
    }

    return false;
  }

  public boolean isBranco(char ch) {
    for (char branco : brancos) {
      if (ch == branco) {
        return true;
      }
    }

    return false;
  }

  public boolean isHexa(char ch) {
    for (char hexa : hexas) {
      if (ch == hexa) {
        return true;
      }
    }

    return false;
  }

  public boolean isValido(char ch) {
    return (isLetra(ch) || isDigito(ch) || isSimbolo(ch) || isBranco(ch)) || ch == EOF;
  }

}