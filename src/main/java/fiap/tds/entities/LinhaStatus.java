package fiap.tds.entities;

public class LinhaStatus {
    private int id;
    private String nome;
    private String cor;
    private String status;
    private String circulo;

    public LinhaStatus() {
    }

    public LinhaStatus(int id, String nome, String cor, String status, String circulo) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.status = status;
        this.circulo = circulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCirculo() {
        return circulo;
    }

    public void setCirculo(String circulo) {
        this.circulo = circulo;
    }
}
