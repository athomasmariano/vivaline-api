package fiap.tds.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    /* Passar credenciais do banco de dados Oracle */

    public static final String URL ="jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "RM561061";
    public static final String PASSWORD = "081204";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

