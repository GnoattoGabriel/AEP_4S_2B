import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String ARQUIVO_LOG = "logs.txt";

    public static void registrar(String mensagem) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHora = LocalDateTime.now().format(formato);

        String log = String.format("[%s] %s%n", dataHora, mensagem);

        try (FileWriter writer = new FileWriter(ARQUIVO_LOG, true)) {
            writer.write(log);
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }
    }
}

