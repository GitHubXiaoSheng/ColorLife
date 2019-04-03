package cn.edu.jssvc.gezhi.colorlife.home;

public class Tuijian {

    private String image,    //图片
                    title,    //名字
                    guankan,    //观看
                    shoucang;   //收藏

    public Tuijian(String image, String title, String guankan, String shoucang) {
        this.image = image;
        this.title = title;
        this.guankan = guankan;
        this.shoucang = shoucang;
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

    public String getGuankan() {
        return guankan;
    }

    public void setGuankan(String guankan) {
        this.guankan = guankan;
    }

    public String getShoucang() {
        return shoucang;
    }

    public void setShoucang(String shoucang) {
        this.shoucang = shoucang;
    }
}
