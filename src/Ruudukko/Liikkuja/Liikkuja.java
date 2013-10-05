package Ruudukko.Liikkuja;

import Polunlaskenta.Polunlaskija;
import Ruudukko.Ruudukko;
import Tietorakenteita.KoordinaattiJono;
import java.awt.Color;

public class Liikkuja {

    private Koordinaatti sijainti;
    private Koordinaatti kohde;
    private KoordinaattiJono path;
    private Color vari;
    private Polunlaskija polunlaskija;

    public Liikkuja(Color vari, int sijaintiX, int sijaintiY, Ruudukko ruudukko) {
        this.vari = vari;
        this.sijainti = new Koordinaatti(sijaintiX, sijaintiY);
        this.polunlaskija = new Polunlaskija(this, ruudukko);
        this.kohde = null;
    }

    public void setPath(KoordinaattiJono path) {
        this.path = path;
    }

    public void lisaaPathiin(Koordinaatti k) {
        this.path.add(k);
    }

    public void liiku() {
        sijainti = path.Dequeue();
    }

    public Koordinaatti getSijainti() {
        return sijainti;
    }

    public Color getVari() {
        return vari;
    }

    public KoordinaattiJono getPath() {
        return path;
    }

    public void setSijainti(Koordinaatti sijainti) {
        this.sijainti = sijainti;
    }

    public void setVari(Color vari) {
        this.vari = vari;
    }

    public void vaihdaKohteeksi(Koordinaatti koordinaatti) {
        this.kohde = koordinaatti;
        this.laskeReitti(koordinaatti);
    }

    public void laskeReittiUudelleen() {
        if (kohde != null) {
            this.laskeReitti(kohde);
        }
    }

    private void laskeReitti(Koordinaatti k) {
        this.polunlaskija.asetaKohdeAstar(k);
    }
}
