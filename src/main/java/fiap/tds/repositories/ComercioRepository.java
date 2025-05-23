package fiap.tds.repositories;

import fiap.tds.entities.Comercio;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComercioRepository {
    private static final Logger logger = LogManager.getLogger(ComercioRepository.class);

    public void registrar(Comercio comercio) {
        var sql = "INSERT INTO T_COMERCIOS (nome, endereco, telefone, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comercio.getNome());
            stmt.setString(2, comercio.getEndereco());
            stmt.setString(3, comercio.getTelefone());
            stmt.setString(4, comercio.getCategoria());

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ Comércio registrado com sucesso!");
            }
        } catch (SQLException e) {
            logger.error("❌ Erro ao registrar comércio", e);
        }
    }

    public List<Comercio> buscarTodos() {
        List<Comercio> lista = new ArrayList<>();
        var sql = "SELECT id, nome, endereco, telefone, categoria FROM T_COMERCIOS";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comercio c = new Comercio();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setCategoria(rs.getString("categoria"));
                lista.add(c);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar comércios", e);
        }
        return lista;
    }

    public Comercio buscarPorId(int id) {
        var sql = "SELECT id, nome, endereco, telefone, categoria FROM T_COMERCIOS WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Comercio c = new Comercio();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setCategoria(rs.getString("categoria"));
                return c;
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar comércio por id", e);
        }
        return null;
    }

    public void atualizar(int id, Comercio comercio) {
        var sql = "UPDATE T_COMERCIOS SET nome = ?, endereco = ?, telefone = ?, categoria = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comercio.getNome());
            stmt.setString(2, comercio.getEndereco());
            stmt.setString(3, comercio.getTelefone());
            stmt.setString(4, comercio.getCategoria());
            stmt.setInt(5, id);

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ Comércio atualizado com sucesso!");
            } else {
                logger.warn("⚠️ Comércio com id " + id + " não encontrado para atualização.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao atualizar comércio", e);
        }
    }

    public void deletar(int id) {
        var sql = "DELETE FROM T_COMERCIOS WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int res = stmt.executeUpdate();

            if (res > 0) {
                logger.info("✅ Comércio deletado com sucesso!");
            } else {
                logger.warn("⚠️ Comércio com id " + id + " não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao deletar comércio", e);
        }
    }
}