package projecto;

/** Classe para objectos do tipo entidade, onde serão contidos todos os valores necessários no contexto da simulação*/

public abstract class Entity {
    protected String id;
    protected String shape;
    protected String color;
    protected Coordinates coords;
    
    /**Construtor com iniciação de todas as variávei
     * @param id
     * @param shape
     * @param color
     * @param coords*/
    public Entity(String id, String shape, String color, Coordinates coords) {
        this.id = id;
        this.shape = shape;
        this.color = color;
        this.coords = coords;
    }
    
    /**Método responsável pela introdução do ID de uma entidade..
     * @param id.*/
    public void setId(String id) {
        this.id = id;
    }

    /**Método responsável pela forma de uma entidade..
     * @param shape.*/
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**Método responsável pela introdução da cor de uma entidade..
     * @param color.*/
    public void setColor(String color) {
        this.color = color;
    }

    /**Método responsável pela introdução das coordenadas de uma entidade..
     * @param coords.*/
    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }
    
     /**Método responsável pelo retorno do ID de uma entidade.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**Método responsável pelo retorno da forma de uma entidade.
     * @return shape
     */
    public String getShape() {
        return shape;
    }

    /**Método responsável pelo retorno da cor de uma entidade.
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**Método responsável pelo retorno das coordenadas de uma entidade.
     * @return coords
     */
    public Coordinates getCoords() {
        return coords;
    }

    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return string*/
    public String toString() {
        return "Id: " + id + ", Forma: " + shape + ", Cor: " + color + ", Coordenadas: " + coords;
    }
    
    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da class
     * @return string*/
    public String toString(int i) {
        return "\nId: " + id + "\nForma: " + shape + "\nCor: " + color;
    }
}
