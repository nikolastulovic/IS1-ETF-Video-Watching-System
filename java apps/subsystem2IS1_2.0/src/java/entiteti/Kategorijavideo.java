/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "kategorijavideo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorijavideo.findAll", query = "SELECT k FROM Kategorijavideo k"),
    @NamedQuery(name = "Kategorijavideo.findByKategorijaVideoId", query = "SELECT k FROM Kategorijavideo k WHERE k.kategorijaVideoId = :kategorijaVideoId")})
public class Kategorijavideo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kategorijaVideoId")
    private Integer kategorijaVideoId;
    @JoinColumn(name = "kategorijaId", referencedColumnName = "kategorijaId")
    @ManyToOne(optional = false)
    private Kategorija kategorijaId;
    @JoinColumn(name = "videoId", referencedColumnName = "videoSnimakId")
    @ManyToOne(optional = false)
    private Videosnimak videoId;

    public Kategorijavideo() {
    }

    public Kategorijavideo(Integer kategorijaVideoId) {
        this.kategorijaVideoId = kategorijaVideoId;
    }

    public Integer getKategorijaVideoId() {
        return kategorijaVideoId;
    }

    public void setKategorijaVideoId(Integer kategorijaVideoId) {
        this.kategorijaVideoId = kategorijaVideoId;
    }

    public Kategorija getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Kategorija kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public Videosnimak getVideoId() {
        return videoId;
    }

    public void setVideoId(Videosnimak videoId) {
        this.videoId = videoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kategorijaVideoId != null ? kategorijaVideoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorijavideo)) {
            return false;
        }
        Kategorijavideo other = (Kategorijavideo) object;
        if ((this.kategorijaVideoId == null && other.kategorijaVideoId != null) || (this.kategorijaVideoId != null && !this.kategorijaVideoId.equals(other.kategorijaVideoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Kategorijavideo[ kategorijaVideoId=" + kategorijaVideoId + " ]";
    }
    
}
