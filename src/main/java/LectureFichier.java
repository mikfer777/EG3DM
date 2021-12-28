import com.mysql.cj.xdevapi.DeleteStatement;
import entity.ArtisteGroupe;
import entity.Bo;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;


import entity.ArtisteGroupe;
import entity.Bo;

import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;


public class LectureFichier {
    public static void main(String[] args) {
        try {
            // A. Lecture au clavier du chemin du fichier texte BO & Live
//            Scanner console = new Scanner(System.in);
            // C:\rootGit\EG3DM\src\test\resources\BoAndLive.txt
            System.out.print("Entrez le chemin du fichier texte: ");
//            String text_filepath = console.nextLine();
            // Lire Le fichier d'entrée, si il n'existe pas sortie avec une IOException
            String text_filepath = "C:\\rootGit\\EG3DM\\src\\test\\resources\\BoAndLive.txt";
            File file = new File(text_filepath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
//            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                // Retourner chaque élement de la ligne séparé par un ";" dans un tableau
                String[] data = line.split(";");
                // Traiter le contenu du tableau et afficher
                for (String val : data) {
                    System.out.println(val);
                }
                // Analyser le type
                String type = data[0];

                if (type.equals("BO")) {
                    System.out.println("c'est une BO");
                } else if (type.equals("Live")) {
                    System.out.println("c'est un Live");
                } else{
                    System.out.println("type non reconnu");
                }
            }
            br.close();
            fr.close();
//            System.out.println("Contenu du fichier: ");
//            System.out.println(sb.toString());
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }


    private static void Insertion() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            ArtisteGroupe artisteGroupe = new ArtisteGroupe();
            artisteGroupe.setAgId(8);

//            Date utilDate = new Date();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//            artisteGroupe.setAnneeSortie(sqlDate);
            artisteGroupe.setNom("ACDC");
            entityManager.merge(artisteGroupe);
            TypedQuery<Bo> boByQuery = entityManager.createNamedQuery("BOBy", Bo.class);
            boByQuery.setParameter(1, "live");
            for (Bo b : boByQuery.getResultList()) {
                System.out.println(b);
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
