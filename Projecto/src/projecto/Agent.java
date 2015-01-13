package projecto;

import java.util.ArrayList;

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
    
    //Walk function
    public int walk(World mundo, Coordinates coordsFinal, Agent agent) {
        int xInitial = agent.getCoords().getX();
	int yInitial = agent.getCoords().getY();
	int xFinal = coordsFinal.getX();
	int yFinal = coordsFinal.getY();
	int distance = 0;

        
        System.out.println("\n ----- Agente em andamento -----");
        System.out.println("Coordenadas Iniciais: ("+ xInitial + ", "+ yInitial + ")");
        
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

            distance++;
           
            System.out.println("Coordenadas Intermédias: ("+ xInitial + ", "+ yInitial + ")"); //escrever para o ficheiro isto
        }

        System.out.println("Agente parou de andar com tempo de vida de " + agent.getLifeSpan() + " passos");
        
        agent.coords.setX(xFinal);
        agent.coords.setY(yFinal);
        mundo.getWorldBoard()[xInitial][yInitial].setOccupied(false);
        mundo.getWorldBoard()[xInitial][yInitial].setEntity(null);

        mundo.getWorldBoard()[xFinal][yFinal].setOccupied(true);
        mundo.getWorldBoard()[xFinal][yFinal].setEntity(agent);

        agent.setLifeSpan(agent.getLifeSpan() - distance);
        mundo.setnObject(mundo.getnObject() - 1);
        
        return distance;
    }
    
    //Get Field of sight - returns a list of all objects in range
    public ArrayList<Obj> getListFieldOfSight(World mundo, Agent agent) {
        int x = agent.getCoords().getX();
	int y = agent.getCoords().getY();
	int field = agent.getFieldOfSight();
	int auxXEnd = x + field;
	int auxXIni = x - field;
	int auxYEnd = y + field;
	int auxYIni = y - field;
	
	ArrayList<Obj> list = new ArrayList<>();

        System.out.println("Agente a averiguar campo de visão, tempo de vida " + agent.getLifeSpan() + " passos");
        
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
        
        System.out.println("Corredenadas Agente: (" + agent.getCoords().getX() + "," + agent.getCoords().getY() + ")");
        for (int i = auxXIni; i <= auxXEnd; i++) {
            for (int j = auxYIni; j <= auxYEnd; j++) {
                if (!(i == x && j == y)) {
                    System.out.println("Coordenadas: (" + i + ", " + j + ")  Objecto: " + mundo.getWorldBoard()[i][j].getEntity());
                    if(mundo.getWorldBoard()[i][j].isOccupied()){ 
                        if(mundo.getWorldBoard()[i][j].getEntity() instanceof Obj) {
                            list.add((Obj) mundo.getWorldBoard()[i][j].getEntity());
                            System.out.println("Adicionado o objecto: " + mundo.getWorldBoard()[i][j].getEntity().getId() + " à lista de objectos no campo de visão!");
			}
                    }
                }
            }
	}

        System.out.println("Número de objectos no campo de visão: " + list.size() + "\n\n");

	return list;
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
