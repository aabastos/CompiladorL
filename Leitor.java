
/**
 * Classe para leitura do arquivo fonte
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {
  private char proximoChar;
  private BufferedReader arquivo;

  Leitor(String nomeArquivo) throws FileNotFoundException {
    try {
      arquivo = new BufferedReader(new FileReader(nomeArquivo));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public char getProximoCaractere() throws Exception {
    return (char) arquivo.read();
  }

}