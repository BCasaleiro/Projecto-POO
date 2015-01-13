package projecto;

import java.util.ArrayList;

/** Classe para objectos do tipo agente closest, onde serão contidos todos os valores e métodos necessários para a simulação da sua vida*/

public class ClosestAgent extends Agent{
    
    /**Construtor com iniciação de todas as variávei
     * @param fieldOfSight
     * @param lifeSpan
     * @param id
     * @param color
     * @param coords
     */
    public ClosestAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
    /**Método responsavel pela escolha do próximo objecto alvo do agente através do calculo da distancia entre o agente e este.
     * Efectua  a proteção para o caso do objecto já se encontrar em memóri
     * @param arrayList
     * @param mundo
     * @return Coordinates
     */
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
 
    /**Método responsável pelo retorno do Objecto no campo de visão mais próximo do agente
     * @return Obj*/
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
    
    /**Método responsável pelo retorno do inteiro correspondente à distancia entre o agente e o objecto
     * @return int*/
    private int calcDistance(Coordinates agenteCoords, Coordinates objCoords){
        int distance;
        
        distance =(int) Math.round( Math.sqrt( Math.pow(agenteCoords.getX() - objCoords.getX(), 2) + Math.pow(agenteCoords.getY() - objCoords.getY(), 2) ) );
        
        return distance;
    }

    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return String*/
    @Override
    public String toString() {
        return "Agente Closest " + super.toString();
    }
    
    @Override
    public String toString(int i) {
        return "Agente Closest " + super.toString(1);
    }
}
