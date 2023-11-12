public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        System.out.println("\n-> Inserindo elementos...");

        arvoreBinaria.inserir(100);//a
        arvoreBinaria.inserir(56);//b
        arvoreBinaria.inserir(32);//c
        arvoreBinaria.inserir(80);//d
        arvoreBinaria.inserir(127);//e
        arvoreBinaria.inserir(115);//f
        arvoreBinaria.removerElementoN(32);//g
        arvoreBinaria.inserir(85);//h
        arvoreBinaria.inserir(130);//i
        arvoreBinaria.inserir(82);//j
        arvoreBinaria.inserir(90);//k
        arvoreBinaria.inserir(120);//l
        arvoreBinaria.removerElementoN(80);//m
        arvoreBinaria.inserir(95);//n
        arvoreBinaria.removerElementoN(127);//o
        arvoreBinaria.inserir(40);//p
        arvoreBinaria.inserir(68);//q
        arvoreBinaria.removerElementoN(56);//56
        arvoreBinaria.removerElementoN(40);//40
        arvoreBinaria.removerElementoN(82);//t
        arvoreBinaria.percorreInOrdem();
    }
}