

public class Aluno extends Usuario {
    private double saldo;

    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean retirarSaldo(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void inserirSaldo(double valor) {
        saldo += valor;
    }

    public String toString() {
        return super.toString() + " (Saldo: R$" + String.format("%.2f", this.saldo) + ")";
    }
}

