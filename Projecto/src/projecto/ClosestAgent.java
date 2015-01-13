package projecto;

import java.util.ArrayList;

public class ClosestAgent extends Agent{
    
    //Constructors
    public ClosestAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
    //Chose next target
    @Override
    public Coordinates pathFinder(ArrayList<Obj> arrayList, World mundo) {
        Obj target;
        int flag = 0;
	
        do{
            if(!arrayList.isEmpty()){
                target = closestObj(arrayList);
                for(int i = 0; i < this.getMemory().getObjectsFound().size(); i++){
                    if(target.getId().equals(this.getMemory().getObjectsFound().get(i).getId())){
                        System.out.println("O objecto " + target.getId() + " já se encontra em memória");
                        flag = 1;
                        arrayList.remove(target);
                        break;
                    }
                }
            } else {
                return null;
            }
        }while(flag == 1);
		
	this.nextTarget = target;
		
	return target.getCoords();
    }
 
    private Obj closestObj(ArrayList<Obj> arrayList){
        Obj target = arrayList.get(0);
        
        if(arrayList.size() > 1){
            for(int i = 0; i < arrayList.size(); i++){
                if(calcDistance(this.getCoords(), arrayList.get(i).getCoords()) < calcDistance(this.getCoords(), target.getCoords())){
                    target = arrayList.get(i);
                }
            }
        }
        
        return target;
    }
    
    private int calcDistance(Coordinates agenteCoords, Coordinates objCoords){
        int distance;
        
        distance =(int) Math.round( Math.sqrt( Math.pow(agenteCoords.getX() - objCoords.getX(), 2) + Math.pow(agenteCoords.getY() - objCoords.getY(), 2) ) );
        
        return distance;
    }

    @Override
    public String toString() {
        return "Agente Closest " + super.toString();
    }
    
    @Override
    public String toString(int i) {
        return "Agente Closest " + super.toString(1);
    }
}
