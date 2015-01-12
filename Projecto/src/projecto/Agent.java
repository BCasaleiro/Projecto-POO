package projecto;

public abstract class Agent extends Entity{
    private String shape;
    protected int fieldOfSight;
    protected int lifeSpan;
    protected Obj nextTarget;
    protected Memory memory;
    
    //Constructors
    public Agent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(id, "Circular", color, coords);
        this.fieldOfSight = fieldOfSight;
        this.lifeSpan = lifeSpan;
        this.nextTarget = null;
        this.memory = new Memory();
    }

    public int getFieldOfSight() {
        return fieldOfSight;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public Obj getNextTarget() {
        return nextTarget;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setFieldOfSight(int fieldOfSight) {
        this.fieldOfSight = fieldOfSight;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setNextTarget(Obj nextTarget) {
        this.nextTarget = nextTarget;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
