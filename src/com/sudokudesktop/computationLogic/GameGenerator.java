package com.sudokudesktop.computationLogic;

import com.sudokudesktop.problemdomain.Coordinates;
import com.sudokudesktop.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sudokudesktop.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
    public static int[][] newGameGrid(){

        return unsolveGame(getSolvedGame());
    }

    private static int[][] unsolveGame(int[][] solvedGame) {
    }

    private static int[][] getSolvedGame() {

        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid =  new int[GRID_BOUNDARY][GRID_BOUNDARY];

        for ( int value = 1;  value <= GRID_BOUNDARY; value++){
            int allocations = 0;
            int interrupt = 0;
            List<Coordinates> allocTracker = new ArrayList<>();

            int attemps = 0;

             while( allocations < GRID_BOUNDARY){

                 if( interrupt >  200){
                     allocTracker.forEach(coordinates -> {
                         newGrid[coordinates.getX() ][coordinates.getY()] = 0;
                     });

                     interrupt =0;
                     allocations = 0;
                     allocTracker.clear();

                     attemps++;

                     if(attemps > 500){
                         clearArray(newGrid);
                         attemps = 0;
                          value= 1;
                     }
                 }

                 int xCoordinate = random.nextInt(GRID_BOUNDARY);
                 int yCoordinate = random.nextInt(GRID_BOUNDARY);

                 if(newGrid[xCoordinate][yCoordinate] == 0){
                     newGrid[xCoordinate][yCoordinate] = value;

                     if(GameLogic.sudokuIsInvalid(newGrid)){
                         newGrid[xCoordinate][yCoordinate] =0;
                     }else{
                         allocTracker.add(new Coordinates(xCoordinate,yCoordinate));
                         allocations++;
                     }
                 }

             }
        }
        return newGrid;
    }

    private static void clearArray(int[][] newGrid) {

        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                newGrid[xIndex][yIndex] = 0;
            }}
    }
}