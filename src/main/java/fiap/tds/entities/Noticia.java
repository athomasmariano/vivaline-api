package fiap.tds.entities;

public class Noticia {
    private int id;
    private String titulo;
    private String data;

    public Noticia() {
    }

    public Noticia(int id, String titulo, String data) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
