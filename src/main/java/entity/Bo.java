package entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(name = "BOBy", query = "SELECT b FROM Bo b where b.titreBo=?1")
@Entity
public class Bo {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "titre_film")
    private String titreFilm;
    @Basic
    @Column(name = "titre_bo")
    private String titreBo;
    @Basic
    @Column(name = "bo_groupe_id" , insertable = false, updatable = false)
    private long boGroupeId;
    @ManyToOne
    @JoinColumn(name = "bo_groupe_id", referencedColumnName = "ag_id", nullable = false)
    private ArtisteGroupe artisteGroupeByBoGroupeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public String getTitreBo() {
        return titreBo;
    }

    public void setTitreBo(String titreBo) {
        this.titreBo = titreBo;
    }

    public long getBoGroupeId() {
        return boGroupeId;
    }

    public void setBoGroupeId(long boGroupeId) {
        this.boGroupeId = boGroupeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bo bo = (Bo) o;
        return id == bo.id && boGroupeId == bo.boGroupeId && Objects.equals(titreFilm, bo.titreFilm) && Objects.equals(titreBo, bo.titreBo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titreFilm, titreBo, boGroupeId);
    }

    public ArtisteGroupe getArtisteGroupeByBoGroupeId() {
        return artisteGroupeByBoGroupeId;
    }

    public void setArtisteGroupeByBoGroupeId(ArtisteGroupe artisteGroupeByBoGroupeId) {
        this.artisteGroupeByBoGroupeId = artisteGroupeByBoGroupeId;
    }
}
