

public class Item {
    private Produto pdt;
    private int qtd;

    public Item(Produto produto, int quantidade) {
        this.pdt = produto;
        this.qtd = quantidade;
    }

    public Produto getProd() {
        return pdt;
    }

    public int getQtd() {
        return qtd;
    }

    public double getTotal() {
        return pdt.getValor() * qtd;
    }
    public String toString() {
        return pdt.getCodigo() + ": " + pdt.getNome() + " (QTD: " + qtd + ")";
    }

}
