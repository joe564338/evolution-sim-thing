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
    int noise[][];
    Random random;
    int size;
    public World(int size){
        this.size = size;
        landMasses = 0;
        tiles = new Tile[size][size];
        //todo: generate structures with tiles...
        for(int i = 0; i < tiles.length; i++){
            for(int k = 0; k < tiles[1].length; k++){
                tiles[i][k] = new Tile(0, 0);
            }
        }
        random = new Random(System.currentTimeMillis());
        noise = new int[size][size];
        int structures= 0;
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                noise[i][j] = 0;
            }
        }
        for (int i = 0; i < 16; i++){
            int xRandom = random.nextInt(size);
            int yRandom = random.nextInt(size);
            noise[yRandom][xRandom] = 100;
            recursivelyGenerateNoise(xRandom, yRandom);
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int tileOk = random.nextInt(100);
                if(noise[i][j] > tileOk) {
                    tiles[i][j] = new Tile(5, 100);
                }
            }
        }
    }
    public void recursivelyGenerateNoise(int initX, int initY){
        recursivelyGenerateNoise(initX, initY, initX, initY, 0, 1);
        recursivelyGenerateNoise(initX, initY, initX, initY, 1, 1);
        recursivelyGenerateNoise(initX, initY, initX, initY, 1, 0);
        recursivelyGenerateNoise(initX, initY, initX, initY, 1, -1);
        recursivelyGenerateNoise(initX, initY, initX, initY, 0, -1);
        recursivelyGenerateNoise(initX, initY, initX, initY, -1, -1);
        recursivelyGenerateNoise(initX, initY, initX, initY, -1, 0);
        recursivelyGenerateNoise(initX, initY, initX, initY, -1, 1);
    }
    public void recursivelyGenerateNoise(int initX, int initY,int currentX, int currentY, int deltaX, int deltaY){
        if(Math.abs(currentX - initX) < 15 && Math.abs(currentY - initY) < 15){
            if(currentX < 0 || currentY < 0 || currentX >= size || currentY >= size){

                return;
            }
            noise[currentY][currentX] = 110 - (int)Math.sqrt(Math.pow(initX-currentX,2) + Math.pow(initY-currentY,2)) * 5;
            if(deltaX == 0 && deltaY == 1) {
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 1, 0);
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 0, 1);
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, -1, 0);
            }
            else if(deltaX == 1 && deltaY == 1){
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 1, 1);
            }
            else if(deltaX == 1 && deltaY == 0){
                //recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 0, 1);
                //recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 0, -1);
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 1, 0);
            }
            else if(deltaX == 1 && deltaY == -1){
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 1, -1);
            }
            else if(deltaX == 0 && deltaY == -1){
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 0, -1);
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 1, 0);
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, -1, 0);
            }
            else if(deltaX == -1 && deltaY == -1){
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, -1, -1);
            }
            else if(deltaX == -1 && deltaY == 0){
                //recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 0, 1);
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, -1, 0);
                //recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, 0, -1);
            }
            else if(deltaX == -1 && deltaY == 1){
                recursivelyGenerateNoise(initX, initY, currentX + deltaX, currentY + deltaY, -1, 1);
            }

        } else{
            return;
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
    public String noiseToASCII(){
        String noiseString = "";
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                noiseString = noiseString + noise[i][j] + " ";
            }
            noiseString = noiseString + "\n";
        }
        return noiseString;
    }
    public String worldToASCII(){
        String world = "";
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(tiles[i][j].status > 0) {
                    world = world + "L ";
                }
                else {
                    world = world + "W ";
                }
            }
            world = world + "\n";
        }
        return world;
    }
}
