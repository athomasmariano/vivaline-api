package fiap.tds.services;

import fiap.tds.entities.Estacao;
import fiap.tds.repositories.EstacaoRepository;

import java.util.List;

public class EstacaoService {

    private final EstacaoRepository repository = new EstacaoRepository();

    public void cadastrar(Estacao estacao) {

        if (repository.buscarPorNumero(estacao.getNumeroEstacao()) != null) {
            throw new IllegalArgumentException("Estação com esse número já existe.");
        }
        repository.cadastrar(estacao);
    }

    public List<Estacao> listarTodas() {
        return repository.buscarTodas();
    }

    public Estacao buscarPorNumero(int numeroEstacao) {
        return repository.buscarPorNumero(numeroEstacao);
    }

    public boolean atualizar(int numeroEstacao, Estacao estacao) {
        Estacao existente = repository.buscarPorNumero(numeroEstacao);
        if (existente != null) {
            repository.atualizar(numeroEstacao, estacao);
            return true;
        }
        return false;
    }

    public boolean deletar(int numeroEstacao) {
        Estacao existente = repository.buscarPorNumero(numeroEstacao);
        if (existente != null) {
            repository.deletar(numeroEstacao);
            return true;
        }
        return false;
    }
}