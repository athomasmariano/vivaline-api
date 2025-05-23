package fiap.tds.services;

import fiap.tds.entities.User;
import fiap.tds.repositories.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public void registrar(User user) {
        User existente = repository.buscarPorEmail(user.getEmail());
        if (existente != null) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }
        repository.registrar(user);
    }

    public List<User> listarTodos() {
        return repository.buscarTodos();
    }

    public User buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public boolean atualizar(int id, User user) {
        User existente = repository.buscarPorId(id);
        if (existente == null) {
            return false;
        }
        repository.atualizar(id, user);
        return true;
    }

    public boolean deletar(int id) {
        User existente = repository.buscarPorId(id);
        if (existente == null) {
            return false;
        }
        repository.deletar(id);
        return true;
    }


    public boolean autenticar(String email, String senha) {
        User user = repository.buscarPorEmail(email);
        return user != null && user.getSenha().equals(senha);
    }

}
