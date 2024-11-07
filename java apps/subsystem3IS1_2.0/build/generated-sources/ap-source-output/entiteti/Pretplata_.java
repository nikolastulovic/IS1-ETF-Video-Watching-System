package entiteti;

import entiteti.Korisnik;
import entiteti.Paket;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-02-10T01:06:16")
@StaticMetamodel(Pretplata.class)
public class Pretplata_ { 

    public static volatile SingularAttribute<Pretplata, Integer> pretplataId;
    public static volatile SingularAttribute<Pretplata, Date> datumPocetka;
    public static volatile SingularAttribute<Pretplata, Integer> vremePocetka;
    public static volatile SingularAttribute<Pretplata, Integer> cenaPretplate;
    public static volatile SingularAttribute<Pretplata, Paket> paketId;
    public static volatile SingularAttribute<Pretplata, Korisnik> korisnikId;

}