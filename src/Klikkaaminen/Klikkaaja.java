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

    public Klikkaaja(Ruudukko ruudukko, int resoluutioX, int resoluutioY) {
        this.ruudukko = ruudukko;
        this.resoluutioY = resoluutioY;
        this.resoluutioX = resoluutioX;
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
        ruudukko.getLiikkuja().laskeReittiUudelleen();
    }

    // omalla koneella yläreunasta 22 pixeliä ja vasemmasta 3 pixeliä tyhjää
    private int haeX(MouseEvent e, int ruudukonX) {
        int x = e.getX() - 3;
        int ruutuX = -1;
        while (x > ruudukonX) {
            ruudukonX += 5;
            if (ruudukonX > x) {
                osuureunaan = true;
                break;
            }
            ruudukonX += 50;
            ruutuX++;
        }
        return ruutuX;
    }

    private int haeY(MouseEvent e, int ruudukonY) {
        int y = e.getY() - 22;
        int ruutuY = -1;
        while (y > ruudukonY) {
            ruudukonY += 5;
            if (ruudukonY > y) {
                osuureunaan = true;
                break;
            }
            ruudukonY += 50;
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
