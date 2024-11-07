/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2is1_20;

import entiteti.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.jms.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.persistence.*;

/**
 *
 * @author korisnik
 */
public class Podsistem2 {
    
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
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Grad g", Grad.class);
        List<Grad> gradovi = query.getResultList();
        em.close();
        emf.close();
        return gradovi;
    }
    public List<Korisnik> dohvatiSveKorisnike() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT k FROM Korisnik k", Korisnik.class);
        List<Korisnik> korisnici = query.getResultList();
        em.close();
        emf.close();
        return korisnici;
    }
    public void promeniEmailZaKorisnika(int korisnikId, String noviEmail) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
    
    public void kreirajKategoriju(String naziv) {
        Kategorija k=new Kategorija();
        k.setNaziv(naziv);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
    public void kreirajVideoSnimak(String naziv, Integer trajanje, Korisnik vlasnik, Date datumPostavljanja, Integer vremePostavljanja){
        Videosnimak vs=new Videosnimak();
        vs.setNaziv(naziv);
        vs.setDatumPostavljanja(datumPostavljanja);
        vs.setVremePostavljanja(vremePostavljanja);
        vs.setTrajanje(trajanje);
        vs.setVlasnikId(vlasnik);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        vs.setDatumPostavljanja(datumPostavljanja);
        vs.setVremePostavljanja(vremePostavljanja);
        vs.setTrajanje(trajanje);
        vs.setVlasnikId(vlasnik);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Videosnimak g", Videosnimak.class);
        List<Videosnimak> snimci = query.getResultList();
        em.close();
        emf.close();
        return snimci;
    }
    public List<Kategorija> dohvatiSveKategorije(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Kategorija g", Kategorija.class);
        List<Kategorija> kategorije = query.getResultList();
        em.close();
        emf.close();
        return kategorije;
    }
    public void promeniNazivSnimka(int videoId,String noviNaziv) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Videosnimak vs = em.find(Videosnimak.class, videoId);
        em.close();
        emf.close();
        return vs;
    }
    public Kategorija dohvatiKategoriju(int katId) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Kategorija kat = em.find(Kategorija.class, katId);
        em.close();
        emf.close();
        return kat;
    }
    public void brisiVideo(Korisnik korisnik, Videosnimak vs){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            if (Objects.equals(vs.getVlasnikId().getKorisnikId(), korisnik.getKorisnikId())) {
                Query queryFirst=em.createQuery("DELETE FROM Kategorijavideo kv WHERE kv.videoId=:videoId", Kategorijavideo.class);
                queryFirst.setParameter("videoId", vs);
                queryFirst.executeUpdate();
                
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            if (Objects.equals(vs.getVlasnikId().getKorisnikId(), korisnikId)) {
                Query queryFirst=em.createQuery("DELETE FROM Kategorijavideo kv WHERE kv.videoId=:videoId", Kategorijavideo.class);
                queryFirst.setParameter("videoId", vs);
                queryFirst.executeUpdate();
                
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            if (Objects.equals(vs.getVlasnikId().getKorisnikId(), korisnikId)) {
                Query queryFirst=em.createQuery("DELETE FROM Kategorijavideo kv WHERE kv.videoId=:videoId", Kategorijavideo.class);
                queryFirst.setParameter("videoId", vs);
                queryFirst.executeUpdate();
                
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
    public void dodajKategorijuSnimku(Kategorija kat,Videosnimak vs){
        Kategorijavideo katVid=new Kategorijavideo();
        katVid.setKategorijaId(kat);
        katVid.setVideoId(vs);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(katVid);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public void dodajKategorijuSnimku(int katId,int vidId){
        Kategorija kat=dohvatiKategoriju(katId);
        Videosnimak vs=dohvatiVideosnimak(vidId);
        if(kat==null || vs==null) return;
        
        Kategorijavideo katVid=new Kategorijavideo();
        katVid.setKategorijaId(kat);
        katVid.setVideoId(vs);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(katVid);
            transaction.commit();
        }
        finally{
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        emf.close();
    }
    public List<Kategorija> dohvatiKategorijeZaSnimak(Videosnimak vs) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT kv FROM Kategorijavideo kv WHERE kv.videoId = :videoId", Kategorijavideo.class);
        query.setParameter("videoId", vs);
        List<Kategorijavideo> kategorijeVidea = query.getResultList();
        List<Kategorija> kategorije=new ArrayList<>();
        for(Kategorijavideo kv:kategorijeVidea)
        {
            kategorije.add(kv.getKategorijaId());
        }
        em.close();
        emf.close();
        if (!kategorije.isEmpty()) {
            return kategorije;
        }
        return null;
    }
    public List<Kategorija> dohvatiKategorijeZaSnimak(int videoId) {
        Videosnimak vs=dohvatiVideosnimak(videoId);
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT kv FROM Kategorijavideo kv WHERE kv.videoId = :videoId", Kategorijavideo.class);
        query.setParameter("videoId", vs);
        List<Kategorijavideo> kategorijeVidea = query.getResultList();
        List<Kategorija> kategorije=new ArrayList<>();
        for(Kategorijavideo kv:kategorijeVidea)
        {
            kategorije.add(kv.getKategorijaId());
        }
        em.close();
        emf.close();
        if (!kategorije.isEmpty()) {
            return kategorije;
        }
        return null;
    }
    
    public static void main(String[] args) {
        Podsistem2 ps2=new Podsistem2();
        
        if (topic == null || queue == null || connectionFactory == null) {
            System.out.println("Error: Topic, queue, ili connectionFactory nije dobro inicijalizovano na Podsistemu2");
            return;
        }

        try {
            try (JMSContext context = connectionFactory.createContext()) {
                JMSProducer producer = context.createProducer();

                JMSConsumer consumer = context.createConsumer(topic);

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
                                    ps2.dohvatiIKreirajAkoNemaGrad(input[1]);
                                    textMsg.setText("Grad kreiran!");
                                    break;
                                case "2"://Kreiranje korisnika
                                    ps2.dodajKorisnika(input[1],input[2],Integer.parseInt(input[3]),input[4],input[5]);
                                    textMsg.setText("Korisnik kreiran!");
                                    break;
                                case "3"://Promena email adrese za korisnika
                                    ps2.promeniEmailZaKorisnika(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjen email!");
                                    break;
                                case "4"://Promena mesta za korisnika
                                    ps2.promeniMestoZaKorisnika(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjeno mesto!");
                                    break;
                                case "5"://Kreiranje kategorije
                                    ps2.kreirajKategoriju(input[1]);
                                    textMsg.setText("Kreirana kategorija!");
                                    break;
                                case "6"://Kreiranje video snimka
                                    String datumString=input[4];
                                    
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date=null;
                                    try {
                                        date = dateFormat.parse(datumString);
                                    } catch (Exception e) {
                                        System.out.println("Error parsing date: " + e.getMessage());
                                    }
                                    ps2.kreirajVideoSnimak(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]), date, Integer.parseInt(input[5]));
                                    textMsg.setText("Kreiran Video Snimak!");
                                    break;
                                case "7"://Promena naziva video snimka
                                    ps2.promeniNazivSnimka(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjen naziv snimka!");
                                    break;
                                case "8"://Dodavanje kategorije video snimku
                                    ps2.dodajKategorijuSnimku(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                    textMsg.setText("Dodeljena kategorija!");
                                    break;
                                case "16"://Brisanje video snimka od strane korisnika koji ga je kreirao
                                    ps2.brisiVideo(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                    textMsg.setText("Video je obrisan!");
                                    break;
                                case "19"://Dohvatanje svih kategorija
                                    List<Kategorija> kategorije=ps2.dohvatiSveKategorije();

                                    for (Kategorija kategorija : kategorije) {
                                        sb.append(kategorija.getKategorijaId()).append(",")
                                          .append(kategorija.getNaziv()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;
                                case "20"://Dohvatanje svih video snimaka
                                    List<Videosnimak> snimci=ps2.dohvatiSveVideoSnimke();

                                    for (Videosnimak snimak : snimci) {
                                        sb.append(snimak.getVideoSnimakId()).append(",")
                                          .append(snimak.getNaziv()).append(",")
                                          .append(snimak.getVlasnikId().getKorisnikId()).append(",")
                                          .append(snimak.getTrajanje()).append(",")
                                          .append(snimak.getDatumPostavljanja().toString()).append(",")
                                          .append(snimak.getVremePostavljanja()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;
                                case "21"://Dohvatanje kategorija za određeni video snimak
                                    List<Kategorija> kategorijeSnimka=ps2.dohvatiKategorijeZaSnimak(Integer.parseInt(input[1]));

                                    for (Kategorija kategorija : kategorijeSnimka) {
                                        sb.append(kategorija.getKategorijaId()).append(",")
                                          .append(kategorija.getNaziv()).append("+");
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
                        if(input[0].equals("5")||input[0].equals("6")||input[0].equals("7")||input[0].equals("8")||input[0].equals("16")||input[0].equals("19")||input[0].equals("20")||input[0].equals("21")) 
                        {
                            producer.send(queue, textMsg);
                            System.out.println("Poslato: \n"+textMsg.getText());
                        }
                        else if(input[0].equals("1")||input[0].equals("2")||input[0].equals("3")||input[0].equals("4")){
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
