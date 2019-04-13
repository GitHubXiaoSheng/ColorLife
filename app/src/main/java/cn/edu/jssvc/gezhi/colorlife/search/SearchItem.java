package cn.edu.jssvc.gezhi.colorlife.search;

public class SearchItem {
private String Image,MapTitle,Content,createdata;

public  SearchItem(String Image,String MapTitle,String Content,String createdata){
    this.Image = Image;
    this.MapTitle = MapTitle;
    this.Content = Content;
    this.createdata = createdata;
}
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMapTitle() {
        return MapTitle;
    }

    public void setMapTitle(String mapTitle) {
        MapTitle = mapTitle;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreatedata() {
        return createdata;
    }

    public void setCreatedata(String createdata) {
        this.createdata = createdata;
    }
}
