import java.util.ArrayList;
import java.util.Scanner;

class Pedido { // Classe Pedido com os atributos:
    private int numero;
    private String cliente;
    private String produto;
    private int quantidade;
    private String transportadora;

    //construtor da classe pedido (inicializa os atributos)
    public Pedido(int numero, String cliente, String produto, int quantidade, String transportadora) {
        this.numero = numero;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.transportadora = transportadora;
    }
        //getters da classe pedido (retorna o valor do atributo)
    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public String getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public String getTransportadora() { return transportadora; }

    //setters da classe pedido (atribui um valor ao atributo)
    public void setNumero(int numero) { this.numero = numero; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setProduto(String produto) { this.produto = produto; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setTransportadora(String transportadora) { this.transportadora = transportadora; }

    public void exibir() {
        System.out.println("Pedido #" + numero);
        System.out.println("Cliente: " + cliente);
        System.out.println("Produto: " + produto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Transportadora: " + transportadora);
        System.out.println("---------------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pedido> pedidos = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== MENU PEDIDOS ===");
            System.out.println("1 - Cadastrar pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o Enter que fica no buffer

            if (opcao == 1) {
                System.out.print("Numero do pedido: ");
                int numero = sc.nextInt();
                sc.nextLine();

                System.out.print("Cliente: ");
                String cliente = sc.nextLine();

                System.out.print("Produto: ");
                String produto = sc.nextLine();

                System.out.print("Quantidade: ");
                int quantidade = sc.nextInt();
                sc.nextLine();

                System.out.print("Transportadora: ");
                String transportadora = sc.nextLine();

                Pedido novo = new Pedido(numero, cliente, produto, quantidade, transportadora);
                pedidos.add(novo);
                System.out.println("Pedido cadastrado com sucesso!");
            } else if (opcao == 2) {
                if (pedidos.isEmpty()) {
                    System.out.println("Nenhum pedido cadastrado.");
                } else {
                    System.out.println("\n=== LISTA DE PEDIDOS ===");
                    for (Pedido p : pedidos) {
                        p.exibir();
                    }
                }
            } else if (opcao != 0) {
                System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        System.out.println("Programa encerrado.");
        sc.close();
    }
}
