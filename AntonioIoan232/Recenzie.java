public class Recenzie {
    private Carte carte;
    private String continut;
    private int nota;

    public Recenzie(Carte carte, String continut, int nota) {
        this.carte = carte;
        this.continut = continut;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Recenzie{" +
                "carte=" + carte +
                ", continut='" + continut + '\'' +
                ", nota=" + nota +
                '}';
    }
}
