package Grafiikka;

import Klikkaaminen.Klikkaaja;
import Ruudukko.Ruudukko;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Grafiikka-luokka luo yksinkertaisen graafisen esityksen ohjelmalle.
 * Pääasiassa ohjelman graafinen ilme tulee Ruudukko-luokan
 * paintComponent-metodista.
 */
public class Grafiikka implements Runnable {

    private Ruudukko ruudukko;
    private JFrame frame;
    private int korkeus;
    private int leveys;
    private int ruudunKoko;
    private int valinKoko;

    public Grafiikka(Ruudukko ruudukko, int korkeus, int leveys, int ruudunKoko, int valinKoko) {
        this.ruudukko = ruudukko;
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.ruudunKoko = ruudunKoko;
        this.valinKoko = valinKoko;
    }

    public void setRuudukko(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Ruudukko getRuudukko() {
        return ruudukko;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {

        frame = new JFrame("Ruudukko");
        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(this.ruudukko);
        frame.addMouseListener(new Klikkaaja(ruudukko, leveys, korkeus, ruudunKoko, valinKoko));

        frame.pack();
        frame.setVisible(true);
    }
}
