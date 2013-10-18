package Ruudukko;

import Ruudukko.Liikkuja.Koordinaatti;

/**
 * Node-luokka esittää yksittäistä ruutua ruudukossa. Polunlaskija käyttää
 * luokkaa pitämään kirjaa algoritmin kannalta nodelle ominaisista
 * ominaisuuksista.
 */
public class Node {

    private Koordinaatti parent;
    private Koordinaatti koordinaatti;
    private boolean closed;
    private boolean open;
    private int gScore;

    /**
     * Palauttaa etäisyyden aloitusnodeen
     */
    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    /**
     * Asettaa nodelle tiedon matkasta aloitusnodeen
     */
    public int getgScore() {
        return gScore;
    }

    /**
     * Noden ominaisuudet on nimetty wikipedia-artikkelin
     * en.wikipedia.org/wiki/A* mukaan.
     *
     * Boolean-arvot closed ja open merkitsevät Noden kuulumista closedsettiin
     * tai opensettiin.
     *
     * G-score on Noden etäisyys lähtöpisteestä.
     *
     * Parent on lyhimmän reitin kautta tähän Nodeen saapuva viereinen Node.
     *
     * @param k osoittaa (x,y)-mallisen koordinaattiesityksen Noden sijainnista
     * ruudukossa
     */
    public Node(Koordinaatti k) {
        this.parent = null;
        this.koordinaatti = k;
        this.closed = false;
        this.open = false;
        this.gScore = Integer.MAX_VALUE;
    }

    public void setClosed() {
        this.closed = true;
        this.open = false;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setOpen() {
        this.open = true;
    }

    public boolean isOpen() {
        return open;
    }

    public void setParent(Koordinaatti parent) {
        this.parent = parent;
    }

    public Koordinaatti getParent() {
        return parent;
    }

    public Koordinaatti getKoordinaatti() {
        return koordinaatti;
    }
}
