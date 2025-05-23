package fiap.tds.dtos;

public class ComercioRequestDto {
    private String nome;
    private String endereco;
    private String telefone;
    private String categoria;

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
