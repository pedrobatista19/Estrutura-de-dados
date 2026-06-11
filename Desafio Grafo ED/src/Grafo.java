import java.util.ArrayList; 

public class Grafo {
    int matriz[][];
    int qtdVertices; 
    ArrayList<String> vertices; 

    public Grafo(ArrayList<String> vertices){
        // copia a lista
        this.vertices = new ArrayList<>(vertices);
        this.qtdVertices = vertices.size(); 
        this.matriz = new int[qtdVertices][qtdVertices]; 
        
        // this.matriz nasce com todas as posições = 0.
    }

    void mostrarMatriz(){
        for(String v : this.vertices){
            System.out.print("    " + v);
        }
        System.out.println();

        for(int i = 0; i < qtdVertices; i++){
            System.out.print(this.vertices.get(i) + "   ");
            for(int j = 0; j < qtdVertices; j++){
                System.out.print(matriz[i][j] + "    ");
            }
            System.out.println();
        }
    }

    void mostrarGrafo(){
        for(int i = 0; i < this.qtdVertices; i++){
            System.out.print(this.vertices.get(i) + ":  ");
            for(int j = 0; j < this.qtdVertices; j++){
                if(this.matriz[i][j] != 0 ){
                    System.out.print(this.vertices.get(j) + "   ");
                }
            }
            System.out.println();
        }
    }

    void inserirAresta(int origem, int destino){
        this.matriz[origem][destino] = 1;
    }

    int pegarIndice(String vertice){
        return this.vertices.indexOf(vertice); 
    }
}