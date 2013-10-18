package Tietorakenteita;

import Ruudukko.Liikkuja.Koordinaatti;

/**
 * KoordinaattiJono on Koordinaatti-luokan olioita säilyttävä jono-tietorakenne.
 * Luokka on tehty pääasiassa kevään 2013 Tietorakenteet ja algoritmit -kurssin
 * materiaalin pohjalta.
 */
public class KoordinaattiJono {

    private Koordinaatti[] taulukko;
    private int jononAlkupaa;
    private int jononLoppupaa;
    private boolean taynna;

    public KoordinaattiJono(int koko) {
        this.taulukko = new Koordinaatti[koko];
        this.jononAlkupaa = 0;
        this.jononLoppupaa = 0;
        this.taynna = false;
    }

    public void add(Koordinaatti k) {
        this.taulukko[jononLoppupaa] = k;
        jononLoppupaa++;
        if (jononLoppupaa > taulukko.length) {
            jononLoppupaa = 0;
        }
        if (jononAlkupaa == jononLoppupaa) {
            this.taynna = true;
        }
    }

    public Koordinaatti Dequeue() {
        if (jononAlkupaa == jononLoppupaa) {
            return null;
        }

        Koordinaatti palautettava = this.taulukko[jononAlkupaa];
        this.taulukko[jononAlkupaa] = null;

        jononAlkupaa++;
        if (jononAlkupaa > taulukko.length) {
            jononAlkupaa = 0;
        }

        this.taynna = false;

        return palautettava;
    }

    public boolean isTaynna() {
        return taynna;
    }

    public boolean isEmpty() {
        if (!taynna && jononAlkupaa == jononLoppupaa) {
            return true;
        }
        return false;
    }

    public Koordinaatti[] getKoordinaatit() {
        return this.taulukko;
    }

    public void tyhjenna() {
        this.taulukko = new Koordinaatti[taulukko.length];
        this.jononAlkupaa = 0;
        this.jononLoppupaa = 0;
        this.taynna = false;
    }
}
