package Ruudukko;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Päivittää ruudukon graafista esitystä.
 */
public class Paivittaja {
    
    private Ruudukko ruudukko;

    public Paivittaja(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }

    public void pyori() {
        try {
            while (true) {
                
                // Pysäytetään maailma millisekunteiksi kerrallaan
                Thread.sleep(200);
                
                if (this.ruudukko.voiLiikkua()) {
                    this.ruudukko.liiku();
                    this.ruudukko.repaint();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Paivittaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
