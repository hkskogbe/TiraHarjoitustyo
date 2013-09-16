package Ruudukko;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Paivittaja {

    // ONCE EVERY SECOND, EVERYTHING SHALL BE UPDATED. maybe once 0.7 seconds, maybe
    private Ruudukko ruudukko;

    public Paivittaja(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }

    public void pyori() {
        try {
            while (true) {
                
                Thread.sleep(1000);
                
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
