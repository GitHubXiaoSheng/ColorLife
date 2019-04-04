package cn.edu.jssvc.gezhi.colorlife.home;

public class Item {
    private String image,     //图片
                    title,     //标题
                    fujia,     //附加内容
                    time;      //发布时间

    public Item(String image, String title, String fujia, String time) {
        this.image = image;
        this.title = title;
        this.fujia = fujia;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFujia() {
        return fujia;
    }

    public void setFujia(String fujia) {
        this.fujia = fujia;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
