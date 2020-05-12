package com.majdtalji.krankenhaus.db.vo;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class UserDetailsVo {

    // ID	USER_ID	FIRST_NAME	FATHER_NAME	MOBILE IMAGE  SPECIALIZATION
    private int id;
    private UsersVo usersVo;
    private String firstName;
    private String fatheName;
    private String mobile;
    private byte[] image;
    private String specialization;

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

    public UsersVo getUsersVo() {
        return usersVo;
    }

    public void setUsersVo(UsersVo usersVo) {
        this.usersVo = usersVo;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
