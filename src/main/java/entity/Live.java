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
    @Column(name = "live_groupe_id", insertable = false, updatable = false)
    private Long liveGroupeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Live live = (Live) o;
        return id == live.id && Objects.equals(titreAlbum, live.titreAlbum) && Objects.equals(lieuConcert, live.lieuConcert) && Objects.equals(liveGroupeId, live.liveGroupeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titreAlbum, lieuConcert, liveGroupeId);
    }

    public ArtisteGroupe getArtisteGroupeByLiveGroupeId() {
        return artisteGroupeByLiveGroupeId;
    }

    public void setArtisteGroupeByLiveGroupeId(ArtisteGroupe artisteGroupeByLiveGroupeId) {
        this.artisteGroupeByLiveGroupeId = artisteGroupeByLiveGroupeId;
    }
}
