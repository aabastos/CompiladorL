public class Caracteres {
  public static char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i',
      'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
      'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };

  public static char digitos[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

  public static char simbolos[] = { '<', '>', '=', '!', '+', '-', '/', '*', '(', ')', '\'', ';', ':' };

  public static char brancos[] = { 10, 13, '\n', ' ' };

  public boolean letra(char ch) {
    for (char letra : letras) {
      if (ch == letra) {
        return true;
      }
    }

    return false;
  }

  public boolean simbolo(char ch) {
    for (char simbolo : simbolos) {
      if (ch == simbolo) {
        return true;
      }
    }

    return false;
  }

  public boolean digito(char ch) {
    for (char digito : digitos) {
      if (ch == digito) {
        return true;
      }
    }

    return false;
  }

  public boolean branco(char ch) {
    for (char branco : brancos) {
      if (ch == branco) {
        return true;
      }
    }

    return false;
  }

  public boolean valido(char ch) {
    return (letra(ch) || digito(ch) || simbolo(ch) || branco(ch));
  }

}