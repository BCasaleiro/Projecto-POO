package projecto;

public class Coordinates {
    private int x;
    private int y;

    //Construtctors
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Set's
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    //Get's
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
    
}
