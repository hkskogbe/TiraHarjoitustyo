
import Grafiikka.Grafiikka;
import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Liikkuja.Liikkuja;
import Ruudukko.Paivittaja;
import Ruudukko.Ruudukko;
import Tietorakenteita.KoordinaattiJono;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {

        String ruudukkoString;

        ruudukkoString = ""
                + "            "
                + "            "
                + "   xxx      "
                + "      xx    "
                + "         x  "
                + "         x  "
                + "         x  "
                + "         x  "
                + "         x  "
                + "            ";

        Ruudukko r = new Ruudukko(10, 12, 50, 50, 5, Color.DARK_GRAY, ruudukkoString);
        Liikkuja ligu = new Liikkuja(Color.ORANGE, 0, 1, r);
        r.setLiikkuja(ligu);

        KoordinaattiJono matka = new KoordinaattiJono(40);
        matka.add(new Koordinaatti(0, 2));
        matka.add(new Koordinaatti(0, 3));
        matka.add(new Koordinaatti(1, 3));
        matka.add(new Koordinaatti(1, 4));

        ligu.setPath(matka);

        Grafiikka g = new Grafiikka(r, 580, 665);


        Paivittaja p = new Paivittaja(r);

        SwingUtilities.invokeLater(g);

        p.pyori();
    }
}
