package fiap.tds.entities;

public class Estacao {
    private int numeroEstacao;
    private String nome;
    private double lat;
    private double lng;
    private String horarioFuncionamento;
    private String descricao;

    public Estacao() {
    }

    public Estacao(int numeroEstacao, String nome, double lat, double lng, String horarioFuncionamento, String descricao) {
        this.numeroEstacao = numeroEstacao;
        this.nome = nome;
        this.lat = lat;
        this.lng = lng;
        this.horarioFuncionamento = horarioFuncionamento;
        this.descricao = descricao;
    }

    // Getters e Setters

    public int getNumeroEstacao() {
        return numeroEstacao;
    }

    public void setNumeroEstacao(int numeroEstacao) {
        this.numeroEstacao = numeroEstacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
