public class Angajat extends Persoana{
    private String tip_angajat;

    public Angajat(String nume, String prenume, int data_nasterii, String tip_angajat) {
        super(nume, prenume, data_nasterii);
        this.tip_angajat = tip_angajat;
    }

    @Override
    public void afisare_detalii() {
        System.out.println("Angajatul : " + this.getNume() + " nascut in anul : " + Integer.toString(this.getData_nasterii()) + " cu functia de" + tip_angajat);
    }
}
