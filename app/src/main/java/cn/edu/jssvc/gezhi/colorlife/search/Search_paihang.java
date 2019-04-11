package cn.edu.jssvc.gezhi.colorlife.search;

public class Search_paihang {
    private int Image;
    private String biaoti;
    private String redu;
public Search_paihang(int Image,String biaoti,String redu){
    this.Image = Image;
    this.biaoti = biaoti;
    this.redu = redu;
}
    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getRedu() {
        return redu;
    }

    public void setRedu(String redu) {
        this.redu = redu;
    }
}
