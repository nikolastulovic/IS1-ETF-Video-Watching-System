delete from podsistem1is1.korisnik k where k.korisnikId!=0;
delete from podsistem1is1.grad g where g.gradId!=0;

delete from podsistem2is1.kategorijavideo k where k.kategorijaId!=0;
delete from podsistem2is1.kategorija k where k.kategorijaId!=0;
delete from podsistem2is1.videosnimak k where k.videoSnimakId!=0;
delete from podsistem2is1.korisnik k where k.korisnikId!=0;
delete from podsistem2is1.grad g where g.gradId!=0;

delete from podsistem3is1.ocena o where o.ocenaId!=0;
delete from podsistem3is1.gledanje g where g.gledanjeId!=0;
delete from podsistem3is1.pretplata p where p.pretplataId!=0;
delete from podsistem3is1.paket p where p.paketId!=0;
delete from podsistem3is1.videosnimak k where k.videoSnimakId!=0;
delete from podsistem3is1.korisnik k where k.korisnikId!=0;
delete from podsistem3is1.grad g where g.gradId!=0;

ALTER TABLE podsistem1is1.grad AUTO_INCREMENT = 1;
ALTER TABLE podsistem1is1.korisnik AUTO_INCREMENT = 1;
ALTER TABLE podsistem2is1.kategorijavideo AUTO_INCREMENT = 1;
ALTER TABLE podsistem2is1.kategorija AUTO_INCREMENT = 1;
ALTER TABLE podsistem2is1.videosnimak AUTO_INCREMENT = 1;
ALTER TABLE podsistem2is1.korisnik AUTO_INCREMENT = 1;
ALTER TABLE podsistem2is1.grad AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.ocena AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.gledanje AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.pretplata AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.paket AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.videosnimak AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.korisnik AUTO_INCREMENT = 1;
ALTER TABLE podsistem3is1.grad AUTO_INCREMENT = 1;