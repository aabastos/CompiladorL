public class AnalisadorSintatico {

    public AnalisadorLexico lexico;

    public AnalisadorSintatico(AnalisadorLexico lexico) {
        this.lexico = lexico;
    }

    public void casaToken(byte simbolo) throws Exception{
        if(lexico.registroAtual.token.simbolo == simbolo){
            //chamar o analisador lexico
        } else {
            throw new Exception("Token não esperado = " + lexico.registroAtual.token.simbolo);
        }
    }

    public void S() {
        while (lexico.registroAtual.token.simbolo == Simbolos.inteiro || 
                lexico.registroAtual.token.simbolo == Simbolos.booleano || 
                lexico.registroAtual.token.simbolo == Simbolos.bit ||
                lexico.registroAtual.token.simbolo == Simbolos.string ||
                lexico.registroAtual.token.simbolo == Simbolos.constante) {

            if(lexico.registroAtual.token.simbolo == Simbolos.inteiro || 
                    lexico.registroAtual.token.simbolo == Simbolos.booleano || 
                    lexico.registroAtual.token.simbolo == Simbolos.bit ||
                    lexico.registroAtual.token.simbolo == Simbolos.string) {
                De();
            } else {
                casaToken(Simbolos.constante);
                casaToken(Simbolos.identificador);
                casaToken(Simbolos.igual);
                casaToken(Simbolos.numero);
                casaToken(Simbolos.pontoEVirgula);
            }

        }

        casaToken(Simbolos.principal);

        do {
            C();
        } while (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                lexico.registroAtual.token.simbolo == Simbolos.se ||
                lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula
        );
    }

    public void T(){
        
    }

    public void V(){
        
    }

    public void C() {
        if(lexico.registroAtual.token.simbolo == Simbolos.identificador) {
            casaToken(Simbolos.identificador);
            casaToken(Simbolos.igual);
            Exp();
            casaToken(Simbolos.pontoEVirgula);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.enquanto) {
            casaToken(Simbolos.enquanto);
            casaToken(Simbolos.abreParenteses);
            Exp();
            casaToken(Simbolos.fechaParenteses);
            if (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                    lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                    lexico.registroAtual.token.simbolo == Simbolos.se ||
                    lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                    lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                    lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                        C();
            } else {
                casaToken(Simbolos.inicio);
                while (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                    lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                    lexico.registroAtual.token.simbolo == Simbolos.se ||
                    lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                    lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                    lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
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
            if (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                    lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                    lexico.registroAtual.token.simbolo == Simbolos.se ||
                    lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                    lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                    lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                        C();
            } else {
                casaToken(Simbolos.inicio);
                do {
                    C();
                } while (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                    lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                    lexico.registroAtual.token.simbolo == Simbolos.se ||
                    lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                    lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                    lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                        C();
                };
                casaToken(Simbolos.fim);
            }
            if (lexico.registroAtual.token.simbolo == Simbolos.senao) {
                casaToken(Simbolos.senao);
                if (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                    lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                    lexico.registroAtual.token.simbolo == Simbolos.se ||
                    lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                    lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                    lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                    lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                        C();
                } else {
                    casaToken(Simbolos.inicio);
                    do {
                        C();
                    } while (lexico.registroAtual.token.simbolo == Simbolos.identificador ||
                        lexico.registroAtual.token.simbolo == Simbolos.enquanto ||
                        lexico.registroAtual.token.simbolo == Simbolos.se ||
                        lexico.registroAtual.token.simbolo == Simbolos.lerLinha ||
                        lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                        lexico.registroAtual.token.simbolo == Simbolos.escreverLinha ||
                        lexico.registroAtual.token.simbolo == Simbolos.abreParenteses ||
                        lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula) {
                            C();
                    };
                    casaToken(Simbolos.fim);
                }
            }
        } else if (lexico.registroAtual.token.simbolo == Simbolos.lerLinha) {
            casaToken(Simbolos.lerLinha);
            casaToken(Simbolos.abreParenteses);
            casaToken(Simbolos.identificador);
            casaToken(Simbolos.fechaParenteses);
            casaToken(Simbolos.pontoEVirgula);
        } else if (lexico.registroAtual.token.simbolo == Simbolos.escrever ||
                    lexico.registroAtual.token.simbolo == Simbolos.escreverLinha) {
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

    public void E(){
        
    }

    public void Exp(){
        
    }

    public void X(){
        
    }

    public void Y(){
        
    }

    public void Z(){
        
    }

}