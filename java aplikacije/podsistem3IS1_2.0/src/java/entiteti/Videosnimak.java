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
    @NamedQuery(name = "Videosnimak.findByDatumPostavke", query = "SELECT v FROM Videosnimak v WHERE v.datumPostavke = :datumPostavke"),
    @NamedQuery(name = "Videosnimak.findByVremePostavke", query = "SELECT v FROM Videosnimak v WHERE v.vremePostavke = :vremePostavke")})
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
    @Column(name = "datumPostavke")
    @Temporal(TemporalType.DATE)
    private Date datumPostavke;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePostavke")
    private int vremePostavke;
    @JoinColumn(name = "vlasnikId", referencedColumnName = "korisnikId")
    @ManyToOne(optional = false)
    private Korisnik vlasnikId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videoSnimakId")
    private List<Gledanje> gledanjeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videosnimakId")
    private List<Ocena> ocenaList;

    public Videosnimak() {
    }

    public Videosnimak(Integer videoSnimakId) {
        this.videoSnimakId = videoSnimakId;
    }

    public Videosnimak(Integer videoSnimakId, String naziv, int trajanje, Date datumPostavke, int vremePostavke) {
        this.videoSnimakId = videoSnimakId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.datumPostavke = datumPostavke;
        this.vremePostavke = vremePostavke;
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

    public Date getDatumPostavke() {
        return datumPostavke;
    }

    public void setDatumPostavke(Date datumPostavke) {
        this.datumPostavke = datumPostavke;
    }

    public int getVremePostavke() {
        return vremePostavke;
    }

    public void setVremePostavke(int vremePostavke) {
        this.vremePostavke = vremePostavke;
    }

    public Korisnik getVlasnikId() {
        return vlasnikId;
    }

    public void setVlasnikId(Korisnik vlasnikId) {
        this.vlasnikId = vlasnikId;
    }

    @XmlTransient
    public List<Gledanje> getGledanjeList() {
        return gledanjeList;
    }

    public void setGledanjeList(List<Gledanje> gledanjeList) {
        this.gledanjeList = gledanjeList;
    }

    @XmlTransient
    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public void setOcenaList(List<Ocena> ocenaList) {
        this.ocenaList = ocenaList;
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
