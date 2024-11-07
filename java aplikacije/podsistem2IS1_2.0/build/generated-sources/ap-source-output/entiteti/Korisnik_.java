package entiteti;

import entiteti.Grad;
import entiteti.Videosnimak;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-02-09T20:10:54")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, Integer> godiste;
    public static volatile SingularAttribute<Korisnik, Integer> korisnikId;
    public static volatile SingularAttribute<Korisnik, String> pol;
    public static volatile ListAttribute<Korisnik, Videosnimak> videosnimakList;
    public static volatile SingularAttribute<Korisnik, String> email;
    public static volatile SingularAttribute<Korisnik, Grad> gradId;

}