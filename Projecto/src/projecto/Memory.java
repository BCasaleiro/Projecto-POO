package projecto;

import java.util.ArrayList;

public class Memory {
    private ArrayList<Coordinates> agentPath = new ArrayList<>();
    private ArrayList<ArrayList<Obj>> agentSurrounding = new ArrayList<>();
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
    
    public void addFieldOfSight(ArrayList<Obj> fieldOfSight){
        agentSurrounding.add(fieldOfSight);
    }
    
    public void addObject(Obj newObject){
        int flag  = 0;
        
        nObjectsFound++;
        System.out.println("Adicionado o objecto " + newObject.getId() + " à memória");
        
        for(int i = 0; i < objectsFound.size();i++){
            if(newObject.getObject().equals(objectsFound.get(i).getObject()) && newObject.getShape().equals(objectsFound.get(i).getShape()) && newObject.getColor().equals(objectsFound.get(i).getColor())){
                flag = 1;
            }
        }
        
        objectsFound.add(newObject);
        
        if(flag == 0){
            nDifferentObjFound++;
        }   
    }

    public ArrayList<Coordinates> getAgentPath() {
        return agentPath;
    }

    public ArrayList<ArrayList<Obj>> getAgentSurrounding() {
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

    @Override
    public String toString() {
        String objFound = "{"; 
        String agentPathString = "{";
        String agentSurroundingString = "";
        
        for(int i = 0; i < this.getObjectsFound().size(); i++){
            objFound += this.getObjectsFound().get(i).toString();
        }
        
        for(int i = 0; i < getAgentPath().size(); i++){
            agentPathString += this.getAgentPath().get(i).toString();
        }
        
        for(int i = 0; i < getAgentSurrounding().size(); i++){
            agentSurroundingString += "{";
            for(int j = 0; j < getAgentSurrounding().get(i).size(); j++){
                agentSurroundingString += this.getAgentSurrounding().get(i).get(j).toString();
            }
            agentSurroundingString = "}";   
        }
        
        return "Memória{" + "Número de Objectos Encontrados: " + nObjectsFound + ", Número de Objectos Diferentes encontrados: " + nDifferentObjFound + ", Distancia percorrida: " + distance +  " Caminho Percorrido: " + agentPathString  + ", à sua volta: " + agentSurroundingString + ", Objectos encontrados: " + objFound +'}';
    }
    
    public String toString(int i) {
        String objFound = "{"; 
        
        for(int j = 0; j < this.getObjectsFound().size(); j++){
            objFound += this.getObjectsFound().get(j).toString(1) + " ";
        }
        
        objFound += "}";
        
        return "Número de Objectos Encontrados " + nObjectsFound + "\nNúmero de Objectos Diferentes encontrados: " + nDifferentObjFound + "\nDistancia percorrida: " + distance + "\nObjectos encontrados: " + objFound +'}';
    }
    
    
}
