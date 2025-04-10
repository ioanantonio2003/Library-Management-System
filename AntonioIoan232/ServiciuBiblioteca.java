import java.util.*;

public class ServiciuBiblioteca {
    private List<Persoana> persoane; // lista pt toate Persoane - upcasting
    private Set<Sectiune> sectii; //lista sortata pt Sectiuni alfabetic
    private List<Imprumuturi> imprumuturi;
    private List<Carte> carti;

    public ServiciuBiblioteca() {
        persoane = new ArrayList<>();
        Autor autor = new Autor("Fara","Autor",0,"-");
        persoane.add(autor);
        sectii = new TreeSet<>(Comparator.comparing(s -> s.nume_sectiune)); //aici avem lista sortata alfabetic
        sectii.add(new Sectiune("Sectiuni diverse"));
        imprumuturi = new ArrayList<>();
        carti = new ArrayList<>();
    }
    //functie pt adaugarea persoanelor
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
            System.out.println("Angajat adaugat cu succes!");
        }
    }
    //functie pentru adaugarea unei sectiuni
    public void adauga_Sectiune(String nume_sectiune){
        Sectiune sectiune = new Sectiune(nume_sectiune);
        sectii.add(sectiune);
        System.out.println("Sectiune adaugat cu succes!");
    }

    public void afiseaza_autori(){
        int i = 0;
        for(Persoana p : persoane){
            if(p instanceof Autor){
                System.out.println(Integer.toString(i) + " ->" + p.getNume());
            }
            i++;
       }
    }

    public void afiseaza_sectiuni(){
        int i = 0;
        for(Sectiune s : sectii){
            System.out.println(Integer.toString(i) +". " +  s.getNume_sectiune());
            i++;
        }
    }

    public void adauga_Carte(String titlu, int an, int copii, int i, String sect){
        if(i >= persoane.size() || !(persoane.get(i) instanceof Autor)){
            Carte carte = new Carte((Autor) persoane.get(0),titlu,an,copii);
            carti.add(carte);
            for(Sectiune sectie : sectii){
                if(sectie.getNume_sectiune().equals(sect)){
                    sectie.adauga_c(carte);
                }
            }
        }else{
            Carte carte = new Carte((Autor) persoane.get(i),titlu,an,copii);
            carti.add(carte);
            for(Sectiune sectie : sectii){
                if(sectie.getNume_sectiune().equals(sect)){
                    sectie.adauga_c(carte);
                }
            }
        }
        System.out.println("Carte adaugata cu succes!");
    }

    public void afisare_persoane(){
        for(Persoana p : persoane){
            p.afisare_detalii();
        }
    }

    public void gasire_persoana(String np){
        for(Persoana p : persoane){
            if(p.getNume().equals(np)){
                p.afisare_detalii();
                return;
            }
        }
        System.out.println("Nu exista aceasta persoana ");
    }

    public void afiseaza_carti_din_sectiune(int s){
        List<Sectiune> sectii2 = new ArrayList<>(sectii);
        if(s < sectii2.size()){
            sectii2.get(s).afiseaza_carti();
        }
    }

    public void valabilitate_carte(String c){
        for(Carte carte : carti){
            if(carte.getTitlu().equals(c)){
                System.out.println("Exemplare disponibile : " + Integer.toString(carte.getNr_copii()));
                return;
            }
        }
        System.out.println("Nu exista cartea !");
    }

    public void active(String id){
        for(Persoana p : persoane){
            if(p instanceof Cititor){
                if(((Cititor) p).getId_citior().equals(id)){
                    for(Imprumuturi i : imprumuturi){
                        if(i.getCititor().getId_citior().equals(id) && i.getStare()){
                            System.out.println("->"+i.toString());
                        }
                    }
                }
            }
        }
    }
}
