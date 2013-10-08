package Ruudukko;

import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Liikkuja.Liikkuja;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Ruudukko extends JPanel {

    private int korkeus;
    private int leveys;
    private int ruudunKorkeus;
    private int ruudunLeveys;
    private int vali;
    private Color taustavari;
    private Color seinavari;
    private Color tyhjavari;
    private boolean[][] ruudukko;
    private Liikkuja liikkuja;

    /**
     * Konstruktori ruudukolle string-muotoisen "pohjapiirroksen" pohjalta
     */
    public Ruudukko(int korkeus, int leveys, int ruudunKorkeus, int ruudunLeveys,
            int vali, Color taustavari, String ruudukkoString) {

        this.korkeus = korkeus;
        this.leveys = leveys;
        this.ruudunKorkeus = ruudunKorkeus;
        this.ruudunLeveys = ruudunLeveys;
        this.vali = vali;
        this.taustavari = taustavari;
        this.kasaaRuudukko(ruudukkoString);

        this.tyhjavari = Color.LIGHT_GRAY;
        this.seinavari = Color.MAGENTA.darker().darker();
    }

    /**
     * Tyhjän ruudukon konstruktori
     */
    public Ruudukko(int korkeus, int leveys, int ruudunKorkeus, int ruudunLeveys,
            int vali, Color taustavari) {

        this.korkeus = korkeus;
        this.leveys = leveys;
        this.ruudunKorkeus = ruudunKorkeus;
        this.ruudunLeveys = ruudunLeveys;
        this.vali = vali;
        this.taustavari = taustavari;
        this.ruudukko = new boolean[korkeus][leveys];

        this.tyhjavari = Color.LIGHT_GRAY;
        this.seinavari = Color.MAGENTA;
    }

    public void setLiikkuja(Liikkuja liikkuja) {
        this.liikkuja = liikkuja;
    }

    public Liikkuja getLiikkuja() {
        return liikkuja;
    }

    /**
     * Tieto siitä, voiko liikkuja edetä.
     */
    public boolean voiLiikkua() {
        if (this.liikkuja.getPath().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Siirtää liikkujan seuraavaan ruutuun.
     */
    public void liiku() {
        this.liikkuja.liiku();
    }

    /**
     * Luo ruudukon String-muotoisen tiedon pohjalta
     *
     * @param ruudukkoString
     */
    private void kasaaRuudukko(String ruudukkoString) {
        this.ruudukko = new boolean[korkeus][leveys];


        int y = 0;
        int x = 0;

        for (int a = 0; a < ruudukkoString.length(); a++) {
            if (ruudukkoString.charAt(a) == 'x') {
                this.ruudukko[y][x] = true;
            } else {
                this.ruudukko[y][x] = false;
            }
            x++;
            if (x >= leveys) {
                x = 0;
                y++;
            }
        }
    }

    /**
     * Palauttaa tiedon, onko ruudukon kohdassa, johon parametrien koordinaatit
     * osoittavat, seinä.
     *
     * @param y -koordinaatti
     * @param x -koordinaatti
     * @return
     */
    public boolean onSeina(int y, int x) {
        return this.ruudukko[y][x];
    }

    /**
     * Jos node on käveltävä, siihen tulee seinä. Muutoin poistetaan
     * olemassaoleva seinä.
     *
     * @param y
     * @param x
     */
    public void vaihdaNodenKaveltavyys(int y, int x) {
        //Jos klikataan itse liikkujaa, return
        if (y == liikkuja.getSijainti().getY() && x == liikkuja.getSijainti().getX()) {
            return;
        }
        //Jos klikataan liikkujan kohdetta, return
        if (y == liikkuja.getKohde().getY() && x == liikkuja.getKohde().getX()) {
            return;
        }


        if (this.ruudukko[y][x]) {
            this.ruudukko[y][x] = false;
        } else {
            this.ruudukko[y][x] = true;
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(taustavari);

        for (int j = 0; j < korkeus; j++) {
            for (int i = 0; i < leveys; i++) {


                if (ruudukko[j][i]) {
                    graphics.setColor(this.seinavari);
                } else {
                    graphics.setColor(this.tyhjavari);
                }
                piirraNelio(graphics, i, j);
            }
        }

        if (liikkuja != null) {
            if (!liikkuja.getPath().isEmpty()) {
                piirraLiikkujanPolku(graphics);
            }

            piirraLiikkuja(graphics);
        }
    }

    private void piirraLiikkuja(Graphics graphics) {
        graphics.setColor(liikkuja.getVari());
        piirraNelio(graphics, liikkuja.getSijainti().getX(), liikkuja.getSijainti().getY());
    }

    private void piirraLiikkujanPolku(Graphics graphics) {
        graphics.setColor(liikkuja.getPolunvari());


        for (Koordinaatti k : liikkuja.getPath().getKoordinaatit()) {
            if (k != null) {
                piirraNelio(graphics, k.getX(), k.getY());
            }
        }

    }

    private void piirraNelio(Graphics graphics, int koordinaattiX, int koordinaattiY) {
        graphics.fillRect(vali + koordinaattiX * (vali + ruudunLeveys),
                vali + koordinaattiY * (vali + ruudunLeveys), ruudunLeveys, ruudunKorkeus);
    }

    public int getRuudukonKorkeus() {
        return korkeus;
    }

    public int getRuudukonLeveys() {
        return leveys;
    }

    public int getRuudunKorkeus() {
        return ruudunKorkeus;
    }

    public int getRuudunLeveys() {
        return ruudunLeveys;
    }

    public int getVali() {
        return vali;
    }
}
