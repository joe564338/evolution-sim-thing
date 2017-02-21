package UI.MapComponents.Map;

import java.util.Random;

/**
 * Created by Joe on 2/3/2017.
 */
public class World {
    public static final int SMALL = 100;
    public static final int MEDIUM = 200;
    public static final int LARGE = 300;
    private int landMasses;
    Tile tiles[][];
    Random random;
    public World(int size){
        landMasses = 0;
        tiles = new Tile[size][size];
        //todo: generate structures with tiles...
        for(int i = 0; i < tiles.length; i++){
            for(int k = 0; k < tiles[1].length; k++){
                tiles[i][k] = new Tile(0, 0);
            }
        }
        random = new Random(System.currentTimeMillis());
        int xCenter = random.nextInt(tiles.length);
        int yCenter = random.nextInt(tiles[1].length);
        recursiveGenerateLand(0, xCenter, yCenter);
    }

    private void recursiveGenerateLand(int previous, int x, int y){
        if(previous == 0){
            tiles[x][y] = new Tile(5, 100);
            int xLength = random.nextInt(15) +5;
        }
    }
    class Tile{
        int status;//Determines the type and color of tile; 0 for water, 1 for barren, 2, 3, 4, and 5 for food tiles where 5 has most food
        int food;
        public Tile(int status, int food){
            this.status = status;
            this.food = food;

        }
        public void reduceFood(){
            if(food > 5){
                food = food - 5;
            }
            else{
                food = 0;
            }
        }
        public int getFood(){
            return food;
        }
        public void increaseFood(){
            if(food < 98){
                food = food + 2;
            }
            else {
                food = 100;
            }
        }
        public void update(){
            if(food < 25 && status != 0 && status != 1){
                status = 2;
            }
            else if(food < 50 && status != 0 && status != 1){
                status = 3;
            }
            else if(food < 75 && status != 0 && status != 1){
                status = 4;
            }
            else if(status != 0 && status != 1){
                status = 5;
            }
        }
    }

}
