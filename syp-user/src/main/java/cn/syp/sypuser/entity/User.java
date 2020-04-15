package cn.syp.sypuser.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: syp
 * @Date: 2020/3/18
 * @Description:
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String realName;
    private String phoneNumber;
    private int age;
    private Timestamp birthDate;
    private int sex;
    private String headImg;
    private String description;
    private int isDelete;
    private int status;
    private String eMail;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", head_img='" + headImg + '\'' +
                ", description='" + description + '\'' +
                ", isDelete=" + isDelete +
                ", status=" + status +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHead_img() {
        return headImg;
    }

    public void setHead_img(String headImg) {
        this.headImg = headImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public User() {
    }

    public User(int id, String username, String password, String realName, String phoneNumber, int age, Timestamp birthDate, int sex, String headImg, String description, int isDelete, int status, String eMail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.birthDate = birthDate;
        this.sex = sex;
        this.headImg = headImg;
        this.description = description;
        this.isDelete = isDelete;
        this.status = status;
        this.eMail = eMail;
    }
}
