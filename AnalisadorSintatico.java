public class AnalisadorSintatico {

    public AnalisadorLexico lexico;

    public AnalisadorSintatico(AnalisadorLexico lexico) throws Exception {
        this.lexico = lexico;
        lexico.getProximoRegistro();
    }

    public void casaToken(byte simbolo) throws Exception {
        if (lexico.registroAtual.token.simbolo == simbolo) {
            lexico.getProximoRegistro();
        } else {
            int numLinha = lexico.leitor.numeroLinha;

            if (lexico.registroAtual.token.simbolo == Simbolos.eof) {
                throw new Exception(numLinha + ":fim de arquivo nao esperado.");
            } else {
                throw new Exception(numLinha + ": Token nao esperado = " + lexico.registroAtual.token.lexema);
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
    }

    public void De() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.inteiro
                || lexico.registroAtual.token.simbolo == Simbolos.booleano
                || lexico.registroAtual.token.simbolo == Simbolos.bit
                || lexico.registroAtual.token.simbolo == Simbolos.string) {
            T();
            V();
            while (lexico.registroAtual.token.simbolo == Simbolos.virgula) {
                casaToken(Simbolos.virgula);
                V();
            }
            casaToken(Simbolos.pontoEVirgula);

        } else {
            casaToken(Simbolos.constante);
            casaToken(Simbolos.identificador);
            casaToken(Simbolos.igual);
            casaToken(Simbolos.value);
            casaToken(Simbolos.pontoEVirgula);
        }
    }

    public void T() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.inteiro) {
            casaToken(Simbolos.inteiro);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.booleano) {
            casaToken(Simbolos.booleano);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.bit) {
            casaToken(Simbolos.bit);
        } else {
            casaToken(Simbolos.string);
        }
    }

    public void V() throws Exception {
        if (lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            casaToken(Simbolos.identificador);
        }
        if (lexico.registroAtual.token.simbolo == Simbolos.igual) {
            casaToken(Simbolos.igual);
            casaToken(Simbolos.value);
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