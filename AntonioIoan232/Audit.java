import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    private static Audit audit = null;
    private final String fisier = "audit.csv";
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //constructor privat
    private Audit() {}

    //daca audit nu exista se creeaza
    public static Audit getAudit() {
        if (audit == null) {
            audit = new Audit();
        }
        return audit;
    }

    //se scrie in fisierul creat audit.csv
    public void actiune(String act) {
        try (FileWriter writer = new FileWriter(fisier,true)) {
            String time = LocalDateTime.now().format(format);
            writer.write(act + " ===> " + time + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
