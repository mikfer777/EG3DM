import entity.ArtisteGroupe;
import entity.Bo;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class LectureFichier {

    static List liste_entites = new LinkedList();

    public static void main(String[] args) {
        try {
            int artiste_groupe_id = 0;
            int bo_id = 0;
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
//                for (String val : data) {
//                    System.out.println(val);
//                }
                // B: Analyser le type et vérifier l'année de sortie et stocker les instances d'entités adéquates
                String type = data[0];
                if (type.equals("BO")) {
                    System.out.println("entrée de type:  BO");
                    // Nom de Groupe ou artiste
                    String titre_bo = data[1];
                    // Titre BO
                    String groupe_artiste = data[2];
                    // Année de sortie
                    String annee_sortie = data[3];
                    // titre film
                    String titre_film = data[4];
                    // Vérifier l'année de sortie
                    try {
                        Integer annee = Integer.parseInt(annee_sortie);
                        // créer une entity artiste_groupe
                        artiste_groupe_id = artiste_groupe_id + 1;
                        ArtisteGroupe artisteGroupe = new ArtisteGroupe();
                        artisteGroupe.setAgId(artiste_groupe_id);
                        artisteGroupe.setNom(groupe_artiste);
                        liste_entites.add(artisteGroupe);
                        // créer une entity Bo
                        bo_id = bo_id + 1;
                        Bo bo = new Bo();
                        bo.setId(bo_id);
                        bo.setAnneeSortie(annee);
                        bo.setTitreBo(titre_bo);
                        bo.setTitreFilm(titre_film);
                        bo.setArtisteGroupeByBoGroupeId(artisteGroupe);
                        liste_entites.add(bo);

                    } catch (NumberFormatException ex) {
                        System.out.println("ERREUR: format année de sortie incorrect (doit être int)");
                    }
                } else if (type.equals("Live")) {
                    System.out.println("entrée de type: Live");
                    // Nom de Groupe ou artiste
                    String titre_album = data[1];
                    // Titre Album
                    String groupe_artiste = data[2];
                    // Année de sortie
                    String annee_sortie = data[3];
                    // lieu_concert
                    String lieu_concert = data[4];
                    // Vérifier l'année de sortie
                    try {
                        int annee = Integer.parseInt(annee_sortie);


                    } catch (NumberFormatException ex) {
                        System.out.println("ERREUR: format année de sortie incorrect (doit être int)");
                    }
                } else {
                    System.out.println("ERREUR: type d'entrée fichier non reconnu");
                }
            }
            br.close();
            fr.close();
//            System.out.println("Contenu du fichier: ");
//            System.out.println(sb.toString());
        } catch (
                IOException e) {
            System.out.println("ERREUR: fichier non trouvé");
//            e.printStackTrace();
        }
        // C: Affichage de la liste obtenue
        for (int i = 0; i < liste_entites.size(); i++) {
            System.out.println("Élément à l'index " + i + " = " + liste_entites.get(i));
        }
        // D: Stocker les entités en BD
        Insertion_entites();
        // Relire les Bo et Live et les afficher

    }

    private static boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    private static void Insertion_entites() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            for (int i = 0; i < liste_entites.size(); i++) {
                entityManager.merge(liste_entites.get(i));
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
