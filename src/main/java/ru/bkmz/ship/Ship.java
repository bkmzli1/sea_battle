package ru.bkmz.ship;

import java.awt.*;

public class Ship extends Line {
    int size;

    Color color;
    boolean live = true;
    int kill = 0;

    /**
     * @param size  размер коробля
     * @param x1,y1 точка появления коробля
     */
    public Ship(int size, int x1, int y1, int direction, int dimensions) throws ExclusionOfPlacement {
        super(x1, y1, direction, size, dimensions);
        this.size = size;

        this.color = Color.green;

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void kill() {
        kill++;
        if (kill >= size) {
            live = false;
        }
    }

    public void killRes() {
        kill = 0;
    }
}
