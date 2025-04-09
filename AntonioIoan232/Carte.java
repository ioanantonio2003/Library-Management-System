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

}
