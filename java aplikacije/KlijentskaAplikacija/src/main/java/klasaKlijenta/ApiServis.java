/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasaKlijenta;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;
/**
 *
 * @author korisnik
 */
public interface ApiServis {
    @GET("/centralniServerIS1/api/podsistemi/kreirajGrad")
    Call<String> kreirajGrad(@Query("nazivGrada") String nazivGrada);
    @GET("/centralniServerIS1/api/podsistemi/kreirajKorisnika")
    Call<String> kreirajKorisnika(@Query("ime")String ime, @Query("email")String email, @Query("godiste")int godiste, @Query("pol")String pol, @Query("imeGrada")String imeGrada);
    @GET("/centralniServerIS1/api/podsistemi/promeniEmailKorisniku")
    Call<String> promeniEmailKorisniku(@Query("korisnikId")int korisnikId, @Query("noviEmail")String noviEmail);
    @GET("/centralniServerIS1/api/podsistemi/promeniMestoKorisniku")
    Call<String> promeniMestoKorisniku(@Query("korisnikId")int korisnikId, @Query("nazivMesta")String nazivMesta);
    @GET("/centralniServerIS1/api/podsistemi/kreirajKategoriju")
    Call<String> kreirajKategoriju(@Query("ime")String ime);
    @GET("/centralniServerIS1/api/podsistemi/kreirajVideo")
    Call<String> kreirajVideo(@Query("naziv")String naziv, @Query("trajanje")Integer trajanje,@Query("vlasnikId") Integer vlasnikId,@Query("datumPostavljanja") String datumPostavljanja, @Query("vremePostavljanja")Integer vremePostavljanja);
    @GET("/centralniServerIS1/api/podsistemi/promeniNazivSnimka")
    Call<String> promeniNazivSnimka(@Query("videoId")int videoId,@Query("noviNaziv")String noviNaziv);
    @GET("/centralniServerIS1/api/podsistemi/dodajKategorijuSnimku")
    Call<String> dodajKategorijuSnimku(@Query("katId")int katId,@Query("vidId")int vidId);
    @GET("/centralniServerIS1/api/podsistemi/kreirajPaket")
    Call<String> kreirajPaket(@Query("naziv")String naziv, @Query("trenutnaCena")Integer trenutnaCena);
    @GET("/centralniServerIS1/api/podsistemi/promeniCenePaketa")
    Call<String> promeniCenuPaketa(@Query("paketId")int paketId, @Query("novaCena")int novaCena);
    @GET("/centralniServerIS1/api/podsistemi/kreirajPretplatu")
    Call<String> kreirajPretplatu(@Query("korId")Integer korId, @Query("pakId")Integer pakId, @Query("datumPocetka")String datumPocetka,@Query("vremePocetka")int vremePocetka);
    @GET("/centralniServerIS1/api/podsistemi/kreirajGledanje")
    Call<String> kreirajGledanje(@Query("korId")Integer korId, @Query("videoId")Integer videoId, @Query("datumPocetka")String datumPocetka,@Query("vremePocetka") Integer vremePocetka, @Query("sekPocetka")Integer sekPocetka, @Query("sekOdgledano")Integer sekOdgledano);
    @GET("/centralniServerIS1/api/podsistemi/kreirajOcenu")
    Call<String> kreirajOcenu(@Query("korId")Integer korId, @Query("videoId")Integer videoId, @Query("ocena")int ocena, @Query("datumOcene")String datumOcene, @Query("vremeOcene")Integer vremeOcene);
    @GET("/centralniServerIS1/api/podsistemi/promeniOcenu")
    Call<String> promeniOcenu(@Query("korId")Integer korId,@Query("videoId") Integer videoId,@Query("novaOcena") int novaOcena);
    @GET("/centralniServerIS1/api/podsistemi/obrisiOcenu")
    Call<String> obrisiOcenu(@Query("korId")Integer korId, @Query("videoId")Integer videoId);
    @GET("/centralniServerIS1/api/podsistemi/obrisiVideo")
    Call<String> obrisiVideo(@Query("korisnikId")int korisnikId, @Query("videoId")int videoId);
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSvaMesta")
    Call<String> dohvatiSvaMesta();
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSveKorisnike")
    Call<String> dohvatiSveKorisnike();
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSveKategorije")
    Call<String> dohvatiSveKatergorije();
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSveVideoSnimke")
    Call<String> dohvatiSveVideoSnimke();
    @GET("/centralniServerIS1/api/podsistemi/dohvatiKategorijeSnimka")
    Call<String> dohvatiKategorijeSnimka(@Query("videoId")Integer videoId);
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSvePakete")
    Call<String> dohvatiSvePakete();
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSvePretplateKorisnika")
    Call<String> dohvatiSvePretplateKorisnika(@Query("korId")Integer korId);
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSvaGledanjaSnimka")
    Call<String> dohvatiSvaGledanjaSnimka(@Query("videoId")Integer videoId);
    @GET("/centralniServerIS1/api/podsistemi/dohvatiSveOceneSnimka")
    Call<String> dohvatiSveOceneSnimka(@Query("videoId")Integer videoId);
    @GET("/centralniServerIS1/api/podsistemi/obrisiZaostalePoruke")
    Call<String> obrisiZaostalePoruke();
}
