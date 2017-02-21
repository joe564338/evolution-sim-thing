package Entities;

import java.util.Random;

/**
 * Created by Joe on 2/2/2017.
 */
public class Organism extends Entity {
    int red;
    int green;
    int blue;
    Random r;
    String speciesName;
    int lifeCircleRadius; // minimum radius needed for life
    int actualRadius;
    int hunger;
    public Organism(int xPosition, int yPosition){
        super(xPosition, yPosition);
        hunger = 0;
        r = new Random(System.currentTimeMillis());
        lifeCircleRadius = r.nextInt(150) + 50;
        actualRadius = lifeCircleRadius + r.nextInt(125) +25;
        red = r.nextInt(255);
        green = r.nextInt(255);
        blue = r.nextInt(255);
        speciesName = nameGenRandom();
    }
    private static String[] nameSyllables = {"osh", "el", "mon", "fay", "shi", "zag", "blarg", "rash", "izen", "cot", "sen", "sin", "si", "so", "ro" };
    private String nameGenRandom()
    {

        int numOfSyllablesInName = r.nextInt(6) + 1;
        String name = "";
        for(int i = 0; i < numOfSyllablesInName; i++)
        {
            name = name + nameSyllables[r.nextInt(nameSyllables.length)];
        }
        return name;
    }
    class Brain{

    }
}
