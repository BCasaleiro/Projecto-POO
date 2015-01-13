package projecto;

import java.util.ArrayList;
import java.util.Random;

public class RandomAgent extends Agent{
    
    //Chose next target
    public Coordinates pathFinder(ArrayList<Obj> arrayList, World mundo, Agent agent) {
	Random gerador = new Random();
        int index;
        int flag = 0;
        
	System.out.println("\n----- Agente a escolher o objecto aleatório -----"); //debug	
	System.out.println("Coordenadas Agente("+ agent.getCoords().getX() + "," + agent.getCoords().getY() + ")"); //debug
		
	if(arrayList.isEmpty()) {
            return null;
	}
        
        do{
            if(!arrayList.isEmpty()){
                index = gerador.nextInt(arrayList.size());
                for(int i = 0; i < agent.getMemory().getObjectsFound().size(); i++){
                    if(arrayList.get(index).getId().equals(agent.getMemory().getObjectsFound().get(i).getId())){
                        System.out.println("O objecto " + arrayList.get(index).getId() + " já se encontra em memória");
                        flag = 1;
                        arrayList.remove(index);
                        break;
                    }
                }
            } else {
                return null;
            }
        }while(flag == 1);
        
	agent.nextTarget = arrayList.get(index);
		
	System.out.println("Coordenadas objecto escolhido aleatoriamente: (" + arrayList.get(index).getCoords().getX() + ", " + arrayList.get(index).getCoords().getY() + ")\n"); //debug
		
        return arrayList.get(index).getCoords();
    }
    
    //Constructors
    public RandomAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
}
