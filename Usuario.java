
public class Usuario {
    protected String cpf;
    protected String nome;
    private String senha;

    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }


    public String getNome() {
        return this.nome;
    }

    public boolean validarAcesso(String s) {
        return s.equals(this.senha);
    }

    public String toString() {
        return this.nome + " - CPF: " + this.cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

}
