package ru.kipelovets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Pair<T> {
    T first;
    T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}

class GameState {
    int floor;
    int pos;
    String dir;
    Pair<Integer>[] elevators;
    List<Pair<Integer>> blocked;
    int exitPos;
    int exitFloor;

    public GameState(int exitFloor, int exitPos, Pair<Integer>[] elevators) {
        this.exitFloor = exitFloor;
        this.exitPos = exitPos;
        this.floor = -1;
        this.pos = -1;
        this.dir = "RIGHT";
        this.blocked = new ArrayList<Pair<Integer>>();
        this.elevators = elevators;
    }

    public void setState(int floor, int pos, String dir) {
        this.floor = floor;
        this.pos = pos;
        this.dir = dir;
    }

    public void block() {
        this.blocked.add(new Pair<Integer>(floor, pos));
    }

    private boolean isPointAhead(int floor, int pos) {
        return floor == this.floor && ((pos >= this.pos && dir.equals("RIGHT")) || (pos <= this.pos && dir.equals("LEFT")));
    }

    public boolean isElevatorAhead() {
        for (Pair<Integer> elev : elevators) {
            if (isPointAhead(elev.first, elev.second)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPathBlocked() {
        System.err.println("path " + floor + ", " + pos);

        for (Pair<Integer> clone : blocked) {
            System.err.println("X " + clone.first + ", " + clone.second);
            if (isPointAhead(clone.first, clone.second)) {
                return true;
            }
        }

        return false;
    }

    public boolean isExitAhead() {
        return isPointAhead(exitFloor, exitPos);
    }
}

class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        System.err.println(
                "nbFloors = " + nbFloors + ", " +
                        "width = " + width + ", " +
                        "nbRounds = " + nbRounds + ", " +
                        "exitFloor = " + exitFloor + ", " +
                        "exitPos = " + exitPos + ", " +
                        "nbTotalClones = " + nbTotalClones + ", " +
                        "nbAdditionalElevators = " + nbAdditionalElevators + ", " +
                        "nbElevators = " + nbElevators);

        Pair<Integer>[] elevators = new Pair[nbElevators];
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevators[i] = new Pair<Integer>(elevatorFloor, elevatorPos);
            System.err.println("\t" + elevators[i].getFirst() + " - " + elevators[i].getSecond());
        }

        GameState state = new GameState(exitFloor, exitPos, elevators);
        int round = 1;
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            state.setState(cloneFloor, clonePos, direction);

            System.err.println("#" + round + " floor " + cloneFloor + ", pos " + clonePos + " <" + direction + ">");

            if (cloneFloor == -1) {
                System.out.println("WAIT");
                continue;
            }

            if (!state.isExitAhead() && !state.isElevatorAhead() && !state.isPathBlocked()) {
                System.out.println("BLOCK");
                state.block();
                continue;
            }

            System.out.println("WAIT");
        }
    }
}