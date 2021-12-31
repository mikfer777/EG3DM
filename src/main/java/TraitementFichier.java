import entity.Live;
import entity.Musique;
import entity.Bo;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class TraitementFichier {

    static List liste_entites = new LinkedList();

    public static void main(String[] args) {
        try {
            int musiqueId = 0;
            int boId = 0;
            int liveId = 0;
            // A. Lecture au clavier du chemin du fichier texte BO & Live
            Scanner console = new Scanner(System.in);
            // C:\rootGit\EG3DM\src\test\resources\BoAndLive.txt
            System.out.print("Entrez le chemin du fichier texte: ");
            String text_filepath = console.nextLine();
            // Lire Le fichier d'entrée, si il n'existe pas sortie avec une IOException
            File file = new File(text_filepath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                // Retourner chaque élement de la ligne séparé par un ";" dans un tableau
                String[] data = line.split(";");
                // Traiter le contenu du tableau et afficher
//                for (String val : data) {
//                    System.out.println(val);
//                }
                // B: Analyser le type et vérifier l'année de sortie et stocker les instances d'entités adéquates dans une liste
                String type = data[0];
                if (type.equals("BO")) {
                    System.out.println("entrée de type:  BO");
                    // Titre BO
                    String titreBO = data[1];
                    // Nom de Groupe ou artiste
                    String nomGroupeOuArtiste = data[2];
                    // Année de sortie
                    String anneeSortie = data[3];
                    // titre film
                    String titreFilm = data[4];
                    // Vérifier l'année de sortie
                    try {
                        Integer annee = Integer.parseInt(anneeSortie);
                        // créer une entity Musique
                        musiqueId = musiqueId + 1;
                        Musique musique = new Musique();
                        musique.setId(musiqueId);
                        musique.setNomGroupeOuArtiste(nomGroupeOuArtiste);
                        musique.setAnneeSortie(annee);
                        musique.setTitre(titreBO);
                        liste_entites.add(musique);
                        // créer une entity Bo
                        boId = boId + 1;
                        Bo bo = new Bo();
                        bo.setId(boId);
                        bo.setTitreFilm(titreFilm);
                        bo.setMusiqueByMusiqueId(musique);
                        liste_entites.add(bo);

                    } catch (NumberFormatException ex) {
                        System.out.println("ERREUR: format année de sortie incorrect (doit être int)");
                    }
                } else if (type.equals("Live")) {
                    System.out.println("entrée de type: Live");
                    // Nom de Groupe ou artiste
                    String nomGroupeOuArtiste = data[1];
                    // Titre Album
                    String titreAlbum = data[2];
                    // Année de sortie
                    String anneeSortie = data[3];
                    // lieu_concert
                    String lieuConcert = data[4];
                    // Vérifier l'année de sortie
                    try {
                        int annee = Integer.parseInt(anneeSortie);
                        // créer une entity Musique
                        musiqueId = musiqueId + 1;
                        Musique musique = new Musique();
                        musique.setId(musiqueId);
                        musique.setNomGroupeOuArtiste(nomGroupeOuArtiste);
                        musique.setAnneeSortie(annee);
                        musique.setTitre(titreAlbum);
                        liste_entites.add(musique);
                        // créer une entity Live
                        liveId = liveId + 1;
                        Live live = new Live();
                        live.setId(liveId);
                        live.setLieuConcert(lieuConcert);
                        live.setMusiqueByMusiqueId(musique);
                        liste_entites.add(live);
                    } catch (NumberFormatException ex) {
                        System.out.println("ERREUR: format année de sortie incorrect (doit être int)");
                    }
                } else {
                    System.out.println("ERREUR: type d'entrée fichier non reconnu");
                }
            }
            br.close();
            fr.close();
        } catch (
                IOException e) {
            System.out.println("ERREUR: fichier non trouvé");
//            e.printStackTrace();
        }
        // C: Affichage de la liste obtenue
        for (int i = 0; i < liste_entites.size(); i++) {
            System.out.println("Élément à l'index " + i + " = " + liste_entites.get(i));
        }
        // D: Inserer ou mettre à jour les entités en BD
        Insertion_entites();
        // Relire les entités Musique Bo et Live et les afficher
        Lecture_entites();

    }


    /**
     *
     */
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

    /**
     *
     */
    private static void Lecture_entites() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Lecture des BO
            TypedQuery<Bo> boByQuery1 = entityManager.createNamedQuery("BOAll", Bo.class);
            for (Bo b : boByQuery1.getResultList()) {
                System.out.println(b);
            }
            // Lecture des Live
            TypedQuery<Live> boByQuery2 = entityManager.createNamedQuery("LiveAll", Live.class);
            for (Live l : boByQuery2.getResultList()) {
                System.out.println(l);
            }
            // Lecture des Musique
            TypedQuery<Musique> boByQuery3 = entityManager.createNamedQuery("MusiqueAll", Musique.class);
            for (Musique m : boByQuery3.getResultList()) {
                System.out.println(m);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
