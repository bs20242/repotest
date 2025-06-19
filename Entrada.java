

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;


public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior e <Bernardo Simão Rosa>
     */
    public Scanner input;
    private Aluno saida; // usei para salvar e printar as infos no final da exec apos sair do sistema

    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALICAÇÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }
    public String lerLinha(String msg) {
        System.out.print(msg);
        String linha = this.input.nextLine();

        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }


    public int lerInteiro(String msg) {
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    public double lerDouble(String msg) {
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    public void menu(Sistema sistema) {
        if (sistema.sistemaVazio()) {
            System.out.println("** Inicializando o sistema **");
            this.cadAdmin(sistema);
        }
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Login.\n" +
                "0) Sair.\n";

        int opcao = this.lerInteiro(msg);
        while (opcao != 0) {
            if (opcao == 1) login(sistema);
            else System.out.println("Opção inválida. Tente novamente: ");

            opcao = this.lerInteiro(msg);
        }
        listarPedidos(this.saida, sistema);

    }

    public void login(Sistema sistema) {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = this.lerLinha("CPF: ");
        String senha = this.lerLinha("Senha: ");

        Admin adm = sistema.getAdmin(cpf);
        if (adm != null) {
            if (adm.validarAcesso(senha)) {
                this.menu(sistema, adm);
            } else {
                System.out.println("Senha inválida.");
            }
        } else {
            Aluno a = sistema.getAluno(cpf);
            if (a != null) {
                if (a.validarAcesso(senha)) {
                    this.menu(sistema, a);
                    this.saida = a;
                } else {
                    System.out.println("Senha inválida.");
                }
            } else {
                System.out.println("Usuário inexistente.");
            }
        }
    }


    public void menu(Sistema sistema, Admin admin) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar novo administrador.\n" +
                "2) Cadastrar aluno.\n" +
                "3) Cadastrar produto.\n" +
                "4) Cadastrar sala.\n" +
                "0) Logout.\n";

        int opcao = this.lerInteiro(msg);
        while (opcao != 0) {
            if (opcao == 1) cadAdmin(sistema);
            if (opcao == 2) cadAluno(sistema);
            if (opcao == 3) cadProduto(sistema);
            if (opcao == 4) cadSala(sistema);
            if (opcao < 0 || opcao > 4) System.out.println("Opção inválida. Tente novamente: ");

            opcao = this.lerInteiro(msg);
        }
    }

    public void menu(Sistema sistema, Aluno aluno) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Fazer pedido.\n" +
                "2) Fazer entrega.\n" +
                "3) Meus pedidos.\n" +
                "4) Inserir crédito.\n" +
                "0) Logout.\n";

        int opcao = this.lerInteiro(msg);
        while (opcao != 0) {
            if (opcao == 1) fazerPedido(aluno, sistema);
            if (opcao == 2) entregarPedido(aluno, sistema);
            if (opcao == 3) listarPedidos(aluno, sistema);
            if (opcao == 4) inserirCredito(aluno, sistema);
            if (opcao < 0 || opcao > 4) System.out.println("Opção inválida. Tente novamente: ");

            opcao = this.lerInteiro(msg);
        }

    }


    public void cadAdmin(Sistema sistema) {
        System.out.println("\n** Cadastrando um novo administrador **");
        String cpf = this.lerLinha("Digite o CPF: ");
        while (sistema.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro CPF: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        String email = this.lerLinha("Digite o email: ");
        Admin admin = new Admin(cpf, nome, senha, email);
        sistema.addAdmin(admin);
        System.out.println("Usuário " + admin + " criado com sucesso.");
    }

    public void cadAluno(Sistema sistema) {
        System.out.println("\n** Cadastrando um novo aluno **");
        String cpf = this.lerLinha("Digite o CPF: ");
        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        Aluno aluno = new Aluno(cpf, nome, senha);
        sistema.addAluno(aluno);
        System.out.println("Aluno " + aluno + " criado com sucesso.");
    }

    public void cadProduto(Sistema sistema) {
        System.out.println("\n** Cadastrando um novo produto **");
        String nome = this.lerLinha("Digite o nome do produto: ");
        int qtd = this.lerInteiro("Digite a quantidade em estoque: ");
        double valor = this.lerDouble("Digite o valor unitário do produto: ");
        String codigo = sistema.gerarCodigoProduto();
        Produto produto = new Produto(nome, qtd, valor, codigo);
        sistema.addProd(produto);
        System.out.println("Produto " + produto + " criado com sucesso.");
    }

    public void cadSala(Sistema sistema) {
        System.out.println("\n** Cadastrando uma nova sala **");
        String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");
        String sala = this.lerLinha("Digite a sala (ex: para 904T, digite 04): ");
        String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");
        Sala salapdt = new Sala(bloco, sala, andar);
        sistema.addSala(salapdt);
        System.out.println("Sala " + salapdt + " criada com sucesso.");
    }
    private Sala lerSala(Sistema sistema) {
        System.out.println("Salas disponíveis: ");
        sistema.listarSalas();
        String nome = lerLinha("Digite a sala: ");
        Sala sala = sistema.getSala(nome);
        return sala;
    }

    private Item lerItem(Sistema sistema) {
        sistema.listarProdutos();
        String codigo = lerLinha("Digite o código do produto:");
        Produto produto = sistema.getProduto(codigo);
        int qtd;
        while (true) {
            qtd = lerInteiro(String.format("Digite a quantidade de "+ produto.getCodigo()+ ": " + produto.getNome()) + " no pedido:");
            if (produto.getQtd() >= qtd) {
                break;
            } else {
                System.out.println("Quantidade fora de estoque - disponível: " + produto.getQtd() + ". Tente outra: ");            }
        }

        return new Item(produto, qtd);
    }

    public void fazerPedido(Aluno aluno, Sistema sistema) {
        Sala sala = lerSala(sistema);
        if (sala == null) return;

        Pedido pedido = new Pedido(null, aluno, sala);
        while (true) { System.out.println(
        """
        Escolha uma opção:
        1) Inserir produto no carrinho.
        2) Fechar pedido.""");

            int opcao = lerInteiro(": ");
            if (opcao == 1) {
                System.out.println("Produtos disponíveis: ");
                Item item = lerItem(sistema);
                if (item != null) {
                    pedido.addItem(item);
                }
            }if (opcao == 2) {
                if (aluno.retirarSaldo(pedido.valorTotal())) {
                    for (Item item : pedido.getItens()) {
                        Produto produto = item.getProd();
                        produto.reduzirQtd(item.getQtd());
                    }
                    pedido.confirmar(sistema);
                    sistema.addPedido(pedido);
                    break;
                } else {
                    System.out.println("pobre");
                    return;
                }
            }
        }
    }




    public void entregarPedido(Aluno aluno, Sistema sistema) {
        Pedido[] pendentes = sistema.filtrarPedidos(false);

        System.out.println("Pedidos disponíveis para entrega:");
        if (pendentes.length == 0) {
            System.out.println("0 pedidos no momento.");
        } else {
            for (Pedido p : pendentes) {
                System.out.println("Código do Pedido: " + p.getCodigo());
                System.out.println("Produtos:");
                for (Item item : p.getItens()) {
                    Produto produto = item.getProd();
                    System.out.println(produto.getCodigo() + ": " + produto.getNome() +
                            " (QTD: " + item.getQtd() + ")");
                }
                System.out.println("Status: " + (p.estaEntregue() ? "Entregue" : "Em aberto"));
                System.out.println("Valor total: R$" + String.format("%.2f", p.valorTotal()));
                System.out.println("*");
            }
        }

        String codPedido = lerLinha("Digite o código do pedido: ");
        Pedido pedido = sistema.getPedido(codPedido);

        if (pedido != null) {
            if (!pedido.estaEntregue()) {
                pedido.atribuirEntregador(aluno);
                pedido.marcarComoEntregue();
                System.out.println("Pedido entregue com sucesso!");
            } else {
                System.out.println("Pedido ja foi entregue antes!");
            }
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }



    public void listarPedidos(Aluno aluno, Sistema sistema) {
        System.out.println("Pedidos de " + aluno.getNome() + " - CPF: " + aluno.getCpf() +
                " (Saldo: R$" + String.format("%.2f", aluno.getSaldo()) + ")");
        System.out.println("*");

        Pedido[] pAluno = sistema.filtrarPedidos(aluno);
        for (Pedido pedido : pAluno) {
            System.out.println("Código do Pedido: " + pedido.getCodigo());
            System.out.println("Produtos:");
            for (Item item : pedido.getItens()) {
                Produto produto = item.getProd();
                System.out.println(produto.getCodigo() + ": " + produto.getNome() +
                        " (QTD: " + item.getQtd() + ")");
            }
            System.out.println("Status: " + (pedido.estaEntregue() ? "Entregue" : "Em aberto"));
            System.out.println("Valor total: R$" + String.format("%.2f", pedido.valorTotal()));
            System.out.println("*");
        }
    }

    

    public void inserirCredito(Aluno aluno, Sistema sistema) {
        double valor = this.lerDouble("Digite o valor: ");
        aluno.inserirSaldo(valor);

        System.out.println("Crédito adicionado!");
    }
}
