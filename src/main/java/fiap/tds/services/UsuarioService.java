package fiap.tds.services;

import fiap.tds.dtos.UsuarioDto;
import fiap.tds.entities.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario(1, "Arthur", "arthur@email.com", "123456"));
        usuarios.add(new Usuario(2, "Ana", "ana@email.com", "abcdef"));
    }

    // LISTAR
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDto> dtos = new ArrayList<>();
        for (Usuario u : usuarios) {
            dtos.add(toDto(u));
        }
        return dtos;
    }

    // ADICIONAR
    public void adicionarUsuario(UsuarioDto dto) {
        int novoId = usuarios.size() + 1;
        Usuario usuario = new Usuario(novoId, dto.getNome(), dto.getEmail(), dto.getSenha());
        usuarios.add(usuario);
    }

    // REMOVER
    public boolean removerUsuario(int id) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        if (usuario.isPresent()) {
            usuarios.remove(usuario.get());
            return true;
        }
        return false;
    }

    // ATUALIZAR
    public boolean atualizarUsuario(int id, UsuarioDto dto) {
        Optional<Usuario> usuarioOptional = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setSenha(dto.getSenha());
            return true;
        }
        return false;
    }

    // VALIDAÇÃO
    public boolean validarUsuario(UsuarioDto dto) {
        return dto != null &&
                dto.getNome() != null && !dto.getNome().isEmpty() &&
                dto.getEmail() != null && !dto.getEmail().isEmpty() &&
                dto.getSenha() != null && !dto.getSenha().isEmpty();
    }

    // CONVERSÃO: Entity → DTO
    private UsuarioDto toDto(Usuario u) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setSenha(u.getSenha()); // ou omita a senha se for resposta pública
        return dto;
    }
}
