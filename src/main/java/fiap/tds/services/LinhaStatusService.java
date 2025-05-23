package fiap.tds.services;

import fiap.tds.entities.LinhaStatus;
import fiap.tds.repositories.LinhaStatusRepository;

import java.util.List;

public class LinhaStatusService {

    private final LinhaStatusRepository repository = new LinhaStatusRepository();

    public void registrar(LinhaStatus linhaStatus) {
        repository.registrar(linhaStatus);
    }

    public List<LinhaStatus> listarTodos() {
        return repository.buscarTodos();
    }

    public LinhaStatus buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public void atualizar(int id, LinhaStatus linhaStatus) {
        repository.atualizar(id, linhaStatus);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }
}
