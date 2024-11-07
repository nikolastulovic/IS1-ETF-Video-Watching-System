package entiteti;

import entiteti.Gledanje;
import entiteti.Korisnik;
import entiteti.Ocena;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-02-10T01:06:16")
@StaticMetamodel(Videosnimak.class)
public class Videosnimak_ { 

    public static volatile SingularAttribute<Videosnimak, Korisnik> vlasnikId;
    public static volatile ListAttribute<Videosnimak, Gledanje> gledanjeList;
    public static volatile SingularAttribute<Videosnimak, Integer> trajanje;
    public static volatile ListAttribute<Videosnimak, Ocena> ocenaList;
    public static volatile SingularAttribute<Videosnimak, String> naziv;
    public static volatile SingularAttribute<Videosnimak, Integer> videoSnimakId;
    public static volatile SingularAttribute<Videosnimak, Date> datumPostavke;
    public static volatile SingularAttribute<Videosnimak, Integer> vremePostavke;

}