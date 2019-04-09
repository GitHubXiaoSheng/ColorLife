package cn.edu.jssvc.gezhi.colorlife.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/4/3.
 */

public class MemberInfo implements Serializable {

    private int id;
    private String photoUrl;//头像地址
    private String nickName;//网名
    private String realName;//真实姓名
    private String password;//密码
    private String QQ;//
    private String phone;//
    private String hobbies;//爱好
    private String sex;//
    private String birthday;
    private String registerDate;//注册日期
    private String postalAddress;//通讯地址
    private String matto;//个新签名
    private int points;//积分
    private int level;//等级

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMatto() {
        return matto;
    }

    public void setMatto(String matto) {
        this.matto = matto;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", photoUrl='" + photoUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", QQ='" + QQ + '\'' +
                ", phone='" + phone + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", matto='" + matto + '\'' +
                ", points=" + points +
                ", level=" + level +
                '}';
    }
}
