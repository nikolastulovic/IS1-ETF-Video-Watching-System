/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3is1_20;

import entiteti.Gledanje;
import entiteti.Grad;
import entiteti.Korisnik;
import entiteti.Ocena;
import entiteti.Paket;
import entiteti.Pretplata;
import entiteti.Videosnimak;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author korisnik
 */
public class Podsistem3 {

    @Resource(lookup = "myConnFactory")
    private static ConnectionFactory connectionFactory;
     
     @Resource(lookup = "topicZaProjekat")
    private static Topic topic;
     @Resource(lookup = "queueZaProjekat")
    private static Queue queue;

    public void dodajKorisnika(String ime, String email, int godiste, String pol, Grad gradId) {
        Korisnik k=new Korisnik();
        k.setEmail(email);
        k.setIme(ime);
        k.setGodiste(godiste);
        k.setPol(pol);
        k.setGradId(gradId);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(k);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void dodajKorisnika(String ime, String email, int godiste, String pol, String imeGrada) {
        Korisnik k=new Korisnik();
        k.setEmail(email);
        k.setIme(ime);
        k.setGodiste(godiste);
        k.setPol(pol);
        k.setGradId(dohvatiIKreirajAkoNemaGrad(imeGrada));
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(k);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void dodajGrad(String name) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            
            Grad g=new Grad();
            g.setNaziv(name);
            
            transaction.begin();
            em.persist(g);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public Grad dohvatiGrad(String naziv){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Grad g WHERE g.naziv = :naziv", Grad.class);
        query.setParameter("naziv", naziv);
        List<Grad> gradovi = query.getResultList();
        em.close();
        emf.close();
        if (!gradovi.isEmpty()) {
            return gradovi.get(0);
        }
        return null;
    }
    public Korisnik dohvatiKorisnika(Integer id){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT k FROM Korisnik k WHERE k.korisnikId = :id", Korisnik.class);
        query.setParameter("id", id);
        List<Korisnik> korisnici = query.getResultList();
        em.close();
        emf.close();
        if (!korisnici.isEmpty()) {
            return korisnici.get(0);
        }
        return null;
    }
    public Grad dohvatiIKreirajAkoNemaGrad(String naziv){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Grad g WHERE g.naziv = :naziv", Grad.class);
        query.setParameter("naziv", naziv);
        List<Grad> gradovi = query.getResultList();
        em.close();
        emf.close();
        if (!gradovi.isEmpty()) {
            return gradovi.get(0);
        }
        else{
            dodajGrad(naziv);
            return dohvatiGrad(naziv);
        }
    }
    public List<Grad> dohvatiSveGradove() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Grad g", Grad.class);
        List<Grad> gradovi = query.getResultList();
        em.close();
        emf.close();
        return gradovi;
    }
    public List<Korisnik> dohvatiSveKorisnike() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT k FROM Korisnik k", Korisnik.class);
        List<Korisnik> korisnici = query.getResultList();
        em.close();
        emf.close();
        return korisnici;
    }
    public void promeniEmailZaKorisnika(int korisnikId, String noviEmail) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Korisnik korisnik = em.find(Korisnik.class, korisnikId);
            
