import UI.MapComponents.Map.World;

/**
 * Created by Joe on 2/2/2017.
 */
public class EvoMain {
    public static void main(String [] args){
        System.out.println("Hello");
        World world = new World(World.SMALL);
        System.out.println(world.noiseToASCII());
        System.out.println(world.worldToASCII());
    }
}
