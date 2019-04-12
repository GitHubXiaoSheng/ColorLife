package cn.edu.jssvc.gezhi.colorlife.search;

import android.widget.ImageView;

public class Search_huati {
    private int image;
    private String biaoti;
    private String content;
    private String member;
    private String time;

    public Search_huati(int image, String biaoti, String content, String member, String time) {
        this.image = image;
        this.biaoti = biaoti;
        this.content = content;
        this.member = member;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
