package Tietorakenteita;

public class KasvavaTaulukko {

    private Object[] taulukko;
    private int indeksi;

    public KasvavaTaulukko() {
        this.taulukko = new Object[10];
    }

    public void add(Object o) {
        taulukko[indeksi] = o;
        indeksi++;

        if (indeksi > taulukko.length) {
            this.tuplaaTaulukonKoko();
        }
    }

    public void set(int indeksi, Object o) {
        try {
            taulukko[indeksi] = o;
        } catch (Exception e) {
        }
    }

    public void tuplaaTaulukonKoko() {
        Object[] uusiTaulukko = new Object[taulukko.length * 2];

        for (int i = 0; i < taulukko.length; i++) {
            uusiTaulukko[i] = taulukko[i];
        }

        taulukko = uusiTaulukko;
    }

    public Object get(int ind) {
        try {
            return this.taulukko[ind];
        } catch (Exception e) {
            return null;
        }
    }
}
