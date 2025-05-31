import java.sql.*;
import java.util.*;


public class ServiciuBiblioteca {
    private List<Persoana> persoane; // lista pt toate Persoane - upcasting
    private Set<Sectiune> sectii; //lista sortata pt Sectiuni alfabetic
    private List<Imprumuturi> imprumuturi;
    private List<Carte> carti;
    private List<Recenzie> recenzii;

    public ServiciuBiblioteca() {
        persoane = new ArrayList<>();
        Autor autor1 = new Autor("Fara","Autor",0,"-");
        persoane.add(autor1);
        sectii = new TreeSet<>(Comparator.comparing(s -> s.nume_sectiune)); //aici avem lista sortata alfabetic
        sectii.add(new Sectiune("Sectiuni diverse"));
        imprumuturi = new ArrayList<>();
        carti = new ArrayList<>();
        recenzii = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            //PENTRU AUTORI
            ResultSet rsAutori = statement.executeQuery(
                    "SELECT nume, prenume, data_nasterii, tip_autor FROM Autor"
            );
            while (rsAutori.next()) {
                String nume = rsAutori.getString("nume");
                String prenume = rsAutori.getString("prenume");
                int data = rsAutori.getInt("data_nasterii");
                String tip = rsAutori.getString("tip_autor");

                Autor autor = new Autor(nume, prenume, data, tip);
                persoane.add(autor);
            }
            rsAutori.close();
            Audit.getAudit().actiune("s-au citit autorii");

            // PENTRU ANGAJATI
            ResultSet rsAngajati = statement.executeQuery(
                    "SELECT nume, prenume, data_nasterii, tip_angajat FROM Angajat"
            );
            while (rsAngajati.next()) {
                String nume = rsAngajati.getString("nume");
                String prenume = rsAngajati.getString("prenume");
                int data = rsAngajati.getInt("data_nasterii");
                String tip = rsAngajati.getString("tip_angajat");

                Angajat angajat = new Angajat(nume, prenume, data, tip);
                persoane.add(angajat);
            }
            rsAngajati.close();
            Audit.getAudit().actiune("s-au citit angajatii");

            // PENTRU CITITORI
            ResultSet rsCititori = statement.executeQuery(
                    "SELECT id_cititor, nume, prenume, data_nasterii FROM Cititor"
            );
            while (rsCititori.next()) {
                String idCititor = rsCititori.getString("id_cititor");
                String nume = rsCititori.getString("nume");
                String prenume = rsCititori.getString("prenume");
                int data = rsCititori.getInt("data_nasterii");

                Cititor cititor = new Cititor(nume, prenume, data, idCititor);
                persoane.add(cititor);
            }
            rsCititori.close();
            Audit.getAudit().actiune("s-au citit cititorii");

            // PENTRU CARTI
            ResultSet rsCarti = statement.executeQuery(
                    "SELECT nume_autor, titlu, an_publicatie, nr_copii, sectiune FROM Carte"
            );
            while (rsCarti.next()) {
                String autorIndex = rsCarti.getString("nume_autor");
                String titlu = rsCarti.getString("titlu");
                int an = rsCarti.getInt("an_publicatie");
                int copii = rsCarti.getInt("nr_copii");
                String numeSectiune = rsCarti.getString("sectiune");


                Carte carte = new Carte(autorIndex, titlu, an, copii,numeSectiune);
                carti.add(carte);


                boolean sectiuneGasita = false;
                for (Sectiune s : sectii) {
                    if (s.getNume_sectiune().equalsIgnoreCase(numeSectiune)) {
                        s.adauga_c(carte);
                        sectiuneGasita = true;
                        break;
                    }
                }
                if (!sectiuneGasita) {
                    Sectiune sectiuneNoua = new Sectiune(numeSectiune);
                    sectiuneNoua.adauga_c(carte);
                    sectii.add(sectiuneNoua);
                }
            }
            rsCarti.close();
            Audit.getAudit().actiune("s-au citit cartile");


            //PENTRU RECENZII
            ResultSet rsRecenzii = statement.executeQuery("SELECT titlu, continut, nota FROM Recenzie");
            while (rsRecenzii.next()) {
                String titlu = rsRecenzii.getString("titlu");
                String continut = rsRecenzii.getString("continut");
                int nota = rsRecenzii.getInt("nota");

                Recenzie recenzie = new Recenzie(titlu, continut, nota);
                recenzii.add(recenzie);
            }
            rsRecenzii.close();

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //functie pt adaugarea persoanelor
    public void adauga_Persoana(int varianta, String nume, String prenume, int data_nasterii, String specificatie) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "";
        if (varianta == 1) {
            sql = "INSERT INTO Autor (nume, prenume, data_nasterii, tip_autor) VALUES (?, ?, ?, ?)";
        } else if (varianta == 2) {
            sql = "INSERT INTO Cititor (id_cititor, nume, prenume, data_nasterii) VALUES (?, ?, ?, ?)";
        } else if (varianta == 3) {
            sql = "INSERT INTO Angajat (nume, prenume, data_nasterii, tip_angajat) VALUES (?, ?, ?, ?)";
        }

        PreparedStatement pstmt = connection.prepareStatement(sql);
        if(varianta == 1 || varianta == 3) {
            pstmt.setString(1, nume);
            pstmt.setString(2, prenume);
            pstmt.setInt(3, data_nasterii);
            pstmt.setString(4, specificatie);
        }else{
            pstmt.setString(1, specificatie);
            pstmt.setString(2, nume);
            pstmt.setString(3, prenume);
            pstmt.setInt(4, data_nasterii);
        }
        Audit.getAudit().actiune("s-a adaugat o persoana in biblioteca");

       pstmt.executeUpdate();



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
            Carte carte = new Carte(persoane.get(0).getNume(),titlu,an,copii,sect);
            carti.add(carte);
            for(Sectiune sectie : sectii){
                if(sectie.getNume_sectiune().equals(sect)){
                    sectie.adauga_c(carte);
                }
            }
        }else{
            Carte carte = new Carte(persoane.get(i).getNume(),titlu,an,copii,sect);
            carti.add(carte);
            for(Sectiune sectie : sectii){
                if(sectie.getNume_sectiune().equals(sect)){
                    sectie.adauga_c(carte);
                }
            }
        }
        System.out.println("Carte adaugata cu succes!");

