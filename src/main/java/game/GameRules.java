package game; 

import java.util.Random;

public class GameRules
{
    public static final boolean ALIVE = true;

    public static boolean isAlive(boolean presentState, int noOfLiveNeighbourCells)
    {
        return presentState == ALIVE && noOfLiveNeighbourCells == 2 || noOfLiveNeighbourCells == 3 ;
    }

    public static boolean inBound(int size, int x, int y)
    {
        return x < size &&  x >= 0 && y < size && y >= 0;
    }

    public static int noOfLivNeighbours(boolean[][] grid, int x, int y)
    {
        int noOfLiveCells = 0;

        for (int i = x - 1; i < (x + 2); i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (inBound(grid.length, i, j) && grid[i][j] == ALIVE)
                    noOfLiveCells++;
            }
        }
        if(grid[x][y] == ALIVE)
            return noOfLiveCells - 1;
        return noOfLiveCells;

    }

    public static boolean[][] generateNext(boolean[][] grid) {

        boolean[][] updatedGrid = new boolean[grid.length][grid.length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int noOfLiveNeighbours = noOfLivNeighbours(grid, i, j);
                updatedGrid[i][j] = isAlive(grid[i][j], noOfLiveNeighbours);
            }
        }

        return updatedGrid;
    }
}