package entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(name = "BOBy", query = "SELECT b FROM Bo b where b.titreFilm=?1")
@NamedQuery(name = "BOAll", query = "SELECT b FROM Bo b")
@Entity
public class Bo {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "musique_id" , insertable = false, updatable = false)
    private long musiqueId;
    @Basic
    @Column(name = "titre_film")
    private String titreFilm;
    @ManyToOne
    @JoinColumn(name = "musique_id", referencedColumnName = "id", nullable = false)
    private Musique musiqueByMusiqueId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMusiqueId() {
        return musiqueId;
    }

    public void setMusiqueId(long musiqueId) {
        this.musiqueId = musiqueId;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bo bo = (Bo) o;
        return id == bo.id && musiqueId == bo.musiqueId && Objects.equals(titreFilm, bo.titreFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musiqueId, titreFilm);
    }

    public Musique getMusiqueByMusiqueId() {
        return musiqueByMusiqueId;
    }

    public void setMusiqueByMusiqueId(Musique musiqueByMusiqueId) {
        this.musiqueByMusiqueId = musiqueByMusiqueId;
    }

    @Override
    public String toString() {
        return "Bo{" +
                "id=" + id +
                ", musiqueId=" + musiqueId +
                ", titreFilm='" + titreFilm + '\'' +
                ", musiqueByMusiqueId=" + musiqueByMusiqueId +
                '}';
    }
}
