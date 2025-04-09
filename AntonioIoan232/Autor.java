public class Autor extends Persoana{
    private String tip_autor;

    public Autor(String nume, String prenume, int data_nasterii, String tip_autor) {
        super(nume, prenume, data_nasterii);
        this.tip_autor = tip_autor;
    }

    @Override
    public void afisare_detalii() {
        System.out.println("Autorul : " + this.getNume() + " nascut in anul : " + Integer.toString(this.getData_nasterii()) + " care este " + tip_autor);
    }
}
