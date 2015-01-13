package projecto;

/** Classe inicial do projecto, onde será iniciado o mundo e a janela inicial de configuração
 *@author Bernardo Casaleiro
 *@author Francisco Magalhães
 */

public class Projecto {
    private static Projecto projecto;
    private GUI mainInterface;
    private World world = new World();
    
    /** Construtor Projecto necessário por forma a evitar o uso de variaveis do tipo static*/
    public Projecto(){
        mainInterface = new GUI(0, world);
    }
    
    public static void main(String args[]) {
        projecto = new Projecto();
    }
}
