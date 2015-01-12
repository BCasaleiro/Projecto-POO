package projecto;

import java.util.ArrayList;

public class World {
    private int worldSize;
    private int nAgent;
    private int nObject;
    private String tempId = "";
    private Terrain[][] worldBoard;
    
    private ArrayList<Agent> agents = new ArrayList<>();
    
    static int idGeral = 0;
    
    //Construtctor's
    public World(int worldSize, int nAgent, int nObject) {
        this.worldSize = worldSize;
        this.nAgent = nAgent;
        this.nObject = nObject;
        this.worldBoard = new Terrain[worldSize][worldSize];
    }
    
    public World(){
        
    }

    public void simulation(){
        showAgents();
    }
    
    public void addAgent(int agentType, int fieldOfSight, int lifeSpan, String color, int x, int y){
        System.out.print("Agente "); //debug
        if(agentType == 1){
            tempId= tempId + "AR" + String.valueOf(idGeral);
            this.agents.add(new RandomAgent(fieldOfSight, lifeSpan, tempId, color, new Coordinates(x, y))); //Random
            System.out.print("Random"); //debug
        } else if(agentType == 2){
            tempId= tempId + "AC" + String.valueOf(idGeral);
            this.agents.add(new ClosestAgent(fieldOfSight, lifeSpan, tempId, color, new Coordinates(x, y))); //Closest
            System.out.print("Closest"); //debug
        } else if(agentType == 3){
            tempId= tempId + "AM" + String.valueOf(idGeral);
            this.agents.add(new MaxDiffAgent(fieldOfSight, lifeSpan, tempId, color, new Coordinates(x, y))); //Max Diff
            System.out.print("Max Diff"); //debug
        } else {
            System.out.println("ERRO!!");
        }

        tempId = "";
        idGeral++;
        System.out.println(" criado para as coordenadas (" + x + "," + y + "). Nº de agentes: " + agents.size()); //debug
        
        if(agents.size() == nAgent){
            simulation();
        }
    }

    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
        this.worldBoard = new Terrain[worldSize][worldSize];
        for(int i = 0; i < worldSize; i++){
            for(int j = 0; j < worldSize; j++){
                this.worldBoard[i][j] = new Terrain(new Coordinates(i, j), null, false);
            }
        }
        
        System.out.println("Mundo criado com a dimensão de " + worldSize + " por " + worldSize + ", com " + worldSize * worldSize + " slots"); //debug
    }

    public void setnAgent(int nAgent) {
        this.nAgent = nAgent;
    }

    public void setnObject(int nObject) {
        this.nObject = nObject;
    }

    public int getWorldSize() {
        return worldSize;
    }

    public int getnAgent() {
        return nAgent;
    }

    public int getnObject() {
        return nObject;
    }

    public Terrain[][] getWorldBoard() {
        return worldBoard;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public static int getIdGeral() {
        return idGeral;
    }
    
    

    //debug
    public void showAgents(){
        System.out.println("Entrou no show");
        for(int i = 0; i < agents.size(); i++){
            System.out.println("Agente com o id " + agents.get(i).getId() + ".");
        }
    }
}
