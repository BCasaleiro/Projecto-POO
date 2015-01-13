package projecto;

import java.util.ArrayList;

/** Classe para objectos do tipo memória, onde serão contidos todos os valores e métodos da vida do agente correspondente*/

public class Memory {
    private ArrayList<Coordinates> agentPath = new ArrayList<>();
    private ArrayList<ArrayList<Obj>> agentSurrounding = new ArrayList<>();
    private ArrayList<Obj> objectsFound = new ArrayList<>();
    private int nObjectsFound = 0;
    private int nDifferentObjFound = 0;
    private int distance = 0;

    /**Construtor com vazio*/
    public Memory() {
    }
    
    /**Método responsável pelo registo de cada passo do agente
     * @param coords*/
    public void addStep(Coordinates coords){
        agentPath.add(coords);
        distance++;
    }
    
    /**Método responsável pelo registo do campo de visão do agente a cada passo dado
     * @param fieldOfSight
     */
    public void addFieldOfSight(ArrayList<Obj> fieldOfSight){
        agentSurrounding.add(fieldOfSight);
    }
    
    /**Método responsável pelo registo de cada objecto encontrado pelo agente
     * @param newObject 
     */
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

    /**Método responsável pelo retorno do próximo caminho percorrido pelo agente
     * @return ArrayList
     */
    public ArrayList<Coordinates> getAgentPath() {
        return agentPath;
    }

    /**Método responsável pelo retorno do redor por onde o agente passou
     * @return ArrayList
     */
    public ArrayList<ArrayList<Obj>> getAgentSurrounding() {
        return agentSurrounding;
    }

    /**Método responsável pelo retorno do objectos encontrados pelo agente
     * @return ArrayList
     */
    public ArrayList<Obj> getObjectsFound() {
        return objectsFound;
    }

    /**Método responsável pelo retorno do número de objectos encontrados pelo agente 
     * @return int
     */
    public int getnObjectsFound() {
        return nObjectsFound;
    }

    /**Método responsável pelo retorno do número de objectos diferentes encontrados pelo agente
     * @return int
     */
    public int getnDifferentObjFound() {
        return nDifferentObjFound;
    }

    /**Método responsável pelo retorno da distância percorrida pelo agente
     * @return int
     */
    public int getDistance() {
        return distance;
    }

    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return String*/
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
