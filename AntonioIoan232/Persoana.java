public abstract class Persoana {
    private String nume;
    private String prenume;
    private int data_nasterii;

    public Persoana(String nume, String prenume, int data_nasterii) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nasterii = data_nasterii;
    }

    public String getNume() {
        return nume + " " + prenume;
    }

    public int getData_nasterii() {
        return data_nasterii;
    }

    public abstract void afisare_detalii();
}
