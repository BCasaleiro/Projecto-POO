package projecto;

import java.util.ArrayList;

public class MaxDiffAgent extends Agent {
    
    /**Construtor com iniciação de todas as variávei
     * @param fieldOfSight
     * @param lifeSpan
     * @param id
     * @param color
     * @param coords
     */
    public MaxDiffAgent(int fieldOfSight, int lifeSpan, String id, String color, Coordinates coords) {
        super(fieldOfSight, lifeSpan, id, color, coords);
    }
    
    /**Método responsavel pela escolha do próximo objecto alvo do agente através do calculo das caracteristicas em comum com os objectos em memória.
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
    
    /**Método responsável pelo retorno do Objecto no campo de visão com a maior diferença nas caracteristicas dos objectos presentes em memória
     * @return Obj*/
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

    /**Método responsável pelo retorno do inteiro correspondente às diferenças entre o objecto e os objectos presentes em memória
     * @return Obj*/
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
    
    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return String*/
    @Override
    public String toString() {
        return "Agente MaxDiff " + super.toString();
    }
    
    public String toString(int i) {
        return "Agente MaxDiff " + super.toString(1);
    }
}
