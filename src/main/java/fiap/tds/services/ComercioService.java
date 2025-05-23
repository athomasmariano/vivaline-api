package fiap.tds.services;

import fiap.tds.dtos.ComercioRequestDto;
import fiap.tds.dtos.ComercioResponseDto;
import fiap.tds.entities.Comercio;
import fiap.tds.repositories.ComercioRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ComercioService {

    private final ComercioRepository repository = new ComercioRepository();

    public void registrar(ComercioRequestDto dto) {
        Comercio c = new Comercio();
        c.setNome(dto.getNome());
        c.setEndereco(dto.getEndereco());
        c.setTelefone(dto.getTelefone());
        c.setCategoria(dto.getCategoria());

        repository.registrar(c);
    }

    public List<ComercioResponseDto> listarTodos() {
        return repository.buscarTodos()
                .stream()
                .map(c -> new ComercioResponseDto(c.getId(), c.getNome(), c.getEndereco(), c.getTelefone(), c.getCategoria()))
                .collect(Collectors.toList());
    }

    public ComercioResponseDto buscarPorId(int id) {
        Comercio c = repository.buscarPorId(id);
        if (c == null) return null;
        return new ComercioResponseDto(c.getId(), c.getNome(), c.getEndereco(), c.getTelefone(), c.getCategoria());
    }

    public void atualizar(int id, ComercioRequestDto dto) {
        Comercio c = new Comercio();
        c.setNome(dto.getNome());
        c.setEndereco(dto.getEndereco());
        c.setTelefone(dto.getTelefone());
        c.setCategoria(dto.getCategoria());

        repository.atualizar(id, c);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }
}