package com.majdtalji.krankenhaus.view;

import com.majdtalji.krankenhaus.db.dao.Dao;
import com.majdtalji.krankenhaus.db.dao.UserDetailsDao;
import com.majdtalji.krankenhaus.db.dao.UsersDao;
import com.majdtalji.krankenhaus.db.type.UsersType;
import com.majdtalji.krankenhaus.db.vo.UserDetailsVo;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import static com.majdtalji.krankenhaus.validation.ScreenBounds.HEIGHT;
import static com.majdtalji.krankenhaus.validation.ScreenBounds.WIDTH;
import com.majdtalji.krankenhaus.validation.Validation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class UsersView extends Dialog {

    private Button btnAdd;
    private Button btnChooseImage;
    private Button btnDelete;
    private Button btnEdit;
    private Button btnSearch;
    private ComboBox<String> cSpecialization;
    private ComboBox<String> cUserType;
    private Label jLabel1;
    private Label jLabel2;
    private Label jLabel3;
    private Label jLabel4;
    private Label jLabel5;
    private Label jLabel6;
    private Label jLabel7;
    private Label jLabel8;
    private Label jLabel9;
    private ImageView lblImage;
    private TextField txtFatherName;
    private TextField txtFirstName;
    private TextField txtId;
    private TextField txtImagePath;
    private TextField txtMobile;
    private PasswordField txtPassword;
    private TextField txtUserName;

    ObservableList<String> cSpecializationList = FXCollections.observableArrayList(
            "general"
    );

    ObservableList<String> cUserTypeList = FXCollections.observableArrayList(
            "admin", "doctor", "nurse", "reception"
    );

    public static byte[] imageByte;

    public UsersView() {

        jLabel1 = new Label("ID");
        jLabel2 = new Label("User name");
        jLabel3 = new Label("Password");
        jLabel4 = new Label("Image Path");
        jLabel5 = new Label("User Type");
        jLabel6 = new Label("First name");
        jLabel7 = new Label("Father name");
        jLabel8 = new Label("Mobile");
        jLabel9 = new Label("Specialzation");
        txtId = new TextField();
        txtUserName = new TextField();
        txtPassword = new PasswordField();
        txtImagePath = new TextField();
        txtFirstName = new TextField();
        cUserType = new ComboBox<>(cUserTypeList);
        txtFatherName = new TextField();
        txtMobile = new TextField();
        cSpecialization = new ComboBox<>(cSpecializationList);
        btnAdd = new Button("Add");
        btnDelete = new Button("Delete");
        btnEdit = new Button("Edit");
        btnChooseImage = new Button("Choose Image");
        btnSearch = new Button("Search");
        lblImage = new ImageView();
        
        lblImage.setFitWidth(130);
        lblImage.setFitHeight(145);

        GridPane root = new GridPane();

        root.add(jLabel1, 0, 0);
        root.add(jLabel2, 0, 1);
        root.add(jLabel3, 0, 2);
        root.add(jLabel4, 0, 3);
        root.add(jLabel5, 0, 4);
        root.add(jLabel6, 0, 5);
        root.add(jLabel7, 0, 6);
        root.add(jLabel8, 0, 7);
        root.add(jLabel9, 0, 8);

        root.add(txtId, 1, 0);
        root.add(txtUserName, 1, 1);
        root.add(txtPassword, 1, 2);
        root.add(txtImagePath, 1, 3);
        root.add(cUserType, 1, 4);
        root.add(txtFirstName, 1, 5);
        root.add(txtFatherName, 1, 6);
        root.add(txtMobile, 1, 7);
        root.add(cSpecialization, 1, 8);

        root.add(btnAdd, 2, 0);
        root.add(btnDelete, 2, 1);
        root.add(btnEdit, 2, 2);
        root.add(btnChooseImage, 2, 3);
        root.add(btnSearch, 2, 4);
        root.add(lblImage, 2, 5);

        btnAdd.setMaxWidth(Double.MAX_VALUE);
        btnDelete.setMaxWidth(Double.MAX_VALUE);
        btnEdit.setMaxWidth(Double.MAX_VALUE);
        btnChooseImage.setMaxWidth(Double.MAX_VALUE);
        btnSearch.setMaxWidth(Double.MAX_VALUE);
        cSpecialization.setMaxWidth(Double.MAX_VALUE);
        cUserType.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(btnAdd, Priority.ALWAYS);
        GridPane.setHgrow(btnDelete, Priority.ALWAYS);
        GridPane.setHgrow(btnEdit, Priority.ALWAYS);
        GridPane.setHgrow(btnChooseImage, Priority.ALWAYS);
        GridPane.setHgrow(btnSearch, Priority.ALWAYS);
        GridPane.setHgrow(cSpecialization, Priority.ALWAYS);
        GridPane.setHgrow(cUserType, Priority.ALWAYS);

        GridPane.setRowSpan(lblImage, 4);

        root.setVgap(15);
        root.setHgap(15);
        root.setPadding(new Insets(25, 25, 10, 25));

        txtImagePath.setDisable(true);

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        this.getDialogPane().setContent(root);
        this.setResizable(false);

        btnAdd.setOnAction((event) -> {
            btnAddActionPerformed(event);
        });

        btnDelete.setOnAction((event) -> {
            btnDeleteActionPerformed(event);
        });

        btnEdit.setOnAction((event) -> {
            btnEditActionPerformed(event);
        });

        btnChooseImage.setOnAction((event) -> {
            btnChooseImageActionPerformed(event);
        });

        btnSearch.setOnAction((event) -> {
            btnSearchActionPerformed(event);
        });

        txtId.setText("" + Dao.getId("users"));

    }

    public void setVisible(boolean bool) {
        if (bool) {
            this.showAndWait();
        } else {
            this.close();
        }
    }

    protected void reset() {
        txtId.setText("");
        txtId.setText("" + Dao.getId("users"));
        txtUserName.setText("");
        txtPassword.setText("");
        cUserType.getSelectionModel().select(-1);
        txtFirstName.setText("");
        txtFatherName.setText("");
        txtMobile.setText("");
        txtImagePath.setText("");
        lblImage.setImage(null);
        cSpecialization.getSelectionModel().select(-1);
    }

    private void btnAddActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtId.getText(), txtUserName.getText(), txtPassword.getText(), txtFirstName.getText(), txtFatherName.getText(), txtMobile.getText(), txtImagePath.getText());
        boolean isEmpt = Validation.isEmpty(cUserType.getSelectionModel().getSelectedItem(), cSpecialization.getSelectionModel().getSelectedItem());
        boolean isDigit = Validation.isDigit(txtId.getText(), txtMobile.getText());
        boolean isText = Validation.isText(txtUserName.getText(), txtFirstName.getText(), txtFatherName.getText());

        if (isTextEmpty == true || isEmpt == true || isDigit == false || isText == false) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
            return;
        }

        int id = Integer.valueOf(txtId.getText());
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        UsersType usersType = UsersType.getUsersTypeByType(cUserType.getSelectionModel().getSelectedItem());
        UsersVo usersVo = new UsersVo();
        usersVo.setId(id);
        usersVo.setUserName(userName);
        usersVo.setPassword(password);
        usersVo.setUsersType(usersType);

        String firstName = txtFirstName.getText();
        String fatherName = txtFatherName.getText();
        String mobile = txtMobile.getText();
        String specialization = cSpecialization.getSelectionModel().getSelectedItem();
        UserDetailsVo userDetailsVo = new UserDetailsVo();
        userDetailsVo.setUsersVo(usersVo);
        userDetailsVo.setFirstName(firstName);
        userDetailsVo.setFatheName(fatherName);
        userDetailsVo.setMobile(mobile);
        userDetailsVo.setImage(imageByte);
        userDetailsVo.setSpecialization(specialization);

        try {
            int count = UserDetailsDao.getInstance().insert(userDetailsVo);

            if (count == 1) {
                Validation.specialAlertShow("Information", "Insert successfully", AlertType.INFORMATION);
                reset();
            } else {
                Validation.specialAlertShow("Information", "Insert not successfully", AlertType.INFORMATION);
            }

        } catch (Exception e) {
        }

    }

    private void btnDeleteActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtId.getText());
        boolean isDigit = Validation.isDigit(txtId.getText());

        if (isTextEmpty == true) {
            Validation.specialAlertShow("Waring", "Please enter valid ID.", AlertType.WARNING);
            return;
        }

        if (isDigit == false) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
            return;
        }

        int id = Integer.valueOf(txtId.getText());
        UsersVo usersVo = new UsersVo();
        usersVo.setId(id);

        UserDetailsVo userDetailsVo = new UserDetailsVo();
        userDetailsVo.setUsersVo(usersVo);

        try {
            UsersVo uv = UsersDao.getInstance().getDataById(id);
            if (uv == null) {
                Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
                return;
            }

            int count = UserDetailsDao.getInstance().delete(userDetailsVo);

            if (count == 1) {
                Validation.specialAlertShow("Information", "Delete successfully", AlertType.INFORMATION);
                reset();
            } else {
                Validation.specialAlertShow("Information", "Delete not successfully", AlertType.INFORMATION);
            }

        } catch (Exception e) {
        }

    }

    private void btnEditActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtId.getText(), txtUserName.getText(), txtPassword.getText(), txtFirstName.getText(), txtFatherName.getText(), txtMobile.getText());
        boolean isEmpt = Validation.isEmpty(cUserType.getSelectionModel().getSelectedItem());
        boolean isDigit = Validation.isDigit(txtId.getText(), txtMobile.getText());
        boolean isText = Validation.isText(txtUserName.getText(), txtPassword.getText(), txtFirstName.getText(), txtFatherName.getText());

        if (isTextEmpty == true || isEmpt == true || isDigit == false || isText == false) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
            return;
        }

        int id = Integer.valueOf(txtId.getText());
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        UsersType usersType = UsersType.getUsersTypeByType(cUserType.getSelectionModel().getSelectedItem().toString());
        UsersVo usersVo = new UsersVo();
        usersVo.setId(id);
        usersVo.setUserName(userName);
        usersVo.setPassword(password);
        usersVo.setUsersType(usersType);

        String firstName = txtFirstName.getText();
        String fatherName = txtFatherName.getText();
        String mobile = txtMobile.getText();
        String specialzation = cSpecialization.getSelectionModel().getSelectedItem();
        UserDetailsVo userDetailsVo = new UserDetailsVo();
        userDetailsVo.setUsersVo(usersVo);
        userDetailsVo.setFirstName(firstName);
        userDetailsVo.setFatheName(fatherName);
        userDetailsVo.setMobile(mobile);
        userDetailsVo.setImage(imageByte);
        userDetailsVo.setSpecialization(specialzation);

        try {
            UsersVo uv = UsersDao.getInstance().getDataById(id);
            if (uv == null) {
                Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
                return;
            }

            int count = UserDetailsDao.getInstance().update(userDetailsVo);

            if (count == 1) {
                Validation.specialAlertShow("Information", "Update successfully", AlertType.INFORMATION);
                reset();
            } else {
                Validation.specialAlertShow("Information", "Update not successfully", AlertType.INFORMATION);
            }

        } catch (Exception e) {
        }

    }

    private void btnChooseImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Select a .JPG .PNG .GIF image", "*.jpg", "*.png", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                txtImagePath.setText(selectedFile.getAbsolutePath());
                lblImage.setImage(
                        new Image(selectedFile.toURI().toString(), 130, 145, true, true)
                );

                FileInputStream fis = new FileInputStream(selectedFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] length = new byte[1024];

                for (int i; (i = fis.read(length)) != -1;) {
                    baos.write(length, 0, i);
                }
                imageByte = baos.toByteArray();

            } catch (Exception e) {
                Validation.specialAlertShow("Error", "Failed to add Image", AlertType.ERROR);
            }
        }

    }

    private void btnSearchActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtId.getText());
        boolean isDigit = Validation.isDigit(txtId.getText());

        if (isTextEmpty == true || isDigit == false) {
            Validation.specialAlertShow("Waring", "Please enter valid ID.", AlertType.WARNING);
            return;
        }
        
        int id = Integer.valueOf(txtId.getText());
        try {
            UserDetailsVo userDetailsVo = UserDetailsDao.getInstance().getDataById(id);
            if (userDetailsVo == null) {
                Validation.specialAlertShow("Waring", "ID not exist", AlertType.WARNING);
                reset();
            } else {

                txtUserName.setText(userDetailsVo.getUsersVo().getUserName());
                txtPassword.setText(userDetailsVo.getUsersVo().getPassword());
                cUserType.getSelectionModel().select(userDetailsVo.getUsersVo().getUsersType().getId() - 1);
                txtFirstName.setText(userDetailsVo.getFirstName());
                txtFatherName.setText(userDetailsVo.getFatheName());
                txtMobile.setText(userDetailsVo.getMobile());
                cSpecialization.getSelectionModel().select(userDetailsVo.getSpecialization());

                byte[] imageByte = userDetailsVo.getImage();
                lblImage.setImage(new Image(new ByteArrayInputStream(imageByte), 130, 145, true, true));

            }

        } catch (Exception e) {
        }

    }

}
