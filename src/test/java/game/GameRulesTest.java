package game; 

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
public class GameRulesTest {

    final boolean ALIVE = true;
    final boolean DEAD = false;

    @Test
    public void aliveCellWithZeroLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 0;
        assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
    }

    @Test
    public void aliveCellWithOneLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 1;
        assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
    }

    @Test
    public void  aliveCellWithTwoLiveNeighborsLives( ){
        int numberOfLiveNeighbors = 2;
        assertEquals(ALIVE, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
    }

    @Test
    public void  aliveCellWithThreeLiveNeighborsLives( ){
        int numberOfLiveNeighbors = 3;
        assertEquals(ALIVE, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
    }

    @Test
    public void  aliveCellWithFourLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 4;
        assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
    }

    @Test
    public void  aliveCellWithFiveLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 5;
        assertEquals(DEAD, GameRules.isAlive(ALIVE, numberOfLiveNeighbors));
    }

    @Test
    public void deadCellWithZeroLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 0;
        assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
    }

    @Test
    public void deadCellWithOneLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 1;
        assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
    }

    @Test
    public void  deadCellWithTwoLiveNeighborsDies( ){
        int numberOfLiveNeighbors = 2;
        assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbors));
    }

    @Test
    public void  deadCellWithThreeLiveNeighborsLives( ){
        int numberOfLiveNeighbours = 3;
        assertEquals(ALIVE, GameRules.isAlive(DEAD, numberOfLiveNeighbours));
    }

    @Test
    public void  deadCellWithFourLiveNeighborsDies( ){
        int numberOfLiveNeighbours = 4;
        assertEquals(DEAD, GameRules.isAlive(DEAD, numberOfLiveNeighbours));
    }

    @Test
    public void cellIndexesWithTwoPositiveValuesInBound(){
        int size = 5;
        assertEquals(true, GameRules.inBound(size, 2, 3));
    }

    @Test
    public void cellIndexesWithOnePositiveValueOneNegativeValueOutBound(){
        int size = 5;
        assertEquals(false, GameRules.inBound(size, -1, 3));
    }

    @Test
    public void cellIndexesWithTwoPositiveValueExceedsSize(){
        int size = 5;
        assertEquals(false, GameRules.inBound(size, 6, 3));
    }

    @Test
    public void cellInMiddleOfGridWithEightNeighbours(){
        boolean cell[][] = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, DEAD, ALIVE},
                {ALIVE, ALIVE, ALIVE} };
        assertEquals(8, GameRules.noOfLivNeighbours(cell, 1, 1));
    }

    @Test
    public void cellAtLeftTopCornerWithThreeNeighbours(){
        boolean cell[][] = {
                {DEAD, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE} };
        assertEquals(3, GameRules.noOfLivNeighbours(cell, 0, 0));
    }

    @Test
    public void cellAtRightTopCornerWithTwoLiveNeighbours(){
        boolean cell[][] = {
                {ALIVE, DEAD, DEAD},
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE} };
        assertEquals(2, GameRules.noOfLivNeighbours(cell, 0, 2));
    }

    @Test
    public void cellAtLeftBottomCornerWithThreeNeighbour(){
        boolean cell[][] = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE},
                {DEAD, ALIVE, ALIVE} };
        assertEquals(3, GameRules.noOfLivNeighbours(cell, 2, 0));
    }

    @Test
    public void cellAtRightBottomCornerWithZeroLiveNeighbour(){
        boolean cell[][] = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, DEAD, DEAD},
                {ALIVE, DEAD, DEAD} };
        assertEquals(0, GameRules.noOfLivNeighbours(cell, 2, 2));
    }

    @Test
    public void cellAtTopEdgeMiddleWithFiveLiveNeighbour(){
        boolean cell[][] = {
                {ALIVE, DEAD, ALIVE},
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, ALIVE} };
        assertEquals(5, GameRules.noOfLivNeighbours(cell, 0, 1));
    }

    @Test
    public void cellAtRightEdgeMiddleWithFourLiveNeighbour(){
        boolean cell[][] = {
                {ALIVE, ALIVE, ALIVE},
                {ALIVE, ALIVE, DEAD},
                {ALIVE, ALIVE, DEAD} };
        assertEquals(4, GameRules.noOfLivNeighbours(cell, 1, 2));
    }

    @Test
    public void cellAtLeftEdgeMiddleWithThreeLiveNeighbour(){
        boolean cell[][] = {
                {ALIVE, DEAD, ALIVE},
                {DEAD, ALIVE, ALIVE},
                {ALIVE, DEAD, ALIVE} };
        assertEquals(3, GameRules.noOfLivNeighbours(cell, 1, 0));
    }

    @Test
    public void cellWithOnlyLiveCellAtInputPosition() {
        boolean cell[][] = {
                {DEAD, DEAD, DEAD},
                {DEAD, ALIVE, DEAD},
                {DEAD, DEAD, DEAD}};
        assertEquals(0, GameRules.noOfLivNeighbours(cell, 1, 1));
    }

    @Test
    public void nextGenerationWithZeroLiveCellsGrid()
    {
        boolean cell[][] = {
                {DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD} };
        boolean expectedCell[][] = {
                {DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD} };
        assertArrayEquals(expectedCell, GameRules.generateNext(cell));
    }

    @Test
    public void nextGenerationForBlock()
    {
        boolean cell[][] = {
                {DEAD, DEAD, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD}};
        boolean expectedCell[][] = {
                {DEAD, DEAD, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD}};
        assertArrayEquals(expectedCell, GameRules.generateNext(cell));
    }

    boolean horizontalBlinker[][] = {
            {DEAD, DEAD, DEAD, DEAD, DEAD},
            {DEAD, DEAD, ALIVE, DEAD, DEAD},
            {DEAD, DEAD, ALIVE, DEAD, DEAD},
            {DEAD, DEAD, ALIVE, DEAD, DEAD},
            {DEAD, DEAD, DEAD, DEAD, DEAD}};
    boolean verticalBlinker[][] = {
            {DEAD, DEAD, DEAD, DEAD, DEAD},
            {DEAD, DEAD, DEAD, DEAD, DEAD},
            {DEAD, ALIVE, ALIVE, ALIVE, DEAD},
            {DEAD, DEAD, DEAD, DEAD, DEAD},
            {DEAD, DEAD, DEAD, DEAD, DEAD}};

    @Test
    public void nextGenerationForHorizontalBlinker()
    {
        assertArrayEquals(verticalBlinker, GameRules.generateNext(horizontalBlinker));
    }

    @Test
    public void nextGenerationForVerticalBlinker()
    {
        assertArrayEquals(horizontalBlinker, GameRules.generateNext(verticalBlinker));
    }
}

