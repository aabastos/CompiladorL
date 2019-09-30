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
            //Tirar o if e else e deixar somente o De()
            //Lembrar de criar o método De()
            if(lexico.registroAtual.token.simbolo == Simbolos.inteiro || 
                    lexico.registroAtual.token.simbolo == Simbolos.booleano || 
                    lexico.registroAtual.token.simbolo == Simbolos.bit ||
                    lexico.registroAtual.token.simbolo == Simbolos.string) {
                De();
            } else {
                casaToken(Simbolos.constante);
                casaToken(Simbolos.identificador);
                casaToken(Simbolos.igual);
                casaToken(Simbolos.value);
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
        if(lexico.registroAtual.token.simbolo == Simbolos.inteiro){
            casaToken(Simbolos.inteiro);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.booleano){
            casaToken(Simbolos.booleano);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.bit){
            casaToken(Simbolos.bit);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.string){
            casaToken(Simbolos.string);
        }  
    }

    public void V(){
        if(lexico.registroAtual.token.simbolo == Simbolos.identificador){
            casaToken(Simbolos.identificador);
        }
        if(lexico.registroAtual.token.simbolo == Simbolos.igual){
            casaToken(Simbolos.igual);
            casaToken(Simbolos.value);
        }
    }

    public void C() throws Exception{
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
                    lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula);
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
                        lexico.registroAtual.token.simbolo == Simbolos.pontoEVirgula);
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
    //duvida
    public void E(){
        if(lexico.registroAtual.token.simbolo == Simbolos.escrever){
            casaToken(Simbolos.escrever);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.escreverLinha){
            casaToken(Simbolos.escreverLinha);
        }

    }

    public void Exp(){
        X();
        while (lexico.registroAtual.token.simbolo == Simbolos.igualIgual || 
                lexico.registroAtual.token.simbolo == Simbolos.diferente || 
                lexico.registroAtual.token.simbolo == Simbolos.menor || 
                lexico.registroAtual.token.simbolo == Simbolos.maior || 
                lexico.registroAtual.token.simbolo == Simbolos.menorOuIgual || 
                lexico.registroAtual.token.simbolo == Simbolos.maiorOuIgual){
            if(lexico.registroAtual.token.simbolo == Simbolos.igualIgual){
                casaToken(Simbolos.igualIgual);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.diferente){
                casaToken(Simbolos.diferente);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.menor){
                casaToken(Simbolos.menor);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.maior){
                casaToken(Simbolos.maior);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.menorOuIgual){
                casaToken(Simbolos.menorOuIgual);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.maiorOuIgual){
                casaToken(Simbolos.maiorOuIgual);
            }
        X();
        }
    }

    public void X(){
        if(lexico.registroAtual.token.simbolo == Simbolos.mais){
            casaToken(Simbolos.mais);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.menos){
            casaToken(Simbolos.menos);
        }
        Y();
        while (lexico.registroAtual.token.simbolo == Simbolos.mais || 
                lexico.registroAtual.token.simbolo == Simbolos.menos || 
                lexico.registroAtual.token.simbolo == Simbolos.ou){
            if(lexico.registroAtual.token.simbolo == Simbolos.mais){
                casaToken(Simbolos.mais);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.menos){
                casaToken(Simbolos.menos);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.ou){
                casaToken(Simbolos.ou);
            }
            Y();
        }
    }

    public void Y(){
        Z();
        while (lexico.registroAtual.token.simbolo == Simbolos.multiplicacao || 
                lexico.registroAtual.token.simbolo == Simbolos.divisao || 
                lexico.registroAtual.token.simbolo == Simbolos.e){
            if(lexico.registroAtual.token.simbolo == Simbolos.multiplicacao){
                casaToken(Simbolos.multiplicacao);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.divisao){
                casaToken(Simbolos.divisao);
            }
            else if(lexico.registroAtual.token.simbolo == Simbolos.e){
                casaToken(Simbolos.e);
            }
        Z();
        }
    }

    public void Z(){
        if(lexico.registroAtual.token.simbolo == Simbolos.identificador){
            casaToken(Simbolos.identificador);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.value){
            casaToken(Simbolos.value);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.abreParenteses){
            casaToken(Simbolos.abreParenteses);
            Exp();
            casaToken(Simbolos.fechaParenteses);
        }
        else if(lexico.registroAtual.token.simbolo == Simbolos.nao){
            casaToken(Simbolos.nao);
            Exp();
        }
    }
}