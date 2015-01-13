package projecto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Classe principal do projecto, onde serão contidos todos os valores e métodos para a simulação*/

public class World {
    private int worldSize;
    private int nAgent;
    private int nObject;
    private String tempId = "";
    private Terrain[][] worldBoard;
    
    private ArrayList<Agent> agents = new ArrayList<>();
    
    static int idGeral = 0;
    
    /**Construtor com iniciação de todas as variávei
     * @param worldSize
     * @param nAgent
     * @param nObject*/
    public World(int worldSize, int nAgent, int nObject) {
        this.worldSize = worldSize;
        this.nAgent = nAgent;
        this.nObject = nObject;
        this.worldBoard = new Terrain[worldSize][worldSize];
    }
    
    /**Construtor vazio*/
    public World(){
        
    }

    /**Método responsável pelo decorrer de toda a simulação.
     *Desde a introdução das entidades no mundo à apresentação dos resultados
     */
    public void simulation() {
        try {
            FileManagement files = new FileManagement();
            GUI infoAgent;
            
            files.openWrite("logs.txt");
            
            initTerrain();
            
            for (Agent agent : agents) {
                agentLife(agent);
                infoAgent = new GUI(agent.toString(1));
                files.writeLine(agent.toString());
            }
            
            files.closeWrite();
        } catch (IOException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**Método responsável pela "vida" de cada agente sempre introduzindo os seus movimentos na memória destes*/
    private void agentLife(Agent agent){
        ArrayList<Obj> fieldOfSight = new ArrayList<>();
        Coordinates nextTargetCoords;
        
        while(agent.getMemory().getDistance() < agent.lifeSpan){
            fieldOfSight = agent.getListFieldOfSight(this, agent);
            agent.getMemory().addFieldOfSight(fieldOfSight);
            nextTargetCoords = agent.pathFinder(fieldOfSight, this);
            if(nextTargetCoords != null){
                agent.walk(this, nextTargetCoords, agent);
                agent.getMemory().addObject(agent.getNextTarget());
            } else {
                agent.walkRandomly(this, agent);
            }
        }
        
        System.out.println("O agente " + agent.getId() + " morreu.");
    }
    
    /**Metodo responsável pela iniciação do mundo e introção das entidades nos seus respectivos lugares*/
    private void initTerrain(){
        FileManagement files = new FileManagement();
        /**Proteção contra a possível âusencia do ficheiro de configuração dos objectos*/
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
    
    /**Método responsável pela introdução dos agentes no ArrayList à medida que o utilizador introduz a configuração destes*/
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

    /**Método responsável pela introdução do tamanho do mundo.
     *Cria também uma matriz do tipo Terrain e inicia a mesma como estando desocupada.
     * @param worldSize
     */
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

    /**Método responsável pela introdução do número de agentes
     * @param nAgent.*/
    public void setnAgent(int nAgent) {
        this.nAgent = nAgent;
    }

    /**Método responsável pela introdução do número de objectos
     * @param nObject.*/
    public void setnObject(int nObject) {
        this.nObject = nObject;
    }

    /**Método responsável pelo retorno do tamanho do mundo.
     * @return int
     */
    public int getWorldSize() {
        return worldSize;
    }

    /**Método responsável pelo retorno do número de agentes.
     * @return int
     */
    public int getnAgent() {
        return nAgent;
    }

    /**Método responsável pelo retorno do número de objectos.
     * @return int
     */
    public int getnObject() {
        return nObject;
    }

    /**Método responsável pelo retorno da matriz que representa o mundo dos agentes.
     * @return Terrain
     */
    public Terrain[][] getWorldBoard() {
        return worldBoard;
    }

    /**Método responsável pelo retorno do ArrayList que contem todos os agentes.
     * @return Terrain
     */
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    /**Método responsável pelo retorno id Geral utilizado para a definição dos id's de cada entidade.
     * @return int
     */
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
