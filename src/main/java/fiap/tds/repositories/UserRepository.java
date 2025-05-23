package fiap.tds.repositories;

import fiap.tds.entities.User;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    public void registrar(User user) {
        var sql = "INSERT INTO T_USERS (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); // controla manualmente a transação

            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());

            int res = stmt.executeUpdate();

            if (res > 0) {
                conn.commit();
                logger.info("✅ Usuário registrado com sucesso!");
            } else {
                conn.rollback();
                logger.warn("⚠️ Nenhum usuário foi inserido.");
            }

        } catch (SQLException e) {
            logger.error("❌ Erro ao registrar usuário", e);
        }
    }

    public List<User> buscarTodos() {
        List<User> lista = new ArrayList<>();
        var sql = "SELECT id, nome, email, senha FROM T_USERS";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                lista.add(u);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar usuários", e);
        }
        return lista;
    }

    public User buscarPorId(int id) {
        var sql = "SELECT id, nome, email, senha FROM T_USERS WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                return u;
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar usuário por id", e);
        }
        return null;
    }

    public void atualizar(int id, User user) {
        var sql = "UPDATE T_USERS SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.setInt(4, id);

            int res = stmt.executeUpdate();

            if (res > 0) {
                conn.commit();
                logger.info("✅ Usuário atualizado com sucesso!");
            } else {
                conn.rollback();
                logger.warn("⚠️ Usuário com id " + id + " não encontrado para atualização.");
            }

        } catch (SQLException e) {
            logger.error("Erro ao atualizar usuário", e);
        }
    }

    public void deletar(int id) {
        var sql = "DELETE FROM T_USERS WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            stmt.setInt(1, id);
            int res = stmt.executeUpdate();

            if (res > 0) {
                conn.commit();
                logger.info("✅ Usuário deletado com sucesso!");
            } else {
                conn.rollback();
                logger.warn("⚠️ Usuário com id " + id + " não encontrado para exclusão.");
            }

        } catch (SQLException e) {
            logger.error("Erro ao deletar usuário", e);
        }
    }

    public User buscarPorEmail(String email) {
        var sql = "SELECT id, nome, email, senha FROM T_USERS WHERE email = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                return u;
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar usuário por e-mail", e);
        }
        return null;
    }
}