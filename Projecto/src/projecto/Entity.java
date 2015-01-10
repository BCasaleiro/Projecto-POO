package projecto;

public abstract class Entity {
    protected String id;
    protected String shape;
    protected String color;
    protected Coordinates coords;
    
    //Construtctors
    public Entity(String id, String shape, String color, Coordinates coords) {
        this.id = id;
        this.shape = shape;
        this.color = color;
        this.coords = coords;
    }
    
    //Set's
    public void setId(String id) {
        this.id = id;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }
    
    //Get's
    public String getId() {
        return id;
    }

    public String getShape() {
        return shape;
    }

    public String getColor() {
        return color;
    }

    public Coordinates getCoords() {
        return coords;
    }
}
