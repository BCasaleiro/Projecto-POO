package projecto;

public class Projecto {
    private static Projecto projecto;
    private GUI mainInterface;
    private World world = new World();
    
    public Projecto(){
        mainInterface = new GUI(0, world);
    }
    
    public static void main(String args[]) {
        projecto = new Projecto();
    }
}
