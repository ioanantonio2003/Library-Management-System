import java.util.ArrayList;
import java.util.List;

public class Sectiune {
    protected String nume_sectiune;
    private List<Carte> carti;

    public Sectiune(String nume_sectiune) {
        this.nume_sectiune = nume_sectiune;
        carti = new ArrayList<>();
    }

    public String getNume_sectiune() {
        return nume_sectiune;
    }
    public void adauga_c(Carte carte){
        carti.add(carte);
    }
}
