import entity.ArtisteGroupe;
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
            ArtisteGroupe artisteGroupe = new ArtisteGroupe();
            artisteGroupe.setAgId(8);

//            Date utilDate = new Date();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            artisteGroupe.setNom("ACDC");
            entityManager.merge(artisteGroupe);
            TypedQuery<Bo> boByQuery = entityManager.createNamedQuery("BOBy", Bo.class);
            boByQuery.setParameter(1,"live");
            for (Bo b :boByQuery.getResultList()){
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
