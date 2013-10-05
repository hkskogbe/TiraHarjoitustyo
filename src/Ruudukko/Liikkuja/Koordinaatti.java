package Ruudukko.Liikkuja;

public class Koordinaatti {

    private int x;
    private int y;

    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public String toString(){
        return "Koordinaatti: " + x + ", " + y;
    }
}
