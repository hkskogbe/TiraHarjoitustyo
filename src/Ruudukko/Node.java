package Ruudukko;

import Ruudukko.Liikkuja.Koordinaatti;

public class Node {

    private Koordinaatti parent;
    private Koordinaatti koordinaatti;
    private boolean closed;
    private boolean open;
    private int gScore;

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public int getgScore() {
        return gScore;
    }

    public Node(Koordinaatti k) {
        this.parent = null;
        this.koordinaatti = k;
        this.closed=false;
        this.open=false;
        this.gScore = Integer.MAX_VALUE;
    }

    public void setClosed() {
        this.closed=true;
        this.open=false;
    }

    public boolean isClosed() {
        return closed;
    }
    public void setOpen(){
        this.open=true;
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
