

public class Sala {
    private String bloco;
    private String sala;
    private String andar;

    public Sala(String bloco, String sala, String andar) {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }
    public String getCodSala() {
        return bloco + sala + andar;
    }

    public String toString() {
        return bloco + sala + andar;
    }

}
