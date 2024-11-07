/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1is1_20;

import entiteti.Grad;
import entiteti.Korisnik;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.jms.*;

/**
 *
 * @author korisnik
 */
public class Podsistem1 {

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
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
    public Korisnik dohvatiKorisnika(Integer id){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
    public void dodajGrad(String name) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
    public Grad dohvatiGrad(String naziv) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
    public Grad dohvatiIKreirajAkoNemaGrad(String naziv) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Grad g", Grad.class);
        List<Grad> gradovi = query.getResultList();
        em.close();
        emf.close();
        return gradovi;
    }
    public List<Korisnik> dohvatiSveKorisnike() {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT k FROM Korisnik k", Korisnik.class);
        List<Korisnik> korisnici = query.getResultList();
        em.close();
        emf.close();
        return korisnici;
    }
    public void promeniEmailZaKorisnika(int korisnikId, String noviEmail) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1IS1_2.0PU");
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
    public static void main(String[] args) {
        Podsistem1 ps1 = new Podsistem1();

        if (topic == null || queue == null || connectionFactory == null) {
            System.out.println("Error: Topic, queue, ili connectionFactory nije dobro inicijalizovano na Podsistemu1");
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
                                    ps1.dohvatiIKreirajAkoNemaGrad(input[1]);
                                    textMsg.setText("Grad kreiran!");
                                    break;
                                case "2"://Kreiranje korisnika
                                    ps1.dodajKorisnika(input[1],input[2],Integer.parseInt(input[3]),input[4],input[5]);
                                    textMsg.setText("Korisnik kreiran!");
                                    break;
                                case "3"://Promena email adrese za korisnika
                                    ps1.promeniEmailZaKorisnika(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjen email!");
                                    break;
                                case "4"://Promena mesta za korisnika
                                    ps1.promeniMestoZaKorisnika(Integer.parseInt(input[1]), input[2]);
                                    textMsg.setText("Promenjeno mesto!");
                                    break;
                                case "17"://Dohvatanje svih mesta
                                    List<Grad> gradovi=ps1.dohvatiSveGradove();

                                    for (Grad grad : gradovi) {
                                        sb.append(grad.getGradId()).append(",")
                                          .append(grad.getNaziv()).append("+");
                                    }
                                    
                                    textMsg.setText(sb.toString());
                                    break;
                                case "18"://Dohvatanje svih korisnika
                                    List<Korisnik> korisnici=ps1.dohvatiSveKorisnike();

                                    for (Korisnik korisnik : korisnici) {
                                        sb.append(korisnik.getKorisnikId()).append(",")
                                          .append(korisnik.getIme()).append(",")
                                          .append(korisnik.getGodiste()).append(",")
                                          .append(korisnik.getEmail()).append(",")
                                          .append(korisnik.getPol()).append(",")
                                          .append(korisnik.getGradId().getGradId()).append("+");
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
                        if(input[0].equals("1")||input[0].equals("2")||input[0].equals("3")||input[0].equals("4")||input[0].equals("17")||input[0].equals("18")) 
                        {
                            producer.send(queue, textMsg);
                            System.out.println("Poslato: \n"+textMsg.getText());
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
