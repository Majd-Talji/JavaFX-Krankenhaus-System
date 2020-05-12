package com.majdtalji.krankenhaus.db.vo;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class PatientInfoVo {

    //ID	FIRST_NAME	FATHER_NAME	MOBILE	EMAIL	USER_ID
    private int id;
    private String firstName;
    private String fatheName;
    private String mobile;
    private String email;
    private UsersVo usersVo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatheName() {
        return fatheName;
    }

    public void setFatheName(String fatheName) {
        this.fatheName = fatheName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersVo getUsersVo() {
        return usersVo;
    }

    public void setUsersVo(UsersVo usersVo) {
        this.usersVo = usersVo;
    }

}
