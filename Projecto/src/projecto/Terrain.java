package projecto;

public class Terrain {
    private Coordinates coords;
    private Entity entity;
    private boolean occupied;
    
    //Construtctors
    public Terrain(Coordinates coords, Entity entity, boolean occupied) {
        this.coords = coords;
        this.entity = entity;
        this.occupied = occupied;
    }
    
    //Set's
    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    //Get's
    public Coordinates getCoords() {
        return coords;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isOccupied() {
        return occupied;
    } 
}
