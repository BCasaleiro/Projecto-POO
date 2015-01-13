package projecto;

import java.util.ArrayList;
import java.util.Random;

/** Classe para objectos do tipo agente, onde serão contidos todos os valores e métodos necessários para a simulação da sua vida*/

public abstract class Agent extends Entity{
    protected int fieldOfSight;
    protected int lifeSpan;
    protected Obj nextTarget;
    protected Memory memory;
    
    /**Construtor com iniciação de todas as variávei
     * @param fieldOfSight
     * @param lifeSpan
     * @param color
     * @param id
     * @param coords*/
    public Agent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(id, "Circular", color, coords);
        this.fieldOfSight = fieldOfSight;
        this.lifeSpan = lifeSpan;
        this.nextTarget = null;
        this.memory = new Memory();
    }
    
    public abstract Coordinates pathFinder(ArrayList<Obj> arrayList, World mundo);
    
    /**Método responsável pelo andar do agent
     * @param mundo
     * @param coordsFinal
     * @param agent*/
    public void walk(World mundo, Coordinates coordsFinal, Agent agent) {
        int xInitial = agent.getCoords().getX();
	int yInitial = agent.getCoords().getY();
	int xFinal = coordsFinal.getX();
	int yFinal = coordsFinal.getY();
        
        System.out.println("\n ----- Agente em andamento -----"); //debug
        System.out.println("Coordenadas Iniciais: ("+ xInitial + ", "+ yInitial + ")"); //debug
        
	while(xFinal != xInitial && yFinal != yInitial) {
            if (xFinal < xInitial) {
		xInitial--;
            } else if (xFinal > xInitial) {
                xInitial++;
            } else if (yFinal < yInitial) {
                yInitial--;
            } else if (yFinal > yInitial) {
		yInitial++;
            }

            memory.addStep(new Coordinates(xInitial, yInitial));
           
            System.out.println("Coordenadas Intermédias: ("+ xInitial + ", "+ yInitial + ")"); //debug
        }

        System.out.println("Agente parou de andar com tempo de vida de " + agent.getLifeSpan() + " passos"); //debug
        
        agent.coords.setX(xFinal);
        agent.coords.setY(yFinal);
        
        mundo.getWorldBoard()[xInitial][yInitial].setEntity(null);
    }
    
    /**Método responsável pelo andar do agente caso este não tenha objectos por visitar à vist
     * @param mundo
     * @param agent*/
    public void walkRandomly(World mundo, Agent agent){
	Random gerador = new Random();
	int rand = gerador.nextInt(4) + 1;
        
        System.out.print("Anda Random para ");
        
	switch(rand) {
            case 1: 
                if(agent.coords.getX() != mundo.getWorldSize() - 1){
                    agent.coords.setX(agent.coords.getX() + 1); 
                    break;
                }
            case 2: 
                if(agent.coords.getX() != 0){ 
                    agent.coords.setX(agent.coords.getX() - 1); 
                    break;
                }
            case 3: 
                if(agent.coords.getY() != mundo.getWorldSize() - 1){ 
                    agent.coords.setY(agent.coords.getY() + 1); 
                    break;
                }
            case 4: 
                if(agent.coords.getY() != 0){ 
                    agent.coords.setY(agent.coords.getY() - 1); 
                    break;
                }
        }
        
        agent.getMemory().addStep(new Coordinates(agent.getCoords().getX(), agent.getCoords().getY()));
        
        System.out.println("(" + agent.getCoords().getX() + ", " +agent.getCoords().getY() +")");
    }
    
    /**Método responsável pela verificação dos objectos existentes no raio de visão
     * @param mundo
     * @param agent
     * @return ArrayList de Obj
     */
    public ArrayList<Obj> getListFieldOfSight(World mundo, Agent agent) {
        int x = agent.getCoords().getX();
	int y = agent.getCoords().getY();
	int field = agent.getFieldOfSight();
	int auxXEnd = x + field;
	int auxXIni = x - field;
	int auxYEnd = y + field;
	int auxYIni = y - field;
	
	ArrayList<Obj> list = new ArrayList<>();

	//Definir os limites do campo de visão
	if(x - field < 0){
            auxXIni = 0;
        }
        if(x + field >= mundo.getWorldSize()){
            auxXEnd = mundo.getWorldSize() - 1;
        }
        if(y - field < 0){
            auxYIni = 0;
        }
        if(y + field >= mundo.getWorldSize()){
            auxYEnd = mundo.getWorldSize() - 1;
        }
        
        System.out.println("Corredenadas Agente: (" + agent.getCoords().getX() + "," + agent.getCoords().getY() + ")"); //debug
        for (int i = auxXIni; i <= auxXEnd; i++) {
            for (int j = auxYIni; j <= auxYEnd; j++) {
                if (!(i == x && j == y)) {
                    System.out.println("Coordenadas: (" + i + ", " + j + ")  Objecto: " + mundo.getWorldBoard()[i][j].getEntity()); // debug
                    if(mundo.getWorldBoard()[i][j].isOccupied()){ 
                        if(mundo.getWorldBoard()[i][j].getEntity() instanceof Obj) {
                            list.add((Obj) mundo.getWorldBoard()[i][j].getEntity());
                            System.out.println("Adicionado o objecto: " + mundo.getWorldBoard()[i][j].getEntity().getId() + " à lista de objectos no campo de visão!"); //debug
			}
                    }
                }
            }
	}

        System.out.println("Número de objectos no campo de visão: " + list.size() + "\n\n"); //debug

	return list;
    }
    
    
    /**Método responsável pelo retorno do raio de visão do agente.
     * @return int
     */
    public int getFieldOfSight() {
        return fieldOfSight;
    }

    /**Método responsável pelo retorno do tempo de vida do agente.
     * @return int
     */
    public int getLifeSpan() {
        return lifeSpan;
    }

    /**Método responsável pelo retorno do próximo alvo do agente.
     * @return Obj
     */
    public Obj getNextTarget() {
        return nextTarget;
    }

    /**Método responsável pelo retorno da memória do agente.
     * @return Memory
     */
    public Memory getMemory() {
        return memory;
    }

    /**Método responsável pela introdução do raio de visão do agente.
     * @param fieldOfSight.*/
    public void setFieldOfSight(int fieldOfSight) {
        this.fieldOfSight = fieldOfSight;
    }

    /**Método responsável pela introdução do tempo de vida do agente.
     * @param lifeSpan.*/
    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    /**Método responsável pela introdução do próximo alvo do agente.
     * @param nextTarget.*/
    public void setNextTarget(Obj nextTarget) {
        this.nextTarget = nextTarget;
    }

    /**Método responsável pela introdução da memória do agente.
     * @param memory.*/
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return e*/
    @Override
    public String toString() {
        return super.toString() + ", Raio de Visão: " + fieldOfSight + ", Tempo de Vida: " + lifeSpan + ", Próximo alvo: " + nextTarget + ", " + memory;
    }
    
    @Override
    public String toString(int i) {
        return super.toString(1) + "\nRaio de Visão: " + fieldOfSight + "\nTempo de Vida: " + lifeSpan + "\n" + memory.toString(1);
    }
}
