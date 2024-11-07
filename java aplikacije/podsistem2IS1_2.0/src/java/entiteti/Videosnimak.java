/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "videosnimak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Videosnimak.findAll", query = "SELECT v FROM Videosnimak v"),
    @NamedQuery(name = "Videosnimak.findByVideoSnimakId", query = "SELECT v FROM Videosnimak v WHERE v.videoSnimakId = :videoSnimakId"),
    @NamedQuery(name = "Videosnimak.findByNaziv", query = "SELECT v FROM Videosnimak v WHERE v.naziv = :naziv"),
    @NamedQuery(name = "Videosnimak.findByTrajanje", query = "SELECT v FROM Videosnimak v WHERE v.trajanje = :trajanje"),
    @NamedQuery(name = "Videosnimak.findByDatumPostavljanja", query = "SELECT v FROM Videosnimak v WHERE v.datumPostavljanja = :datumPostavljanja"),
    @NamedQuery(name = "Videosnimak.findByVremePostavljanja", query = "SELECT v FROM Videosnimak v WHERE v.vremePostavljanja = :vremePostavljanja")})
public class Videosnimak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "videoSnimakId")
    private Integer videoSnimakId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trajanje")
    private int trajanje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumPostavljanja")
    @Temporal(TemporalType.DATE)
    private Date datumPostavljanja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePostavljanja")
    private int vremePostavljanja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videoId")
    private List<Kategorijavideo> kategorijavideoList;
    @JoinColumn(name = "vlasnikId", referencedColumnName = "korisnikId")
    @ManyToOne(optional = false)
    private Korisnik vlasnikId;

    public Videosnimak() {
    }

    public Videosnimak(Integer videoSnimakId) {
        this.videoSnimakId = videoSnimakId;
    }

    public Videosnimak(Integer videoSnimakId, String naziv, int trajanje, Date datumPostavljanja, int vremePostavljanja) {
        this.videoSnimakId = videoSnimakId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.datumPostavljanja = datumPostavljanja;
        this.vremePostavljanja = vremePostavljanja;
    }

    public Integer getVideoSnimakId() {
        return videoSnimakId;
    }

    public void setVideoSnimakId(Integer videoSnimakId) {
        this.videoSnimakId = videoSnimakId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Date getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja(Date datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }

    public int getVremePostavljanja() {
        return vremePostavljanja;
    }

    public void setVremePostavljanja(int vremePostavljanja) {
        this.vremePostavljanja = vremePostavljanja;
    }

    @XmlTransient
    public List<Kategorijavideo> getKategorijavideoList() {
        return kategorijavideoList;
    }

    public void setKategorijavideoList(List<Kategorijavideo> kategorijavideoList) {
        this.kategorijavideoList = kategorijavideoList;
    }

    public Korisnik getVlasnikId() {
        return vlasnikId;
    }

    public void setVlasnikId(Korisnik vlasnikId) {
        this.vlasnikId = vlasnikId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (videoSnimakId != null ? videoSnimakId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Videosnimak)) {
            return false;
        }
        Videosnimak other = (Videosnimak) object;
        if ((this.videoSnimakId == null && other.videoSnimakId != null) || (this.videoSnimakId != null && !this.videoSnimakId.equals(other.videoSnimakId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Videosnimak[ videoSnimakId=" + videoSnimakId + " ]";
    }
    
}
