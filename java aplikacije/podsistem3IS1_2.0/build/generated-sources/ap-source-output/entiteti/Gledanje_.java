package entiteti;

import entiteti.Korisnik;
import entiteti.Videosnimak;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-02-10T01:06:16")
@StaticMetamodel(Gledanje.class)
public class Gledanje_ { 

    public static volatile SingularAttribute<Gledanje, Integer> gledanjeId;
    public static volatile SingularAttribute<Gledanje, Integer> sekundPocetkaSnimka;
    public static volatile SingularAttribute<Gledanje, Date> datumPocetka;
    public static volatile SingularAttribute<Gledanje, Integer> vremePocetka;
    public static volatile SingularAttribute<Gledanje, Integer> sekundeOdgledano;
    public static volatile SingularAttribute<Gledanje, Videosnimak> videoSnimakId;
    public static volatile SingularAttribute<Gledanje, Korisnik> korisnikId;

}