package ru.bkmz.ship;

import ru.bkmz.Main;
import ru.bkmz.util.Address;
import ru.bkmz.util.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class Line {
    int x1, y1, x2, y2;
    int direction;
    Set<Coordinates> coordinates;

    public Line(int x1, int y1, int direction, int size, int dimensions) throws ExclusionOfPlacement {
        this.coordinates = new HashSet<>();
        this.x1 = x1;
        this.y1 = y1;
        this.direction = direction;


        if (size != 1) {
            size--;
            switch (direction) {
                case 0:
                    if (y1 + size > (dimensions / 2) - 1)
                        throw new ExclusionOfPlacement("выход в положительную сторону");
                    this.y2 = y1 + size;
                    this.x2 = x1;
                    break;
                case 1:
                    if (y1 - size < 0) throw new ExclusionOfPlacement("выход в отрецательную сторону");
                    this.y2 = y1 - size;
                    this.x2 = x1;
                    break;
                case 2:
                    if (x1 + size > (dimensions / 2) - 1)
                        throw new ExclusionOfPlacement("выход в положительную сторону");
                    this.x2 = x1 + size;
                    this.y2 = y1;
                    break;
                case 3:
                    if (x1 - size < 0) throw new ExclusionOfPlacement("выход в отрецательную сторону");
                    this.x2 = x1 - size;
                    this.y2 = y1;
            }
        } else {
            this.x2 = x1;
            this.y2 = y1;
        }
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                coordinates.add(new Coordinates(x, y));
            }
        }
        for (int x = x2; x <= x1; x++) {
            for (int y = y2; y <= y1; y++) {
                coordinates.add(new Coordinates(x, y));
            }
        }
        System.out.println(coordinates.size());
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Set<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Set<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public class ExclusionOfPlacement extends Exception {


        public ExclusionOfPlacement(String message) {

            super(message);

        }
    }
}
