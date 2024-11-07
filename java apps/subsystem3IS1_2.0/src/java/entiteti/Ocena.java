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
@Table(name = "ocena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocena.findAll", query = "SELECT o FROM Ocena o"),
    @NamedQuery(name = "Ocena.findByOcenaId", query = "SELECT o FROM Ocena o WHERE o.ocenaId = :ocenaId"),
    @NamedQuery(name = "Ocena.findByDatumOcene", query = "SELECT o FROM Ocena o WHERE o.datumOcene = :datumOcene"),
    @NamedQuery(name = "Ocena.findByVremeOcene", query = "SELECT o FROM Ocena o WHERE o.vremeOcene = :vremeOcene"),
    @NamedQuery(name = "Ocena.findByOcena", query = "SELECT o FROM Ocena o WHERE o.ocena = :ocena")})
public class Ocena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ocenaId")
    private Integer ocenaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumOcene")
    @Temporal(TemporalType.DATE)
    private Date datumOcene;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremeOcene")
    private int vremeOcene;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocena")
    private int ocena;
    @JoinColumn(name = "korisnikId", referencedColumnName = "korisnikId")
    @ManyToOne(optional = false)
    private Korisnik korisnikId;
    @JoinColumn(name = "videosnimakId", referencedColumnName = "videoSnimakId")
    @ManyToOne(optional = false)
    private Videosnimak videosnimakId;

    public Ocena() {
    }

    public Ocena(Integer ocenaId) {
        this.ocenaId = ocenaId;
    }

    public Ocena(Integer ocenaId, Date datumOcene, int vremeOcene, int ocena) {
        this.ocenaId = ocenaId;
        this.datumOcene = datumOcene;
        this.vremeOcene = vremeOcene;
        this.ocena = ocena;
    }

    public Integer getOcenaId() {
        return ocenaId;
    }

    public void setOcenaId(Integer ocenaId) {
        this.ocenaId = ocenaId;
    }

    public Date getDatumOcene() {
        return datumOcene;
    }

    public void setDatumOcene(Date datumOcene) {
        this.datumOcene = datumOcene;
    }

    public int getVremeOcene() {
        return vremeOcene;
    }

    public void setVremeOcene(int vremeOcene) {
        this.vremeOcene = vremeOcene;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Videosnimak getVideosnimakId() {
        return videosnimakId;
    }

    public void setVideosnimakId(Videosnimak videosnimakId) {
        this.videosnimakId = videosnimakId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocenaId != null ? ocenaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocena)) {
            return false;
        }
        Ocena other = (Ocena) object;
        if ((this.ocenaId == null && other.ocenaId != null) || (this.ocenaId != null && !this.ocenaId.equals(other.ocenaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Ocena[ ocenaId=" + ocenaId + " ]";
    }
    
}
