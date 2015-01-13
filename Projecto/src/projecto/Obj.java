package projecto;

/** Classe para objectos do tipo Obj, onde serão contidos todos os valores e métodos necessários para a sua introdução no mundo*/

public class Obj extends Entity{
    private String object;
    
    /**Construtor com iniciação de todas as variáveis
     * @param object
     * @param id
     * @param shape
     * @param color
     * @param coords*/
    public Obj(String object, String id, String shape, String color, Coordinates coords) {
        super(id, shape, color, coords);
        this.object = object;
    }
    
    /**Método responsável pela introdução do tipo de objecto
     * @param object.*/
    public void setObject(String object) {
        this.object = object;
    }
    
    /**Método responsável pelo retorno do tipo de objecto
     * @return String
     */
    public String getObject() {
        return object;
    }

    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return String*/
    @Override
    public String toString() {
        return "{" + object + ", " +  super.toString() + "}";
    }

    @Override
    public String toString(int i) {
        return object;
    }
}
