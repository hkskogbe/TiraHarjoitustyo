package Klikkaaminen;

import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Ruudukko;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Klikkaaja implements MouseListener {

    private Ruudukko ruudukko;
    private int resoluutioX;
    private int resoluutioY;
    private boolean osuureunaan;
    private int ruudunKoko;
    private int valinKoko;

    public Klikkaaja(Ruudukko ruudukko, int resoluutioX, int resoluutioY, int ruudunKoko, int valinKoko) {
        this.ruudukko = ruudukko;
        this.resoluutioY = resoluutioY;
        this.resoluutioX = resoluutioX;
        this.ruudunKoko = ruudunKoko;
        this.valinKoko = valinKoko;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int ruudukonY = 0;
        int ruudukonX = 0;

        osuureunaan = false;
        int ruutuX = haeX(e, ruudukonX);
        int ruutuY = haeY(e, ruudukonY);

        if (!osuureunaan) {
            if (e.isShiftDown()) {
                shiftPress(e, ruutuX, ruutuY);
            } else {
                noModPress(e, ruutuX, ruutuY);
            }
            ruudukko.repaint();
        }
    }

    private void noModPress(MouseEvent e, int ruutuX, int ruutuY) {
        if (!ruudukko.onSeina(ruutuY, ruutuX)) {
            ruudukko.getLiikkuja().vaihdaKohteeksi(new Koordinaatti(ruutuX, ruutuY));
        }
    }

    private void shiftPress(MouseEvent e, int ruutuX, int ruutuY) {
        // Lisää/poistaa esteen
        ruudukko.vaihdaNodenKaveltavyys(ruutuY, ruutuX);

        // Jos klikkaus osuu liikkujan reitille, lasketaan uusi reitti

        for (Koordinaatti k : ruudukko.getLiikkuja().getPath().getKoordinaatit()) {
            if (k != null) {
                if (k.getX() == ruutuX && k.getY() == ruutuY) {
                    ruudukko.getLiikkuja().laskeReittiUudelleen();
                    return;
                }
            }
        }


    }

    // omalla koneella yläreunasta 22 pixeliä ja vasemmasta 3 pixeliä, 
    // tämän pohjalta x ja y arvot säädetty
    private int haeX(MouseEvent e, int ruudukonX) {
        int x = e.getX() - 3;
        int ruutuX = -1;
        while (x > ruudukonX) {
            ruudukonX += valinKoko;
            if (ruudukonX > x) {
                osuureunaan = true;
                break;
            }
            ruudukonX += ruudunKoko;
            ruutuX++;
        }
        return ruutuX;
    }

    private int haeY(MouseEvent e, int ruudukonY) {
        int y = e.getY() - 22;
        int ruutuY = -1;
        while (y > ruudukonY) {
            ruudukonY += valinKoko;
            if (ruudukonY > y) {
                osuureunaan = true;
                break;
            }
            ruudukonY += ruudunKoko;
            ruutuY++;
        }
        return ruutuY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
