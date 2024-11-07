package entiteti;

import entiteti.Korisnik;
import entiteti.Videosnimak;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-02-10T01:06:16")
@StaticMetamodel(Ocena.class)
public class Ocena_ { 

    public static volatile SingularAttribute<Ocena, Videosnimak> videosnimakId;
    public static volatile SingularAttribute<Ocena, Integer> vremeOcene;
    public static volatile SingularAttribute<Ocena, Integer> ocenaId;
    public static volatile SingularAttribute<Ocena, Korisnik> korisnikId;
    public static volatile SingularAttribute<Ocena, Date> datumOcene;
    public static volatile SingularAttribute<Ocena, Integer> ocena;

}