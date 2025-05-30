public class Recenzie {
    private String titlu;
    private String continut;
    private int nota;

    public Recenzie(String titlu, String continut, int nota) {
        this.titlu = titlu;
        this.continut = continut;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Recenzie{" +
                "carte=" + titlu +
                ", continut='" + continut + '\'' +
                ", nota=" + nota +
                '}';
    }

    public String getTitlu() {
        return titlu;
    }

    public String getContinut() {
        return continut;
    }

    public int getNota() {
        return nota;
    }
}
