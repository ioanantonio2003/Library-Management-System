public class Imprumuturi {
    private Carte carte;
    private Cititor cititor;
    private static int id = 1;
    private int cod_imprumut;
    private Boolean stare;


    public Imprumuturi(Carte carte, Cititor cititor) {
        this.carte = carte;
        this.cititor = cititor;
        this.cod_imprumut = id++;
        stare = true;
    }

    public void setStare(Boolean stare) {
        this.stare = false;
    }

    public Cititor getCititor() {
        return cititor;
    }

    public Boolean getStare() {
        return stare;
    }

    @Override
    public String toString() {
        return "Imprumuturi{" +
                "carte=" + carte.getTitlu() +
                ", cod_imprumut=" + cod_imprumut +
                '}';
    }
}
