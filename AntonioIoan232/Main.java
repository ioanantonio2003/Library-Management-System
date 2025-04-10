import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //meniul interactiv al aplicatiei
        ServiciuBiblioteca serviciuBiblioteca = new ServiciuBiblioteca();
        String actiuni = "1.Adauga o sectiune pentru carti in biblioteca\n" +
                "2.Adauga o carte in biblioteca\n" +
                "3.Adauga o persoana in biblioteca" +
                "4.Fa un imprumut (pt cineva)\n" +
                "5.Afiseaza toate cartile dintr-o sectiune specificata\n" +
                "6.Afiseaza raportul sectiunilor (numarul de carti si imprumuturile)\n" +
                "7.Verifica valabilitatea unei anumite carti (cartea trebuie sa existe)\n" +
                "8.Verifica imprumuturile active ale unui cititor\n" +
                "9.Returneaza o carte de catre un cititor\n" +
                "10.Afiseaza informatii despre un anumit cititor sau angajat al bibliotecii";
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

                    break;
                case 2:
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

                    break;
                case 5:
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    break;
                case 9:

                    break;
                case 10:

                    break;
            }
        }


    }
}