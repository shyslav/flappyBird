package com.shyslav.models;

import com.shyslav.start.game;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Shyshkin Vladyslav on 28.02.2016.
 */
public class bird extends Pane {
    public Point2D velocity;           //принимает координаты, и по ним мы двигаем птичку
    Circle Bird;

    public bird() {
        Bird = new Circle(10, Color.RED);
        velocity = new Point2D(0, 0);
        //относится к Pane
        setTranslateX(300);
        setTranslateY(100);
        //добавить круг на панель птички
        getChildren().addAll(Bird);
    }

    public void moveY(int value) {
        //проверка куда двигается обьект, если больше 0, вниз, иначе вверх
        boolean moveDown = value > 0 ? true : false;
        for (int i = 0; i < Math.abs(value); i++) {
            for (wall w : game.walls) {
                if (getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (moveDown) {
                       // System.out.println("1----Столкновение по Y внизу");
                        setTranslateY(getTranslateY() - 1);
                        return;
                    } else {
                        //System.out.println("2----Столкновение по Y вверху");
                        setTranslateY(getTranslateY() + 1);
                        return;
                    }
                }
            }
            //нельзя пригать выше 0
            if (getTranslateY() < 0) {
                setTranslateY(0);
            }
            //нельзя упасть ниже 580
            if (getTranslateY() > 580) {
                setTranslateY(580);
            }
            //получить текущую координату и плюс или минус 1
            setTranslateY(getTranslateY() + (moveDown ? 1 : -1));
        }
    }

    public void moveX(int value) {
        for (int i = 0; i < Math.abs(value); i++) {
            setTranslateX(getTranslateX() + 1);
            for (wall w : game.walls) {
                //проверка на столкновение со стеной
                if (getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (getTranslateX() + 20 == w.getTranslateX()) {
                        //System.out.println("Столкновение по Х");
                        setTranslateX(getTranslateX() - 1);
                        return;
                    }
                }
                if (getTranslateX() + 20 == w.getTranslateX()) {
                    game.score++;
                }
            }
        }
    }

    public void jump() {
                velocity = new Point2D(3, -15);
        }
    public void alert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Вы не выбрали ни одной записи");
        alert.setContentText("Для выбора записи из таблицы необходимо нажать на нее один раз!");
        alert.showAndWait();
    }
}
