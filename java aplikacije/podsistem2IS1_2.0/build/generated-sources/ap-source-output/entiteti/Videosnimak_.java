package entiteti;

import entiteti.Kategorijavideo;
import entiteti.Korisnik;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-02-09T20:10:54")
@StaticMetamodel(Videosnimak.class)
public class Videosnimak_ { 

    public static volatile SingularAttribute<Videosnimak, Korisnik> vlasnikId;
    public static volatile ListAttribute<Videosnimak, Kategorijavideo> kategorijavideoList;
    public static volatile SingularAttribute<Videosnimak, Date> datumPostavljanja;
    public static volatile SingularAttribute<Videosnimak, Integer> trajanje;
    public static volatile SingularAttribute<Videosnimak, Integer> vremePostavljanja;
    public static volatile SingularAttribute<Videosnimak, String> naziv;
    public static volatile SingularAttribute<Videosnimak, Integer> videoSnimakId;

}