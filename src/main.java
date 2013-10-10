
import Grafiikka.Grafiikka;
import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Liikkuja.Liikkuja;
import Ruudukko.Node;
import Ruudukko.Paivittaja;
import Ruudukko.Ruudukko;
import Tietorakenteita.KoordinaattiJono;
import Tietorakenteita.NodeHeap;
import java.awt.Color;
import java.util.Random;
import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {

        
       

        Random rng = new Random();
        StringBuilder ruudukkoString;

        ruudukkoString = new StringBuilder(" ");
        for (int i = 0; i < 50*60-1; i++) {
            if (rng.nextDouble()<0.3){
                ruudukkoString.append('x');
            } else {
                ruudukkoString.append(" ");
            }
        }
// Alla viritelmä oman ruudukon piirtelyyn, testailin tällä
//                + "            "
//                + "            "
//                + "            "
//                + "            "
//                + "            "
//                + "            "
//                + "            "
//                + "            "
//                + "            "
//                + "            ";

        Ruudukko r = new Ruudukko(50, 60, 10, 10, 1, Color.DARK_GRAY, ruudukkoString.toString());
        Liikkuja liikkuja = new Liikkuja(Color.CYAN, 0, 0, r);
        r.setLiikkuja(liikkuja);

        KoordinaattiJono matka = new KoordinaattiJono(300);
//        matka.add(new Koordinaatti(0, 2));
//        matka.add(new Koordinaatti(0, 3));
//        matka.add(new Koordinaatti(1, 3));
//        matka.add(new Koordinaatti(1, 4));
        liikkuja.setPath(matka);

        Grafiikka g = new Grafiikka(r, 581, 665, 10, 1);


        Paivittaja p = new Paivittaja(r);

        SwingUtilities.invokeLater(g);

        p.pyori();
    }
}
