public  class Carte {
    private Autor autor;
    private String titlu;
    private int an_publicatie;
    private int nr_copii;

    public Carte(Autor autor, String titlu, int an_publicatie, int nr_copii) {
        this.autor = autor;
        this.titlu = titlu;
        this.an_publicatie = an_publicatie;
        this.nr_copii = nr_copii;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "autor=" + autor +
                ", titlu='" + titlu + '\'' +
                ", an_publicatie=" + an_publicatie +
                ", nr_copii=" + nr_copii +
                '}';
    }

    public int getNr_copii() {
        return nr_copii;
    }

    public String getTitlu() {
        return titlu;
    }
}
