public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        System.out.println("\n-> Inserindo elementos...");

        arvoreBinaria.inserir(65);
        arvoreBinaria.inserir(95);
        //arvoreBinaria.inserir(125);
        //arvoreBinaria.inserir(35);
        //arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(80);

        /*arvoreBinaria.inserir(30);//A
        arvoreBinaria.inserir(25);//B
        arvoreBinaria.inserir(35);//C
        arvoreBinaria.inserir(20);//D
        arvoreBinaria.inserir(28);//E
        arvoreBinaria.inserir(40);//F
        arvoreBinaria.inserir(26);//G
        arvoreBinaria.inserir(38);//H
        arvoreBinaria.inserir(45);//I
        arvoreBinaria.percorrePreOrdem();
        arvoreBinaria.percorreInOrdem();
        arvoreBinaria.percorrePosOrdem();

        System.out.println("\n-> Removendo elemento N...");
        arvoreBinaria.removerElementoN(30);
        arvoreBinaria.percorrePreOrdem();
        arvoreBinaria.percorreInOrdem();
        arvoreBinaria.percorrePosOrdem();

        System.out.println("\n-> Removendo maior elemento...");
        arvoreBinaria.removerMaiorElemento();
        arvoreBinaria.percorrePreOrdem();
        arvoreBinaria.percorreInOrdem();
        arvoreBinaria.percorrePosOrdem();

        System.out.println("\n-> Removendo menor elemento...");
        arvoreBinaria.removerMenorElemento();*/
        arvoreBinaria.percorrePreOrdem();
        arvoreBinaria.percorreInOrdem();
        arvoreBinaria.percorrePosOrdem();
    }
}