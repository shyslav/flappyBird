package com.shyslav.start;

import com.shyslav.models.bird;
import com.shyslav.models.wall;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Shyshkin Vladyslav on 28.02.2016.
 */
public class game extends Application {
    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();
    public static ArrayList<wall> walls = new ArrayList<>();
    bird bird = new bird();
    public static int score = 0;
    public Label scroleLabel = new Label("Score" + " :" + score);


    public Parent createContent()
    {
        gameRoot.setPrefSize(600,600);

        for(int i = 0 ; i<100;i++)
        {
            int enter = (int)(Math.random()*100+50); //50-150
            int height = new Random().nextInt(600-enter);//0-600 - enter

            wall wall1 = new wall(height);
            wall1.setTranslateX(i*350+600);
            wall1.setTranslateY(0);
            walls.add(wall1);

            wall wall2 = new wall(600-height-enter);
            wall2.setTranslateX(i*350+600);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);
            gameRoot.getChildren().addAll(wall1,wall2);
        }
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot);
        return appRoot;
    }

    public void update()
    {
        if(bird.velocity.getY()<5)
        {
            bird.velocity = bird.velocity.add(0,1); //на 5 пикселей вниз
        }
        bird.moveX((int) bird.velocity.getX());//двигатся на колво пикселей которое указано в точке велосити
        bird.moveY((int) bird.velocity.getY());//двигаться на колво пикселей которое указано в точке велосити
        scroleLabel.setText(String.valueOf(score));
        bird.translateXProperty().addListener((ovs,old,newValue)->{ //при изминении Х птички будет происходить действие
            int offset = newValue.intValue();
            if(offset>200){
                gameRoot.setLayoutX(-(offset-200));
            }

        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> bird.jump());
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bird.jump();
            }
        });
        primaryStage.setTitle("Flappy Bird");
        primaryStage.setScene(scene);
        primaryStage.show();

        //вызывается каждый кадр игры
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
    public static void main(String[]args)
    {
        launch(args);
    }
}
