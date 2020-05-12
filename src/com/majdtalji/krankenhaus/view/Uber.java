package com.majdtalji.krankenhaus.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Uber extends Dialog {

    private TextField email;
    private Label jLabel1;
    private Label jLabel2;
    private Label jLabel3;
    private Label jLabel4;
    private Label jLabel5;
    private Label jLabel6;
    private Label jLabel7;
    private GridPane jPanel1;
    private TextField jTextField1;
    private TextField jTextField2;
    private TextField jTextField3;
    private TextField jTextField4;
    private Hyperlink pathFacebook;

    public Uber() {

        email = new TextField("en.majd.talji@gmail.com");
        pathFacebook = new Hyperlink("https://github.com/Majd-Talji");
        jLabel1 = new Label("Der Programmname");
        jLabel2 = new Label("Der Programmierer");
        jLabel3 = new Label("Die Adresse");
        jLabel4 = new Label("Die Handynummer");
        jLabel5 = new Label("Die E-Mail");
        jLabel6 = new Label("Website");
        jLabel7 = new Label("Über mir");
        jPanel1 = new GridPane();
        jTextField1 = new TextField("Krankenhaus system");
        jTextField2 = new TextField("Majd Talji");
        jTextField3 = new TextField("Landeskronstr. 48, 02826 Görlitz");
        jTextField4 = new TextField("+49 157 81142620");

        Font font12 = Font.font("Tahoma", FontWeight.BOLD, 12);
        Font font14 = Font.font("Tahoma", FontWeight.BOLD, 14);

        jTextField1.setDisable(true);
        jTextField2.setDisable(true);
        jTextField3.setDisable(true);
        jTextField4.setDisable(true);
        email.setDisable(true);

        jLabel1.setFont(font14);
        jLabel2.setFont(font14);
        jLabel3.setFont(font14);
        jLabel4.setFont(font14);
        jLabel5.setFont(font14);
        jLabel6.setFont(font14);
        jLabel7.setFont(font12);

        jTextField1.setFont(font12);
        jTextField2.setFont(font12);
        jTextField3.setFont(font12);
        jTextField4.setFont(font12);
        email.setFont(font12);
        pathFacebook.setFont(font12);

        jLabel7.setTextFill(Color.BLUE);
        jTextField4.setStyle("-fx-text-fill: red;");
        email.setStyle("-fx-text-fill: red;");
        pathFacebook.setStyle("-fx-text-fill: rgb(255, 0, 51);");

        pathFacebook.setPrefWidth(300);

        jPanel1.add(jLabel1, 0, 0);
        jPanel1.add(jLabel2, 0, 1);
        jPanel1.add(jLabel3, 0, 2);
        jPanel1.add(jLabel4, 0, 3);
        jPanel1.add(jLabel5, 0, 4);
        jPanel1.add(jLabel6, 0, 5);
        jPanel1.add(jTextField1, 1, 0);
        jPanel1.add(jTextField2, 1, 1);
        jPanel1.add(jTextField3, 1, 2);
        jPanel1.add(jTextField4, 1, 3);
        jPanel1.add(email, 1, 4);
        jPanel1.add(pathFacebook, 1, 5);

        jPanel1.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: gray;");
        jPanel1.setHgap(20);
        jPanel1.setVgap(15);

        pathFacebook.setOnAction((event) -> {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(pathFacebook.getText()));
            } catch (IOException | URISyntaxException ex) {
            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(3);
        VBox.setMargin(jPanel1, new Insets(0, 10, 0, 10));
        VBox.setMargin(jLabel7, new Insets(10, 10, 0, 25));

        root.getChildren().add(jLabel7);
        root.getChildren().add(jPanel1);

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        this.getDialogPane().setContent(root);
        this.setTitle("Informationen über Programmierer");
        this.setResizable(false);
    }

    public void setVisible(boolean bool) {
        if (bool) {
            this.showAndWait();
        } else {
            this.close();
        }
    }

    public void jButton1ActionPerformed() {

    }

}
