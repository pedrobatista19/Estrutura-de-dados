import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Estrutura {

    public static void gerarProcessos(ArrayList<Processo> lista, Scanner teclado) {
        int id;
        String descricao;
        Random gerador = new Random();

        while (true) {
            System.out.println("Descrição do processo (ou digite SAIR): ");
            descricao = teclado.nextLine().toUpperCase();
            
            // A MUDANÇA FOI FEITA NESTA LINHA ABAIXO:
            if (descricao.equals("SAIR")) {
                break;
            }
        
            id = gerador.nextInt(500);
        
            Processo tmp = new Processo(id, descricao);
            if (!lista.contains(tmp)) {
                lista.add(tmp);
            } 
        }
    }

    public static void exibirProcessos(ArrayList<Processo> lista) {
        System.out.println("Quantidade de processos: " + lista.size());

        for(Processo p : lista){
            System.out.println(p);
        }
    }
    
    public static void localizarProcessos(ArrayList<Processo> lista, Scanner teclado) {
        System.out.println("\nDigite a palavra ou expressão que deseja localizar:");
        String pesquisa = teclado.nextLine().toUpperCase();

        for(Processo p : lista){
            if(p.descricao.contains(pesquisa)){
                System.out.println(p);
            }
        }
    }

    public static void removerProcessos(ArrayList<Processo> lista, Scanner teclado) {
        System.out.println("\nDigite a palavra ou expressão do processo que deseja REMOVER:");
        String termoRemocao = teclado.nextLine().toUpperCase();

        boolean removido = lista.removeIf(p -> p.descricao.contains(termoRemocao));

        if (removido) {
            System.out.println("Processo(s) removido(s) com sucesso!");
        } else {
            System.out.println("Nenhum processo encontrado com essa palavra para remover.");
        }
    }

    public static void main(String[] args) {
        ArrayList<Processo> lista = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);

        Estrutura.gerarProcessos(lista, teclado);
        
        System.out.println("\n=== Lista Inicial ===");
        Estrutura.exibirProcessos(lista);
        
        Estrutura.localizarProcessos(lista, teclado);
        
        Estrutura.removerProcessos(lista, teclado);
        
        System.out.println("\n=== Lista Atualizada após Remoção ===");
        Estrutura.exibirProcessos(lista); 
        
        teclado.close();
    }
}