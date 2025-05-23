package fiap.tds.repositories;

import fiap.tds.entities.Duvida;
import fiap.tds.exceptions.NotFoundException;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuvidaRepository {

    private static final Logger logger = LogManager.getLogger(DuvidaRepository.class);

    // Listar todas as dúvidas
    public List<Duvida> listar() {
        var query = "SELECT id, pergunta, resposta FROM T_DUVIDAS";
        List<Duvida> duvidas = new ArrayList<>();

        try (var conn = DatabaseConfig.getConnection();
             var stmt = conn.prepareStatement(query);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {
                Duvida d = new Duvida();
                d.setId(rs.getInt("id"));
                d.setPergunta(rs.getString("pergunta"));
                d.setResposta(rs.getString("resposta"));
                duvidas.add(d);
            }

            logger.info("✅ Dúvidas listadas com sucesso.");

        } catch (SQLException e) {
            logger.error("❌ Erro ao listar dúvidas", e);
            throw new RuntimeException("Erro ao acessar banco de dados.");
        }

        return duvidas;
    }

    // Salvar uma nova dúvida
    public void salvar(Duvida duvida) {
        var query = "INSERT INTO T_DUVIDAS (pergunta, resposta) VALUES (?, ?)";

        try (var conn = DatabaseConfig.getConnection();
             var stmt = conn.prepareStatement(query)) {

            stmt.setString(1, duvida.getPergunta());
            stmt.setString(2, duvida.getResposta());

            int res = stmt.executeUpdate();

            if (res > 0) {
                logger.info("✅ Dúvida salva com sucesso!");
            } else {
                throw new RuntimeException("Não foi possível salvar a dúvida.");
            }

        } catch (SQLException e) {
            logger.error("❌ Erro ao salvar dúvida", e);
            throw new RuntimeException("Erro ao salvar dúvida no banco.");
        }
    }

    // Buscar dúvida por ID
    public Duvida buscarPorId(int id) {
        var query = "SELECT id, pergunta, resposta FROM T_DUVIDAS WHERE id = ?";

        try (var conn = DatabaseConfig.getConnection();
             var stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Duvida d = new Duvida();
                    d.setId(rs.getInt("id"));
                    d.setPergunta(rs.getString("pergunta"));
                    d.setResposta(rs.getString("resposta"));
                    logger.info("✅ Dúvida encontrada com ID: " + id);
                    return d;
                }
            }

        } catch (SQLException e) {
            logger.error("❌ Erro ao buscar dúvida por ID: {}", id, e);
        }

        throw new NotFoundException("Dúvida não encontrada com ID: " + id);
    }

    // Atualizar dúvida existente por ID
    public void atualizar(Duvida duvida) {
        var query = "UPDATE T_DUVIDAS SET pergunta = ?, resposta = ? WHERE id = ?";

        try (var conn = DatabaseConfig.getConnection();
             var stmt = conn.prepareStatement(query)) {

            stmt.setString(1, duvida.getPergunta());
            stmt.setString(2, duvida.getResposta());
            stmt.setInt(3, duvida.getId());

            int res = stmt.executeUpdate();

            if (res == 0) {
                throw new NotFoundException("Dúvida não encontrada para atualização.");
            }

            logger.info("✅ Dúvida atualizada com sucesso!");

        } catch (SQLException e) {
            logger.error("❌ Erro ao atualizar dúvida", e);
            throw new RuntimeException("Erro ao atualizar dúvida no banco.");
        }
    }

    // Deletar dúvida por ID
    public void deletar(int id) {
        var query = "DELETE FROM T_DUVIDAS WHERE id = ?";

        try (var conn = DatabaseConfig.getConnection();
             var stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();

            if (res > 0) {
                logger.info("✅ Dúvida deletada com sucesso!");
            } else {
                throw new NotFoundException("Dúvida não encontrada para exclusão.");
            }

        } catch (SQLException e) {
            logger.error("❌ Erro ao deletar dúvida", e);
            throw new RuntimeException("Erro ao deletar dúvida no banco.");
        }
    }
}
