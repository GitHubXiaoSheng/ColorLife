package cn.edu.jssvc.gezhi.colorlife.home;

public class Pinglun {
    private String tilteImg,    //用户头像
                    nameText,    //用户名字
                    timeText,    //用户评论时间
                    contentText; //用户评论内容

    public Pinglun( ){ }

    public String getTilteImg() {
        return tilteImg;
    }

    public void setTilteImg(String tilteImg) {
        this.tilteImg = tilteImg;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
