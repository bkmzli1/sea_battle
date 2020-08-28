package ru.bkmz.graphics;

import ru.bkmz.ship.Ship;
import ru.bkmz.util.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Player {

    String name;

    ArrayList<Ship> shipsGame = new ArrayList<>();


    ArrayList<Ship> shipsOne = new ArrayList<>();
    ArrayList<Ship> shipsTwo = new ArrayList<>();
    ArrayList<Ship> shipsTree = new ArrayList<>();
    ArrayList<Ship> shipsFour = new ArrayList<>();
    Set<Coordinates> missed = new HashSet<>();
    public Player(String name) {
        this.name = name;
    }

    public ArrayList<Ship> getShipsGame() {
        return shipsGame;
    }

    public void setShipsGame(ArrayList<Ship> shipsGame) {
        this.shipsGame = shipsGame;
    }

    public ArrayList<Ship> getShipsOne() {
        return shipsOne;
    }

    public void setShipsOne(ArrayList<Ship> shipsOne) {
        this.shipsOne = shipsOne;
    }

    public ArrayList<Ship> getShipsTwo() {
        return shipsTwo;
    }

    public void setShipsTwo(ArrayList<Ship> shipsTwo) {
        this.shipsTwo = shipsTwo;
    }

    public ArrayList<Ship> getShipsTree() {
        return shipsTree;
    }

    public void setShipsTree(ArrayList<Ship> shipsTree) {
        this.shipsTree = shipsTree;
    }

    public ArrayList<Ship> getShipsFour() {
        return shipsFour;
    }

    public void setShipsFour(ArrayList<Ship> shipsFour) {
        this.shipsFour = shipsFour;
    }
}
