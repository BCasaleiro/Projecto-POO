package projecto;

/** Classe com a posição de uma entidade*/

public class Coordinates {
    private int x;
    private int y;

   /**Construtor com a iniciação das variáveis
    * @param x
    * @param y 
    */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**Método responsável pela introdução da coordenada X de uma entidade..
     * @param x.*/
    public void setX(int x) {
        this.x = x;
    }

    /**Método responsável pela introdução da coordenada Y de uma entidade..
     * @param y.*/
    public void setY(int y) {
        this.y = y;
    }
    
    /**Método responsável pelo retorno da coordenada X de uma entidade.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**Método responsável pelo retorno da coordenada Y de uma entidade.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**Método responsável pelo retorno de uma string sempre que existe a tentativa de impressão da classe.
     * @return String*/
    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
    
}
