package com.majdtalji.krankenhaus.view;

import com.majdtalji.krankenhaus.db.dao.Dao;
import com.majdtalji.krankenhaus.db.dao.PatientInfoDao;
import com.majdtalji.krankenhaus.db.vo.PatientInfoVo;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import com.majdtalji.krankenhaus.validation.Validation;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class PatientInfoView extends Dialog {

    private Button btnNew;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnEdit;
    private Button btnGetMessage;
    private Button btnSearch;
    private Label jLabel1;
    private Label jLabel2;
    private Label jLabel3;
    private Label jLabel4;
    private Label jLabel5;
    private Label jLabel6;
    private TextField txtEmail;
    private TextField txtFatherName;
    private TextField txtFirstName;
    private TextField txtId;
    private TextField txtMobile;
    private TextField txtUserId;

    public PatientInfoView() throws Exception {

        jLabel1 = new Label("ID");
        jLabel2 = new Label("First name");
        jLabel3 = new Label("Father name");
        jLabel4 = new Label("Mobile");
        jLabel5 = new Label("E-mail");
        jLabel6 = new Label("User Id");
        txtFirstName = new TextField();
        txtFatherName = new TextField();
        txtMobile = new TextField();
        btnAdd = new Button("Add");
        btnNew = new Button("New");
        txtId = new TextField();
        txtEmail = new TextField();
        txtUserId = new TextField();
        btnDelete = new Button("Delete");
        btnEdit = new Button("Edit");
        btnSearch = new Button("Search");
        btnGetMessage = new Button("Get message");

        GridPane root = new GridPane();

        root.add(jLabel1, 0, 1);
        root.add(jLabel2, 0, 2);
        root.add(jLabel3, 0, 3);
        root.add(jLabel4, 0, 4);
        root.add(jLabel5, 0, 5);
        root.add(jLabel6, 0, 6);

        root.add(txtId, 1, 1);
        root.add(txtFirstName, 1, 2);
        root.add(txtFatherName, 1, 3);
        root.add(txtMobile, 1, 4);
        root.add(txtEmail, 1, 5);
        root.add(txtUserId, 1, 6);

        root.add(btnNew, 2, 1);
        root.add(btnAdd, 2, 2);
        root.add(btnDelete, 2, 3);
        root.add(btnEdit, 2, 4);
        root.add(btnSearch, 2, 5);
        root.add(btnGetMessage, 2, 6);

        btnNew.setMaxWidth(Double.MAX_VALUE);
        btnAdd.setMaxWidth(Double.MAX_VALUE);
        btnDelete.setMaxWidth(Double.MAX_VALUE);
        btnEdit.setMaxWidth(Double.MAX_VALUE);
        btnSearch.setMaxWidth(Double.MAX_VALUE);
        btnGetMessage.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(btnNew, Priority.ALWAYS);
        GridPane.setHgrow(btnAdd, Priority.ALWAYS);
        GridPane.setHgrow(btnDelete, Priority.ALWAYS);
        GridPane.setHgrow(btnEdit, Priority.ALWAYS);
        GridPane.setHgrow(btnSearch, Priority.ALWAYS);
        GridPane.setHgrow(btnGetMessage, Priority.ALWAYS);

        btnNew.setOnAction((event) -> {
            try {
                btnNewActionPerformed(event);
            } catch (Exception ex) {
                Validation.specialAlertShow("Error", ex.getMessage(), AlertType.ERROR);
            }
        });

        btnAdd.setOnAction((event) -> {
            btnAddActionPerformed(event);
        });

        btnDelete.setOnAction((event) -> {
            btnDeleteActionPerformed(event);
        });

        btnEdit.setOnAction((event) -> {
            btnEditActionPerformed(event);
        });

        btnSearch.setOnAction((event) -> {
            btnSearchActionPerformed(event);
        });

        btnGetMessage.setOnAction((event) -> {
            btnGetMessageActionPerformed(event);
        });

        root.setVgap(15);
        root.setHgap(15);
        root.setPadding(new Insets(15, 25, 5, 25));

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        this.getDialogPane().setContent(root);
        this.setTitle("");
        this.setResizable(false);

        txtId.setText("" + Dao.getId("patient_info"));

    }

    public void setVisible(boolean bool) {
        if (bool) {
            this.showAndWait();
        } else {
            this.close();
        }
    }

    private void btnAddActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtId.getText(), txtFirstName.getText(), txtFatherName.getText(), txtMobile.getText(), txtEmail.getText(), txtUserId.getText());
        boolean isDigit = Validation.isDigit(txtId.getText(), txtUserId.getText(), txtMobile.getText());
        boolean isText = Validation.isText(txtFirstName.getText(), txtFatherName.getText(), txtEmail.getText());

        if (isTextEmpty == true || isDigit == false || isText == false) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
            return;
        }

        int id = Integer.valueOf(txtId.getText());
        String firstName = txtFirstName.getText();
        String fatherName = txtFatherName.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        int userId = Integer.valueOf(txtUserId.getText());

        PatientInfoVo patientInfoVo = new PatientInfoVo();
        patientInfoVo.setId(id);
        patientInfoVo.setFirstName(firstName);
        patientInfoVo.setFatheName(fatherName);
        patientInfoVo.setMobile(mobile);
        patientInfoVo.setEmail(email);
        UsersVo usersVo = new UsersVo();
        usersVo.setId(userId);
        patientInfoVo.setUsersVo(usersVo);

        try {
            int count = PatientInfoDao.getInstance().insert(patientInfoVo);

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
            Validation.specialAlertShow("Waring", "Please enter valid ID.", AlertType.WARNING);
            return;
        }

        int id = Integer.valueOf(txtId.getText());
        PatientInfoVo patientInfoVo = new PatientInfoVo();
        patientInfoVo.setId(id);

        try {
            PatientInfoVo piv = PatientInfoDao.getInstance().getDataById(id);
            if (piv == null) {
                Validation.specialAlertShow("Waring", "There is not a patient by ID", AlertType.WARNING);
                return;
            }

            int count = PatientInfoDao.getInstance().delete(patientInfoVo);

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

        boolean isTextEmpty = Validation.isEmpty(txtId.getText(), txtFirstName.getText(), txtFatherName.getText(), txtMobile.getText(), txtEmail.getText(), txtUserId.getText());
        boolean isDigit = Validation.isDigit(txtId.getText(), txtUserId.getText(), txtMobile.getText());
        boolean isText = Validation.isText(txtFirstName.getText(), txtFatherName.getText(), txtEmail.getText());

        if (isTextEmpty == true) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
            return;
        }
        if (isDigit == false || isText == false) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
            return;
        }

        int id = Integer.valueOf(txtId.getText());
        String firstName = txtFirstName.getText();
        String fatherName = txtFatherName.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        int userId = Integer.valueOf(txtUserId.getText());

        PatientInfoVo patientInfoVo = new PatientInfoVo();
        patientInfoVo.setId(id);
        patientInfoVo.setFirstName(firstName);
        patientInfoVo.setFatheName(fatherName);
        patientInfoVo.setMobile(mobile);
        patientInfoVo.setEmail(email);
        UsersVo usersVo = new UsersVo();
        usersVo.setId(userId);
        patientInfoVo.setUsersVo(usersVo);

        try {
            PatientInfoVo piv = PatientInfoDao.getInstance().getDataById(id);
            if (piv == null) {
                Validation.specialAlertShow("Waring", "Please enter valid data.", AlertType.WARNING);
                return;
            }

            int count = PatientInfoDao.getInstance().update(patientInfoVo);

            if (count == 1) {
                Validation.specialAlertShow("Information", "Update successfully", AlertType.INFORMATION);
                reset();
            } else {
                Validation.specialAlertShow("Information", "Update not successfully", AlertType.INFORMATION);
            }

        } catch (Exception e) {
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
            PatientInfoVo piv = PatientInfoDao.getInstance().getDataById(id);
            if (piv == null) {
                Validation.specialAlertShow("Waring", "There is not a patient by ID", AlertType.WARNING);
                reset();
            } else {
                txtFirstName.setText(piv.getFirstName());
                txtFatherName.setText(piv.getFatheName());
                txtMobile.setText(piv.getMobile());
                txtEmail.setText(piv.getEmail());
                txtUserId.setText(String.valueOf(piv.getUsersVo().getId()));
            }

        } catch (Exception e) {
        }

    }

    private void btnGetMessageActionPerformed(ActionEvent event) {

        MessageView messageView = new MessageView();
        messageView.setVisible(true);

    }

    private void reset() throws Exception {

        txtId.setText("");
        txtId.setText("" + Dao.getId("patient_info"));
        txtFirstName.setText("");
        txtFatherName.setText("");
        txtMobile.setText("");
        txtEmail.setText("");
        txtUserId.setText("");

    }

    private void btnNewActionPerformed(ActionEvent event) throws Exception {
        reset();
    }

}
