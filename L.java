public class L {
  public static void main(String[] args) throws Exception {
    Leitor leitorFonte = new Leitor("teste.txt");

    char ch;
    while ((ch = leitorFonte.getProximoCaractere()) != (char) -1) {
      System.out.println(ch);
    }
  }
}