        try  {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Carte (nume_autor, titlu, an_publicatie, nr_copii, sectiune) VALUES (?, ?, ?, ?, ?)"
            );

            ps.setString(1, persoane.get(i).getNume());
            ps.setString(2, titlu);
            ps.setInt(3, an);
            ps.setInt(4, copii);
            ps.setString(5, sect);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Audit.getAudit().actiune("s-a adaugat o carte");
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

    public void returnare(int id){
        for(Imprumuturi i : imprumuturi){
            if(i.getCod_imprumut() == id){
                i.setStare();
                i.getCarte().copii(1);
            }
        }
        System.out.println("Carte returnata cu succes!");
    }

    public void imprumut(String id, String c){
        for(Persoana persoana : persoane){
            if(persoana instanceof Cititor){
                if(((Cititor) persoana).getId_citior().equals(id)){
                    for(Carte car : carti){
                        if(car.getTitlu().equals(c)){
                            if(car.getNr_copii() > 0){
                                imprumuturi.add(new Imprumuturi(car, (Cititor) persoana));
                                System.out.println("Imprumut cu succes!");
                                car.copii(-1);
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Eroare!");
    }

    public void adaugareRecenzie(int i, String continut, int nota){
        Recenzie recenzie = new Recenzie(carti.get(i).getTitlu(), continut, nota);
        recenzii.add(recenzie);

        System.out.println("Recenzie adaugata ");

        String sql = "INSERT INTO Recenzie (titlu, continut, nota) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, recenzie.getTitlu());
            ps.setString(2, recenzie.getContinut());
            ps.setInt(3, recenzie.getNota());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Audit.getAudit().actiune("s-a adaugat recenzie");
    }

    public void afiseazaCarti(){
        int i = 0;
        for(Carte c : carti){
            System.out.println(Integer.toString(i) + "." + c.toString());
            i++;
        }
    }

    public void afiseazaRecenzii(){
        for(Recenzie r: recenzii){
            System.out.println(r.toString());
        }
    }

    public void afiseazaRecenzii2(){
        int i = 0;
        for(Recenzie r: recenzii){
            System.out.println(Integer.toString(i) + " " + r.toString());
            i = i + 1;
        }
    }

    public void stergereRecenzie(int i){
        if (i < recenzii.size()) {
            Recenzie recenzie = recenzii.get(i);

            try {
                Connection connection = DatabaseConnection.getConnection();

                PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM Recenzie WHERE titlu = ? AND continut = ? AND nota = ?"
                );
                ps.setString(1, recenzie.getTitlu());
                ps.setString(2, recenzie.getContinut());
                ps.setInt(3, recenzie.getNota());

                ps.executeUpdate();

                recenzii.remove(i);

                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Index prea mare");
        }
        Audit.getAudit().actiune("s-a sters o recenzie");
    }
}
