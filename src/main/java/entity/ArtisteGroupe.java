package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "artiste_groupe", schema = "new_schema", catalog = "")
public class ArtisteGroupe {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ag_id")
    private long agId;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "annee_sortie")
    private Date anneeSortie;

    public long getAgId() {
        return agId;
    }

    public void setAgId(long agId) {
        this.agId = agId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(Date anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtisteGroupe that = (ArtisteGroupe) o;
        return agId == that.agId && Objects.equals(nom, that.nom) && Objects.equals(anneeSortie, that.anneeSortie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agId, nom, anneeSortie);
    }
}
