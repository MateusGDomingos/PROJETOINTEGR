import java.util.Scanner;
import java.util.ArrayList;

//classe principal
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner para ler os dados do usuario
        ControlePedidos controle = new ControlePedidos();
        int opcao; // variavel para armazenar a opcao do usuario

        do { // Faça enquanto for diferente da opção 0
            System.out.println("\n=== CONTROLE DE PEDIDOS ===");
            System.out.println("1 - Cadastrar pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("3 - Buscar pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = sc.nextInt(); // le a opcao do usuario
            sc.nextLine(); // limpa o Enter que fica no buffer

            if (opcao == 1) { // cadastra um novo pedido
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

                System.out.print("Tipo de entrega (1 - Normal, 2 - Expresso): ");
                int tipo = sc.nextInt();
                sc.nextLine();

                // a variavel e do tipo Pedido, mas o objeto criado e de uma subclasse (polimorfismo) 
                Pedido novo;
                if (tipo == 2) {
                    novo = new PedidoExpresso(numero, cliente, produto, quantidade, transportadora);
                } else {
                    novo = new PedidoNormal(numero, cliente, produto, quantidade, transportadora);
                }

                if (controle.cadastrar(novo)) {
                    System.out.println("Pedido cadastrado com sucesso!");
                } else {
                    System.out.println("Ja existe um pedido com esse numero.");
                }
            } else if (opcao == 2) { // lista todos os pedidos
                controle.listar();
            } else if (opcao == 3) { // busca um pedido pelo numero
                System.out.print("Numero do pedido: ");
                int numero = sc.nextInt();
                sc.nextLine();

                Pedido encontrado = controle.buscar(numero); // busca de pedido por numero
                if (encontrado != null) { 
                    encontrado.exibir();
                } else {
                    System.out.println("Pedido nao encontrado.");
                }
            } else if (opcao != 0) {
                System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        System.out.println("Programa encerrado.");
        sc.close();
    }

    // ABSTRACAO: classe abstrata, nao pode ser instanciada diretamente (new Pedido() nao funciona !!!
    // Ela define o que todo pedido tem, e deixa as subclasses decidirem os detalhes delas
    static abstract class Pedido {
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

        // metodos abstratos: sem corpo, cada subclasse e obrigada a implementar
        public abstract String getTipo();
        public abstract double calcularFrete();

        //metodo para exibir os dados do pedido (imprime os dados do pedido)
        // POLIMORFISMO: getTipo() e calcularFrete() chamam a versao da subclasse real do objeto
        public void exibir() {
            System.out.println("----------------------------");
            System.out.println("Pedido #" + numero + " (" + getTipo() + ")");
            System.out.println("Cliente: " + cliente);
            System.out.println("Produto: " + produto);
            System.out.println("Quantidade: " + quantidade);
            System.out.println("Transportadora: " + transportadora);
            System.out.printf("Frete: R$ %.2f%n", calcularFrete());
        }
    }

    // HERANCA: PedidoNormal herda todos os atributos e metodos de Pedido
    static class PedidoNormal extends Pedido {

        public PedidoNormal(int numero, String cliente, String produto, int quantidade, String transportadora) {
            super(numero, cliente, produto, quantidade, transportadora); // chama o construtor da classe pai
        }

        @Override
        public String getTipo() {
            return "Normal";
        }

        // POLIMORFISMO: frete normal = R$ 10,00 + R$ 2,00 por unidade
        @Override
        public double calcularFrete() {
            return 10.0 + 2.0 * getQuantidade();
        }
    }

    // HERANCA: PedidoExpresso tambem herda de Pedido
    static class PedidoExpresso extends Pedido {

        public PedidoExpresso(int numero, String cliente, String produto, int quantidade, String transportadora) {
            super(numero, cliente, produto, quantidade, transportadora); // chama o construtor da classe pai
        }

        @Override
        public String getTipo() {
            return "Expresso";
        }

        // POLIMORFISMO: frete expresso = R$ 25,00 + R$ 3,00 por unidade (mais caro, entrega mais rapida)
        @Override
        public double calcularFrete() {
            return 25.0 + 3.0 * getQuantidade();
        }
    }

    // classe responsavel por guardar e gerenciar os pedidos
    static class ControlePedidos {
        // a lista e do tipo Pedido, mas guarda PedidoNormal e PedidoExpresso (polimorfismo)
        private ArrayList<Pedido> pedidos = new ArrayList<>();

        // cadastra um pedido; retorna false se ja existir um pedido com o mesmo numero
        public boolean cadastrar(Pedido pedido) {
            if (buscar(pedido.getNumero()) != null) {
                return false;
            }
            pedidos.add(pedido);
            return true;
        }

        // lista todos os pedidos cadastrados
        public void listar() {
            if (pedidos.isEmpty()) {
                System.out.println("Nenhum pedido cadastrado.");
                return;
            }
            System.out.println("\n=== LISTA DE PEDIDOS ===");
            for (Pedido p : pedidos) {
                p.exibir(); // cada objeto usa o seu proprio calcularFrete()
            }
        }

        // busca um pedido pelo numero; retorna null se nao encontrar
        public Pedido buscar(int numero) {
            for (Pedido p : pedidos) {
                if (p.getNumero() == numero) {
                    return p;
                }
            }
            return null;
        }
    }
}
