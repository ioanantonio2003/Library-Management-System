public  class Carte {
    private String nume_autor;
    private String titlu;
    private int an_publicatie;
    private int nr_copii;
    private String sectiune;

    public Carte(String autor, String titlu, int an_publicatie, int nr_copii, String sectiune) {
        this.nume_autor = autor;
        this.titlu = titlu;
        this.an_publicatie = an_publicatie;
        this.nr_copii = nr_copii;
        this.sectiune = sectiune;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "autor=" + nume_autor +
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
    public void copii(int i){
        nr_copii = nr_copii + i;
    }
}
