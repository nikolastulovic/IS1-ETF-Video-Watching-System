/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.centralniserveris1.resources;

import javax.annotation.Resource;
import javax.jms.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author korisnik
 */

@Path("podsistemi")
public class PodsistemiKomunikacija {
    
    @Resource(lookup = "myConnFactory")
    private ConnectionFactory connectionFactory;
     
    @Resource(lookup = "topicZaProjekat")
    private Topic topic;
    
    @Resource(lookup = "queueZaProjekat")
    private Queue queue;
    
    @GET
    @Path("kreirajGrad")
    //1 Kreiranje grada
    public String kreirajGrad(@QueryParam("nazivGrada") String nazivGrada) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("1," + nazivGrada);
            producer.send(topic, textMsg);

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajKorisnika")
    //2 Kreiranje korisnika
    public String kreirajKorisnika(@QueryParam("ime")String ime, @QueryParam("email")String email, @QueryParam("godiste")int godiste, @QueryParam("pol")String pol, @QueryParam("imeGrada")String imeGrada) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("2," + ime+","+ email+","+ godiste+","+ pol+","+ imeGrada);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("promeniEmailKorisniku")
    //3 Promena email adrese za korisnika
    public String promeniEmailKorisniku(@QueryParam("korisnikId")int korisnikId, @QueryParam("noviEmail")String noviEmail) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("3," + korisnikId+","+ noviEmail);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("promeniMestoKorisniku")
    //4 Promena mesta za korisnika
    public String promeniMestoKorisniku(@QueryParam("korisnikId")int korisnikId, @QueryParam("nazivMesta")String nazivMesta) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("4," + korisnikId+","+ nazivMesta);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajKategoriju")
    //5 Kreiranje kategorije
    public String kreirajKategoriju(@QueryParam("ime")String ime) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("5," + ime);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajVideo")
    //6 Kreiranje video snimka
    public String kreirajVideo(@QueryParam("naziv")String naziv, @QueryParam("trajanje")Integer trajanje,@QueryParam("vlasnikId") Integer vlasnikId,@QueryParam("datumPostavljanja") String datumPostavljanja, @QueryParam("vremePostavljanja")Integer vremePostavljanja) {
         try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("6," + naziv+","+ trajanje+","+ vlasnikId+","+ datumPostavljanja+","+ vremePostavljanja);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("promeniNazivSnimka")
    //7 Promena naziva video snimka
    public String promeniNazivSnimka(@QueryParam("videoId")int videoId,@QueryParam("noviNaziv")String noviNaziv)  {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("7," + videoId+","+ noviNaziv);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dodajKategorijuSnimku")
    //8 Dodavanje kategorije video snimku
    public String dodajKategorijuSnimku(@QueryParam("katId")int katId,@QueryParam("vidId")int vidId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("8," + katId+","+ vidId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajPaket")
    //9 Kreiranje paketa
    public String kreirajPaket(@QueryParam("naziv")String naziv, @QueryParam("trenutnaCena")Integer trenutnaCena) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("9," + naziv+","+ trenutnaCena);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("promeniCenePaketa")
    //10 Promena mesečne cene za paket
    public String promeniCenuPaketa(@QueryParam("paketId")int paketId, @QueryParam("novaCena")int novaCena) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("10," + paketId+","+ novaCena);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajPretplatu")
    //11 Kreiranje pretplate korisnika na paket
    public String kreirajPretplatu(@QueryParam("korId")Integer korId, @QueryParam("pakId")Integer pakId, @QueryParam("datumPocetka")String datumPocetka,@QueryParam("vremePocetka")int vremePocetka) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("11," + korId+","+ pakId+","+ datumPocetka+","+ vremePocetka);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajGledanje")
    //12 Kreiranje gledanja video snimka od strane korisnika
    public String kreirajGledanje(@QueryParam("korId")Integer korId, @QueryParam("videoId")Integer videoId, @QueryParam("datumPocetka")String datumPocetka,@QueryParam("vremePocetka") Integer vremePocetka, @QueryParam("sekPocetka")Integer sekPocetka, @QueryParam("sekOdgledano")Integer sekOdgledano) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("12," + korId+","+ videoId+","+ datumPocetka+","+ vremePocetka+","+ sekPocetka+","+ sekOdgledano);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("kreirajOcenu")
    //13 Kreiranje ocene korisnika za video snimak
    public String kreirajOcenu(@QueryParam("korId")Integer korId, @QueryParam("videoId")Integer videoId, @QueryParam("ocena")int ocena, @QueryParam("datumOcene")String datumOcene, @QueryParam("vremeOcene")Integer vremeOcene) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("13," + korId+","+ videoId+","+ ocena+","+ datumOcene+","+ vremeOcene);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("promeniOcenu")
    //14 Menjanje ocene korisnika za video snimak
    public String promeniOcenu(@QueryParam("korId")Integer korId,@QueryParam("videoId") Integer videoId,@QueryParam("novaOcena") int novaOcena) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("14," + korId+","+ videoId+","+ novaOcena);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("obrisiOcenu")
    //15 Brisanje ocene korisnika za video snimak
    public String obrisiOcenu(@QueryParam("korId")Integer korId, @QueryParam("videoId")Integer videoId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("15," + korId+","+ videoId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("obrisiVideo")
    //16 Brisanje video snimka od strane korisnika koji ga je kreirao
    public String obrisiVideo(@QueryParam("korisnikId")int korisnikId, @QueryParam("videoId")int videoId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("16," + korisnikId+","+ videoId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
               return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSvaMesta")
    //17 Dohvatanje svih mesta
    public String dohvatiSvaMesta() {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("17,T");
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSveKorisnike")
    //18 Dohvatanje svih korisnika
    public String dohvatiSveKorisnike() {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("18,T");
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSveKategorije")
    //19 Dohvatanje svih kategorija
    public String dohvatiSveKatergorije() {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("19,T");
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSveVideoSnimke")
    //20 Dohvatanje svih video snimaka
    public String dohvatiSveVideoSnimke() {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("20,T");
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiKategorijeSnimka")
    //21 Dohvatanje kategorija za određeni video snimak
    public String dohvatiKategorijeSnimka(@QueryParam("videoId")Integer videoId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("21," + videoId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSvePakete")
    //22 Dohvatanje svih paketa
    public String dohvatiSvePakete() {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("22,T");
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSvePretplateKorisnika")
    //23 Dohvatanje svih pretplata za korisnika
    public String dohvatiSvePretplateKorisnika(@QueryParam("korId")Integer korId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("23," + korId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSvaGledanjaSnimka")
    //24 Dohvatanje svih gledanja za video snimak
    public String dohvatiSvaGledanjaSnimka(@QueryParam("videoId")Integer videoId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("24," + videoId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
                return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("dohvatiSveOceneSnimka")
    //25 Dohvatanje svih ocena za video snimak
    public String dohvatiSveOceneSnimka(@QueryParam("videoId")Integer videoId) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(queue);

            TextMessage textMsg = context.createTextMessage("25," + videoId);
            producer.send(topic, textMsg);
            System.out.println("Message sent to topic: " + textMsg.getText());

            Message message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                TextMessage receivedMessage = (TextMessage) message;
                String text = receivedMessage.getText();
               return text;
            } else {
                return "GRESKA_FORMAT";
            }
            
        } catch (Exception e) {
            System.out.println("GRESKA_" + e.getMessage());
            return "GRESKA_"+e.getMessage();
        }
    }
    @GET
    @Path("obrisiZaostalePoruke")
    public String obrisiZaostalePoruke() {
        try (JMSContext context = connectionFactory.createContext()) {
            
            JMSConsumer consumer = context.createConsumer(queue);

            while (true) {
                Message message = consumer.receiveNoWait();
                if (message == null) {
                    break;
                }
                return ((TextMessage)message).getText();
            }
        } catch (Exception e) { }
        return "OK";
    }
}
