package com.majdtalji.krankenhaus.validation;

import javafx.scene.control.Alert;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Validation implements ScreenBounds {

    public static boolean isEmpty(String... text) {
        for (String s : text) {
            if (s != null && s.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(int... value) {
        for (int i : value) {
            if (i < 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDigit(String... text) {
        for (String s : text) {
            if (!s.matches("[0-9]+")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isText(String... text) {
        for (String s : text) {
            if (!s.matches("[a-z]+")) {
                return false;
            }
        }
        return true;
    }

    static Alert alert = new Alert(Alert.AlertType.NONE);

    public static void specialAlertShow(String title, String message, Alert.AlertType alertType) {
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.setAlertType(alertType);
        alert.showAndWait();
    }

}
