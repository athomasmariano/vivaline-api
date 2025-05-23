package fiap.tds.repositories;

import fiap.tds.entities.Estacao;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstacaoRepository {
    private static final Logger logger = LogManager.getLogger(EstacaoRepository.class);

    public void cadastrar(Estacao estacao) {
        var sql = "INSERT INTO T_ESTACAO (numero_estacao, nome, lat, lng, horario_funcionamento, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estacao.getNumeroEstacao());
            stmt.setString(2, estacao.getNome());
            stmt.setDouble(3, estacao.getLat());
            stmt.setDouble(4, estacao.getLng());
            stmt.setString(5, estacao.getHorarioFuncionamento());
            stmt.setString(6, estacao.getDescricao());

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ Estacao registrada com sucesso!");
            }
        } catch (SQLException e) {
            logger.error("❌ Erro ao registrar Estacao", e);
        }
    }

    public List<Estacao> buscarTodas() {
        List<Estacao> lista = new ArrayList<>();
        var sql = "SELECT numero_estacao, nome, lat, lng, horario_funcionamento, descricao FROM T_ESTACAO";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estacao estacao = new Estacao();
                estacao.setNumeroEstacao(rs.getInt("numero_estacao"));
                estacao.setNome(rs.getString("nome"));
                estacao.setLat(rs.getDouble("lat"));
                estacao.setLng(rs.getDouble("lng"));
                estacao.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
                estacao.setDescricao(rs.getString("descricao"));
                lista.add(estacao);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar Estacoes", e);
        }
        return lista;
    }

    public Estacao buscarPorNumero(int numeroEstacao) {
        var sql = "SELECT numero_estacao, nome, lat, lng, horario_funcionamento, descricao FROM T_ESTACAO WHERE numero_estacao = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroEstacao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estacao estacao = new Estacao();
                estacao.setNumeroEstacao(rs.getInt("numero_estacao"));
                estacao.setNome(rs.getString("nome"));
                estacao.setLat(rs.getDouble("lat"));
                estacao.setLng(rs.getDouble("lng"));
                estacao.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
                estacao.setDescricao(rs.getString("descricao"));
                return estacao;
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar Estacao por numero", e);
        }
        return null;
    }

    public void atualizar(int numeroEstacao, Estacao estacao) {
        var sql = "UPDATE T_ESTACAO SET nome = ?, lat = ?, lng = ?, horario_funcionamento = ?, descricao = ? WHERE numero_estacao = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estacao.getNome());
            stmt.setDouble(2, estacao.getLat());
            stmt.setDouble(3, estacao.getLng());
            stmt.setString(4, estacao.getHorarioFuncionamento());
            stmt.setString(5, estacao.getDescricao());
            stmt.setInt(6, numeroEstacao);

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ Estacao atualizada com sucesso!");
            } else {
                logger.warn("⚠️ Estacao com numero " + numeroEstacao + " não encontrada para atualização.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao atualizar Estacao", e);
        }
    }

    public void deletar(int numeroEstacao) {
        var sql = "DELETE FROM T_ESTACAO WHERE numero_estacao = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroEstacao);
            int res = stmt.executeUpdate();

            if (res > 0) {
                logger.info("✅ Estacao deletada com sucesso!");
            } else {
                logger.warn("⚠️ Estacao com numero " + numeroEstacao + " não encontrada para exclusão.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao deletar Estacao", e);
        }
    }
}