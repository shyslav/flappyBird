package com.shyslav.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * Created by Shyshkin Vladyslav on 28.02.2016.
 */
public class wall extends Pane {
    Rectangle rect;
    public int height;
    public final int width = 20;

    public wall(int height)
    {
        this.height = height;
        rect = new Rectangle(width,height);
        getChildren().add(rect);
    }

}
