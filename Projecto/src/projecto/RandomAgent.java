package projecto;

import java.util.ArrayList;
import java.util.Random;

public class RandomAgent extends Agent{
    
    //Chose next target
    @Override
    public Coordinates pathFinder(ArrayList<Obj> arrayList, World mundo) {
	Random gerador = new Random();
        int index;
        int flag = 0;
        
	System.out.println("\n----- Agente a escolher o objecto aleatório -----"); //debug	
	System.out.println("Coordenadas Agente("+ this.getCoords().getX() + "," + this.getCoords().getY() + ")"); //debug
        
        do{
            if(!arrayList.isEmpty()){
                index = gerador.nextInt(arrayList.size());
                for(int i = 0; i < this.getMemory().getObjectsFound().size(); i++){
                    if(arrayList.get(index).getId().equals(this.getMemory().getObjectsFound().get(i).getId())){
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
        
	this.nextTarget = arrayList.get(index);
		
	System.out.println("Coordenadas objecto escolhido aleatoriamente: (" + arrayList.get(index).getCoords().getX() + ", " + arrayList.get(index).getCoords().getY() + ")\n"); //debug
		
        return arrayList.get(index).getCoords();
    }
    
    //Constructors
    public RandomAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
}
