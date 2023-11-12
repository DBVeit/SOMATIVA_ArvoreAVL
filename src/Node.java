public class Node {

    public int info;
    public int altura;
    public  Node esquerda;
    public Node direita;

    public Node(int info){
        this.info = info;
        esquerda = null; //elemento esquerda com início nulo
        direita = null; //elemento direita com início nulo
        this.altura = 0; //altura do nó com início em 0
    }
}
