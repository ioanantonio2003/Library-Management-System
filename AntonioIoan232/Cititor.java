public class Cititor extends Persoana {
    private int id_citior;

    public Cititor(String nume, String prenume, int data_nasterii, int id_citior) {
        super(nume, prenume, data_nasterii);
        this.id_citior = id_citior;
    }

    @Override
    public void afisare_detalii() {
        System.out.println("Cititorul : " + this.getNume() + " nascut in anul " + this.getData_nasterii() + " cu id-ul : " + id_citior);

    }
}
