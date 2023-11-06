public class ArvoreBinaria {

    private Node raiz;


    /*----------AVL----------*/
    private int calcAltura(Node node){
        if (node == null){
            return -1;
        }
        return node.altura;
    }
    /*Obtem a altura do nó - Se o nó for nulo a altura é -1*/

    private int calcFatorBalanceamento(Node node){
        if (node == null){
            return 0;
        }
        return calcAltura(node.esquerda) - calcAltura(node.direita);
    }
    /*Calcula o fator de balanciamento do nó - Se o nó for nulo fb é -1*/

    private void atualizaAltura(Node node){
        node.altura = 1 + Math.max(calcAltura(node.esquerda), calcAltura(node.direita));
    }

    private Node rotacaoADireita(Node y){
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizaAltura(y);
        atualizaAltura(x);

        return x;
    }

    private Node rotacaoAEsquerda(Node x){
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizaAltura(x);
        atualizaAltura(y);

        return y;
    }
    /*----------AVL----------*/


    /*public ArvoreBinaria(){
        raiz = null;
    }*/

    public void inserir(int info) {
        raiz = inserirElemento(raiz, info);
    }

    private Node inserirElemento(Node node, int info){
        if (node == null){
            return new Node(info);//Se o nó raiz é nulo -> cria-se um nó com um valor info que será a raiz
        }
        if (info < node.info){
            node.esquerda = inserirElemento(node.esquerda, info);//Se o valor info a inserir é menor que o info raiz -> insere à esquerda da raiz atual
        } else if (info > node.info) {
            node.direita = inserirElemento(node.direita, info);//Se o valor info a inserir é maior que o info raiz -> insere à direita da raiz atual
        }else{
            return node;
        }


        /*----------AVL----------*/
        atualizaAltura(node);

        int fatorBalanceamento = calcFatorBalanceamento(node);

        //Rotação a direita simples
        if (fatorBalanceamento > 1 && info < node.esquerda.info){
            return rotacaoADireita(node);
        }

        //Rotação a esquerda simples
        if (fatorBalanceamento < -1 && info > node.direita.info){
            return rotacaoAEsquerda(node);
        }

        //Dupla rotação - esquerda-direita
        if (fatorBalanceamento > 1 && info > node.esquerda.info){
            node.esquerda = rotacaoAEsquerda(node.esquerda);
            return rotacaoADireita(node);
        }

        //Dupla rotação - direita-esquerda
        if (fatorBalanceamento < -1 && info < node.direita.info){
            node.direita = rotacaoADireita(node.direita);
            return rotacaoAEsquerda(node);
        }

        return node;
        /*----------AVL----------*/
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

        if (fatorBalanceamento > 1 && calcFatorBalanceamento(node.esquerda) >= 0){
            return rotacaoADireita(node);
        }

        if (fatorBalanceamento < -1 && calcFatorBalanceamento(node.direita) <= 0){
            return rotacaoAEsquerda(node);
        }

        if (fatorBalanceamento > 1 && calcFatorBalanceamento(node.esquerda) < 0){
            node.esquerda = rotacaoAEsquerda(node.esquerda);
            return rotacaoADireita(node);
        }

        if (fatorBalanceamento < -1 && calcFatorBalanceamento(node.direita) > 0){
            node.direita = rotacaoADireita(node.direita);
            return rotacaoAEsquerda(node);
        }

        return node;
    }
}