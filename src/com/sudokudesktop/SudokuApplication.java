package com.sudokudesktop;

import com.sudokudesktop.userinterface.IUserInterfaceContract;
import com.sudokudesktop.userinterface.UserInterfaceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.applet.Applet;
import java.io.IOException;

public class SudokuApplication extends Application {

    private  IUserInterfaceContract.View  uiImpl;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       uiImpl = new UserInterfaceImpl(primaryStage);

        try{
            SudokuBuilderLogic.build(uiImpl);
        } catch(IOException e){
            e.printStackTrace();
            throw e;
        }

    }
}
