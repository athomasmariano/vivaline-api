package fiap.tds.entities;

public class Duvida {
    // Para perguntas frequentes
    private int id;
    private String pergunta;
    private String resposta;

    public Duvida() {
    }

    public Duvida(int id, String pergunta, String resposta) {
        this.id = id;
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
