package fiap.tds.services;

import fiap.tds.entities.Noticia;
import fiap.tds.exceptions.BadRequestException;
import fiap.tds.exceptions.NotFoundException;
import fiap.tds.repositories.NoticiaRepository;

import java.util.List;

public class NoticiaService {

    private final NoticiaRepository repository = new NoticiaRepository();

    public void registrar(Noticia noticia) {
        if (noticia == null || noticia.getTitulo() == null || noticia.getData() == null) {
            throw new BadRequestException("Título e data são obrigatórios.");
        }

        repository.registrar(noticia); // o repositório deve preencher o ID no objeto
    }

    public List<Noticia> listarTodos() {
        return repository.buscarTodos();
    }

    public Noticia buscarPorId(int id) {
        Noticia noticia = repository.buscarPorId(id);
        if (noticia == null) {
            throw new NotFoundException("Notícia com ID " + id + " não encontrada.");
        }
        return noticia;
    }

    public void atualizar(int id, Noticia noticia) {
        if (noticia == null || noticia.getTitulo() == null || noticia.getData() == null) {
            throw new BadRequestException("Título e data são obrigatórios para atualização.");
        }

        Noticia existente = repository.buscarPorId(id);
        if (existente == null) {
            throw new NotFoundException("Notícia com ID " + id + " não encontrada para atualização.");
        }

        repository.atualizar(id, noticia);
    }

    public void deletar(int id) {
        Noticia existente = repository.buscarPorId(id);
        if (existente == null) {
            throw new NotFoundException("Notícia com ID " + id + " não encontrada para exclusão.");
        }

        repository.deletar(id);
    }
}
