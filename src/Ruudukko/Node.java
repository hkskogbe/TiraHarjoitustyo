package Ruudukko;

public class Node {

    private Node parent;
    private NodeSet tyyppi;
    
    private int gScore;
    private int fScore;

    public void setfScore(int fScore) {
        this.fScore = fScore;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public int getgScore() {
        return gScore;
    }

    public int getfScore() {
        return fScore;
    }

    public Node() {
        this.parent = null;
        this.tyyppi = NodeSet.NONE;
        this.fScore = Integer.MAX_VALUE;
        this.gScore = Integer.MAX_VALUE;
    }

    public void setClosed() {
        this.tyyppi = NodeSet.CLOSED;
    }

    public void setOpen() {
        this.tyyppi = NodeSet.OPEN;
    }

    public void setNone() {
        this.tyyppi = NodeSet.NONE;
    }

    public NodeSet getTyyppi() {
        return tyyppi;
    }

    public boolean isClosed() {
        if (this.tyyppi == NodeSet.CLOSED) {
            return true;
        }
        return false;
    }

    public boolean isOpen() {
        if (this.tyyppi == NodeSet.OPEN) {
            return true;
        }
        return false;
    }

    public boolean isNotInSet() {
        if (this.tyyppi == NodeSet.NONE) {
            return true;
        }
        return false;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
}
