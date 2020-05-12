package com.majdtalji.krankenhaus.validation;


import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public interface ScreenBounds {
    
    Screen SCREEN = Screen.getPrimary();
    Rectangle2D BOUNDES = SCREEN.getVisualBounds();
    
    double WIDTH = BOUNDES.getMaxX();
    double HEIGHT = BOUNDES.getMaxY();
    double MINX = BOUNDES.getMinX();
    double MINY = BOUNDES.getMinX();
    
}
