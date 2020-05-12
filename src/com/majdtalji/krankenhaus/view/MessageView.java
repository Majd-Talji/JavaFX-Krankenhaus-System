package com.majdtalji.krankenhaus.view;

import com.majdtalji.krankenhaus.db.dao.MessageDao;
import com.majdtalji.krankenhaus.db.vo.MessageVo;
import com.majdtalji.krankenhaus.db.vo.PatientInfoVo;
import com.majdtalji.krankenhaus.db.vo.UsersVo;
import com.majdtalji.krankenhaus.validation.Validation;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.StringConverter;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class MessageView extends Dialog {

    final String dateFormatter = "yyyy-MM-dd";
    DateTimeFormatter dateFormat;
    LocalDate messageDate;

    private Button btnGetMessage;
    private Button btnNew;
    private Button btnSend;
    private Label jLabel1;
    private Label jLabel2;
    private Label jLabel3;
    private Label jLabel4;
    private TextArea txtMessageBody;
    private DatePicker txtMessageDate;
    private TextField txtPatientId;
    private TextField txtToUser;

    public MessageView() {

        jLabel1 = new Label("Patient id");
        jLabel2 = new Label("To user");
        jLabel3 = new Label("Message date");
        jLabel4 = new Label("Message body");
        txtPatientId = new TextField();
        txtToUser = new TextField();
        txtMessageDate = new DatePicker();
        txtMessageBody = new TextArea();
        btnSend = new Button("Send");
        btnGetMessage = new Button("Get message");
        btnNew = new Button("New");

        txtMessageBody.setDisable(true);
        btnSend.setDisable(true);

        GridPane root = new GridPane();

        root.add(jLabel1, 0, 1);
        root.add(jLabel2, 0, 2);
        root.add(jLabel3, 0, 3);
        root.add(txtMessageBody, 0, 4);

        root.add(txtPatientId, 1, 1);
        root.add(txtToUser, 1, 2);
        root.add(txtMessageDate, 1, 3);

        root.add(btnSend, 2, 1);
        root.add(btnGetMessage, 2, 2);
        root.add(btnNew, 2, 3);

        GridPane.setColumnSpan(txtMessageBody, 2);

        txtMessageBody.setPromptText(jLabel4.getText());

        btnSend.setMaxWidth(Double.MAX_VALUE);
        btnGetMessage.setMaxWidth(Double.MAX_VALUE);
        btnNew.setMaxWidth(Double.MAX_VALUE);
        txtMessageDate.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(btnSend, Priority.ALWAYS);
        GridPane.setHgrow(btnGetMessage, Priority.ALWAYS);
        GridPane.setHgrow(btnNew, Priority.ALWAYS);
        GridPane.setHgrow(txtMessageDate, Priority.ALWAYS);

        txtMessageDate.setConverter(dateFormatter());
        dateFormat = DateTimeFormatter.ofPattern(dateFormatter);
        messageDate = LocalDate.now();
        txtMessageDate.getEditor().setText(dateFormat.format(messageDate));

        btnSend.setOnAction((event) -> {
            btnSendActionPerformed(event);
        });

        btnGetMessage.setOnAction((event) -> {
            btnGetMessageActionPerformed(event);
        });

        btnNew.setOnAction((event) -> {
            btnNewActionPerformed(event);
        });

        root.setVgap(15);
        root.setHgap(35);
        root.setPadding(new Insets(15, 25, 5, 25));

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        this.getDialogPane().setContent(root);
        this.setTitle("");
        this.setResizable(false);

    }

    public void setVisible(boolean bool) {
        if (bool) {
            this.showAndWait();
        } else {
            this.close();
        }
    }

    private void btnSendActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtToUser.getText(), txtPatientId.getText(), txtMessageDate.getEditor().getText(), txtMessageBody.getText());
        boolean isDigit = Validation.isDigit(txtToUser.getText(), txtPatientId.getText());
        boolean isText = Validation.isText(txtMessageBody.getText());

        if (isTextEmpty == true || isDigit == false || isText == false) {
            Validation.specialAlertShow("Waring", "Please enter valid data.", Alert.AlertType.WARNING);
            return;
        }

        int patientId = Integer.parseInt(txtPatientId.getText());
        int to = Integer.parseInt(txtToUser.getText());
        int from = Home.usersVo.getId();

        Date messageDate = Date.valueOf(txtMessageDate.getEditor().getText());
        String messageBody = txtMessageBody.getText();

        MessageVo messageVo = new MessageVo();
        PatientInfoVo patientInfoVo = new PatientInfoVo();
        patientInfoVo.setId(patientId);
        messageVo.setPatientInfoVo(patientInfoVo);

        UsersVo toUser = new UsersVo();
        toUser.setId(to);
        messageVo.setToUser(toUser);

        UsersVo fromUser = new UsersVo();
        fromUser.setId(from);
        messageVo.setFromUser(fromUser);

        messageVo.setMessageDate(messageDate);
        messageVo.setMessegeBody(messageBody);

        try {
            int count = MessageDao.getInstance().insert(messageVo);

            if (count == 1) {
                Validation.specialAlertShow("Information", "Insert successfully", AlertType.INFORMATION);
                reset();
            } else {
                Validation.specialAlertShow("Information", "Insert not successfully", AlertType.INFORMATION);
            }

        } catch (Exception e) {
        }

    }

    private void btnGetMessageActionPerformed(ActionEvent event) {

        boolean isTextEmpty = Validation.isEmpty(txtPatientId.getText());
        boolean isDigit = Validation.isDigit(txtPatientId.getText());

        if (isTextEmpty == true || isDigit == false) {
            Validation.specialAlertShow("Waring", "Please enter valid ID.", AlertType.WARNING);
            return;
        }

        int patientId = Integer.valueOf(txtPatientId.getText());
        int fromUser = Home.usersVo.getId();
        try {
            MessageVo messageVo = MessageDao.getInstance().getDataByPatientIdAndUserId(patientId, fromUser);
            if (messageVo == null) {
                Validation.specialAlertShow("Waring", "ID not exist", AlertType.WARNING);
                reset();
            } else {

                txtMessageBody.setText(messageVo.getMessegeBody());
                txtToUser.setText(messageVo.getToUser().getId() + "");
                txtMessageDate.getEditor().setText(messageVo.getMessageDate().toString());

            }

        } catch (Exception e) {
        }

    }

    private void btnNewActionPerformed(ActionEvent event) {

        reset();

    }

    private void reset() {

        txtPatientId.setText("");
        txtToUser.setText("");
        txtMessageDate.getEditor().setText(dateFormat.format(messageDate));
        txtMessageBody.setText("");
        txtMessageBody.setDisable(true);
        btnSend.setDisable(true);

    }

    private StringConverter<LocalDate> dateFormatter() {
        StringConverter converter = new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatt = DateTimeFormatter.ofPattern(dateFormatter);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatt.format(date);
                }
                return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatt);
                }
                return null;
            }
        };
        return converter;
    }

}
