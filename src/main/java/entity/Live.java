package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Live {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "titre_album")
    private String titreAlbum;
    @Basic
    @Column(name = "lieuConcert")
    private String lieuConcert;
    @Basic
    @Column(name = "live_groupe_id" , insertable = false, updatable = false)
    private Long liveGroupeId;
    @Basic
    @Column(name = "annee_sortie")
    private Integer anneeSortie;
    @ManyToOne
    @JoinColumn(name = "live_groupe_id", referencedColumnName = "ag_id")
    private ArtisteGroupe artisteGroupeByLiveGroupeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitreAlbum() {
        return titreAlbum;
    }

    public void setTitreAlbum(String titreAlbum) {
        this.titreAlbum = titreAlbum;
    }

    public String getLieuConcert() {
        return lieuConcert;
    }

    public void setLieuConcert(String lieuConcert) {
        this.lieuConcert = lieuConcert;
    }

    public Long getLiveGroupeId() {
        return liveGroupeId;
    }

    public void setLiveGroupeId(Long liveGroupeId) {
        this.liveGroupeId = liveGroupeId;
    }

    public Integer getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(Integer anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Live live = (Live) o;
        return id == live.id && Objects.equals(titreAlbum, live.titreAlbum) && Objects.equals(lieuConcert, live.lieuConcert) && Objects.equals(liveGroupeId, live.liveGroupeId) && Objects.equals(anneeSortie, live.anneeSortie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titreAlbum, lieuConcert, liveGroupeId, anneeSortie);
    }

    public ArtisteGroupe getArtisteGroupeByLiveGroupeId() {
        return artisteGroupeByLiveGroupeId;
    }

    public void setArtisteGroupeByLiveGroupeId(ArtisteGroupe artisteGroupeByLiveGroupeId) {
        this.artisteGroupeByLiveGroupeId = artisteGroupeByLiveGroupeId;
    }
}
