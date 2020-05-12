package com.majdtalji.krankenhaus.view;

import com.majdtalji.krankenhaus.validation.ScreenBounds;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import com.majdtalji.krankenhaus.validation.Validation;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Home extends Stage implements ScreenBounds {

    public static UsersVo usersVo;
    private Menu jMenu1;
    private Menu jMenu2;
    private MenuBar jMenuBar1;
    private MenuItem jMenuItem1;
    private MenuItem jMenuItem2;
    private MenuItem jMenuItem3;
    private MenuItem jMenuItem4;
    private MenuItem jMenuItem5;
    private MenuItem jMenuItem6;
    private Menu mAdmin;
    private Menu mDoctor;
    private Menu mNurse;
    private Menu mReceptionist;

    public Home() {

        jMenuBar1 = new MenuBar();

        mAdmin = new Menu("Admin");
        jMenuItem1 = new MenuItem("Add new User");
        jMenuItem1.setOnAction((event) -> {
            try {
                jMenuItem1ActionPerformed(event);
            } catch (Exception ex) {
                Validation.specialAlertShow("Error", "Please enter valid data.", AlertType.ERROR);
            }
        });
        mAdmin.getItems().add(jMenuItem1);
        jMenuBar1.getMenus().add(mAdmin);

        mDoctor = new Menu("Arzt");
        jMenuItem3 = new MenuItem("Patient Info");
        jMenuItem3.setOnAction((event) -> {
            jMenuItem3ActionPerformed(event);
        });
        mDoctor.getItems().add(jMenuItem3);
        jMenuBar1.getMenus().add(mDoctor);

        mReceptionist = new Menu("Receptionist");
        jMenuItem2 = new MenuItem("Patient Info");
        jMenuItem2.setOnAction((event) -> {
            try {
                jMenuItem2ActionPerformed(event);
            } catch (Exception ex) {
                Validation.specialAlertShow("Error", "Please enter valid data.", AlertType.ERROR);
            }
        });
        mReceptionist.getItems().add(jMenuItem2);
        jMenuBar1.getMenus().add(mReceptionist);

        mNurse = new Menu("Nurse");
        jMenuItem4 = new MenuItem("Patient Info");
        jMenuItem4.setOnAction((event) -> {
            jMenuItem4ActionPerformed(event);
        });
        mNurse.getItems().add(jMenuItem4);
        jMenuBar1.getMenus().add(mNurse);

        jMenu1 = new Menu("Hilfe");
        jMenuItem5 = new MenuItem("Über");
        jMenuItem5.setOnAction((event) -> {
            jMenuItem5ActionPerformed(event);
        });
        jMenu1.getItems().add(jMenuItem5);
        jMenuBar1.getMenus().add(jMenu1);

        jMenu2 = new Menu("Ausloggen");
        jMenuItem6 = new MenuItem("Ausloggen");
        jMenuItem6.setOnAction((event) -> {
            jMenuItem6ActionPerformed(event);
        });
        jMenu2.getItems().add(jMenuItem6);
        jMenuBar1.getMenus().add(jMenu2);

        VBox root = new VBox();

        Image image = new Image("images/black-wallpaper.jpg", WIDTH, HEIGHT, true, true);
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().bind(this.heightProperty());
        imageView.fitWidthProperty().bind(this.widthProperty());

        root.getChildren().add(jMenuBar1);
        root.getChildren().add(imageView);

        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setX(MINX);
        this.setY(MINY);
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
        getUserLevel();
        this.show();
    }

    private void getUserLevel() {
        switch (usersVo.getUsersType().getType()) {
            case "admin":
                mDoctor.setDisable(true);
                mNurse.setDisable(true);
                mReceptionist.setDisable(true);
                break;
            case "doctor":
                mAdmin.setDisable(true);
                mNurse.setDisable(true);
                mReceptionist.setDisable(true);
                break;
            case "nurse":
                mDoctor.setDisable(true);
                mAdmin.setDisable(true);
                mReceptionist.setDisable(true);
                break;
            case "reception":
                mDoctor.setDisable(true);
                mNurse.setDisable(true);
                mAdmin.setDisable(true);
                break;
        }
    }

    public void setVisible(boolean bool) {
        if (bool) {
            this.show();
        } else {
            this.close();
        }
    }

    private void jMenuItem1ActionPerformed(ActionEvent event) throws Exception {

        UsersView usersView = new UsersView();
        usersView.setVisible(true);

    }

    private void jMenuItem2ActionPerformed(ActionEvent event) throws Exception {

        PatientInfoView patientInfoView = new PatientInfoView();
        patientInfoView.setVisible(true);

    }

    private void jMenuItem3ActionPerformed(ActionEvent event) {

        MessageView messageView = new MessageView();
        messageView.setVisible(true);

    }

    private void jMenuItem4ActionPerformed(ActionEvent event) {

        MessageView messageView = new MessageView();
        messageView.setVisible(true);

    }

    private void jMenuItem5ActionPerformed(ActionEvent event) {

        Uber uber = new Uber();
        uber.setVisible(true);

    }

    private void jMenuItem6ActionPerformed(ActionEvent event) {

        Login login = new Login();
        this.setVisible(false);
        login.setVisible(true);

    }

}
