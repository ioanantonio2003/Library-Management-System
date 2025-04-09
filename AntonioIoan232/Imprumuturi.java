public class Imprumuturi {
    private Carte carte;
    private Cititor cititor;
    private static int id = 1;
    private int cod_imprumut;


    public Imprumuturi(Carte carte, Cititor cititor) {
        this.carte = carte;
        this.cititor = cititor;
        this.cod_imprumut = id++;
    }
}
