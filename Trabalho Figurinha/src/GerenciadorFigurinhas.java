import java.io.*;
import java.util.*;

public class GerenciadorFigurinhas {
    private static final String ARQUIVO_REPETIDAS = "figuras_repetidas_pessoais.csv";
    private static final String ARQUIVO_DESEJADAS = "figuras_desejadas_pessoais.csv";

    // Estruturas de Árvore
    private static TreeSet<Figura> arvore_repetidas_pessoais = new TreeSet<>();
    private static TreeSet<Figura> arvore_desejadas_pessoais = new TreeSet<>();

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // Carga inicial
        carregarArquivoParaArvore(ARQUIVO_REPETIDAS, arvore_repetidas_pessoais);
        carregarArquivoParaArvore(ARQUIVO_DESEJADAS, arvore_desejadas_pessoais);

        int opcao = 0;
        while (opcao != 7) {
            System.out.println("\n--- Gerenciador de Figurinhas Copa 2026 ---");
            System.out.println("1 - Cadastrar figuras repetidas pessoais");
            System.out.println("2 - Listar figuras repetidas pessoais");
            System.out.println("3 - Cadastrar figuras desejadas pessoais");
            System.out.println("4 - Listar figuras desejadas pessoais");
            System.out.println("5 - Carregar figuras repetidas OUTRO (Match com desejadas)");
            System.out.println("6 - Carregar figuras desejadas OUTRO (Match com repetidas)");
            System.out.println("7 - Sair");
            System.out.print("Opção: ");
            
            try {
                opcao = Integer.parseInt(teclado.nextLine());
                processarOpcao(opcao, teclado);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
        teclado.close();
        System.out.println("Programa encerrado.");
    }

    private static void processarOpcao(int opcao, Scanner teclado) {
        switch (opcao) {
            case 1:
                cadastrarFigura(teclado, arvore_repetidas_pessoais, ARQUIVO_REPETIDAS);
                break;
            case 2:
                listarFiguras("Figuras Repetidas Pessoais", arvore_repetidas_pessoais);
                break;
            case 3:
                cadastrarFigura(teclado, arvore_desejadas_pessoais, ARQUIVO_DESEJADAS);
                break;
            case 4:
                listarFiguras("Figuras Desejadas Pessoais", arvore_desejadas_pessoais);
                break;
            case 5:
                verificarMatchOutro(teclado, arvore_desejadas_pessoais, "Desejadas Pessoais");
                break;
            case 6:
                verificarMatchOutro(teclado, arvore_repetidas_pessoais, "Repetidas Pessoais");
                break;
            case 7:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarFigura(Scanner teclado, TreeSet<Figura> arvore, String arquivo) {
        System.out.print("Nome da Seleção: ");
        String selecao = teclado.nextLine();
        
        System.out.print("Número da Figura: ");
        int numero = Integer.parseInt(teclado.nextLine());
        
        System.out.print("Descrição (Jogador, Brasão, etc.): ");
        String descricao = teclado.nextLine();
        
        System.out.print("Quantidade (padrão é 1): ");
        String qtdStr = teclado.nextLine();
        int qtd = qtdStr.isEmpty() ? 1 : Integer.parseInt(qtdStr);
        
        System.out.print("É rara? (true/false): ");
        boolean rara = Boolean.parseBoolean(teclado.nextLine());

        Figura novaFigura = new Figura(selecao, numero, descricao, qtd, rara);
        
        if (arvore.add(novaFigura)) { 
            salvarFiguraNoCSV(novaFigura, arquivo); 
            System.out.println("Figura cadastrada com sucesso!");
        } else {
            System.out.println("Essa figura já está cadastrada. Atualize a quantidade se necessário.");
        }
    }

    private static void listarFiguras(String titulo, TreeSet<Figura> arvore) {
        System.out.println("\n--- " + titulo + " ---");
        if (arvore.isEmpty()) {
            System.out.println("Nenhuma figura cadastrada.");
            return;
        }
        for (Figura f : arvore) {
            System.out.println(f.toString());
        }
        System.out.println("Total: " + arvore.size() + " figurinhas únicas.");
    }

    private static void verificarMatchOutro(Scanner teclado, TreeSet<Figura> arvoreAlvo, String nomeAlvo) {
        System.out.print("Digite o caminho/nome do arquivo CSV do OUTRO: ");
        String arquivoOutro = teclado.nextLine();
        
        TreeSet<Figura> arvoreOutro = new TreeSet<>();
        carregarArquivoParaArvore(arquivoOutro, arvoreOutro);
        
        if (arvoreOutro.isEmpty()) {
            System.out.println("O arquivo do outro usuário está vazio ou não foi encontrado.");
            return;
        }

        listarFiguras("Figuras do Arquivo: " + arquivoOutro, arvoreOutro);

        TreeSet<Figura> match = new TreeSet<>(arvoreOutro);
        match.retainAll(arvoreAlvo); 

        System.out.println("\n*** MATCH ENCONTRADO com suas " + nomeAlvo + " ***");
        if (match.isEmpty()) {
            System.out.println("Nenhuma figurinha deu match para troca.");
        } else {
            for (Figura f : match) {
                System.out.println(" -> " + f.toString());
            }
        }
    }

    private static void carregarArquivoParaArvore(String nomeArquivo, TreeSet<Figura> arvore) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Figura f = Figura.fromCSV(linha);
                if (f != null) {
                    arvore.add(f);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo);
        }
    }

    private static void salvarFiguraNoCSV(Figura figura, String nomeArquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            bw.write(figura.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + nomeArquivo);
        }
    }
}