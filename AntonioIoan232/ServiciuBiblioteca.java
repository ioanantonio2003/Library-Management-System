import java.util.*;

public class ServiciuBiblioteca {
    private List<Persoana> persoane; // lista pt toate Persoane - upcasting
    private Set<Sectiune> sectii; //lista sortata pt Sectiuni alfabetic
    private List<Imprumuturi> imprumuturi; // Lista de imprumturi

    public ServiciuBiblioteca() {
        persoane = new ArrayList<>();
        sectii = new TreeSet<>(Comparator.comparing(s -> s.nume_sectiune)); //aici avem lista sortata alfabetic
        imprumuturi = new ArrayList<>();
    }

    public void adauga_Persoana(int varianta, String nume, String prenume, int data_nasterii, String specificatie){
        if(varianta == 1){
            Persoana autor = new Autor(nume,prenume,data_nasterii,specificatie);
            persoane.add(autor);
            System.out.println("Autor adaugat cu succes!");
        }else if(varianta == 2){
            Persoana cititor = new Cititor(nume,prenume,data_nasterii,specificatie);
            persoane.add(cititor);
            System.out.println("Cititor adaugat cu succes!");
        }else if(varianta == 3){
            Persoana angajat = new Angajat(nume,prenume,data_nasterii,specificatie);
            persoane.add(angajat);
            System.out.println("Angajat adaut cu succes");
        }
    }
}
