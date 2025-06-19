
import java.util.ArrayList;

public class Pedido {
    private String cod;
    private Aluno aluno;
    private Aluno ifood;
    private Sala sala;
    private ArrayList<Item> itens;
    private boolean pendente;

    public Pedido(String codigo, Aluno aluno, Sala sala) {
        this.cod = codigo;
        this.aluno = aluno;
        this.sala = sala;
        this.itens = new ArrayList<>();
        this.pendente = true;
    }

    public void addItem(Item item) {
        itens.add(item);
    }

    public double valorTotal() {
        double itensvalor = 0.0;
        for (Item item : itens) {
            itensvalor += item.getTotal();

        }

        double taxa = 1.0;

        return itensvalor + taxa;
    }


    public void confirmar(Sistema sistema) {
        if (this.cod == null) {
            this.cod = sistema.gerarCodigoPedido();
        }
        pendente = true;
    }

    public void atribuirEntregador(Aluno entregador) {
        this.ifood = entregador;
    }

    public void marcarComoEntregue() {
        pendente = false;
    }

    public boolean estaEntregue() {
        return !pendente;
    }

    public String getCodigo() {
        return cod;
    }

    public Aluno getAluno() {
        return aluno;
    }


    public String toString() {
        String resultado = "Código do Pedido: " + cod + "\n";
        resultado += "Produtos:\n";

        for (Item item : itens) {
            resultado += item.toString() + "\n";
        }

        resultado += "Status: " + (pendente ? "Em aberto" : "Entregue") + "\n";
        resultado += "Valor total: R$" + String.format("%.2f", valorTotal()) + "\n";

        return resultado;
    }


    public ArrayList<Item> getItens() {
        return itens;
    }
}
