/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
*/
public class AnalisadorSintatico {

    public AnalisadorLexico lexico;
    public TabelaDeSimbolos tabela;

    public AnalisadorSintatico(AnalisadorLexico lexico, TabelaDeSimbolos tabela) throws Exception {
        this.lexico = lexico;
        this.tabela = tabela;
        lexico.getProximoRegistro();
    }

    public Registro casaToken(byte simbolo) throws Exception {
        if (lexico.registroAtual.token.simbolo == simbolo) {
            Registro registro = lexico.registroAtual;
            lexico.getProximoRegistro();

            return registro;
        } else {
            int numLinha = lexico.leitor.numeroLinha;

            if (lexico.registroAtual.token.simbolo == Simbolos.eof) {
                System.out.println(numLinha + ":fim de arquivo nao esperado.");
                System.exit(1);

                return null;
            } else {
                System.out.println(numLinha + ": Token nao esperado = " + lexico.registroAtual.token.lexema);
                System.exit(1);

                return null;
            }
        }
    }

    public void S() throws Exception {
        while (lexico.registroAtual.token.simbolo == Simbolos.inteiro
                || lexico.registroAtual.token.simbolo == Simbolos.booleano
                || lexico.registroAtual.token.simbolo == Simbolos.bit
                || lexico.registroAtual.token.simbolo == Simbolos.string
                || lexico.registroAtual.token.simbolo == Simbolos.constante) {
            De();
        }

        casaToken(Simbolos.principal);

        do {
            C();
        } while (lexico.registroAtual.token.simbolo == Simbolos.identificador
                || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                || lexico.registroAtual.token.simbolo == Simbolos.se
                || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                || lexico.registroAtual.token.simbolo == Simbolos.escrever
                || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula);

        casaToken(Simbolos.fim);
        casaToken(Simbolos.eof);
    }

    public void De() throws Exception {
        Registro identificador = new Registro();

        if (lexico.registroAtual.token.simbolo == Simbolos.inteiro
                || lexico.registroAtual.token.simbolo == Simbolos.booleano
                || lexico.registroAtual.token.simbolo == Simbolos.bit
                || lexico.registroAtual.token.simbolo == Simbolos.string) {
            RegraT regraT = T();
            V(regraT.tipo);
            while (lexico.registroAtual.token.simbolo == Simbolos.virgula) {
                casaToken(Simbolos.virgula);
                V(regraT.tipo);
            }
            casaToken(Simbolos.pontoEVirgula);

        } else {
            casaToken(Simbolos.constante);
            identificador = casaToken(Simbolos.identificador);
            int index = tabela.buscarSimbolo(identificador.token.lexema);
            if (index != -1) {
                identificador = tabela.tabelaDeSimbolos.get(index);
            }

            if (identificador.token.classe != Classe.tipo_vazio) {
                /**
                 * Printar erro
                 */
            } else {
                identificador.token.classe = Classe.constante;
                tabela.tabelaDeSimbolos.add(index, identificador);
            }

            casaToken(Simbolos.igual);

            if (lexico.registroAtual.token.simbolo == Simbolos.mais)
                casaToken(Simbolos.mais);
            else if (lexico.registroAtual.token.simbolo == Simbolos.menos)
                casaToken(Simbolos.menos);

            Registro valor = casaToken(Simbolos.value);
            identificador.tipo = valor.token.tipo;
            tabela.tabelaDeSimbolos.add(index, identificador);
            casaToken(Simbolos.pontoEVirgula);
        }
    }

    public RegraT T() throws Exception {
        RegraT regraT = new RegraT();

        if (lexico.registroAtual.token.simbolo == Simbolos.inteiro) {
            casaToken(Simbolos.inteiro);
            regraT.tipo = Tipo.inteiro;
        } else if (lexico.registroAtual.token.simbolo == Simbolos.booleano) {
            casaToken(Simbolos.booleano);
            regraT.tipo = Tipo.booleano;
        } else if (lexico.registroAtual.token.simbolo == Simbolos.bit) {
            casaToken(Simbolos.bit);
            regraT.tipo = Tipo.bit;
        } else {
            casaToken(Simbolos.string);
            regraT.tipo = Tipo.string;
        }

        return regraT;
    }

