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
    @Column(name = "lieu_concert")
    private String lieuConcert;
    @Basic
    @Column(name = "musique_id", insertable = false, updatable = false)
    private Long musiqueId;
    @ManyToOne
    @JoinColumn(name = "musique_id", referencedColumnName = "id")
    private Musique musiqueByMusiqueId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLieuConcert() {
        return lieuConcert;
    }

    public void setLieuConcert(String lieuConcert) {
        this.lieuConcert = lieuConcert;
    }

    public Long getMusiqueId() {
        return musiqueId;
    }

    public void setMusiqueId(Long musiqueId) {
        this.musiqueId = musiqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Live live = (Live) o;
        return id == live.id && Objects.equals(lieuConcert, live.lieuConcert) && Objects.equals(musiqueId, live.musiqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lieuConcert, musiqueId);
    }

    public Musique getMusiqueByMusiqueId() {
        return musiqueByMusiqueId;
    }

    public void setMusiqueByMusiqueId(Musique musiqueByMusiqueId) {
        this.musiqueByMusiqueId = musiqueByMusiqueId;
    }
}
