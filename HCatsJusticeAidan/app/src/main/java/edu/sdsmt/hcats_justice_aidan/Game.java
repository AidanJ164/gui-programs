/*
 * Description: Holds the file for the main game model. This controls the backend logic of the game.
 */
package edu.sdsmt.hcats_justice_aidan;

import java.util.ArrayList;

public class Game {
    private final ArrayList<ArrayList<Integer>> grid;
    private final int SIZE = 3;
    private int treats = 3;
    private int moves = 15;
    private boolean treatBtn = false;

    /*
     * Description: Constructor for the Game.
     * Return: Game
     */
    public Game() {
        // Initialize the cat grid
        grid = new ArrayList<>();
        for (int i = 0; i < SIZE ; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                if ((i == SIZE - 1) && (j == SIZE - 1)) {
                    grid.get(i).add(0);
                }
                else {
                    grid.get(i).add(5);
                }
            }
        }
    }

    /*
     * Description: Another constructor for Game that allows for moves, treats, and the grid to be
     * defined.
     * Parameters: int moves - amount of moves left
     *             int treats - amount of treats left
     *             int[] cats - array that holds the number of cats in each grid.
     * Return: Game
     */
    public Game(int moves, int treats, int[] cats) {
        this.moves = moves;
        this.treats = treats;
        grid = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                grid.get(i).add(cats[i * SIZE + j]);
            }
        }
    }

    public int getCatsAt(int i, int j) {
        return grid.get(j).get(i);
    }

    public int getCatsCaught() {
        return grid.get(SIZE - 1).get(SIZE - 1);
    }

    public int getHeight() {
        return SIZE;
    }

    public int getMoves() {
        return moves;
    }
    public int getSize() {
        return SIZE;
    }
    public boolean getTreatBtn() {
        if (treats <= 0) {
            treatBtn = false;
        }
        return treatBtn;
    }

    /*
     * Description: Counts the total amount of cats not in the collection area.
     * Return: int - number of cats left
     */
    public int getTotalCatsLeft() {
        int numCats = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!(i == SIZE - 1 && j == SIZE - 1)) {
                    numCats += grid.get(i).get(j);
                }
            }
        }

        return numCats;
    }

    public int getTreats() {
        return treats;
    }

    public int getWidth() {
        return SIZE;
    }

    public boolean isLost() {
        return moves < 0;
    }

    public boolean isWon() {
        return grid.get(SIZE - 1).get(SIZE - 1) == 40;
    }

    /*
     * Description: Resets the game to its beginning state.
     */
    public void reset() {
        moves = 15;
        treats = 3;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!(i == SIZE - 1 && j == SIZE - 1)) {
                    grid.get(i).set(j, 5);
                }
                else {
                    grid.get(i).set(j, 0);
                }
            }
        }
    }

    public void setTreatBtn(boolean enable) {
        treatBtn = enable;
    }

    /*
     * Description: Sweeps the cats right depending on the parameters given. Given a minimum and a
     * percent, it will take the larger amount of cats that will move.
     * Parameters: int min - minimum amount of cats to move.
     *             int percent - percentage of cats to move.
     */
    public void sweepRight(int min, int percent) {
        moves -= 1;
        for (int i = 0; i < SIZE; i++) {
            int catsToMove = 0;
            for (int j = 0; j < SIZE; j++) {
                int numCats = grid.get(i).get(j);
                grid.get(i).set(j, numCats + catsToMove);
                if (numCats != 0) {
                    if (j != SIZE - 1) {
                        catsToMove = min;
                        int percentCats = (int)Math.floor(numCats * (percent / 100.00));
                        // Grab the larger of the minimum and the percent.
                        if (percentCats > catsToMove) {
                            catsToMove = percentCats;
                        }
                        // Check for more cats moving than what is available.
                        if (catsToMove > numCats) {
                            catsToMove = numCats;
                        }
                    }
                    else {
                        catsToMove = 0;
                    }
                    grid.get(i).set(j, grid.get(i).get(j) - catsToMove);
                }
            }
        }
    }

    /*
     * Description: Sweeps the cats down depending on the parameters given. Given a minimum and a
     * percent, it will take the larger amount of cats that will move.
     * Parameters: int min - minimum amount of cats to move.
     *             int percent - percentage of cats to move.
     */
    public void sweepDown(int min, int percent) {
        moves -= 1;
        for (int j = 0; j < SIZE; j++) {
            int catsToMove = 0;
            for (int i = 0; i < SIZE; i++) {
                int numCats = grid.get(i).get(j);
                grid.get(i).set(j, numCats + catsToMove);
                if (numCats != 0) {
                    if (i != SIZE - 1) {
                        catsToMove = min;
                        int percentCats = (int)Math.floor(numCats * (percent / 100.00));
                        // Grab the larger of the minimum and the percent.
                        if (percentCats > catsToMove) {
                            catsToMove = percentCats;
                        }
                        // Check for more cats moving than what is available.
                        if (catsToMove > numCats) {
                            catsToMove = numCats;
                        }
                    }
                    else {
                        catsToMove = 0;
                    }
                    grid.get(i).set(j, grid.get(i).get(j) - catsToMove);
                }
            }
        }
    }

    /*
     * Description: Uses a treat. Using a treat decreases treats and moves counters.
     */
    public void useTreat() {
        if (treats <= 0) {
            return;
        }
        treats -= 1;
        moves -= 1;
    }
}
