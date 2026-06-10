import java.util.Objects;

public class Figura implements Comparable<Figura> {
    String nomeSelecao;
    int numeroFigura;
    String descricao;
    int quantidade;
    boolean rara;

    public Figura(String nomeSelecao, int numeroFigura, String descricao, int quantidade, boolean rara) {
        this.nomeSelecao = nomeSelecao;
        this.numeroFigura = numeroFigura;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.rara = rara;
    }

    // Lê o CSV
    public static Figura fromCSV(String linha) {
        String[] partes = linha.split(";");
        if (partes.length >= 5) {
            return new Figura(
                partes[0], 
                Integer.parseInt(partes[1]), 
                partes[2], 
                Integer.parseInt(partes[3]), 
                Boolean.parseBoolean(partes[4])
            );
        }
        return null;
    }

    // Salva no CSV
    public String toCSV() {
        return nomeSelecao + ";" + numeroFigura + ";" + descricao + ";" + quantidade + ";" + rara;
    }

    @Override
    public String toString() {
        return String.format("[%s - %02d] %s | Rara: %b | Qtd: %d", 
                nomeSelecao, numeroFigura, descricao, rara, quantidade);
    }

    // Regra de ordenação na Árvore
    @Override
    public int compareTo(Figura outra) {
        int compSelecao = this.nomeSelecao.compareToIgnoreCase(outra.nomeSelecao);
        if (compSelecao != 0) {
            return compSelecao;
        }
        return Integer.compare(this.numeroFigura, outra.numeroFigura);
    }

    // Serve para o método retainAll() (match) funcionar
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Figura figura = (Figura) obj;
        return numeroFigura == figura.numeroFigura && nomeSelecao.equalsIgnoreCase(figura.nomeSelecao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeSelecao.toLowerCase(), numeroFigura);
    }
}