    public RegraV V(String tipo) throws Exception {
        Registro identificador = new Registro();
        RegraV regraV = new RegraV();

        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            identificador = casaToken(Simbolos.identificador);
        }

        int index = tabela.buscarSimbolo(identificador.token.lexema);
        if (index != -1) {
            identificador = tabela.tabelaDeSimbolos.get(index);
        }

        if (identificador.token.classe != Classe.tipo_vazio) {
            System.out.println("CLASSE VAZIA");
        } else {
            identificador.token.classe = Classe.variavel;
            identificador.token.tipo = tipo;
            regraV.tipo = tipo;
        }

        if (lexico.registroAtual.token.simbolo == Simbolos.igual) {
            casaToken(Simbolos.igual);

            if (lexico.registroAtual.token.simbolo == Simbolos.mais)
                casaToken(Simbolos.mais);
            else if (lexico.registroAtual.token.simbolo == Simbolos.menos)
                casaToken(Simbolos.menos);

            Registro valor = casaToken(Simbolos.value);
            if (valor.token.tipo != identificador.token.tipo) {
                System.out.println("TIPO ERRADO");
            }
        }

        return regraV;
    }

    public void C() throws Exception {
        Registro identificador = new Registro();
        RegraExp regraExp = new RegraExp();

        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            identificador = casaToken(Simbolos.identificador);
            int index = tabela.buscarSimbolo(identificador.token.lexema);
            if (index != -1) {
                identificador = tabela.tabelaDeSimbolos.get(index);
            }
            if (identificador.token.classe != Classe.variavel) {
                /**
                 * Printar erro
                 */
            }
            casaToken(Simbolos.igual);
            regraExp = Exp();
            if (identificador.tipo != regraExp.tipo) {
                /**
                 * Printar erro
                 */
            }
            casaToken(Simbolos.pontoEVirgula);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.enquanto) {
            casaToken(Simbolos.enquanto);
            casaToken(Simbolos.abreParenteses);
            regraExp = Exp();
            if (regraExp.tipo != Tipo.booleano) {
                System.out.println("TIPO ERRADO");
            }
            casaToken(Simbolos.fechaParenteses);
            if (lexico.registroAtual.token.simbolo == Simbolos.identificador
                    || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                    || lexico.registroAtual.token.simbolo == Simbolos.se
                    || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                    || lexico.registroAtual.token.simbolo == Simbolos.escrever
                    || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                    || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                    || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                C();
            } else {
                casaToken(Simbolos.inicio);
                while (lexico.registroAtual.token.simbolo == Simbolos.identificador
                        || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                        || lexico.registroAtual.token.simbolo == Simbolos.se
                        || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                        || lexico.registroAtual.token.simbolo == Simbolos.escrever
                        || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                        || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                        || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                    C();
                }
                casaToken(Simbolos.fim);
            }
        } else if (lexico.registroAtual.token.simbolo == Simbolos.se) {
            casaToken(Simbolos.se);
            casaToken(Simbolos.abreParenteses);
            regraExp = Exp();
            if (regraExp.tipo != Tipo.booleano) {
                /**
                 * Printar erro
                 */
            }
            casaToken(Simbolos.fechaParenteses);
            casaToken(Simbolos.entao);
            if (lexico.registroAtual.token.simbolo == Simbolos.identificador
                    || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                    || lexico.registroAtual.token.simbolo == Simbolos.se
                    || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                    || lexico.registroAtual.token.simbolo == Simbolos.escrever
                    || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                    || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                    || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                C();
            } else {
                casaToken(Simbolos.inicio);
                do {
                    C();
                } while (lexico.registroAtual.token.simbolo == Simbolos.identificador
                        || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                        || lexico.registroAtual.token.simbolo == Simbolos.se
                        || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                        || lexico.registroAtual.token.simbolo == Simbolos.escrever
                        || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                        || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                        || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula);
                casaToken(Simbolos.fim);
            }
            if (lexico.registroAtual.token.simbolo == Simbolos.senao) {
                casaToken(Simbolos.senao);
                if (lexico.registroAtual.token.simbolo == Simbolos.identificador
                        || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                        || lexico.registroAtual.token.simbolo == Simbolos.se
                        || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                        || lexico.registroAtual.token.simbolo == Simbolos.escrever
                        || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                        || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                        || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                    C();
                } else {
                    casaToken(Simbolos.inicio);
                    do {
                        C();
                    } while (lexico.registroAtual.token.simbolo == Simbolos.identificador
                            || lexico.registroAtual.token.simbolo == Simbolos.enquanto
                            || lexico.registroAtual.token.simbolo == Simbolos.se
                            || lexico.registroAtual.token.simbolo == Simbolos.lerLinha
                            || lexico.registroAtual.token.simbolo == Simbolos.escrever
                            || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha
                            || lexico.registroAtual.token.simbolo == Simbolos.abreParenteses
                            || lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula);
                    casaToken(Simbolos.fim);
                }
            }
        } else if (lexico.registroAtual.token.simbolo == Simbolos.lerLinha) {
            casaToken(Simbolos.lerLinha);
            casaToken(Simbolos.abreParenteses);
            identificador = casaToken(Simbolos.identificador);
            int index = tabela.buscarSimbolo(identificador.token.lexema);
            if (index != -1) {
                identificador = tabela.tabelaDeSimbolos.get(index);
            }
            if (identificador.token.classe != Classe.variavel) {
                /**
                 * Printar erro
                 */
            }
            casaToken(Simbolos.fechaParenteses);
            casaToken(Simbolos.pontoEVirgula);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.escrever
                || lexico.registroAtual.token.simbolo == Simbolos.escreverLinha) {
            if (lexico.registroAtual.token.simbolo == Simbolos.escrever) {
                casaToken(Simbolos.escrever);
            } else {
                casaToken(Simbolos.escreverLinha);
            }
            casaToken(Simbolos.abreParenteses);
            Exp();
            while (lexico.registroAtual.token.simbolo == Simbolos.virgula) {
                casaToken(Simbolos.virgula);
                Exp();
            }
            casaToken(Simbolos.fechaParenteses);
            casaToken(Simbolos.pontoEVirgula);
        } else {
            casaToken(Simbolos.pontoEVirgula);
        }
    }

    public RegraExp Exp() throws Exception {
        RegraExp regraExp = new RegraExp();
        RegraX regraX = new RegraX();

        regraX = X();
        regraExp.tipo = regraX.tipo;

        while (lexico.registroAtual.token.simbolo == Simbolos.igualIgual
                || lexico.registroAtual.token.simbolo == Simbolos.diferente
                || lexico.registroAtual.token.simbolo == Simbolos.menor
                || lexico.registroAtual.token.simbolo == Simbolos.maior
                || lexico.registroAtual.token.simbolo == Simbolos.menorOuIgual
                || lexico.registroAtual.token.simbolo == Simbolos.maiorOuIgual) {
            if (lexico.registroAtual.token.simbolo == Simbolos.igualIgual) {
                casaToken(Simbolos.igualIgual);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.diferente) {
                casaToken(Simbolos.diferente);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.menor) {
                casaToken(Simbolos.menor);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.maior) {
                casaToken(Simbolos.maior);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.menorOuIgual) {
                casaToken(Simbolos.menorOuIgual);
            } else {
                casaToken(Simbolos.maiorOuIgual);
            }
            regraX = X();
            if (regraExp.tipo != regraX.tipo) {
                /**
                 * Printar erro
                 */
            }
        }
        return regraExp;
    }

    public RegraX X() throws Exception {
        RegraX regraX = new RegraX();
        RegraY regraY = new RegraY();

        if (lexico.registroAtual.token.simbolo == Simbolos.mais
                || lexico.registroAtual.token.simbolo == Simbolos.menos) {
            regraX.sinal = true;
            if (lexico.registroAtual.token.simbolo == Simbolos.mais) {
                casaToken(Simbolos.mais);
            } else {
                casaToken(Simbolos.menos);
            }
        }
        regraY = Y();
        if (regraX.sinal) {
            if (regraY.tipo == Tipo.string || regraY.tipo == Tipo.booleano) {
                /**
                 * Printar erro
                 */
            } else {
                regraX.tipo = regraY.tipo;
            }
        } else {
            regraX.tipo = regraY.tipo;
        }
        while (lexico.registroAtual.token.simbolo == Simbolos.mais
                || lexico.registroAtual.token.simbolo == Simbolos.menos
                || lexico.registroAtual.token.simbolo == Simbolos.ou) {
            if (lexico.registroAtual.token.simbolo == Simbolos.mais) {
                if (regraX.tipo == Tipo.booleano) {
                    /**
                     * Printar erro
                     */
                }
                casaToken(Simbolos.mais);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.menos) {
                if (regraX.tipo != Tipo.inteiro && regraX.tipo != Tipo.bit) {
                    /**
                     * Printar erro
                     */
                }
                casaToken(Simbolos.menos);
            } else {
                if (regraX.tipo != Tipo.booleano) {
                    /**
                     * Printar erro
                     */
                }
                casaToken(Simbolos.ou);
            }
            regraY = Y();
            if (regraX.tipo != regraY.tipo) {
                /**
                 * Printar erro
                 */
            }
        }
        return regraX;
    }

    public RegraY Y() throws Exception {
        RegraY regraY = new RegraY();
        RegraZ regraZ = new RegraZ();

        regraZ = Z();
        regraY.tipo = regraZ.tipo;
        while (lexico.registroAtual.token.simbolo == Simbolos.multiplicacao
                || lexico.registroAtual.token.simbolo == Simbolos.divisao
                || lexico.registroAtual.token.simbolo == Simbolos.e) {
            if (lexico.registroAtual.token.simbolo == Simbolos.multiplicacao) {
                casaToken(Simbolos.multiplicacao);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.divisao) {
                casaToken(Simbolos.divisao);
            } else {
                if (regraY.tipo != Tipo.booleano) {
                    /**
                     * Printar erro
                     */
                }
                casaToken(Simbolos.e);
            }
            regraZ = Z();
            if (regraY.tipo != regraZ.tipo) {
                /**
                 * Printar erro
                 */
            } else {
                if (regraZ.tipo == Tipo.string) {
                    /**
                     * Printar erro
                     */
                }
            }
        }
        return regraY;
    }

    public RegraZ Z() throws Exception {
        RegraZ regraZ = new RegraZ();
        RegraExp regraExp = new RegraExp();

        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            Registro identificador;
            identificador = casaToken(Simbolos.identificador);
            int index = tabela.buscarSimbolo(identificador.token.lexema);
            if (index != -1) {
                identificador = tabela.tabelaDeSimbolos.get(index);
            }
            if (identificador.token.classe == Classe.tipo_vazio) {
                System.out.println("NAO DECLARADO");
            } else {
                regraZ.tipo = identificador.tipo;
            }
        } else if (lexico.registroAtual.token.simbolo == Simbolos.value) {
            Registro valor = new Registro();
            valor = casaToken(Simbolos.value);
            regraZ.tipo = valor.token.tipo;
        } else if (lexico.registroAtual.token.simbolo == Simbolos.abreParenteses) {
            casaToken(Simbolos.abreParenteses);
            regraExp = Exp();
            regraZ.tipo = regraExp.tipo;
            casaToken(Simbolos.fechaParenteses);
        } else {
            casaToken(Simbolos.nao);
            regraExp = Exp();
            if (regraExp.tipo != Tipo.booleano) {
                /**
                 * Printar erro
                 */
            }
        }
        return regraZ;
    }
}

class RegraT {
    public String tipo;
}

class RegraV {
    public String tipo;
}

class RegraExp {
    public String tipo;
}

class RegraZ {
    public String tipo;
}

class RegraY {
    public String tipo;
}

class RegraX {
    public String tipo;
    public boolean sinal;
}