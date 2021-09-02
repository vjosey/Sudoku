package com.sudokudesktop.computationLogic;

import com.sudokudesktop.constants.GameState;
import com.sudokudesktop.persistence.LocalStorageImpl;
import com.sudokudesktop.problemdomain.IStorage;
import com.sudokudesktop.problemdomain.SudokuGame;
import com.sudokudesktop.userinterface.IUserInterfaceContract;
import com.sudokudesktop.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuilderLogic {

    public static void build(IUserInterfaceContract.View userInterface)throws IOException{
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        }catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage,userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
