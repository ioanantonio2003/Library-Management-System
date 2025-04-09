import java.util.ArrayList;
import java.util.List;

public class Sectiune {
    private String nume_sectiune;
    private List<Carte> carti;

    public Sectiune(String nume_sectiune) {
        this.nume_sectiune = nume_sectiune;
        carti = new ArrayList<>();
    }
}
