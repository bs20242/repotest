

public class Produto {
    private String nome;
    private String codigo;
    private int qtd;
    private double valor;

    public Produto(String nome, int qtd, double valor, String codigo) {
        this.nome = nome;
        this.qtd = qtd;
        this.valor = valor;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQtd() {
        return qtd;
    }

    public double getValor() {
        return valor;
    }

    public void reduzirQtd(int qtd) {
        this.qtd -= qtd;
    }


    public String toString() {
        return "Produto " + codigo + ": " + nome;
    }

}
