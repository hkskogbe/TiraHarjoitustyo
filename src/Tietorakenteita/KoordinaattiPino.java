package Tietorakenteita;

import Ruudukko.Liikkuja.Koordinaatti;

/**
 * KoordinaattiPino on Koordinaatti-luokan olioita säilyttävä pino-tietorakenne.
 * Luokka on tehty pääasiassa kevään 2013 Tietorakenteet ja algoritmit -kurssin
 * materiaalin pohjalta.
 */
public class KoordinaattiPino {

    private Koordinaatti[] taulukko;
    private int indeksi;

    public KoordinaattiPino(int pituus) {
        this.taulukko = new Koordinaatti[pituus];
        indeksi = 0;
    }

    public void lisaa(Koordinaatti k) {
        taulukko[indeksi] = k;
        indeksi++;
    }

    public Koordinaatti pop() {
        indeksi--;
        Koordinaatti palautettava = taulukko[indeksi];
        taulukko[indeksi] = null;
        return palautettava;
    }

    public boolean isTyhja() {
        if (indeksi == 0) {
            return true;
        }
        return false;
    }
}
