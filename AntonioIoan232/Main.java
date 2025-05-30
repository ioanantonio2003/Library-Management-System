import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        //meniul interactiv al aplicatiei
        ServiciuBiblioteca serviciuBiblioteca = new ServiciuBiblioteca();
        String actiuni = "0.Inchidere aplicatie\n" + "1.Adauga o sectiune pentru carti in biblioteca\n" +
                "2.Adauga o carte in biblioteca\n" +
                "3.Adauga o persoana in biblioteca\n" +
                "4.Fa un imprumut (pt cineva)\n" +
                "5.Afiseaza toate cartile dintr-o sectiune specificata\n" +
                "6.Returneaza o carte de catre cititor\n" +
                "7.Verifica valabilitatea unei anumite carti (cartea trebuie sa existe)\n" +
                "8.Verifica imprumuturile active ale unui cititor\n" +
                "9.Afiseaza toate persoanele din biblioteca(si autorii)\n" +
                "10.Afiseaza informatii despre un anumit cititor sau angajat al bibliotecii\n"+
                "11.Adaugare recenzii \n"+
                "12.Vizualizare Recenzii";
        System.out.println("Bine ati venit in aplicatia bibliotecii noastre! Actiunile/interogarile pe care le puteti face:");
        System.out.println(actiuni);

        boolean bool = true;

        while(bool){
            System.out.println("Alegerea dvs : ");
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            scanner.nextLine();

            switch (x){
                case 0:
                    bool = false;
                    break;
                case 1:
                    String nume_sectiune;
                    System.out.println("Nume sectiune : ");
                    nume_sectiune = scanner.nextLine();
                    serviciuBiblioteca.adauga_Sectiune(nume_sectiune);
                    break;
                case 2:
                    String titlu;
                    int an, copii;
                    System.out.println("Titlu carte: ");
                    titlu = scanner.nextLine();
                    System.out.println("An publicatie : ");
                    an = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Numar copii: ");
                    copii = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Autori : ");
                    serviciuBiblioteca.afiseaza_autori();
                    int al;
                    al = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Sectiuni : ");
                    serviciuBiblioteca.afiseaza_sectiuni();
                    String sect;
                    sect = scanner.nextLine();
                    serviciuBiblioteca.adauga_Carte(titlu,an,copii,al,sect);
                    break;
                case 3:
                    String nume, prenume;
                    int data_nasterii;
                    System.out.println("Numele : ");
                    nume = scanner.nextLine();
                    System.out.println("Prenumele : ");
                    prenume = scanner.nextLine();
                    System.out.println("Data nasterii : ");
                    data_nasterii = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ce tip de persoana vreti sa adaugati :");
                    System.out.println("1-Autor.........2-Cititor........3-Angajat");
                    System.out.println("Alegerea dvs : ");
                    int y = scanner.nextInt();
                    scanner.nextLine();
                    if(y == 1){
                        String tip;
                        System.out.println("Tip autor: ");
                        tip = scanner.nextLine();
                        serviciuBiblioteca.adauga_Persoana(y,nume,prenume,data_nasterii,tip);
                    }else if(y == 2){
                        String id;
                        System.out.println("Id cititor : ");
                        id = scanner.nextLine();
                        serviciuBiblioteca.adauga_Persoana(y,nume,prenume,data_nasterii,id);
                    }else if(y == 3){
                        String tip;
                        System.out.println("Tip angajat : ");
                        tip = scanner.nextLine();
                        serviciuBiblioteca.adauga_Persoana(y,nume,prenume,data_nasterii,tip);
                    }
                    break;
                case 4:
                    System.out.println("Id cititor:");
                    String id_citi = scanner.nextLine();
                    System.out.println("Nume Carte:");
                    String nume_cart = scanner.nextLine();
                    serviciuBiblioteca.imprumut(id_citi,nume_cart);
                    break;
                case 5:
                    serviciuBiblioteca.afiseaza_sectiuni();
                    System.out.println("Sectiunea dorita : ");
                    int alegere;
                    alegere = scanner.nextInt();
                    scanner.nextLine();
                    serviciuBiblioteca.afiseaza_carti_din_sectiune(alegere);
                    break;
                case 6:
                    System.out.println("Id imprumut : ");
                    int id_i;
                    id_i = scanner.nextInt();
                    scanner.nextLine();
                    serviciuBiblioteca.returnare(id_i);
                    break;
                case 7:
                    System.out.println("Nume carte:");
                    String nume_carte;
                    nume_carte = scanner.nextLine();
                    serviciuBiblioteca.valabilitate_carte(nume_carte);
                    break;
                case 8:
                    System.out.println("Id cititor: ");
                    String id_cit;
                    id_cit = scanner.nextLine();
                    serviciuBiblioteca.active(id_cit);
                    break;
                case 9:
                    serviciuBiblioteca.afisare_persoane();
                    break;
                case 10:
                    String nume_prenume;
                    System.out.println("Numele persoanei pe care o cautati: ");
                    nume_prenume = scanner.nextLine();
                    serviciuBiblioteca.gasire_persoana(nume_prenume);
                    break;
                case 11:
                    String continut;
                    int ind, nota;
                    System.out.println("Carti existente");
                    serviciuBiblioteca.afiseazaCarti();
                    System.out.println("Alegere: ");
                    ind = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Recenzie: ");
                    continut = scanner.nextLine();
                    System.out.println("Nota: ");
                    nota = scanner.nextInt();
                    scanner.nextLine();
                    serviciuBiblioteca.adaugareRecenzie(ind,continut,nota);
                    break;
                case 12:
                    serviciuBiblioteca.afiseazaRecenzii();
                    break;
            }
        }
    }
}