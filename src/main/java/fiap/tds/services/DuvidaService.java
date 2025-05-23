package fiap.tds.services;

import fiap.tds.entities.Duvida;
import fiap.tds.repositories.DuvidaRepository;

import java.util.List;

public class DuvidaService {

    private final DuvidaRepository repository;

    public DuvidaService() {
        this.repository = new DuvidaRepository();
    }

    public List<Duvida> listarFaqs() {
        return repository.listar();
    }

    public String registrarFaq(Duvida dto) {
        if (dto.getPergunta() == null || dto.getPergunta().isBlank() ||
                dto.getResposta() == null || dto.getResposta().isBlank()) {
            return null;
        }

        repository.salvar(dto);
        return "Contato registrado com sucesso.";
    }

    public Duvida buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizar(Duvida duvida) {
        repository.atualizar(duvida);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }}

