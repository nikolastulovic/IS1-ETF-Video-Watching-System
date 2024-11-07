/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasaKlijenta;
import java.util.Scanner;
import retrofit2.*;
import klasaKlijenta.ApiServis;
import retrofit2.Call;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class KlijentskaAplikacija {
    private static final String BASE_URL = "http://localhost:8080/";
    
    public static void displayMenu() {
        System.out.println("\n Akcije:");
        System.out.println("1. Kreiranje grada");
        System.out.println("2. Kreiranje korisnika");
        System.out.println("3. Promena email adrese za korisnika");
        System.out.println("4. Promena mesta za korisnika");
        System.out.println("5. Kreiranje kategorije");
        System.out.println("6. Kreiranje video snimka");
        System.out.println("7. Promena naziva video snimka");
        System.out.println("8. Dodavanje kategorije video snimku");
        System.out.println("9. Kreiranje paketa");
        System.out.println("10. Promena mesečne cene za paket");
        System.out.println("11. Kreiranje pretplate korisnika na paket");
        System.out.println("12. Kreiranje gledanja video snimka od strane korisnika");
        System.out.println("13. Kreiranje ocene korisnika za video snimak");
        System.out.println("14. Menjanje ocene korisnika za video snimak");
        System.out.println("15. Brisanje ocene korisnika za video snimak");
        System.out.println("16. Brisanje video snimka od strane korisnika koji ga je kreirao");
        System.out.println("17. Dohvatanje svih mesta");
        System.out.println("18. Dohvatanje svih korisnika");
        System.out.println("19. Dohvatanje svih kategorija");
        System.out.println("20. Dohvatanje svih video snimaka");
        System.out.println("21. Dohvatanje kategorija za određeni video snimak");
        System.out.println("22. Dohvatanje svih paketa");
        System.out.println("23. Dohvatanje svih pretplata za korisnika");
        System.out.println("24. Dohvatanje svih gledanja za video snimak");
        System.out.println("25. Dohvatanje svih ocena za video snimak");
        System.out.println("26. Oporavljanje sistema");
        System.out.println("0. Zavrsetak rada");
        System.out.println("Vas unos? ");
    }

    public static int getUserChoice(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 0 && choice <= 26) {
                return choice;
            }
        }
        return -1;
    }

    public static void handleOption(int choice, Scanner scanner) {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        ApiServis service = retrofit.create(ApiServis.class);
        
        switch (choice) {
            case 1://Kreiranje grada
                //dohvatanje podataka
                String grad="";
                while (grad.equals("")) {
                    System.out.println("Unesite ime grada:");
                    grad = scanner.nextLine();
                }

                // HTTP request
                Call<String> kreirajGradCall = service.kreirajGrad(grad);
                kreirajGradCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 2://Kreiranje korisnika
                String imeKorisnika="";
                String email="";
                Integer godiste=0;
                String pol="";
                String imeGrada="";
                while (imeKorisnika.equals("")) {
                    System.out.println("Unesite ime Korisnika:");
                    imeKorisnika = scanner.nextLine();
                }
                while (email.equals("")) {
                    System.out.println("Unesite email:");
                    email = scanner.nextLine();
                }
                while (godiste.equals(0)) {
                    System.out.println("Unesite godiste:");
                    godiste = scanner.nextInt();
                    scanner.nextLine();
                }
                while (!(pol.equals("M")||pol.equals("Z"))) {
                    System.out.println("Unesite pol(M/Z):");
                    pol = scanner.nextLine();
                }
                while (imeGrada.equals("")) {
                    System.out.println("Unesite naziv grada:");
                    imeGrada = scanner.nextLine();
                }
                // HTTP request
                Call<String> kreirajKorisnikaCall = service.kreirajKorisnika(imeKorisnika, email, godiste, pol, imeGrada);
                kreirajKorisnikaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
             case 3://Promena email adrese za korisnika
                int korisnikId = 0;
                String noviEmail = "";
                while (korisnikId <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korisnikId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (noviEmail.equals("")) {
                    System.out.println("Unesite novi email:");
                    noviEmail = scanner.nextLine();
                }
                // HTTP request
                Call<String> promeniEmailCall = service.promeniEmailKorisniku(korisnikId, noviEmail);
                promeniEmailCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 4://Promena mesta za korisnika
                int korisnikIdMesta = 0;
                String novoMesto = "";
                while (korisnikIdMesta <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korisnikIdMesta = scanner.nextInt();
                    scanner.nextLine();
                }
                while (novoMesto.equals("")) {
                    System.out.println("Unesite novo mesto:");
                    novoMesto = scanner.nextLine();
                }
                // HTTP request
                Call<String> promeniMestoCall = service.promeniMestoKorisniku(korisnikIdMesta, novoMesto);
                promeniMestoCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 5://Kreiranje kategorije
                String nazivKategorije = "";
                while (nazivKategorije.equals("")) {
                    System.out.println("Unesite naziv kategorije:");
                    nazivKategorije = scanner.nextLine();
                }
                // HTTP request
                Call<String> kreirajKategorijuCall = service.kreirajKategoriju(nazivKategorije);
                kreirajKategorijuCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 6: // Kreiranje video snimka
                String nazivSnimka = "";
                int trajanjeSnimka = 0;
                int vlasnikId = 0;
                String datumPostavljanja = "";
                int vremePostavljanja = -1;

                while (nazivSnimka.equals("")) {
                    System.out.println("Unesite naziv video snimka:");
                    nazivSnimka = scanner.nextLine();
                }
                while (trajanjeSnimka <= 0) {
                    System.out.println("Unesite trajanje video snimka u sekundama:");
                    trajanjeSnimka = scanner.nextInt();
                    scanner.nextLine();
                }
                while (vlasnikId <= 0) {
                    System.out.println("Unesite ID vlasnika video snimka:");
                    vlasnikId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (datumPostavljanja.equals("")) {
                    System.out.println("Unesite datum postavljanja video snimka (YYYY-MM-DD):");//2024-02-09T12:30:45.000Z
                    datumPostavljanja = scanner.nextLine();
                }
                while (vremePostavljanja < 0 || vremePostavljanja>=86400) {
                    System.out.println("Unesite vreme postavljanja video snimka u sekundama:");
                    vremePostavljanja = scanner.nextInt();
                    scanner.nextLine();
                }

                // HTTP request
                Call<String> kreirajVideoCall = service.kreirajVideo(nazivSnimka, trajanjeSnimka, vlasnikId, datumPostavljanja, vremePostavljanja);
                kreirajVideoCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;

            case 7: // Promena naziva video snimka
                int videoId = 0;
                String noviNaziv = "";

                while (videoId <= 0) {
                    System.out.println("Unesite ID video snimka koji želite da promenite:");
                    videoId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (noviNaziv.equals("")) {
                    System.out.println("Unesite novi naziv video snimka:");
                    noviNaziv = scanner.nextLine();
                }

                // HTTP request
                Call<String> promeniNazivSnimkaCall = service.promeniNazivSnimka(videoId, noviNaziv);
                promeniNazivSnimkaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;

            case 8: // Dodavanje kategorije video snimku
                int katId = 0;
                int vidId = 0;

                while (katId <= 0) {
                    System.out.println("Unesite ID kategorije:");
                    katId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (vidId <= 0) {
                    System.out.println("Unesite ID video snimka:");
                    vidId = scanner.nextInt();
                    scanner.nextLine();
                }

                // HTTP request
                Call<String> dodajKategorijuSnimkuCall = service.dodajKategorijuSnimku(katId, vidId);
                dodajKategorijuSnimkuCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 9://Kreiranje paketa
                String nazivPaketa = "";
                int cenaPaketa = -1;
                while (nazivPaketa.equals("")) {
                    System.out.println("Unesite naziv paketa:");
                    nazivPaketa = scanner.nextLine();
                }
                while (cenaPaketa < 0) {
                    System.out.println("Unesite cenu paketa:");
                    cenaPaketa = scanner.nextInt();
                    scanner.nextLine();
                }
                // HTTP request
                Call<String> kreirajPaketCall = service.kreirajPaket(nazivPaketa, cenaPaketa);
                kreirajPaketCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 10://Promena mesečne cene za paket
                int paketId = 0;
                int novaCena = -1;
                while (paketId <= 0) {
                    System.out.println("Unesite ID paketa:");
                    paketId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (novaCena < 0) {
                    System.out.println("Unesite novu cenu paketa:");
                    novaCena = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> promeniCenuPaketaCall = service.promeniCenuPaketa(paketId, novaCena);
                promeniCenuPaketaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 11: // Kreiranje pretplate korisnika na paket
                int korisnId = 0;
                int pakeId = 0;
                String datumPocetka = "";
                int vremePocetka = -1;
                while (korisnId <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korisnId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (pakeId <= 0) {
                    System.out.println("Unesite ID paketa:");
                    pakeId = scanner.nextInt();
                    scanner.nextLine();
                }
                while (datumPocetka.equals("")) {
                    System.out.println("Unesite datum pocetka(YYYY-MM-DD):");
                    datumPocetka = scanner.nextLine();
                }
                while (vremePocetka < 0 || vremePocetka>=86400) {
                    System.out.println("Unesite vreme pocetka u sekundama:");
                    vremePocetka = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> kreirajPretplatuCall = service.kreirajPretplatu(korisnId, pakeId, datumPocetka, vremePocetka);
                kreirajPretplatuCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 12://Kreiranje gledanja video snimka od strane korisnika
                int korid=0,vidid=0,vrePoc=-1,sekPoc=-1,sekOdg=0;
                String datPoc="";
                while (korid <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korid = scanner.nextInt();
                    scanner.nextLine();
                }
                while (vidid <= 0) {
                    System.out.println("Unesite ID videa:");
                    vidid = scanner.nextInt();
                    scanner.nextLine();
                }
                while (datPoc.equals("")) {
                    System.out.println("Unesite datum pocetka gledanja(GGGG-MM-DD):");
                    datPoc = scanner.nextLine();
                }
                while (vrePoc < 0 || vrePoc>86400) {
                    System.out.println("Unesite vreme pocetka gledanja u sekundama:");
                    vrePoc = scanner.nextInt();
                    scanner.nextLine();
                }
                while (sekPoc < 0) {
                    System.out.println("Unesite sekund pocetka snimka:");
                    sekPoc = scanner.nextInt();
                    scanner.nextLine();
                }
                while (sekOdg <= 0) {
                    System.out.println("Unesite odgledane sekunde:");
                    sekOdg = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> kreirajGledanjeCall = service.kreirajGledanje(korid, vidid, datPoc, vrePoc, sekPoc, sekOdg);
                kreirajGledanjeCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 13:// Kreiranje ocene korisnika za video snimak
                int korIdOcena = 0, videoIdOcena = 0, ocena = 0, vremeOcene = -1;
                String datumOcene = "";
                while (korIdOcena <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korIdOcena = scanner.nextInt();
                    scanner.nextLine();
                }
                while (videoIdOcena <= 0) {
                    System.out.println("Unesite ID videa:");
                    videoIdOcena = scanner.nextInt();
                    scanner.nextLine();
                }
                while (ocena <= 0 || ocena > 5) {
                    System.out.println("Unesite ocenu (1-5):");
                    ocena = scanner.nextInt();
                    scanner.nextLine();
                }
                while (datumOcene.equals("")) {
                    System.out.println("Unesite datum ocene(GGGG-MM-DD):");
                    datumOcene = scanner.nextLine();
                }
                while (vremeOcene < 0 || vremeOcene >= 86400) {
                    System.out.println("Unesite vreme ocene u sekundama:");
                    vremeOcene = scanner.nextInt();
                    scanner.nextLine();
                }
                // HTTP request
                Call<String> kreirajOcenuCall = service.kreirajOcenu(korIdOcena, videoIdOcena, ocena, datumOcene, vremeOcene);
                kreirajOcenuCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;

            case 14:// Menjanje ocene korisnika za video snimak
                int korIdPromeniOcenu = 0, videoIdPromeniOcenu = 0, novaOcena = 0;
                while (korIdPromeniOcenu <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korIdPromeniOcenu = scanner.nextInt();
                    scanner.nextLine();
                }
                while (videoIdPromeniOcenu <= 0) {
                    System.out.println("Unesite ID videa:");
                    videoIdPromeniOcenu = scanner.nextInt();
                    scanner.nextLine();
                }
                while (novaOcena <= 0 || novaOcena > 5) {
                    System.out.println("Unesite novu ocenu (1-5):");
                    novaOcena = scanner.nextInt();
                    scanner.nextLine();
                }
                // HTTP request
                Call<String> promeniOcenuCall = service.promeniOcenu(korIdPromeniOcenu, videoIdPromeniOcenu, novaOcena);
                promeniOcenuCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;

            case 15:// Brisanje ocene korisnika za video snimak
                int korIdObrisiOcenu = 0, videoIdObrisiOcenu = 0;
                while (korIdObrisiOcenu <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korIdObrisiOcenu = scanner.nextInt();
                    scanner.nextLine();
                }
                while (videoIdObrisiOcenu <= 0) {
                    System.out.println("Unesite ID videa:");
                    videoIdObrisiOcenu = scanner.nextInt();
                    scanner.nextLine();
                }
                // HTTP request
                Call<String> obrisiOcenuCall = service.obrisiOcenu(korIdObrisiOcenu, videoIdObrisiOcenu);
                obrisiOcenuCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;

            case 16:// Brisanje video snimka od strane korisnika koji ga je kreirao
                int korisnikIdObrisiVideo = 0, videoIdObrisiVideo = 0;
                while (korisnikIdObrisiVideo <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korisnikIdObrisiVideo = scanner.nextInt();
                    scanner.nextLine();
                }
                while (videoIdObrisiVideo <= 0) {
                    System.out.println("Unesite ID videa:");
                    videoIdObrisiVideo = scanner.nextInt();
                    scanner.nextLine();
                }
                // HTTP request
                Call<String> obrisiVideoCall = service.obrisiVideo(korisnikIdObrisiVideo, videoIdObrisiVideo);
                obrisiVideoCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 17://Dohvatanje svih mesta
                //Nema parametre
                //HTTP request
                Call<String> dohvatiSvaMestaCall = service.dohvatiSvaMesta();
                dohvatiSvaMestaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Baza je prazna!");
                            }
                            else{
                                System.out.println("Mesta:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    System.out.println(responseDataLine);
                                }
                            }
                            
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 18://Dohvatanje svih korisnika
                //Nema parametre
                //HTTP request
                Call<String> dohvatiSveKorisnikeCall = service.dohvatiSveKorisnike();
                dohvatiSveKorisnikeCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Baza je prazna!");
                            }
                            else{
                                System.out.println("Korisnici:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    System.err.println(responseDataLine);
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 19://Dohvatanje svih kategorija
                //Nema parametre
                //HTTP request
                Call<String> dohvatiSveKatergorijeCall = service.dohvatiSveKatergorije();
                dohvatiSveKatergorijeCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Baza je prazna!");
                            }
                            else{
                                System.out.println("Kategorije:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    System.out.println(responseDataLine);
                                }
                            }
                            
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 20://Dohvatanje svih video snimaka
                //Nema parametre
                //HTTP request
                Call<String> dohvatiSveVideoSnimkeCall = service.dohvatiSveVideoSnimke();
                dohvatiSveVideoSnimkeCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Baza je prazna!");
                            }
                            else{
                                System.out.println("Video snimci:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    String[] parts = responseDataLine.split(",");
                                    
                                    int trajanjeMin=Integer.parseInt(parts[3])/60;
                                    int trajanjeSek=Integer.parseInt(parts[3])%60;
                                    
                                    String[] subparts = parts[4].split("\\s+");
                                    String dateEnd=subparts[0]+" "+subparts[2]+" "+subparts[1]+" "+subparts[5];
                                    
                                    int vremeString=Integer.parseInt(parts[5]);
                                    int hours = vremeString / 3600;
                                    int remainingSeconds = vremeString % 3600;
                                    int minutes = remainingSeconds / 60;
                                    String formattedTime = String.format("%02d:%02d", hours, minutes);
                                    
                                    System.out.println(parts[0]+","+parts[1]+","+trajanjeMin+":"+trajanjeSek+","+parts[2]+","+dateEnd+","+formattedTime+"h");
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 21://Dohvatanje kategorija za određeni video snimak
                int vidIdSveKat=0;
                while (vidIdSveKat <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    vidIdSveKat = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> dohvatiKategorijeSnimkaCall = service.dohvatiKategorijeSnimka(vidIdSveKat);
                dohvatiKategorijeSnimkaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Snimak nije ni u jednoj kategoriji!");
                            }
                            else{
                                System.out.println("Kategorije datog snimka:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    System.out.println(responseDataLine);
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 22://Dohvatanje svih paketa

                //HTTP request
                Call<String> dohvatiSvePaketeCall = service.dohvatiSvePakete();
                dohvatiSvePaketeCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Baza je prazna!");
                            }
                            else
                            {
                                System.out.println("Paketi:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    System.out.println(responseDataLine);
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 23://Dohvatanje svih pretplata za korisnika
                int korIdSvePretplate=0;
                while (korIdSvePretplate <= 0) {
                    System.out.println("Unesite ID korisnika:");
                    korIdSvePretplate = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> dohvatiSvePretplateKorisnikaCall = service.dohvatiSvePretplateKorisnika(korIdSvePretplate);
                dohvatiSvePretplateKorisnikaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Korisnik nije pretplacen!");
                            }
                            else{
                                System.out.println("Pretplate datog korisnika:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    String[] parts = responseDataLine.split(",");
                                    
                                    String[] subparts = parts[4].split("\\s+");
                                    String dateEnd=subparts[0]+" "+subparts[2]+" "+subparts[1]+" "+subparts[5];
                                    
                                    int vremeString=Integer.parseInt(parts[5]);
                                    int hours = vremeString / 3600;
                                    int remainingSeconds = vremeString % 3600;
                                    int minutes = remainingSeconds / 60;
                                    String formattedTime = String.format("%02d:%02d", hours, minutes);
                                    
                                    System.out.println(parts[0]+","+parts[1]+","+parts[2]+","+parts[3]+","+dateEnd+","+formattedTime+"h");
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 24://Dohvatanje svih gledanja za video snimak
                int vidIdSvaGledanja=0;
                while (vidIdSvaGledanja <= 0) {
                    System.out.println("Unesite ID video snimka:");
                    vidIdSvaGledanja = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> dohvatiSvaGledanjaSnimkaCall = service.dohvatiSvaGledanjaSnimka(vidIdSvaGledanja);
                dohvatiSvaGledanjaSnimkaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null)
                            {
                                System.out.println("Snimak nije gledan!");
                            }
                            else{
                                System.out.println("Sva gledanja datog snimka:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    String[] parts = responseDataLine.split(",");
                                    
                                    String[] subparts = parts[3].split("\\s+");
                                    String dateEnd=subparts[0]+" "+subparts[2]+" "+subparts[1]+" "+subparts[5];
                                    
                                    int vremeString=Integer.parseInt(parts[4]);
                                    int hours = vremeString / 3600;
                                    int remainingSeconds = vremeString % 3600;
                                    int minutes = remainingSeconds / 60;
                                    String formattedTime = String.format("%02d:%02d", hours, minutes);
                                    
                                    System.out.println(parts[0]+","+parts[1]+","+parts[2]+","+dateEnd+","+formattedTime+"h"+","+parts[5]+","+parts[6]);
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 25://Dohvatanje svih ocena za video snimak
                int snId=0;
                while (snId <= 0) {
                    System.out.println("Unesite ID snimka:");
                    snId = scanner.nextInt();
                    scanner.nextLine();
                }
                //HTTP request
                Call<String> dohvatiOceneSnimkaCall = service.dohvatiSveOceneSnimka(snId);
                dohvatiOceneSnimkaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            if(responseData==null){
                                System.err.println("Video nije ocenjen!");
                            }
                            else{
                                System.out.println("Sve ocene datog snimka:");
                                String[] responseDataParts = responseData.split("\\+");
                                for (String responseDataLine : responseDataParts) {
                                    String[] parts = responseDataLine.split(",");
                                    
                                    String[] subparts = parts[3].split("\\s+");
                                    String dateEnd=subparts[0]+" "+subparts[2]+" "+subparts[1]+" "+subparts[5];
                                    
                                    int vremeString=Integer.parseInt(parts[4]);
                                    int hours = vremeString / 3600;
                                    int remainingSeconds = vremeString % 3600;
                                    int minutes = remainingSeconds / 60;
                                    String formattedTime = String.format("%02d:%02d", hours, minutes);
                                    
                                    System.out.println(parts[0]+","+parts[1]+","+parts[2]+","+dateEnd+","+formattedTime+"h"+","+parts[5]);
                                }
                            }
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            case 26://Oporavljanje sistema
                //Nema parametre
                //HTTP request
                Call<String> oporavljanjesistemaCall = service.obrisiZaostalePoruke();
                oporavljanjesistemaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseData = response.body();
                            System.out.println(responseData);
                        } else {
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                break;
            default://
                System.out.println("Nevalidna opcija");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            displayMenu();
            
            int choice = getUserChoice(scanner);
            if (choice == -1) {
                System.out.println("Nevalidan unos. Unesite broj izmedju 1 i 26.");
            } else if(choice == 0) {
                System.exit(0);
            }else {
                handleOption(choice, scanner);
            }
        }
    }
}