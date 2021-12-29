import entity.Musique;
import entity.Bo;

import javax.persistence.*;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Musique musique = new Musique();
            musique.setId(1);

//            Date utilDate = new Date();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            musique.setTitre("lalala");
            musique.setNomGroupeOuArtiste("U2");
            musique.setAnneeSortie(2000);
            entityManager.merge(musique);
            TypedQuery<Bo> boByQuery = entityManager.createNamedQuery("BOBy", Bo.class);
            boByQuery.setParameter(1, "007");
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
