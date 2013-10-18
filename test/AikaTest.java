/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Node;
import Tietorakenteita.NodeHeap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hkskogbe
 */
public class AikaTest {

    public AikaTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    void testaaHeappia() {


        NodeHeap k = new NodeHeap(7, new Koordinaatti(0, 0));


        Node toka = new Node(new Koordinaatti(4, 4));
        Node kolmas = new Node(new Koordinaatti(5, 4));
        Node eka = new Node(new Koordinaatti(3, 4));
        k.insert(toka);
        k.insert(eka);
        k.insert(kolmas);
        assertEquals(k.delMin(), eka);
        assertEquals(k.delMin(), toka);
        assertEquals(k.delMin(), kolmas);

    }
}