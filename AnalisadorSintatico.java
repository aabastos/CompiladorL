/*Trabalho de Compiladores Versão 1
 *Alunos: André Albino
 *        João Pedro
 *        Vitor Fernandes
*/
public class AnalisadorSintatico {

    public AnalisadorLexico lexico;

    public AnalisadorSintatico(AnalisadorLexico lexico) throws Exception {
        this.lexico = lexico;
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
            casaToken(Simbolos.identificador);
            casaToken(Simbolos.igual);

            if (lexico.registroAtual.token.simbolo == Simbolos.mais)
                casaToken(Simbolos.mais);
            else if (lexico.registroAtual.token.simbolo == Simbolos.menos)
                casaToken(Simbolos.menos);

            casaToken(Simbolos.value);
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

    public void V(String tipo) throws Exception {
        Registro identificador = new Registro();
        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            identificador = casaToken(Simbolos.identificador);
        }

        if (identificador.classe != Classe.tipo_vazio) {
            /**
             * Printar erro
             */
        } else {
            identificador.classe = Classe.variavel;
            identificador.tipo = tipo;
        }

        if (lexico.registroAtual.token.simbolo == Simbolos.igual) {
            casaToken(Simbolos.igual);

            if (lexico.registroAtual.token.simbolo == Simbolos.mais)
                casaToken(Simbolos.mais);
            else if (lexico.registroAtual.token.simbolo == Simbolos.menos)
                casaToken(Simbolos.menos);

            Registro valor = casaToken(Simbolos.value);
            if (valor.tipo != identificador.tipo) {
                /**
                 * Printar erro
                 */
            }
        }
    }

    public void C() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            casaToken(Simbolos.identificador);
            casaToken(Simbolos.igual);
            Exp();
            casaToken(Simbolos.pontoEVirgula);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.enquanto) {
            casaToken(Simbolos.enquanto);
            casaToken(Simbolos.abreParenteses);
            Exp();
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
            Exp();
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
            casaToken(Simbolos.identificador);
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

    // duvida
    public void E() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.escrever) {
            casaToken(Simbolos.escrever);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.escreverLinha) {
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

    }

    public void Exp() throws Exception {
        X();
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
            X();
        }
    }

    public void X() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.mais
                || lexico.registroAtual.token.simbolo == Simbolos.menos) {
            if (lexico.registroAtual.token.simbolo == Simbolos.mais) {
                casaToken(Simbolos.mais);
            } else {
                casaToken(Simbolos.menos);
            }
        }
        Y();
        while (lexico.registroAtual.token.simbolo == Simbolos.mais
                || lexico.registroAtual.token.simbolo == Simbolos.menos
                || lexico.registroAtual.token.simbolo == Simbolos.ou) {
            if (lexico.registroAtual.token.simbolo == Simbolos.mais) {
                casaToken(Simbolos.mais);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.menos) {
                casaToken(Simbolos.menos);
            } else {
                casaToken(Simbolos.ou);
            }
            Y();
        }
    }

    public void Y() throws Exception {
        Z();
        while (lexico.registroAtual.token.simbolo == Simbolos.multiplicacao
                || lexico.registroAtual.token.simbolo == Simbolos.divisao
                || lexico.registroAtual.token.simbolo == Simbolos.e) {
            if (lexico.registroAtual.token.simbolo == Simbolos.multiplicacao) {
                casaToken(Simbolos.multiplicacao);
            } else if (lexico.registroAtual.token.simbolo == Simbolos.divisao) {
                casaToken(Simbolos.divisao);
            } else {
                casaToken(Simbolos.e);
            }
            Z();
        }
    }

    public void Z() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            casaToken(Simbolos.identificador);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.value) {
            casaToken(Simbolos.value);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.abreParenteses) {
            casaToken(Simbolos.abreParenteses);
            Exp();
            casaToken(Simbolos.fechaParenteses);
        } else {
            casaToken(Simbolos.nao);
            Exp();
        }
    }
}

class RegraT {
    public String tipo;
}