import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> estacoes = new ArrayList<>();
        ArrayList<String> origens = new ArrayList<>();
        ArrayList<String> destinos = new ArrayList<>();

        try (Scanner leitor = new Scanner(new File("src/mapa.csv"))) {
            
            while(leitor.hasNextLine()) {
                // le e divide a linha de uma vez
                String[] dados = leitor.nextLine().split(",");
                
                String origem = dados[0].trim();
                String destino = dados[1].trim();

                origens.add(origem);
                destinos.add(destino);

                if(!estacoes.contains(origem)) estacoes.add(origem);
                if(!estacoes.contains(destino)) estacoes.add(destino);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo mapa.csv não encontrado.");
            return; // encerra o programa aqui se não achar o arquivo
        }

        Collections.sort(estacoes);
        Grafo gAssimetrico = new Grafo(estacoes);

        for(int i = 0; i < origens.size(); i++){
            int indiceOrigem = gAssimetrico.pegarIndice(origens.get(i));
            int indiceDestino = gAssimetrico.pegarIndice(destinos.get(i));
            gAssimetrico.inserirAresta(indiceOrigem, indiceDestino);
        }

        gAssimetrico.mostrarMatriz(); 
        System.out.println();
        gAssimetrico.mostrarGrafo();
    }
}