package projecto;

import java.util.ArrayList;
import java.util.Random;

/** Classe para objectos do tipo agente random, onde serão contidos todos os valores e métodos necessários para a simulação da sua vida*/

public class RandomAgent extends Agent{
    
    /**Método responsavel pela escolha do próximo objecto alvo do agente de forma aleatória.
     * Efectua  a proteção para o caso do objecto já se encontrar em memóri
     * @param arrayList
     * @param mundo
     * @return Coordinates
     */
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
    
    /**Construtor com iniciação de todas as variávei
     * @param fieldOfSight
     * @param lifeSpan
     * @param id
     * @param color
     * @param coords
     */
    public RandomAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return String*/
    @Override
    public String toString() {
        return "Agente Random " + super.toString();
    }
    
    @Override
    public String toString(int i) {
        return "Agente Random " + super.toString(1);
    }
}
