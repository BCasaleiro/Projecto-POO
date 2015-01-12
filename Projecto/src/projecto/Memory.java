package projecto;

import java.util.ArrayList;

public class Memory {
    private ArrayList<Coordinates> agentPath = new ArrayList<>();
    private ArrayList<ArrayList<Entity>> agentSurrounding = new ArrayList<>();
    private ArrayList<Obj> objectsFound = new ArrayList<>();
    private int nObjectsFound = 0;
    private int nDifferentObjFound = 0;
    private int distance = 0;

    //Constructor
    public Memory() {
    }
    
    public void addStep(Coordinates coords){
        agentPath.add(coords);
        distance++;
    }
    
    public void addFieldOfSight(ArrayList<Entity> fieldOfSight){
        agentSurrounding.add(fieldOfSight);
    }
    
    public void addObject(Obj newObject){
        int flag  = 0;
        
        nObjectsFound++;
        objectsFound.add(newObject);
        
        for(int i = 0; i < objectsFound.size();i++){
            if(!newObject.getObject().equals(objectsFound.get(i).getObject())){
                flag = 1;
            } else if(!newObject.getShape().equals(objectsFound.get(i).getShape())){
                flag = 1;
            } else if(!newObject.getColor().equals(objectsFound.get(i).getColor())){
                flag = 1;
            }
        }
        
        if(flag == 0){
            nDifferentObjFound++;
        }   
    }

    public ArrayList<Coordinates> getAgentPath() {
        return agentPath;
    }

    public ArrayList<ArrayList<Entity>> getAgentSurrounding() {
        return agentSurrounding;
    }

    public ArrayList<Obj> getObjectsFound() {
        return objectsFound;
    }

    public int getnObjectsFound() {
        return nObjectsFound;
    }

    public int getnDifferentObjFound() {
        return nDifferentObjFound;
    }

    public int getDistance() {
        return distance;
    }
}
