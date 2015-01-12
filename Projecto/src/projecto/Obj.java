package projecto;

public class Obj extends Entity{
    private String object;
    
    //Constructors
    public Obj(String object, String id, String shape, String color, Coordinates coords) {
        super(id, shape, color, coords);
        this.object = object;
    }
    
    //Set's
    public void setObject(String object) {
        this.object = object;
    }
    
    //Get's
    public String getObject() {
        return object;
    }
}
