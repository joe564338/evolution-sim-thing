package UI.MapComponents.Map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Created by Joe on 2/8/2017.
 */
public abstract class MapEntity {
    private double xPos;
    private double yPos;
    private int    draw_xPos;
    private int    draw_yPos;
    private double xPos_prev;
    private double yPos_prev;
    private int    height;
    private int    width;
    private double scale;

    private Rectangle rect;
    private World world;
    protected void init(double x, double y, int w, int h, double zoom) {

        this.xPos = x;
        this.yPos = y;
        this.draw_xPos = (int) x;
        this.draw_yPos = (int) y;
        this.height = h;
        this.width = w;
        this.xPos_prev = x;
        this.yPos_prev = y;
        this.rect = new Rectangle((int) xPos, (int) yPos, (int) width, (int) height);
        this.scale = zoom;
    }
}
