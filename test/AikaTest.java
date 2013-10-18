/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Polunlaskenta.Polunlaskija;
import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Liikkuja.Liikkuja;
import Ruudukko.Ruudukko;
import java.awt.Color;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hkskogbe
 */
public class AikaTest {

    private Ruudukko pieni;
    private Ruudukko iso;
    private Ruudukko isoin;
    private Ruudukko pieniRandom;
    private Ruudukko isoRandom;
    private Ruudukko isoinRandom;
    private Polunlaskija pieniLaskija;
    private Polunlaskija isoLaskija;
    private Polunlaskija isoinLaskija;
    private Polunlaskija pieniRandomLaskija;
    private Polunlaskija isoRandomLaskija;
    private Polunlaskija isoinRandomLaskija;

    public AikaTest() {

        // Tyhjät ruudukot

        pieni = new Ruudukko(100, 100, 1, 1, 1, Color.lightGray);
        iso = new Ruudukko(1000, 1000, 1, 1, 1, Color.lightGray);
        isoin = new Ruudukko(3000, 3000, 1, 1, 1, Color.lightGray);


        Liikkuja lPieni = new Liikkuja(Color.gray, 0, 0, pieni, pieniLaskija);
        pieniLaskija = new Polunlaskija(lPieni, pieni);
        pieni.setLiikkuja(lPieni);

        Liikkuja lIso = new Liikkuja(Color.gray, 0, 0, iso, isoLaskija);
        isoLaskija = new Polunlaskija(lIso, iso);
        iso.setLiikkuja(lIso);

        Liikkuja lIsoin = new Liikkuja(Color.gray, 0, 0, isoin, isoinLaskija);
        isoinLaskija = new Polunlaskija(lIsoin, isoin);
        isoin.setLiikkuja(lIsoin);


        // Ruudukot, joissa on sattumanvaraisesti sijoiteltuja esteitä

        Random rng = new Random();
        StringBuilder ruudukkoString;

        ruudukkoString = new StringBuilder(" ");
        for (int i = 0; i < 100 * 100 - 1; i++) {
            if (rng.nextDouble() < 0.1) {
                ruudukkoString.append('x');
            } else {
                ruudukkoString.append(" ");
            }
        }
        pieniRandom = new Ruudukko(1000, 1000, 1, 1, 1, Color.lightGray, ruudukkoString.toString());

        Liikkuja lPieniRandom = new Liikkuja(Color.gray, 0, 0, pieniRandom, pieniRandomLaskija);
        pieniRandomLaskija = new Polunlaskija(lPieniRandom, pieniRandom);
        pieniRandom.setLiikkuja(lPieni);

        ruudukkoString = new StringBuilder(" ");
        for (int i = 0; i < 1000 * 1000 - 1; i++) {
            if (rng.nextDouble() < 0.1) {
                ruudukkoString.append('x');
            } else {
                ruudukkoString.append(" ");
            }
        }
        isoRandom = new Ruudukko(1000, 1000, 1, 1, 1, Color.lightGray, ruudukkoString.toString());

        Liikkuja lIsoRandom = new Liikkuja(Color.gray, 0, 0, isoRandom, isoRandomLaskija);
        isoRandomLaskija = new Polunlaskija(lIsoRandom, isoRandom);
        isoRandom.setLiikkuja(lIso);

        ruudukkoString = new StringBuilder(" ");
        for (int i = 0; i < 3000 * 3000 - 1; i++) {
            if (rng.nextDouble() < 0.1) {
                ruudukkoString.append('x');
            } else {
                ruudukkoString.append(" ");
            }
        }
        isoinRandom = new Ruudukko(3000, 3000, 1, 1, 1, Color.lightGray, ruudukkoString.toString());

        Liikkuja lIsoinRandom = new Liikkuja(Color.gray, 0, 0, isoinRandom, isoinRandomLaskija);
        isoinRandomLaskija = new Polunlaskija(lIsoinRandom, isoinRandom);
        isoinRandom.setLiikkuja(lIso);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaPieni() {

        pieniLaskija.alustetaanNodet();
        long aika1 = System.currentTimeMillis();
        pieniLaskija.laskeAstarTesti(new Koordinaatti(99, 99));
        long aika2 = System.currentTimeMillis() - aika1;
        System.out.println("Aika pienelle (100x100) tyhjälle ruudukolle: \t\t\t\t" + aika2 + " ms" + "\n");
    }

    @Test
    public void testaaIso() {

        isoLaskija.alustetaanNodet();
        long aika1 = System.currentTimeMillis();
        isoLaskija.laskeAstarTesti(new Koordinaatti(999, 999));
        long aika2 = System.currentTimeMillis() - aika1;
        System.out.println("Aika isolle (1000x1000) tyhjälle ruudukolle: \t\t\t\t" + aika2 + " ms" + "\n");
    }

    @Test
    public void testaaIsoin() {

        isoinLaskija.alustetaanNodet();
        long aika1 = System.currentTimeMillis();
        isoinLaskija.laskeAstarTesti(new Koordinaatti(2999, 2999));
        long aika2 = System.currentTimeMillis() - aika1;
        System.out.println("Aika isoimmalle (3000x3000) tyhjälle ruudukolle: \t\t\t" + aika2 + " ms" + "\n");
    }

    @Test
    public void testaaPieniRandom() {

        pieniRandomLaskija.alustetaanNodet();
        long aika1 = System.currentTimeMillis();
        boolean loytyi = pieniRandomLaskija.laskeAstarTesti(new Koordinaatti(99, 99));
        long aika2 = System.currentTimeMillis() - aika1;
        System.out.println("Aika pienelle (100x100) sattumanvaraisesti täytetylle ruudukolle: \t" + aika2 + " ms" + "\nPolku löytyi: " + loytyi + "\n");
    }

    @Test
    public void testaaIsoRandom() {

        isoRandomLaskija.alustetaanNodet();
        long aika1 = System.currentTimeMillis();
        boolean loytyi = isoRandomLaskija.laskeAstarTesti(new Koordinaatti(999, 999));
        long aika2 = System.currentTimeMillis() - aika1;
        System.out.println("Aika isolle (1000x1000) sattumanvaraisesti täytetylle ruudukolle: \t" + aika2 + " ms" + "\nPolku löytyi: " + loytyi + "\n");
    }

    @Test
    public void testaaIsoinRandom() {

        isoinRandomLaskija.alustetaanNodet();
        long aika1 = System.currentTimeMillis();
        boolean loytyi = isoinRandomLaskija.laskeAstarTesti(new Koordinaatti(2999, 2999));
        long aika2 = System.currentTimeMillis() - aika1;
        System.out.println("Aika isoimmalle (3000x3000) sattumanvaraisesti täytetylle ruudukolle: \t" + aika2 + " ms" + "\nPolku löytyi: " + loytyi + "\n");
    }
}