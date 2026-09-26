// Classe base Veiculo
class Veiculo {
    private int id;
    private String descricao;
    private String modelo;
    private String cor;
    private String ano;
    private String preco;

    // Construtor da classe Veiculo
    public Veiculo(int id, String descricao, String modelo, String cor, String ano, String preco) {
        this.id = id;
        this.descricao = descricao;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
    }

    // Getters e Setters da classe Veiculo para acessar e modificar os atributos da classe      
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getAno() { return ano; }
    public void setAno(String ano) { this.ano = ano; }
    public String getPreco() { return preco; }
    public void setPreco(String preco) { this.preco = preco; }
}

// Subclasse Trator que herda da classe Veiculo
class Trator extends Veiculo {
    public Trator(int id, String descricao, String modelo, String cor, String ano, String preco) { // Construtor da classe Trator
        super(id, descricao, modelo, cor, ano, preco);
    }
}

public class Main {
    public static Trator criarTratorJaguarSerie5004(){ // Função para criar o trator Jaguar Serie 5004
        return new Trator(1, "APARELHO P/ MILHO 450", "JAGUAR SERIE 5004", "Preto", "2024", "100000");
    }

    public static void mostrarTrator(Trator tractor) { // Função para mostrar as informações do trator
        System.out.println("Descrição: " + tractor.getDescricao());
        System.out.println("Modelo: " + tractor.getModelo());
        System.out.println("Cor: " + tractor.getCor());
        System.out.println("Ano: " + tractor.getAno());
        System.out.println("Preço: " + tractor.getPreco());
    }

    public static void main(String[] args) {
        Trator t = criarTratorJaguarSerie5004();
        mostrarTrator(t);
    }
}