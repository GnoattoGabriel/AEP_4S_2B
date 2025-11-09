import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3306/microgestor";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar(){

        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Erro ao conectar DB :c!");
            return null;
        }
    }
}
