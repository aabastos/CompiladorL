
/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
 *
 * Classe para leitura do arquivo fonte
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {
  private BufferedReader arquivo;

  public int numeroLinha = 1;

  Leitor(String nomeArquivo) throws FileNotFoundException {
    try {
      arquivo = new BufferedReader(new FileReader(nomeArquivo));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public char getProximoCaractere() throws Exception {
    try {
      char ch = (char) arquivo.read();

      if (ch == '\n') {
        numeroLinha++;
      }

      if ((int) ch == 65535) {
        ch = Caracteres.EOF;
      }

      return ch;
    } catch (Exception e) {
      throw e;
    }

  }

}