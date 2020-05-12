package com.majdtalji.krankenhaus.view;

import com.majdtalji.krankenhaus.db.dao.UsersDao;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import com.majdtalji.krankenhaus.validation.Validation;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Login extends Stage {

    private Button btnLogin;
    private Label jLabel2;
    private Label jLabel3;
    private PasswordField txtPassword;
    private TextField txtUserName;

    public Login() {
        jLabel2 = new Label("User name");
        jLabel3 = new Label("Password");
        txtUserName = new TextField();
        btnLogin = new Button("Login");
        txtPassword = new PasswordField();

        jLabel2.setTranslateX(40);
        jLabel2.setTranslateY(35);

        jLabel3.setTranslateX(40);
        jLabel3.setTranslateY(85);

        txtUserName.setTranslateX(120);
        txtUserName.setTranslateY(31);

        txtPassword.setTranslateX(120);
        txtPassword.setTranslateY(81);

        btnLogin.setTranslateX(120);
        btnLogin.setTranslateY(130);
        btnLogin.setPrefSize(100, 35);

        btnLogin.setOnAction((event) -> {
            btnLoginActionPerformed(event);
        });
        
        Group root = new Group();

        root.getChildren().add(jLabel2);
        root.getChildren().add(jLabel3);
        root.getChildren().add(txtUserName);
        root.getChildren().add(txtPassword);
        root.getChildren().add(btnLogin);

        Scene scene = new Scene(root, 310, 190);
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }

    public void setVisible(boolean bool) {
        if (bool) {
            this.show();
        } else {
            this.close();
        }
    }

    private void btnLoginActionPerformed(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        UsersVo usersVo = new UsersVo();
        usersVo.setUserName(userName);
        usersVo.setPassword(password);
        try {
            UsersVo uv = UsersDao.getInstance().getData(usersVo);
            Home.usersVo = uv;
            if (uv == null) {
                Validation.specialAlertShow("Waring", "enter valid user name and password", AlertType.WARNING);
            } else {
                Home home = new Home();
                home.setVisible(true);
                this.setVisible(false);
            }
        } catch (Exception e) {
        }
    }

}
