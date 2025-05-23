package fiap.tds.repositories;

import fiap.tds.entities.Noticia;
import fiap.tds.infrastructure.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticiaRepository {

    private static final Logger logger = LogManager.getLogger(NoticiaRepository.class);

    public void registrar(Noticia noticia) {
        var sql = "INSERT INTO T_NOTICIA (titulo, data) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getData());

            int res = stmt.executeUpdate();
            if (res > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        noticia.setId(rs.getInt(1)); // ✅ Seta o ID gerado
                    }
                }
                logger.info("✅ Notícia registrada com sucesso! ID: " + noticia.getId());
            }
        } catch (SQLException e) {
            logger.error("❌ Erro ao registrar Notícia", e);
        }
    }

    public List<Noticia> buscarTodos() {
        List<Noticia> lista = new ArrayList<>();
        var sql = "SELECT id, titulo, data FROM T_NOTICIA";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id")); // ✅ Incluído
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setData(rs.getString("data"));
                lista.add(noticia);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar notícias", e);
        }
        return lista;
    }

    public Noticia buscarPorId(int id) {
        var sql = "SELECT id, titulo, data FROM T_NOTICIA WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id")); // ✅ Incluído
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setData(rs.getString("data"));
                return noticia;
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar notícia por id", e);
        }
        return null;
    }

    public void atualizar(int id, Noticia noticia) {
        var sql = "UPDATE T_NOTICIA SET titulo = ?, data = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getData());
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();
            if (res > 0) {
                logger.info("✅ Notícia atualizada com sucesso!");
            } else {
                logger.warn("⚠️ Notícia com id " + id + " não encontrada para atualização.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao atualizar Notícia", e);
        }
    }

    public void deletar(int id) {
        var sql = "DELETE FROM T_NOTICIA WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int res = stmt.executeUpdate();

            if (res > 0) {
                logger.info("✅ Notícia deletada com sucesso!");
            } else {
                logger.warn("⚠️ Notícia com id " + id + " não encontrada para exclusão.");
            }
        } catch (SQLException e) {
            logger.error("Erro ao deletar Notícia", e);
        }
    }
}
