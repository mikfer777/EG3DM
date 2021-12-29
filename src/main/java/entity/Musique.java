package entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(name = "MusiqueAll", query = "SELECT m FROM Musique m")
@Entity
public class Musique {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "nom_groupe_ou_artiste")
    private String nomGroupeOuArtiste;
    @Basic
    @Column(name = "annee_sortie")
    private int anneeSortie;
    @Basic
    @Column(name = "titre")
    private String titre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomGroupeOuArtiste() {
        return nomGroupeOuArtiste;
    }

    public void setNomGroupeOuArtiste(String nomGroupeOuArtiste) {
        this.nomGroupeOuArtiste = nomGroupeOuArtiste;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musique musique = (Musique) o;
        return id == musique.id && anneeSortie == musique.anneeSortie && Objects.equals(nomGroupeOuArtiste, musique.nomGroupeOuArtiste) && Objects.equals(titre, musique.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomGroupeOuArtiste, anneeSortie, titre);
    }

    @Override
    public String toString() {
        return "Musique{" +
                "id=" + id +
                ", nomGroupeOuArtiste='" + nomGroupeOuArtiste + '\'' +
                ", anneeSortie=" + anneeSortie +
                ", titre='" + titre + '\'' +
                '}';
    }
}
