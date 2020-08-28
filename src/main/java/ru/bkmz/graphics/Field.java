package ru.bkmz.graphics;


import ru.bkmz.util.Coordinates;
import ru.bkmz.ship.Line;
import ru.bkmz.ship.Ship;
import ru.bkmz.util.ColorLine;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Field {
    char[][] field;
    Scanner scn;

    String horizontal = "═", vertical = "║", DAR = "╔", DAL = "╗", UAR = "╚", UAL = "╝", nul = " ";
    String error = "";

    int dimensions;
    int indent = 1;
    boolean game = false;

    Map<String, Coordinates> coordinatesMap;

    Player player1 = new Player("1");
    Player player2 = new Player("2");

    public Field(int size) throws Line.ExclusionOfPlacement {
        coordinatesMap = new HashMap<>();
        scn = new Scanner(System.in);
        dimensions = 20 + (size * 2);
        init(player1);

    }

    public void init(Player player) throws Line.ExclusionOfPlacement {
        player.shipsGame.clear();

        player.shipsGame.addAll(player.shipsOne);
        player.shipsGame.addAll(player.shipsTwo);
        player.shipsGame.addAll(player.shipsTree);
        player.shipsGame.addAll(player.shipsFour);


        clearConsole(error);


        error = "";
        render(dimensions, player);
        System.out.println("Игрок:" + player.name);
        System.out.println("Выберите корабль: ");
        System.out.println("(1): четырехпалубный\t" + Math.abs(player.shipsFour.size() - 1));
        System.out.println("(2): трехпалубных\t\t" + Math.abs(player.shipsTree.size() - 2));
        System.out.println("(3): двухпалубных\t\t" + Math.abs(player.shipsTwo.size() - 3));
        System.out.println("(4): однопалубных\t\t" + Math.abs(player.shipsOne.size() - 4));
        System.out.println("(5): сменить игрока");
        System.out.println("(6): начать игру (будет ходить первым игрок который выбран)");
        System.out.println(player.shipsGame.size());
        System.out.print("Введите номер коробя: ");
        try {
            switch (scn.next()) {
                case "1":
                    addShip(player.shipsGame, player.shipsFour, 1, 4);
                    break;
                case "2":
                    addShip(player.shipsGame, player.shipsTree, 2, 3);
                    break;
                case "3":
                    addShip(player.shipsGame, player.shipsTwo, 3, 2);
                    break;
                case "4":
                    addShip(player.shipsGame, player.shipsOne, 4, 1);
                    break;
                case "5":
                    if (player == player1) {
                        player = player2;
                    } else {
                        player = player1;
                    }
                    break;
                case "6":
                    play(player);
                    break;
                default:
                    error = "что-то пошло не так";

            }
        } catch (Exception e) {
            e.printStackTrace();
            error = "что-то пошло не так";
        }
        init(player);
    }

    private void play(Player player) {

        for (Ship ship : player.shipsGame) {
            ship.killRes();
        }
        if (player == player1) {
            player = player2;
        } else {
            player = player1;
        }
        game = true;
        clearConsole(error);
        error = "";
        System.out.println("Вы атакуете игрока " + player.name);
        render(dimensions, player);
        System.out.print("Введите поле корабля: ");
        player.missed.add(coordinatesMap.get(scn.next()));
        clearConsole("");
        render(dimensions, player);
        boolean live = false;
        for (Ship ship : player.shipsGame) {
            if (ship.isLive()) {
                live = true;
                break;
            }
        }
        if (!live) {
            System.out.println("игрок " + player.name + "повержен");
            System.exit(0);
        }
        System.out.print("ведите что-то");
        scn.next();
        play(player);
    }


    void addShip(ArrayList<Ship> shipsGmae, ArrayList<Ship> ships, int maxSize, int size) throws Line.ExclusionOfPlacement {
        if (ships.size() < maxSize) {
            System.out.print("Ведите адрес ячейки(A/1):");
            String in = scn.next();
            System.out.println("Ведите направление:");
            System.out.println("(1) Вниз");
            System.out.println("(2) Вверх");
            System.out.println("(3) Право");
            System.out.println("(4) Влево");
            System.out.print("Введите номер:");
            int in2 = scn.nextInt();
            Ship ship = new Ship(size, coordinatesMap.get(in).getX(), coordinatesMap.get(in).getY(), in2 - 1, dimensions);
            boolean add = true;
            for (Ship ship1 : shipsGmae) {
                Set<Coordinates> coordinatesAdd = ship.getCoordinates();
                Set<Coordinates> coordinates = ship1.getCoordinates();
                for (Coordinates coordinate : coordinatesAdd) {
                    for (Coordinates coordinate1 : coordinates) {
                        if (coordinate.getX() == coordinate1.getX() & coordinate.getY() == coordinate1.getY()) {
                            add = false;
                            break;
                        }
                    }
                    if (!add) {
                        break;
                    }
                }
            }
            if (add)
                ships.add(ship);

        }
    }

    private void render(int size, Player player) {
        System.out.print("\t");
        indent(indent);
        for (char i = 65; i < 65 + dimensions / 2; i++) {
            System.out.print(i);
            indent(indent);
        }
        indent(indent);
        System.out.println();
        System.out.print("\t");
        int number = 1;
        int realX = 0, realY = 0;
        for (int y = 0; y <= size; y++) {
            int i = 0;
            for (int x = 0; x <= size; x++) {
                if (y == 0 & x == 0) {
                    System.out.print(DAR);
                } else if (y == size & x == 0) {
                    System.out.print(UAR);
                } else if (y == 0 & x == size) {
                    System.out.print(DAL);
                } else if (y == size & x == size) {
                    System.out.print(UAL);
                } else if (y == 0 & x != 0 & x < size) {
                    System.out.print(horizontal);
                } else if (y % 2 == 0 & x > 0 & x < size) {
                    System.out.print(horizontal);
                } else if (x % 2 == 0 & y > 0 & y < size) {
                    System.out.print(vertical);
                } else if (x == 0 | x == size) {
                    System.out.print(vertical);
                } else if (y == size) {
                    System.out.print(horizontal);
                } else {
                    coordinatesMap.put((char) (65 + i) + "/" + (number - 1), new Coordinates(realX, realY));
                    i++;
                    boolean b = true;
                    if (!game)
                        for (Ship ship : player.shipsGame) {
                            if (ship.getDirection() == 2 | ship.getDirection() == 0) {
                                if ((ship.getX1() <= realX & ship.getY1() <= realY) & (ship.getX2() >= realX & ship.getY2() >= realY)) {
                                    if (ship.getColor() == Color.green) {
                                        System.out.print(ColorLine.ANSI_GREEN + "█" + ColorLine.ANSI_WHITE);
                                    }
                                    b = !b;
                                }
                            }
                            if (ship.getDirection() == 1 | ship.getDirection() == 3) {

                                if ((ship.getX1() >= realX & ship.getY1() >= realY) & (ship.getX2() <= realX & ship.getY2() <= realY)) {
                                    if (ship.getColor() == Color.green) {
                                        System.out.print(ColorLine.ANSI_GREEN + "█" + ColorLine.ANSI_WHITE);
                                    }
                                    b = !b;
                                } else {

                                }

                            }
                        }
                    if (b) {
                        boolean mes = false;

                        for (Coordinates coordinate : player.missed) {
                            if (coordinate.getX() == realX & coordinate.getY() == realY) {
                                mes = true;
                            }
                        }
                        if (!mes) {
                            System.out.print("█");
                        } else {
                            boolean got = false;
                            for (Ship ship : player.shipsGame) {
                                for (Coordinates coordinate : ship.getCoordinates()) {
                                    if (coordinate.getX() == realX & coordinate.getY() == realY) {
                                        got = true;
                                        ship.kill();
                                    }
                                }
                            }
                            if (!got) {
                                System.out.print(ColorLine.ANSI_YELLOW + "█" + ColorLine.ANSI_WHITE);
                            } else {
                                System.out.print(ColorLine.ANSI_RED + "█" + ColorLine.ANSI_WHITE);
                            }

                        }

                    }
                    realX++;
                }

            }

            System.out.println();

            if (y % 2 == 0 & y < size) {
                System.out.print((number++) + "\t");
                if (y != 0) {
                    realX = 0;
                    realY++;
                }
            } else System.out.print("\t");

        }
    }

    private void indent(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
    }


    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public final static void clearConsole(String error) {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
        System.out.println(error);
    }


}
