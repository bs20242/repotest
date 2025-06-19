
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


}
