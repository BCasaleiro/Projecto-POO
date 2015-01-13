package projecto;

/** Classe para objectos do tipo Terrain, onde serão introduzidos os Objectos do ambiente */

public class Terrain {
    private Coordinates coords;
    private Entity entity;
    private boolean occupied;
    
    /**Construtor com iniciação de todas as variávei
     * @param coords
     * @param entity
     * @param occupied */
    public Terrain(Coordinates coords, Entity entity, boolean occupied) {
        this.coords = coords;
        this.entity = entity;
        this.occupied = occupied;
    }
    
    /**Método responsável pela introdução das coordenadas
     * @param coords */
    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }
    
    /**Método responsável pela introdução da entidade presente no terreno
     * @param entity */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**Método responsável pela introdução da variavel de condição por forma a saber se o terreno está ocupado ou não
     * @param occupied */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    /**Método responsável pelo retorno das coordenadas do terreno.
     * @return Coordinates
     */
    public Coordinates getCoords() {
        return coords;
    }

    /**Método responsável pelo retorno do tamanho da entidade presente no terreno.
     * @return Entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**Método responsável pelo retorno da variavel de condição por forma a saber se o terreno está ocupado ou não.
     * @return boolean
     */
    public boolean isOccupied() {
        return occupied;
    } 
}