            if (korisnik != null) {
                korisnik.setEmail(noviEmail);
                em.merge(korisnik);
                
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("Korisnik sa ID=" + korisnikId + " ne postoji.");
            }
        }
        finally
        {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            emf.close();
        }
        
    }
    public void promeniMestoZaKorisnika(int korisnikId, String nazivMesta) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Korisnik korisnik = em.find(Korisnik.class, korisnikId);
            
            if (korisnik != null) {
                korisnik.setGradId(dohvatiIKreirajAkoNemaGrad(nazivMesta));
                em.merge(korisnik);
                
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("Korisnik sa ID=" + korisnikId + " ne postoji.");
            }
        }
        finally
        {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    public void promeniMestoZaKorisnika(int korisnikId, Grad mesto) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Korisnik korisnik = em.find(Korisnik.class, korisnikId);
            
            if (korisnik != null) {
                korisnik.setGradId(mesto);
                em.merge(korisnik);
                
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("Korisnik sa ID=" + korisnikId + " ne postoji.");
            }
        }
        finally
        {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            emf.close();
        }
        
    }
    
    public void kreirajVideoSnimak(String naziv, Integer trajanje, Korisnik vlasnik, Date datumPostavljanja, Integer vremePostavljanja){
        Videosnimak vs=new Videosnimak();
        vs.setNaziv(naziv);
        vs.setDatumPostavke(datumPostavljanja);
        vs.setVremePostavke(vremePostavljanja);
        vs.setTrajanje(trajanje);
        vs.setVlasnikId(vlasnik);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(vs);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void kreirajVideoSnimak(String naziv, Integer trajanje, Integer vlasnikId, Date datumPostavljanja, Integer vremePostavljanja){
        Korisnik vlasnik=dohvatiKorisnika(vlasnikId);
        
        Videosnimak vs=new Videosnimak();
        vs.setNaziv(naziv);
        vs.setDatumPostavke(datumPostavljanja);
        vs.setVremePostavke(vremePostavljanja);
        vs.setTrajanje(trajanje);
        vs.setVlasnikId(vlasnik);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(vs);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public List<Videosnimak> dohvatiSveVideoSnimke(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Videosnimak g", Videosnimak.class);
        List<Videosnimak> snimci = query.getResultList();
        em.close();
        emf.close();
        return snimci;
    }
    public void promeniNazivSnimka(int videoId,String noviNaziv) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Videosnimak vs = em.find(Videosnimak.class, videoId);
            
            if (vs != null) {
                vs.setNaziv(noviNaziv);
                em.merge(vs);
                
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("VideoSnimak sa ID=" + videoId + " ne postoji.");
            }
        }
        finally
        {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    public void promeniNazivSnimka(Videosnimak video,String noviNaziv){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            if (video != null) {
                video.setNaziv(noviNaziv);
                em.merge(video);
                
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("VideoSnimak sa ID=" + video + " ne postoji.");
            }
        }
        finally
        {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    public Videosnimak dohvatiVideosnimak(int videoId){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Videosnimak vs = em.find(Videosnimak.class, videoId);
        em.close();
        emf.close();
        return vs;
    }
    public void brisiVideo(Korisnik korisnik, Videosnimak vs){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            if (Objects.equals(vs.getVlasnikId().getKorisnikId(), korisnik.getKorisnikId())) {
                Query queryFirst=em.createQuery("DELETE FROM Ocena o WHERE o.videosnimakId=:videoId", Ocena.class);
                queryFirst.setParameter("videoId", vs);
                queryFirst.executeUpdate();
                
                Query querySecond=em.createQuery("DELETE FROM Gledanje g WHERE g.videoSnimakId=:videoId", Gledanje.class);
                querySecond.setParameter("videoId", vs);
                querySecond.executeUpdate();
                
                Query query = em.createQuery("DELETE FROM Videosnimak vs WHERE vs.videoSnimakId=:videoId", Videosnimak.class);
                query.setParameter("videoId", vs.getVideoSnimakId());
                query.executeUpdate();
            }
            
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            em.close();
        }
        emf.close();
    }
    public void brisiVideo(int korisnikId, Videosnimak vs){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            if (Objects.equals(vs.getVlasnikId().getKorisnikId(), korisnikId)) {
                Query queryFirst=em.createQuery("DELETE FROM Ocena o WHERE o.videosnimakId=:videoId", Ocena.class);
                queryFirst.setParameter("videoId", vs);
                queryFirst.executeUpdate();
                
                Query querySecond=em.createQuery("DELETE FROM Gledanje g WHERE g.videoSnimakId=:videoId", Gledanje.class);
                querySecond.setParameter("videoId", vs);
                querySecond.executeUpdate();
                
                Query query = em.createQuery("DELETE FROM Videosnimak vs WHERE vs.videoSnimakId=:videoId", Videosnimak.class);
                query.setParameter("videoId", vs.getVideoSnimakId());
                query.executeUpdate();
            }
            
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            em.close();
        }
        emf.close();
    }
    public void brisiVideo(int korisnikId, int videoId){
        Videosnimak vs=dohvatiVideosnimak(videoId);
        if(vs==null) return;
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            if (Objects.equals(vs.getVlasnikId().getKorisnikId(), korisnikId)) {
                Query queryFirst=em.createQuery("DELETE FROM Ocena o WHERE o.videosnimakId=:videoId", Ocena.class);
                queryFirst.setParameter("videoId", vs);
                queryFirst.executeUpdate();
                
                Query querySecond=em.createQuery("DELETE FROM Gledanje g WHERE g.videoSnimakId=:videoId", Gledanje.class);
                querySecond.setParameter("videoId", vs);
                querySecond.executeUpdate();
                
                Query query = em.createQuery("DELETE FROM Videosnimak vs WHERE vs.videoSnimakId=:videoId", Videosnimak.class);
                query.setParameter("videoId", vs.getVideoSnimakId());
                query.executeUpdate();
            }
            
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            em.close();
        }
        emf.close();
    }
    
    public void kreirajPaket(String naziv, int trenutnaCena) {
        Paket pak=new Paket();
        pak.setNaziv(naziv);
        pak.setTrenutnaCena(trenutnaCena);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(pak);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public Paket dohvatiPaket(int pakId){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Paket p WHERE p.paketId = :pakId", Paket.class);
        query.setParameter("pakId", pakId);
        List<Paket> paketi = query.getResultList();
        em.close();
        emf.close();
        if (!paketi.isEmpty()) {
            return paketi.get(0);
        }
        return null;
    }
    public List<Paket> dohvatiSvePakete() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Paket p", Paket.class);
        List<Paket> paketi = query.getResultList();
        em.close();
        emf.close();
        return paketi;
    }
    public void promenaCenePaketa(int paketId, int novaCena){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Paket pak = em.find(Paket.class, paketId);
            
            if (pak != null) {
                pak.setTrenutnaCena(novaCena);
                em.merge(pak);
                
                transaction.commit();
            } else {
                transaction.rollback();
                System.out.println("VideoSnimak sa ID=" + paketId + " ne postoji.");
            }
        }
        finally
        {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
            emf.close();
        }
    }
    public void kreiranjePretplateNaPaket(Korisnik kor, Paket pak, Date datumPocetka,int vremePocetka){
        Pretplata pr=new Pretplata();
        pr.setCenaPretplate(pak.getTrenutnaCena());
        pr.setDatumPocetka(datumPocetka);
        pr.setKorisnikId(kor);
        pr.setPaketId(pak);
        pr.setVremePocetka(vremePocetka);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(pr);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void kreiranjePretplateNaPaket(Integer korId, Integer pakId, Date datumPocetka,int vremePocetka){
        Korisnik kor=dohvatiKorisnika(korId);
        Paket pak=dohvatiPaket(pakId);
        if(kor==null||pak==null) return;
        
        Pretplata pr=new Pretplata();
        pr.setCenaPretplate(pak.getTrenutnaCena());
        pr.setDatumPocetka(datumPocetka);
        pr.setKorisnikId(kor);
        pr.setPaketId(pak);
        pr.setVremePocetka(vremePocetka);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        //provera postoji li vec
        Query query = em.createQuery("SELECT p FROM Pretplata p WHERE p.korisnikId = :kor and p.paketId=:pak", Pretplata.class);
        query.setParameter("kor", kor);
        query.setParameter("pak", pak);
        List<Pretplata> pretplate = query.getResultList();
        if (!pretplate.isEmpty())
        {
            em.close();
            emf.close();
            return;
        }
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(pr);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public List<Pretplata> dohvatiPretplateZaKorisnika(Korisnik kor) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Pretplata p WHERE p.korisnikId = :id", Pretplata.class);
        query.setParameter("id", kor);
        List<Pretplata> pretplate = query.getResultList();
        em.close();
        emf.close();
        if (!pretplate.isEmpty()) {
            return pretplate;
        }
        return null;
    }
    public List<Pretplata> dohvatiPretplateZaKorisnika(Integer korId) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Pretplata p WHERE p.korisnikId = :id", Pretplata.class);
        query.setParameter("id", dohvatiKorisnika(korId));
        List<Pretplata> pretplate = query.getResultList();
        em.close();
        emf.close();
        if (!pretplate.isEmpty()) {
            return pretplate;
        }
        return null;
    }
    public void kreirajGledanje(Korisnik kor, Videosnimak vs, Date datumPocetka, Integer vremePocetka, Integer sekPocetka, Integer sekOdgledano){
        Gledanje gled=new Gledanje();
        gled.setKorisnikId(kor);
        gled.setVideoSnimakId(vs);
        gled.setDatumPocetka(datumPocetka);
        gled.setVremePocetka(vremePocetka);
        gled.setSekundPocetkaSnimka(sekPocetka);
        gled.setSekundeOdgledano(sekOdgledano);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(gled);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void kreirajGledanje(Integer korId, Integer videoId, Date datumPocetka, Integer vremePocetka, Integer sekPocetka, Integer sekOdgledano){
        Korisnik kor=dohvatiKorisnika(korId);
        Videosnimak vs=dohvatiVideosnimak(videoId);
        if(kor==null||vs==null) return;
        
        Gledanje gled=new Gledanje();
        gled.setKorisnikId(kor);
        gled.setVideoSnimakId(vs);
        gled.setDatumPocetka(datumPocetka);
        gled.setVremePocetka(vremePocetka);
        gled.setSekundPocetkaSnimka(sekPocetka);
        gled.setSekundeOdgledano(sekOdgledano);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(gled);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void kreirajOcenu(Korisnik kor, Videosnimak vs, int ocena, Date datumOcene, Integer vremeOcene){
        if(ocena<1 || ocena>5) return;
        
        Ocena oc=new Ocena();
        oc.setKorisnikId(kor);
        oc.setVideosnimakId(vs);
        oc.setOcena(ocena);
        oc.setDatumOcene(datumOcene);
        oc.setVremeOcene(vremeOcene);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(oc);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void kreirajOcenu(Integer korId, Integer videoId, int ocena, Date datumOcene, Integer vremeOcene){
        if(ocena<1 || ocena>5) return;
        
        Korisnik kor=dohvatiKorisnika(korId);
        Videosnimak vs=dohvatiVideosnimak(videoId);
        if(kor==null||vs==null) return;
        
        Ocena oc=new Ocena();
        oc.setKorisnikId(kor);
        oc.setVideosnimakId(vs);
        oc.setOcena(ocena);
        oc.setDatumOcene(datumOcene);
        oc.setVremeOcene(vremeOcene);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
         //provera postoji li vec
        Query query = em.createQuery("SELECT o FROM Ocena o WHERE o.korisnikId = :kor and o.videosnimakId=:vs", Ocena.class);
        query.setParameter("kor", kor);
        query.setParameter("vs", vs);
        List<Ocena> ocene = query.getResultList();
        if (!ocene.isEmpty())
        {
            em.close();
            emf.close();
            return;
        }
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(oc);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    
    public void promeniOcenu(Korisnik kor, Videosnimak vs, int novaOcena){
        if(novaOcena<1 || novaOcena>5) return;
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT oc FROM Ocena oc WHERE oc.korisnikId = :korId and oc.videosnimakId=:vidId", Ocena.class);
        query.setParameter("korId", kor);
        query.setParameter("vidId", vs);
        List<Ocena> ocene = query.getResultList();
        if (!ocene.isEmpty()) {
            Ocena oc = ocene.get(0);
            
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
            
                oc.setOcena(novaOcena);
                em.merge(oc);

                transaction.commit();
            } finally {
                if(transaction.isActive()) transaction.rollback();
                em.close();
            }
            
        }
        emf.close();
    }
    public void promeniOcenu(Integer korId, Integer videoId, int novaOcena){
        if(novaOcena<1 || novaOcena>5) return;
        
        Korisnik kor=dohvatiKorisnika(korId);
        Videosnimak vs=dohvatiVideosnimak(videoId);
        
        if(kor==null||vs==null) return;
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT oc FROM Ocena oc WHERE oc.korisnikId = :korId and oc.videosnimakId=:vidId", Ocena.class);
        query.setParameter("korId", kor);
        query.setParameter("vidId", vs);
        List<Ocena> ocene = query.getResultList();
        if (!ocene.isEmpty()) {
            Ocena oc = ocene.get(0);
            
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
            
                oc.setOcena(novaOcena);
                em.merge(oc);

                transaction.commit();
            } finally {
                if(transaction.isActive()) transaction.rollback();
                em.close();
            }
            
        }
        emf.close();
    }
    public void obrisiOcenu(Korisnik kor, Videosnimak vs){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            Query query = em.createQuery("DELETE FROM Ocena oc WHERE oc.videosnimakId=:vidId and oc.korisnikId=:korId", Ocena.class);
            query.setParameter("vidId", vs);
            query.setParameter("korId", kor);
            query.executeUpdate();
            
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            em.close();
        }
        emf.close();
    }
    public void obrisiOcenu(Integer korId, Integer videoId){
        Korisnik kor=dohvatiKorisnika(korId);
        Videosnimak vs=dohvatiVideosnimak(videoId);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            Query query = em.createQuery("DELETE FROM Ocena oc WHERE oc.videosnimakId=:vidId and oc.korisnikId=:korId", Ocena.class);
            query.setParameter("vidId", vs);
            query.setParameter("korId", kor);
            query.executeUpdate();
            
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            em.close();
        }
        emf.close();
    }
    public List<Gledanje> dohvatiGledanjaSnimka(Videosnimak vs){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Gledanje g WHERE g.videoSnimakId = :videoId", Gledanje.class);
        query.setParameter("videoId", vs);
        List<Gledanje> gledanja = query.getResultList();
        em.close();
        emf.close();
        if (!gledanja.isEmpty()) {
            return gledanja;
        }
        return null;
    }
    public List<Gledanje> dohvatiGledanjaSnimka(Integer videoId){
        Videosnimak vs=dohvatiVideosnimak(videoId);
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Gledanje g WHERE g.videoSnimakId = :videoId", Gledanje.class);
        query.setParameter("videoId", vs);
        List<Gledanje> gledanja = query.getResultList();
        em.close();
        emf.close();
        if (!gledanja.isEmpty()) {
            return gledanja;
        }
        return null;
    }
    public List<Ocena> dohvatiOceneSnimka(Videosnimak vs){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Ocena o WHERE o.videosnimakId = :videoId", Ocena.class);
        query.setParameter("videoId", vs);
        List<Ocena> ocene = query.getResultList();
        em.close();
        emf.close();
        if (!ocene.isEmpty()) {
            return ocene;
        }
        return null;
    }
    public List<Ocena> dohvatiOceneSnimka(Integer videoId){
        Videosnimak vs=dohvatiVideosnimak(videoId);
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Ocena o WHERE o.videosnimakId = :videoId", Ocena.class);
        query.setParameter("videoId", vs);
        List<Ocena> ocene = query.getResultList();
        em.close();
        emf.close();
        if (!ocene.isEmpty()) {
            return ocene;
        }
        return null;
    }
    
    public static void main(String[] args) {
        Podsistem3 ps3=new Podsistem3();
        
        if (topic == null || queue == null || connectionFactory == null) {
            System.out.println("Error: Topic, queue, ili connectionFactory nije dobro inicijalizovano na Podsistemu2");
            return;
        }

        try {
            try (JMSContext context = connectionFactory.createContext()) {
                JMSProducer producer = context.createProducer();

                JMSConsumer consumer = context.createConsumer(topic);
                
                String datumString;
                SimpleDateFormat dateFormat;
                Date date=null;
                
                while (true) {
                    Message message = consumer.receive();
                    if (message instanceof TextMessage) {
                        TextMessage receivedMessage = (TextMessage) message;
                        String[] input = receivedMessage.getText().split(",");
                        //System.out.println("Received message from topic: " + receivedMessage.getText());
                        TextMessage textMsg = context.createTextMessage("Pocetno");
                        StringBuilder sb = new StringBuilder();
                        if (input.length > 0) {
                            switch (input[0]) {
                                case "1"://Kreiranje grada
                                    ps3.dohvatiIKreirajAkoNemaGrad(input[1]);
                                    textMsg.setText("Grad kreiran!");
                                    break;
                                case "2"://Kreiranje korisnika
                                    ps3.dodajKorisnika(input[1],input[2],Integer.parseInt(input[3]),input[4],input[5]);
                                    textMsg.setText("Korisnik kreiran!");
                                    break;
                                case "3"://Promena email adrese za korisnika
                                    ps3.promeniEmailZaKorisnika(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjen email!");
                                    break;
                                case "4"://Promena mesta za korisnika
                                    ps3.promeniMestoZaKorisnika(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjeno mesto!");
                                    break;
                                case "6"://Kreiranje video snimka
                                    datumString=input[4];
                                    
                                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    date=null;
                                    try {
                                        date = dateFormat.parse(datumString);
                                    } catch (Exception e) {
                                        System.out.println("Error parsing date: " + e.getMessage());
                                    }
                                    ps3.kreirajVideoSnimak(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]), date, Integer.parseInt(input[5]));
                                    textMsg.setText("Kreiran Video Snimak!");
                                    break;
                                case "7"://Promena naziva video snimka
                                    ps3.promeniNazivSnimka(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjen naziv snimka!");
                                    break;
                                case "16"://Brisanje video snimka od strane korisnika koji ga je kreirao
                                    ps3.brisiVideo(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                    textMsg.setText("Video je obrisan!");
                                    break;
                                case "9"://Kreiranje paketa
                                    ps3.kreirajPaket(input[1], Integer.parseInt(input[2]));
                                    textMsg.setText("Paket je kreiran!");
                                    break;
                                case "10"://Promena mesečne cene za paket
                                    ps3.promenaCenePaketa(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                    textMsg.setText("Cena paketa je promenjena!");
                                    break;
                                case "11"://Kreiranje pretplate korisnika na paket
                                    datumString=input[3];
                                    
                                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    date=null;
                                    try {
                                        date = dateFormat.parse(datumString);
                                    } catch (Exception e) {
                                        System.out.println("Error parsing date: " + e.getMessage());
                                    }
                                    
                                    ps3.kreiranjePretplateNaPaket(Integer.parseInt(input[1]), Integer.parseInt(input[2]), date, Integer.parseInt(input[4]));
                                    textMsg.setText("Kreirana je pretplata!");
                                    break;
                                case "12"://Kreiranje gledanja video snimka od strane korisnika
                                    datumString=input[3];
                                    
                                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    date=null;
                                    try {
                                        date = dateFormat.parse(datumString);
                                    } catch (Exception e) {
                                        System.out.println("Error parsing date: " + e.getMessage());
                                    }
                                    
                                    ps3.kreirajGledanje(Integer.parseInt(input[1]), Integer.parseInt(input[2]), date, Integer.parseInt(input[4]), Integer.parseInt(input[5]), Integer.parseInt(input[6]));
                                    textMsg.setText("Gledanje je kreirano!");
                                    break;
                                case "13"://Kreiranje ocene korisnika za video snimak
                                    datumString=input[4];
                                    
                                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    date=null;
                                    try {
                                        date = dateFormat.parse(datumString);
                                    } catch (Exception e) {
                                        System.out.println("Greska pri obradi datuma: " + e.getMessage());
                                    }
                                    
                                    ps3.kreirajOcenu(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), date, Integer.parseInt(input[5]));
                                    textMsg.setText("Kreirana je ocena!");
                                    break;
                                case "14"://Menjanje ocene korisnika za video snimak
                                    ps3.promeniOcenu(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                                    textMsg.setText("Promenjena je ocena!");
                                    break;
                                case "15"://Brisanje ocene korisnika za video snimak
                                    ps3.obrisiOcenu(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                    textMsg.setText("Ocena je obrisana");
                                    break;
                                case "22"://Dohvatanje svih paketa
                                    List<Paket> paketi=ps3.dohvatiSvePakete();
                                    
                                    for (Paket p : paketi) {
                                        sb.append(p.getPaketId()).append(",")
                                          .append(p.getNaziv()).append(",")
                                          .append(p.getTrenutnaCena()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;
                                case "23"://Dohvatanje svih pretplata za korisnika
                                    List<Pretplata> pretplate=ps3.dohvatiPretplateZaKorisnika(Integer.parseInt(input[1]));
                                    
                                    for (Pretplata p : pretplate) {
                                        sb.append(p.getPretplataId()).append(",")
                                          .append(p.getKorisnikId().getKorisnikId()).append(",")
                                          .append(p.getPaketId().getPaketId()).append(",")
                                          .append(p.getCenaPretplate()).append(",")
                                          .append(p.getDatumPocetka().toString()).append(",")
                                          .append(p.getVremePocetka()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;
                                case "24"://Dohvatanje svih gledanja za video snimak
                                    List<Gledanje> gledanja=ps3.dohvatiGledanjaSnimka(Integer.parseInt(input[1]));
                                    
                                    for (Gledanje gled : gledanja) {
                                        sb.append(gled.getGledanjeId()).append(",")
                                          .append(gled.getKorisnikId().getKorisnikId()).append(",")
                                          .append(gled.getVideoSnimakId().getVideoSnimakId()).append(",")
                                          .append(gled.getDatumPocetka().toString()).append(",")
                                          .append(gled.getVremePocetka()).append(",")
                                          .append(gled.getSekundPocetkaSnimka()).append(",")
                                          .append(gled.getSekundeOdgledano()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;
                                case "25"://Dohvatanje svih ocena za video snimak
                                    List<Ocena> ocene=ps3.dohvatiOceneSnimka(Integer.parseInt(input[1]));
                                    
                                    for (Ocena ocena : ocene) {
                                        sb.append(ocena.getOcenaId()).append(",")
                                          .append(ocena.getKorisnikId().getKorisnikId()).append(",")
                                          .append(ocena.getVideosnimakId().getVideoSnimakId()).append(",")
                                          .append(ocena.getDatumOcene().toString()).append(",")
                                          .append(ocena.getVremeOcene()).append(",")
                                          .append(ocena.getOcena()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;    
                                default:
                                    textMsg.setText("Sve OK!");
                                    break;
                            }
                        }
                        else{
                            textMsg.setText("Sve OK, nista nije primljeno!");
                        }
                        if(input[0].equals("9")||input[0].equals("10")||input[0].equals("11")||input[0].equals("12")||input[0].equals("13")||input[0].equals("14")||input[0].equals("15")||input[0].equals("22")||input[0].equals("23")||input[0].equals("24")||input[0].equals("25")) 
                        {
                            producer.send(queue, textMsg);
                            System.out.println("Poslato: \n"+textMsg.getText());
                        }
                        else if(input[0].equals("1")||input[0].equals("2")||input[0].equals("3")||input[0].equals("4")||input[0].equals("6")||input[0].equals("7")||input[0].equals("16")){
                            System.out.println("Sinhronizovano!");
                        }
                    }
                }
            }
        } 
        catch (JMSException e) {
            System.out.println("Error u JMS: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}