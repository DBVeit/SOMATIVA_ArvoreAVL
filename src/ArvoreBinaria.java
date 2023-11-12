public class ArvoreBinaria {

    private Node raiz = null;

    private int calcAltura(Node node){
        if (node == null){
            return -1;
        }
        int esquerda = calcAltura(node.esquerda);
        int direita = calcAltura(node.direita);
        if (esquerda > direita)
            return 1 + esquerda;
        return 1 + direita;
    }
    /*Obtem a altura do nó - Se o nó for nulo a altura é -1*/

    private int calcFatorBalanceamento(Node node){
        if (node == null){
            return 0;
        }
        return calcAltura(node.esquerda) - calcAltura(node.direita);
    }
    /*Calcula o fator de balanciamento do nó - Se o nó for nulo fb é 0*/

    private void atualizaAltura(Node node){
        node.altura = 1 + Math.max(calcAltura(node.esquerda), calcAltura(node.direita));
    }
    /*Recebe um nó e atualiza a altura, somando o máximo entre as alturas das subárvores*/

    private Node rotacaoADireita(Node raiz){
        Node novaRaiz = raiz.esquerda; //novaRaiz é o filho à esquerda de raiz
        Node temp1 = novaRaiz.direita; //temp1 é a subárvore à direita de novaRaiz que se tornará a nova subárvore à esquerda de raiz

        //Realiza rotação à direita
        novaRaiz.direita = raiz;
        raiz.esquerda = temp1;

        //Atualiza altura após rotação
        atualizaAltura(raiz);
        atualizaAltura(novaRaiz);

        return novaRaiz; //Retorna o novo nó raiz
    }

    private Node rotacaoAEsquerda(Node raiz){
        Node novaRaiz = raiz.direita; //novaRaiz é o filho à direita de raiz
        Node temp1 = novaRaiz.esquerda; // temp1 é a subárvore à esquerda de novaRaiz que se tornará a nova subárvore à direita de raiz

        //Realiza rotação à esquerda
        novaRaiz.esquerda = raiz;
        raiz.direita = temp1;

        //Atualiza altura após rotação
        atualizaAltura(raiz);
        atualizaAltura(novaRaiz);

        return novaRaiz; //Retorna o novo nó raiz
    }

    public void inserir(int info) {
        raiz = inserirElemento(raiz, info);
    }
    /*Chama e executa o método inserirElemento*/

    private Node inserirElemento(Node node, int info){
        if (node == null){
            return new Node(info); //Se o nó raiz é nulo -> cria-se um nó com um valor info que será a raiz
        }
        if (info < node.info){
            node.esquerda = inserirElemento(node.esquerda, info); //Se o valor info a inserir é menor que o info raiz -> insere à esquerda da raiz atual
        } else if (info >= node.info) {
            node.direita = inserirElemento(node.direita, info); //Se o valor info a inserir é maior que o info raiz -> insere à direita da raiz atual
        }else{
            return node; //Se valor info já existe não fará nada
        }

        atualizaAltura(node); //Atualiza altura do nó

        int fatorBalanceamento = calcFatorBalanceamento(node); //Calcula fb e atribui a uma variável

        //Rotação simples a direita
        if (fatorBalanceamento > 1 && info < node.esquerda.info){
            System.out.println("ROTAÇÃO SIMPLES À DIREITA");
            return rotacaoADireita(node);
        }

        //Rotação simples a esquerda
        if (fatorBalanceamento < -1 && info > node.direita.info){
            System.out.println("ROTAÇÃO SIMPLES À ESQUERDA");
            return rotacaoAEsquerda(node);
        }

        //Dupla rotação - esquerda-direita
        if (fatorBalanceamento > 1 && info > node.esquerda.info){
            System.out.println("DUPLA ROTAÇÃO-ESQUERDA-DIREITA");
            node.esquerda = rotacaoAEsquerda(node.esquerda);
            return rotacaoADireita(node);
        }

        //Dupla rotação - direita-esquerda
        if (fatorBalanceamento < -1 && info < node.direita.info){
            System.out.println("DUPLA ROTAÇÃO-DIREITA-ESQUERDA");
            node.direita = rotacaoADireita(node.direita);
            return rotacaoAEsquerda(node);
        }

        return node;
    }


    public void percorrePreOrdem(){
        System.out.println("Pré-Ordem: ");
        preOrdem(raiz);
    }
    private void preOrdem(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.info + " ");
        preOrdem(node.esquerda);
        preOrdem(node.direita);
    }

    public void percorreInOrdem(){
        System.out.println("\nIn-Ordem: ");
        InOrdem(raiz);
    }
    private void InOrdem(Node node){
        if (node == null){
            return;
        }
        InOrdem(node.esquerda);
        System.out.println(node.info + " ");
        InOrdem(node.direita);
    }

    public void percorrePosOrdem(){
        System.out.println("\nPós-Ordem: ");
        PosOrdem(raiz);
    }
    private void PosOrdem(Node node){
        if (node == null){
            return;
        }
        InOrdem(node.esquerda);
        InOrdem(node.direita);
        System.out.println(node.info + " ");
    }

    public void removerMaiorElemento(){
        if (raiz != null){
            raiz = removeMaior(raiz);
        }
    }

    private Node removeMaior(Node node){
        if (node.direita == null){
            return node.esquerda;
        }
        node.direita = removeMaior(node.direita);
        return node;
    }

    public void removerMenorElemento(){
        if (raiz != null){
            raiz = removeMenor(raiz);
        }
    }

    private Node removeMenor(Node node){
        if (node.esquerda == null){
            return node.direita;
        }
        node.esquerda = removeMenor(node.esquerda);
        return node;
    }


    public void removerElementoN(int info){
        raiz = removerElemento(raiz, info);
    }

    private Node encontrarMenorInfo(Node node){
        Node atual = node;
        while (atual.esquerda != null){
            atual = atual.esquerda;
        }
        return atual;
    }

    private int encontrarMenorElemento(Node node){
        int menorValor = node.info;
        while (node.esquerda != null){
            menorValor = node.esquerda.info;
            node = node.esquerda;
        }
        return menorValor;
    }

    private Node removerElemento(Node node, int info){
        if (node == null){
            return node;
        }

        if (info < node.info){
            node.esquerda = removerElemento(node.esquerda, info);
        } else if (info > node.info) {
            node.direita = removerElemento(node.direita, info);
        } else {
            if (node.esquerda == null || node.direita == null) {
                Node temp = (node.esquerda != null) ? node.esquerda : node.direita;

                /*----------AVL----------*/
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
                temp = null;

            } else {
                Node temp = encontrarMenorInfo(node.direita);

                node.info = temp.info;

                node.direita = removerElemento(node.direita, temp.info);
            }
        }

        if (node == null){
            return node;
        }

        atualizaAltura(node);

        int fatorBalanceamento = calcFatorBalanceamento(node);

        //Rotação simples a direita
        if (fatorBalanceamento > 1 && calcFatorBalanceamento(node.esquerda) >= 0){
            System.out.println("ROTAÇÃO SIMPLES À DIREITA");
            return rotacaoADireita(node);
        }

        //Rotação simples a esquerda
        if (fatorBalanceamento < -1 && calcFatorBalanceamento(node.direita) <= 0){
            System.out.println("ROTAÇÃO SIMPLES À ESQUERDA");
            return rotacaoAEsquerda(node);
        }

        //Dupla rotação - esquerda-direita
        if (fatorBalanceamento > 1 && calcFatorBalanceamento(node.esquerda) < 0){
            System.out.println("DUPLA ROTAÇÃO-ESQUERDA-DIREITA");
            node.esquerda = rotacaoAEsquerda(node.esquerda);
            return rotacaoADireita(node);
        }

        //Dupla rotação - direita-esquerda
        if (fatorBalanceamento < -1 && calcFatorBalanceamento(node.direita) > 0){
            System.out.println("DUPLA ROTAÇÃO-DIREITA-ESQUERDA");
            node.direita = rotacaoADireita(node.direita);
            return rotacaoAEsquerda(node);
        }
        /*Funcionamento semelhante a inserção com algumas diferenças*/

        return node;
    }
}