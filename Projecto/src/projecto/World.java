package projecto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void simulation() {
        initTerrain();
        
        for (Agent agent : agents) {
            agentLife(agent);
        }
    }
    
    private void agentLife(Agent agent){
        ArrayList<Obj> fieldOfSight = new ArrayList<>();
        Coordinates nextTargetCoords;
        
        while(agent.getMemory().getDistance() < agent.lifeSpan){
            fieldOfSight = agent.getListFieldOfSight(this, agent);
            agent.getMemory().addFieldOfSight(fieldOfSight);
            nextTargetCoords = agent.pathFinder(fieldOfSight, this, agent);
            if(nextTargetCoords != null){
                agent.walk(this, nextTargetCoords, agent);
                agent.getMemory().addObject(agent.getNextTarget());
            } else {
                agent.walkRandomly(this, agent);
            }
        }
        
        System.out.println("O agente " + agent.getId() + " morreu.");
    }
    
    private void initTerrain(){
        FileManagement files = new FileManagement();
        try {
            files.openRead("config.txt");
        } catch (IOException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        int x, y, i;
        
        StringTokenizer st;
        
        //Introduzir agentes
        for(i = 0; i < nAgent; i++){
            worldBoard[agents.get(i).getCoords().getX()][agents.get(i).getCoords().getY()].setEntity(agents.get(i));
        }
        
        i = 0;
        //Introduzir objectos
        while(i < nObject){
            tempId = tempId + "O" + String.valueOf(idGeral);
            try {
                line = files.readLine();
                if(line != null){
                    st = new StringTokenizer(line);

                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());

                    if(x < 0 || x >= worldSize){
                        System.out.println(st.nextToken() + " fora dos limites do mundo, não introduzido");
                    } else if(y < 0 || y >=worldSize){
                        System.out.println(st.nextToken() + " fora dos limites do mundo, não introduzido");
                    } else {
                        if(!worldBoard[x][y].isOccupied()){
                            worldBoard[x][y].setEntity(new Obj(st.nextToken(), tempId, st.nextToken(), st.nextToken(), new Coordinates(x, y)));
                            worldBoard[x][y].setOccupied(true);
                            i++;
                            idGeral++;
                            System.out.println(worldBoard[x][y].getEntity().getId() + " introduzido nas coordenadas (" + worldBoard[x][y].getEntity().getCoords().getX() + "," + worldBoard[x][y].getEntity().getCoords().getY() + ")"); //debug
                        } else {
                            System.out.println(st.nextToken() + " possui as mesmas coordenadas de " + worldBoard[x][y].getEntity().getId() + ", não introduzido");
                        }
                    }

                    tempId = "";
                } else {
                    System.out.println("Objectos na configuração insuficientes");
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            files.closeRead();
        } catch (IOException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        System.out.println("\nAgentes: ");
        for(int i = 0; i < agents.size(); i++){
            System.out.println("Agente com o id " + agents.get(i).getId() + ".");
        }
    }
    
    public void showTerrain(){
        System.out.println("\nTerrain: ");
        for(int i = 0; i < worldSize; i++){
            for(int j = 0; j < worldSize; j++){
                System.out.println(i + " " + j + " " + worldBoard[i][j].getEntity());
            }
        }
    }
}
