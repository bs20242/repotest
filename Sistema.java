
import java.util.ArrayList;

public class Sistema {
    private ArrayList<Aluno> alunos;
    private ArrayList<Admin> adms;
    private ArrayList<Produto> produtos;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Sala> salas;

    private int codigoProduto = 1;
    private int codigoPedido = 1;

    public Sistema() {
        alunos = new ArrayList<>();
        adms = new ArrayList<>();
        produtos = new ArrayList<>();
        pedidos = new ArrayList<>();
        salas = new ArrayList<>();
    }

    public boolean sistemaVazio() {
        return this.adms.size() == 0;
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void addAdmin(Admin admin) {
        this.adms.add(admin);
    }


    public void addProd(Produto produto) {
        produtos.add(produto);
    }


    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }


    public void addSala(Sala sala) {
        salas.add(sala);
    }


    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCpf())) return a;
        }

        return null;
    }


    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCpf())) return a;
        }

        return null;
    }


    public Produto getProduto(String codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }

    public Pedido getPedido(String codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo().equals(codigo)) {
                return pedido;
            }
        }
        return null;
    }

    public Sala getSala(String nomeSala) {
        for (Sala sala : salas) {
            if (sala.getCodSala().equalsIgnoreCase(nomeSala)) {
                return sala;
            }
        }
        return null;
    }

    public void listarProdutos() {
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void listarSalas() {
        for (Sala sala : salas) {
            System.out.println(sala);
        }
    }

    public Pedido[] filtrarPedidos(boolean disponivel) {
        ArrayList<Pedido> filtro = new ArrayList<>();
        for (Pedido pedido : this.pedidos) {
            if (pedido.estaEntregue() == disponivel) {
                filtro.add(pedido);
            }
        }
        return filtro.toArray(new Pedido[0]);
    }

    public Pedido[] filtrarPedidos(Aluno aluno) {
        ArrayList<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : this.pedidos) {
            if (pedido.getAluno().equals(aluno)) {
                resultado.add(pedido);
            }
        }
        return resultado.toArray(new Pedido[0]);
    }



    public String gerarCodigoProduto() {
        return "PROD-" + (codigoProduto++);
    }

    public String gerarCodigoPedido() {
        return "PEDIDO-" + (codigoPedido++);
    }
}
