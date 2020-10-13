import javax.lang.model.element.Element;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class main {
    public static void lireFichier() {
        BufferedReader reader = null;
        try {
            FileInputStream file = new FileInputStream(new File("sales.csv"));
            reader = new BufferedReader(new InputStreamReader(file));
            String ligne = reader.readLine();
            while (ligne != null) {
                System.out.println(ligne);
                ligne = reader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TrouverPosition(int numeroCom) {
        BufferedReader reader = null;
        try {
            FileInputStream file = new FileInputStream(new File("sales.csv"));
            reader = new BufferedReader(new InputStreamReader(file));
            String ligne = reader.readLine();
            while (ligne != null) {
                String charsquence = Integer.toString(numeroCom);
                if (ligne.contains(charsquence)) {
                    System.out.println(ligne);
                }
                ligne = reader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TrouverCommandDate(String date, String type) {
        BufferedReader reader = null;
        String ligne = "";
        String cvsSplitBy = ",";
        try {
            FileInputStream file = new FileInputStream(new File("sales.csv"));
            reader = new BufferedReader(new InputStreamReader(file));
            int columnIndexOfOrderDate = 5;
            int columnIndexOfItemType = 2;
            while ((ligne = reader.readLine()) != null) {
                String[] cols = ligne.split(cvsSplitBy);
                if(cols[columnIndexOfOrderDate].contains(date) && cols[columnIndexOfItemType].contains(type)){
                    System.out.println(ligne);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> TrouverMemeCommande(String orderId) {
        BufferedReader reader = null;
        String ligne = "";
        String cvsSplitBy = ",";
        ArrayList<String> data = new ArrayList<>();
        int columnIndexOfOrderId = 6;
        int columnIndexOfDate = 5;
        int columnIndexOfType = 2;
        String date = "";
        String type = "";
        try {
            FileInputStream file = new FileInputStream(new File("sales.csv"));
            reader = new BufferedReader(new InputStreamReader(file));
            while ((ligne = reader.readLine()) != null) {
               String[] cols = ligne.split(cvsSplitBy);
               if (cols[columnIndexOfOrderId].contains(orderId)) {
                   date = cols[columnIndexOfDate];
                   type = cols[columnIndexOfType];
                   reader.close();
                   file.close();
                   file = new FileInputStream(new File("sales.csv"));
                   reader = new BufferedReader(new InputStreamReader(file));
                   while ((ligne = reader.readLine()) != null) {
                       cols = ligne.split(cvsSplitBy);
                       if (cols[columnIndexOfDate].contains(date) && cols[columnIndexOfType].contains(type)) {
                           data.add(ligne);
                       }
                   }
                   reader.close();
                   file.close();
                   break;
               }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void AfficherPositionVentesMemeItemJour(String orderId) {
        int positionOrder = 1;
        ArrayList<String> data = TrouverMemeCommande(orderId);
        System.out.println("Order id: " + orderId);
        System.out.println("======================");
        for (String s : data) {
            if (s.contains(orderId)) {
                break;
            }
            positionOrder++;
        }
        System.out.println("Position pour un item du meme type pour la meme journee: " + positionOrder + "/" + data.size());
    }

    public static Integer getPremierSinonDeuxieme(Element<T> liste1, Element<T> liste2, int i) {
        if (<T> liste1[i] != null) {
            return <T> liste1[i];
        }
        else if (<T> liste2[i] != null) {
            return <T> liste2[i];
        }
        return null;
    }


    public static void main(String[] args) {
        AfficherPositionVentesMemeItemJour(Integer.toString(449057424));
        AfficherPositionVentesMemeItemJour(Integer.toString(968184173));
        AfficherPositionVentesMemeItemJour(Integer.toString(211039054));
        AfficherPositionVentesMemeItemJour(Integer.toString(546093308));
        System.out.println("=======================");
        System.out.println("QUESTION 2");
        System.out.println("=======================");
        Integer[] liste1_1 = {10, null, 30, 40, 50};
        Integer[] liste1_2 = {110, 120, 130, 140, 150};
        Integer resultat = getPremierSinonDeuxieme(liste1_1, liste1_2, 2);
        System.out.println(resultat);
        Integer[] liste2_1 = {10, null, 30, 40, 50};
        Integer[] liste2_2 = {110, 120, 130, 140, 150};
        Integer resultat2 = getPremierSinonDeuxieme(liste2_1, liste2_2, 1);
        System.out.println(resultat2);
    }

    private static class T {
    }
}
