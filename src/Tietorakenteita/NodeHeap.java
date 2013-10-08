package Tietorakenteita;

import Ruudukko.Liikkuja.Koordinaatti;
import Ruudukko.Node;

public class NodeHeap {

    private Node[] nodet;
    private int heapSize;
    private Koordinaatti kohde;

    public NodeHeap(int koko, Koordinaatti kohde) {
        this.nodet = new Node[koko];
        this.heapSize = -1;
        this.kohde = kohde;
    }

    public boolean isEmpty() {
        if (heapSize == -1) {
            return true;
        }
        return false;
    }

    public void insert(Node node) {
        heapSize++;
        int i = heapSize;

        while (i > 0 && fScoreJaGScore(nodet[parent(i)]) > fScoreJaGScore(node)) {
            nodet[i] = nodet[parent(i)];
            i = parent(i);
        }
            nodet[i] = node;
            node.setOpen();
    }

    public Node min() {
        return nodet[0];
    }

    public Node delMin() {
        Node min = this.min();
        nodet[0] = nodet[heapSize];
        heapSize--;
        heapify(0);
        return min;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    public void heapify(int i) {
        Node vasen;
        Node oikea;
        if (left(i) <= heapSize) {
            vasen = nodet[left(i)];
        } else {
            vasen = null;
        }
        if (right(i) <= heapSize) {
            oikea = nodet[right(i)];
        } else {
            oikea = null;
        }
        Node kasiteltava = nodet[i];

        if (vasen != null && fScoreJaGScore(vasen) < fScoreJaGScore(kasiteltava) && fScoreJaGScore(vasen) < fScoreJaGScore(oikea)) {
            nodet[i] = vasen;
            nodet[left(i)] = kasiteltava;
            heapify(left(i));
        } else if (oikea != null && fScoreJaGScore(oikea) < fScoreJaGScore(kasiteltava)) {
            nodet[i] = oikea;
            nodet[right(i)] = kasiteltava;
            heapify(right(i));
        }

    }

    private int fScoreJaGScore(Node n) {
        if (n == null) {
            return Integer.MAX_VALUE;
        }
        int g = n.getgScore();
        if (g == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        //alla fscore
        return this.manhattanEtaisyys(n.getKoordinaatti(), kohde) + g;
    }

    public int manhattanEtaisyys(Koordinaatti a, Koordinaatti b) {
        int etaisyys;

        etaisyys = Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());

        return etaisyys;
    }
}
