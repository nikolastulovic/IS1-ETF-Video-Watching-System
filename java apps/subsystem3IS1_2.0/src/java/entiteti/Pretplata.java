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
@Table(name = "pretplata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pretplata.findAll", query = "SELECT p FROM Pretplata p"),
    @NamedQuery(name = "Pretplata.findByPretplataId", query = "SELECT p FROM Pretplata p WHERE p.pretplataId = :pretplataId"),
    @NamedQuery(name = "Pretplata.findByDatumPocetka", query = "SELECT p FROM Pretplata p WHERE p.datumPocetka = :datumPocetka"),
    @NamedQuery(name = "Pretplata.findByVremePocetka", query = "SELECT p FROM Pretplata p WHERE p.vremePocetka = :vremePocetka"),
    @NamedQuery(name = "Pretplata.findByCenaPretplate", query = "SELECT p FROM Pretplata p WHERE p.cenaPretplate = :cenaPretplate")})
public class Pretplata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pretplataId")
    private Integer pretplataId;
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
    @Column(name = "cenaPretplate")
    private int cenaPretplate;
    @JoinColumn(name = "korisnikId", referencedColumnName = "korisnikId")
    @ManyToOne(optional = false)
    private Korisnik korisnikId;
    @JoinColumn(name = "paketId", referencedColumnName = "paketId")
    @ManyToOne(optional = false)
    private Paket paketId;

    public Pretplata() {
    }

    public Pretplata(Integer pretplataId) {
        this.pretplataId = pretplataId;
    }

    public Pretplata(Integer pretplataId, Date datumPocetka, int vremePocetka, int cenaPretplate) {
        this.pretplataId = pretplataId;
        this.datumPocetka = datumPocetka;
        this.vremePocetka = vremePocetka;
        this.cenaPretplate = cenaPretplate;
    }

    public Integer getPretplataId() {
        return pretplataId;
    }

    public void setPretplataId(Integer pretplataId) {
        this.pretplataId = pretplataId;
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

    public int getCenaPretplate() {
        return cenaPretplate;
    }

    public void setCenaPretplate(int cenaPretplate) {
        this.cenaPretplate = cenaPretplate;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Paket getPaketId() {
        return paketId;
    }

    public void setPaketId(Paket paketId) {
        this.paketId = paketId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pretplataId != null ? pretplataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pretplata)) {
            return false;
        }
        Pretplata other = (Pretplata) object;
        if ((this.pretplataId == null && other.pretplataId != null) || (this.pretplataId != null && !this.pretplataId.equals(other.pretplataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Pretplata[ pretplataId=" + pretplataId + " ]";
    }
    
}
