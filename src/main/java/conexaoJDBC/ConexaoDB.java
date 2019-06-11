package conexaoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {
    /**
     * @author eduardosilva.santos
     *
     */
    public static class SingleConnection {
        private static String url = "jdbc:postgresql://localhost:5432/meu_banco";
        private static String password = "root";
        private static String user = "postgres";
        private static Connection connection = null;

        private static void conectar() {
            try {
                if(connection == null) {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, password);
                    connection.setAutoCommit(false);
                    System.out.println("Conectou com sucesso");
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        public SingleConnection() {
            conectar();
        }
        public static Connection getConnection() {
            return connection;
        }
        static {
            conectar();
        }
    }
}
