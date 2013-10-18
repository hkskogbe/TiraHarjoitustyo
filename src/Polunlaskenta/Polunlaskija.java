package Polunlaskenta;

import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Liikkuja.Liikkuja;
import Ruudukko.Node;
import Ruudukko.Ruudukko;
import Tietorakenteita.KoordinaattiJono;
import Tietorakenteita.KoordinaattiPino;
import Tietorakenteita.NodeHeap;

/**
 * Luokka, jossa algoritmin polunlaskenta toteutetaan. Polunlaskijalle kuuluu
 * Liikkuja, jolle polku lasketaan.
 */
public class Polunlaskija {

    private Liikkuja liikkuja;
    private Ruudukko ruudukko;
    private Koordinaatti kohde;
    private Node[][] nodet;
    private int naapurienEtaisyys;
    private NodeHeap openset;

    public Polunlaskija(Liikkuja liikkuja, Ruudukko ruudukko) {
        this.liikkuja = liikkuja;
        this.ruudukko = ruudukko;
        this.kohde = null;
        this.nodet = new Node[ruudukko.getRuudukonKorkeus()][ruudukko.getRuudukonLeveys()];
        this.naapurienEtaisyys = 1;
    }

    /**
     * A*-algoritmin sovellus polun laskemiseksi liikkujalle. Algoritmin
     * toteutus mukailee wikipedia-artikkelia en.wikipedia.org/wiki/A* ja
     * käyttää siinä esitetyn pseudokoodin nimeämistä.
     *
     * parametri kohde on liikkujalle asetettava kohde
     */
    public void laskeAstar(Koordinaatti kohde) {

        alustetaanNodet();

        this.kohde = kohde;
        this.openset = new NodeHeap((ruudukko.getRuudukonKorkeus()) * (ruudukko.getRuudukonLeveys()), kohde);

        // Laitetaan liikkujan alkunode opensettiin
        openset.insert(nodet[liikkuja.getSijainti().getY()][liikkuja.getSijainti().getX()]);

        Node current = null;

        while (!openset.isEmpty()) {

            current = openset.delMin();

            // Jos current == kohde rakennetaan polku ja lopetetaan while-loop
            if (current == nodet[kohde.getY()][kohde.getX()]) {
                this.rakennaPolku();
                return;
            }

            current.setClosed();

            // Tarkastellaan currentin naapurinodeja

            Node naapuri;

            int currentY = current.getKoordinaatti().getY();
            int currentX = current.getKoordinaatti().getX();

            //ylös
            if (currentY > 0) {
                if (!ruudukko.onSeina(currentY - 1, currentX)) {
                    naapuri = nodet[currentY - 1][currentX];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }
            //oikea
            if (currentX < this.ruudukko.getRuudukonLeveys() - 1) {
                if (!ruudukko.onSeina(currentY, currentX + 1)) {
                    naapuri = nodet[currentY][currentX + 1];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }
            //alas
            if (currentY < this.ruudukko.getRuudukonKorkeus() - 1) {
                if (!ruudukko.onSeina(currentY + 1, currentX)) {
                    naapuri = nodet[currentY + 1][currentX];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }
            //vasen
            if (currentX > 0) {
                if (!ruudukko.onSeina(currentY, currentX - 1)) {
                    naapuri = nodet[currentY][currentX - 1];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }


        }
        // Jos päästään tähän niin kohdetta ei voi asettaa
        // Pysäytetään liikkuja
        liikkuja.setPath(new KoordinaattiJono(0));

    }

    /**
     * Testaamista varten polunlaskennan nodejen alustaminen on erotettu
     * metodista
     */
    public boolean laskeAstarTesti(Koordinaatti kohde) {
        // Oletetaan nodejen olevan alustettu

        this.kohde = kohde;
        this.openset = new NodeHeap((ruudukko.getRuudukonKorkeus()) * (ruudukko.getRuudukonLeveys()), kohde);

        // Laitetaan liikkujan alkunode opensettiin
        openset.insert(nodet[liikkuja.getSijainti().getY()][liikkuja.getSijainti().getX()]);

        Node current = null;

        while (!openset.isEmpty()) {

            current = openset.delMin();

            // Jos current == kohde rakennetaan polku ja lopetetaan while-loop
            if (current == nodet[kohde.getY()][kohde.getX()]) {
                return true;
            }

            current.setClosed();

            // Tarkastellaan currentin naapurinodeja

            Node naapuri;

            int currentY = current.getKoordinaatti().getY();
            int currentX = current.getKoordinaatti().getX();

            //ylös
            if (currentY > 0) {
                if (!ruudukko.onSeina(currentY - 1, currentX)) {
                    naapuri = nodet[currentY - 1][currentX];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }
            //oikea
            if (currentX < this.ruudukko.getRuudukonLeveys() - 1) {
                if (!ruudukko.onSeina(currentY, currentX + 1)) {
                    naapuri = nodet[currentY][currentX + 1];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }
            //alas
            if (currentY < this.ruudukko.getRuudukonKorkeus() - 1) {
                if (!ruudukko.onSeina(currentY + 1, currentX)) {
                    naapuri = nodet[currentY + 1][currentX];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }
            //vasen
            if (currentX > 0) {
                if (!ruudukko.onSeina(currentY, currentX - 1)) {
                    naapuri = nodet[currentY][currentX - 1];
                    this.tarkasteleNaapuria(naapuri, current);
                }
            }


        }
        // Jos päästään tähän niin kohdetta ei voi asettaa
        // Pysäytetään liikkuja
        return false;
    }

    private void tarkasteleNaapuria(Node naapuri, Node current) {
        int alustavaGScore = current.getgScore() + this.naapurienEtaisyys;

        if (naapuri.isClosed()) {
            return;
        }
        if (alustavaGScore >= naapuri.getgScore()) {
            return;
        }

        naapuri.setParent(current.getKoordinaatti());
        naapuri.setgScore(alustavaGScore);
        if (!naapuri.isOpen()) {
            openset.insert(naapuri);
        }
    }

    /**
     * Metodi polun laskemiseksi sen pohjalta, että jokainen Node tietää
     * parent-nodensa. Metodi käyttää KoordinaattiPino-luokkaa polun
     * kääntämiseksi Liikkujalle ymmärrettävään muotoon.
     */
    private void rakennaPolku() {
        int polunPituus = 1;
        Koordinaatti vika = kohde;
        Koordinaatti eka = liikkuja.getSijainti();
        Koordinaatti nyt = vika;

        // Lasketaan ensin polun pituus
        while (!samat(eka, nyt)) {
            nyt = nodet[nyt.getY()][nyt.getX()].getParent();
            polunPituus++;
        }

        // Koordinaatit pinoon, jonka avulla ne käännetään jonoksi liikkujalle
        KoordinaattiPino kaantaja = new KoordinaattiPino(polunPituus);
        nyt = vika;

        while (!samat(eka, nyt)) {
            kaantaja.lisaa(nyt);
            nyt = nodet[nyt.getY()][nyt.getX()].getParent();
        }
        kaantaja.lisaa(eka);

        KoordinaattiJono jono = new KoordinaattiJono(polunPituus);

        while (!kaantaja.isTyhja()) {
            jono.add(kaantaja.pop());
        }

        liikkuja.setPath(jono);
    }

    /**
     * Tarkistaa kahden koordinaatin yhtäsuuruuden
     */
    private boolean samat(Koordinaatti a, Koordinaatti b) {
        if (a.getX() != b.getX()) {
            return false;
        }
        if (a.getY() != b.getY()) {
            return false;
        }
        return true;
    }

    /**
     * Metodi manhattan-etäisyyden laskemiseen algoritmin etäisyysarviota h(v)
     * varten
     */
    public int manhattanEtaisyys(Koordinaatti a, Koordinaatti b) {
        int etaisyys;

        etaisyys = Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());

        return etaisyys;
    }

    public void alustetaanNodet() {
        for (int y = 0; y < nodet.length; y++) {
            for (int x = 0; x < nodet[0].length; x++) {
                nodet[y][x] = new Node(new Koordinaatti(x, y));
            }
        }
    }
}
