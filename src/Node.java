public class Node {

    public int info;
    public int altura;
    public  Node esquerda;
    public Node direita;

    public Node(int info){
        this.info = info;
        esquerda = null;
        direita = null;
        this.altura = 1;
    }
}
