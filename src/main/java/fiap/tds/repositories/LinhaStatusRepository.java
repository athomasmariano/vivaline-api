package fiap.tds.repositories;

import fiap.tds.entities.LinhaStatus;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinhaStatusRepository {
    private static final Logger logger = LogManager.getLogger(LinhaStatusRepository.class);

    public void registrar(LinhaStatus linhaStatus) {
        var sql = "INSERT INTO T_LINHA_STATUS (nome, cor, status, circulo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, linhaStatus.getNome());
            stmt.setString(2, linhaStatus.getCor());
            stmt.setString(3, linhaStatus.getStatus());
            stmt.setString(4, linhaStatus.getCirculo());

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ LinhaStatus registrado com sucesso!");
            }
        } catch (SQLException e) {
            logger.error("❌ Erro ao registrar LinhaStatus", e);
        }
    }

    public List<LinhaStatus> buscarTodos() {
        List<LinhaStatus> lista = new ArrayList<>();
        var sql = "SELECT id, nome, cor, status, circulo FROM T_LINHA_STATUS";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LinhaStatus ls = new LinhaStatus();
                ls.setId(rs.getInt("id"));
                ls.setNome(rs.getString("nome"));
                ls.setCor(rs.getString("cor"));
                ls.setStatus(rs.getString("status"));
                ls.setCirculo(rs.getString("circulo"));
                lista.add(ls);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar LinhaStatus", e);
        }
        return lista;
    }

    public LinhaStatus buscarPorId(int id) {
        var sql = "SELECT id, nome, cor, status, circulo FROM T_LINHA_STATUS WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LinhaStatus ls = new LinhaStatus();
                ls.setId(rs.getInt("id"));
                ls.setNome(rs.getString("nome"));
                ls.setCor(rs.getString("cor"));
                ls.setStatus(rs.getString("status"));
                ls.setCirculo(rs.getString("circulo"));
                return ls;
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar LinhaStatus por id", e);
        }
        return null;
    }

    public void atualizar(int id, LinhaStatus linhaStatus) {
        var sql = "UPDATE T_LINHA_STATUS SET nome = ?, cor = ?, status = ?, circulo = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, linhaStatus.getNome());
            stmt.setString(2, linhaStatus.getCor());
            stmt.setString(3, linhaStatus.getStatus());
            stmt.setString(4, linhaStatus.getCirculo());
            stmt.setInt(5, id);

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ LinhaStatus atualizado com sucesso!");
            } else {
                logger.warn("⚠️ LinhaStatus com id " + id + " não encontrado para atualização.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao atualizar LinhaStatus", e);
        }
    }

    public void deletar(int id) {
        var sql = "DELETE FROM T_LINHA_STATUS WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int res = stmt.executeUpdate();

            if (res > 0) {
                logger.info("✅ LinhaStatus deletado com sucesso!");
            } else {
                logger.warn("⚠️ LinhaStatus com id " + id + " não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao deletar LinhaStatus", e);
        }
    }
}
