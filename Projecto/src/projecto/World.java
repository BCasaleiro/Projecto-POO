package projecto;



public class World {
    private int worldSize;
    private int nAgent;
    private int nObject;
    private Terrain[][] worldBoard;
    
    //private ArrayList<Agent> = new ArrayList<>();
    
    static int idGeral = 0;
    
    //Construtctor's
    public World(int worldSize, int nAgent, int nObject) {
        this.worldSize = worldSize;
        this.nAgent = nAgent;
        this.nObject = nObject;
        this.worldBoard = new Terrain[worldSize][worldSize];
    }

    public void addAgent(){
        
    }
}
