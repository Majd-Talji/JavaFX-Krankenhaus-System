package com.majdtalji.krankenhaus.db.vo;

import java.sql.Date;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class MessageVo {

    // MESSAGES  ID MESSAGE_BODY	MESSAGE_DATE	FROM_USER	TO_USER	PATIENT_ID
    private int id;
    private String messegeBody;
    private Date messageDate;
    private UsersVo fromUser;
    private UsersVo toUser;
    private PatientInfoVo patientInfoVo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessegeBody() {
        return messegeBody;
    }

    public void setMessegeBody(String messegeBody) {
        this.messegeBody = messegeBody;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public UsersVo getFromUser() {
        return fromUser;
    }

    public void setFromUser(UsersVo fromUser) {
        this.fromUser = fromUser;
    }

    public UsersVo getToUser() {
        return toUser;
    }

    public void setToUser(UsersVo toUser) {
        this.toUser = toUser;
    }

    public PatientInfoVo getPatientInfoVo() {
        return patientInfoVo;
    }

    public void setPatientInfoVo(PatientInfoVo patientInfoVo) {
        this.patientInfoVo = patientInfoVo;
    }
}
