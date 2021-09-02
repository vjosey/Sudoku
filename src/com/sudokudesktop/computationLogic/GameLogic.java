package com.sudokudesktop.computationLogic;

import com.sudokudesktop.constants.GameState;
import com.sudokudesktop.constants.Rows;
import com.sudokudesktop.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sudokudesktop.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.newGameGrid()
        );
    }

    public static GameState  checkForCompletion(int[][] grid){
        if( sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if( tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid)) return true;
        if(columsAreInvalid(grid)) return true;
        if(squaresAreInvalid(grid)) return true;

        else return false;
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        return AreInvalid(grid, true);
    }

    private static boolean columsAreInvalid(int[][] grid) {
        return AreInvalid(grid, false);
    }

    private static  boolean AreInvalid(int[][] grid, boolean rows){

        for(int xIndex =0; xIndex < GRID_BOUNDARY; xIndex++){
            List<Integer> row =  new ArrayList<>();
            for(int yIndex =0; yIndex < GRID_BOUNDARY; yIndex++){

                if(rows){
                    row.add(grid[yIndex][xIndex]);
                }else{
                    row.add(grid[xIndex][yIndex]);
                }
                ;
            }

            return collectionHasRepeats(row);
        }

        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if(rowsOfSquaresIsInvalid(Rows.TOP, grid)) return true;

        if(rowsOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        if(rowsOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean rowsOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch(value){
            case TOP:
                if(squareIsInvalid(0, 0, grid)) return true;
                if(squareIsInvalid(0, 3, grid)) return true;
                if(squareIsInvalid(0, 6, grid)) return true;
                return false;
            case MIDDLE:
                if(squareIsInvalid(3, 0, grid)) return true;
                if(squareIsInvalid(3, 3, grid)) return true;
                if(squareIsInvalid(3, 6, grid)) return true;
                return false;
            case BOTTOM:
                if(squareIsInvalid(6, 0, grid)) return true;
                if(squareIsInvalid(6, 3, grid)) return true;
                if(squareIsInvalid(6, 6, grid)) return true;
                return false;
        }

        return false;
    }

    private static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {

        int  yIndexEnd = yIndex + 3;
        int  xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while( yIndex < yIndexEnd){
            while (xIndex < xIndexEnd){
                square.add(grid[xIndex][yIndex]);
                xIndex++;
            }

            xIndex -= 3;
            yIndex++;
        }

        if(collectionHasRepeats(square)) return true;
         return false;
    }

    private static boolean collectionHasRepeats(List<Integer> collection) {
        for(int index = 1; index <= GRID_BOUNDARY; index++){
            if(Collections.frequency(collection, index) > 1) return true;
        }
        return false;
    }

    private static boolean tilesAreNotFilled(int[][] grid) {

        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                if(grid[xIndex][yIndex] == 0 ) return true;
            }
        }
        return false;
    }
}