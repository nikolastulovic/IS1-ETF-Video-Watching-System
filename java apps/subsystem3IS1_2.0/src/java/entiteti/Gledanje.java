/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "gledanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gledanje.findAll", query = "SELECT g FROM Gledanje g"),
    @NamedQuery(name = "Gledanje.findByGledanjeId", query = "SELECT g FROM Gledanje g WHERE g.gledanjeId = :gledanjeId"),
    @NamedQuery(name = "Gledanje.findByDatumPocetka", query = "SELECT g FROM Gledanje g WHERE g.datumPocetka = :datumPocetka"),
    @NamedQuery(name = "Gledanje.findByVremePocetka", query = "SELECT g FROM Gledanje g WHERE g.vremePocetka = :vremePocetka"),
    @NamedQuery(name = "Gledanje.findBySekundPocetkaSnimka", query = "SELECT g FROM Gledanje g WHERE g.sekundPocetkaSnimka = :sekundPocetkaSnimka"),
    @NamedQuery(name = "Gledanje.findBySekundeOdgledano", query = "SELECT g FROM Gledanje g WHERE g.sekundeOdgledano = :sekundeOdgledano")})
public class Gledanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gledanjeId")
    private Integer gledanjeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumPocetka")
    @Temporal(TemporalType.DATE)
    private Date datumPocetka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePocetka")
    private int vremePocetka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sekundPocetkaSnimka")
    private int sekundPocetkaSnimka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sekundeOdgledano")
    private int sekundeOdgledano;
    @JoinColumn(name = "korisnikId", referencedColumnName = "korisnikId")
    @ManyToOne(optional = false)
    private Korisnik korisnikId;
    @JoinColumn(name = "videoSnimakId", referencedColumnName = "videoSnimakId")
    @ManyToOne(optional = false)
    private Videosnimak videoSnimakId;

    public Gledanje() {
    }

    public Gledanje(Integer gledanjeId) {
        this.gledanjeId = gledanjeId;
    }

    public Gledanje(Integer gledanjeId, Date datumPocetka, int vremePocetka, int sekundPocetkaSnimka, int sekundeOdgledano) {
        this.gledanjeId = gledanjeId;
        this.datumPocetka = datumPocetka;
        this.vremePocetka = vremePocetka;
        this.sekundPocetkaSnimka = sekundPocetkaSnimka;
        this.sekundeOdgledano = sekundeOdgledano;
    }

    public Integer getGledanjeId() {
        return gledanjeId;
    }

    public void setGledanjeId(Integer gledanjeId) {
        this.gledanjeId = gledanjeId;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public int getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(int vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public int getSekundPocetkaSnimka() {
        return sekundPocetkaSnimka;
    }

    public void setSekundPocetkaSnimka(int sekundPocetkaSnimka) {
        this.sekundPocetkaSnimka = sekundPocetkaSnimka;
    }

    public int getSekundeOdgledano() {
        return sekundeOdgledano;
    }

    public void setSekundeOdgledano(int sekundeOdgledano) {
        this.sekundeOdgledano = sekundeOdgledano;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Videosnimak getVideoSnimakId() {
        return videoSnimakId;
    }

    public void setVideoSnimakId(Videosnimak videoSnimakId) {
        this.videoSnimakId = videoSnimakId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gledanjeId != null ? gledanjeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gledanje)) {
            return false;
        }
        Gledanje other = (Gledanje) object;
        if ((this.gledanjeId == null && other.gledanjeId != null) || (this.gledanjeId != null && !this.gledanjeId.equals(other.gledanjeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Gledanje[ gledanjeId=" + gledanjeId + " ]";
    }
    
}
