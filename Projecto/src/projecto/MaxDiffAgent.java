package projecto;

import java.util.ArrayList;

public class MaxDiffAgent extends Agent {
    
    //Constructors
    public MaxDiffAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
    //Chose next target
    @Override
    public Coordinates pathFinder(ArrayList<Obj> arrayList, World mundo) {
        Obj target;
        int flag = 0;
	
        do{
            if(!arrayList.isEmpty()){
                target = maxDiff(arrayList);
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
    
    private Obj maxDiff(ArrayList<Obj> arrayList){
        Obj target = arrayList.get(0);
        
        if(arrayList.size() > 1){
            for(int i = 0; i < arrayList.size(); i++){
                if(calcDiff(arrayList.get(i)) > calcDiff(target)){
                    target = arrayList.get(i);
                }
            }
        }
        
        return target;
    }

    private int calcDiff(Obj target){
        int diff = 0;
        
        for(int j = 0; j < this.getMemory().getObjectsFound().size(); j++){
            if(!target.getObject().equals(this.getMemory().getObjectsFound().get(j).getObject())){
                diff++;
            }
        
            if(!target.getShape().equals(this.getMemory().getObjectsFound().get(j).getShape())){
                diff++;
            }

            if(!target.getColor().equals(this.getMemory().getObjectsFound().get(j).getColor())){
                diff++;
            }
        }
            
        return diff;
    }
}
