/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "paket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paket.findAll", query = "SELECT p FROM Paket p"),
    @NamedQuery(name = "Paket.findByPaketId", query = "SELECT p FROM Paket p WHERE p.paketId = :paketId"),
    @NamedQuery(name = "Paket.findByNaziv", query = "SELECT p FROM Paket p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Paket.findByTrenutnaCena", query = "SELECT p FROM Paket p WHERE p.trenutnaCena = :trenutnaCena")})
public class Paket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paketId")
    private Integer paketId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trenutnaCena")
    private int trenutnaCena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paketId")
    private List<Pretplata> pretplataList;

    public Paket() {
    }

    public Paket(Integer paketId) {
        this.paketId = paketId;
    }

    public Paket(Integer paketId, String naziv, int trenutnaCena) {
        this.paketId = paketId;
        this.naziv = naziv;
        this.trenutnaCena = trenutnaCena;
    }

    public Integer getPaketId() {
        return paketId;
    }

    public void setPaketId(Integer paketId) {
        this.paketId = paketId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrenutnaCena() {
        return trenutnaCena;
    }

    public void setTrenutnaCena(int trenutnaCena) {
        this.trenutnaCena = trenutnaCena;
    }

    @XmlTransient
    public List<Pretplata> getPretplataList() {
        return pretplataList;
    }

    public void setPretplataList(List<Pretplata> pretplataList) {
        this.pretplataList = pretplataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paketId != null ? paketId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paket)) {
            return false;
        }
        Paket other = (Paket) object;
        if ((this.paketId == null && other.paketId != null) || (this.paketId != null && !this.paketId.equals(other.paketId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Paket[ paketId=" + paketId + " ]";
    }
    
}
