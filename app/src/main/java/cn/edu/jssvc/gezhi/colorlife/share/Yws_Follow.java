package cn.edu.jssvc.gezhi.colorlife.share;

import android.widget.TextView;

public class Yws_Follow {
    public String getText_head() {
        return text_head;
    }

    public void setText_head(String text_head) {
        this.text_head = text_head;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getText_time() {
        return text_time;
    }

    public void setText_time(String text_time) {
        this.text_time = text_time;
    }

    public int getImagehead() {
        return image_head;
    }

    public void setImage_head(int image_head) {
        this.image_head = image_head;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public Yws_Follow(String text_head, String text_content, String text_time, int image_head, int image1, int image2) {
        this.text_head = text_head;
        this.text_content = text_content;
        this.text_time = text_time;
        this.image_head = image_head;
        this.image1 = image1;
        this.image2 = image2;
    }

    private String text_head,text_content,text_time;
private int image_head,image1,image2;
}
