package cn.edu.jssvc.gezhi.colorlife.home;

public class Arts_info {

    private String art_id,           //作品id
                    url,              //链接
                    create_date,     //创作日期
                    release_date,    //发布日期
                    price,            //价格
                    tags,             //附加内容
                    author_id,       //作者id
                    classify_id,     //作品分类id
                    theme_id,        //题材id
                    content,         //内容
                    maptilte;         //内容

    public Arts_info() {}            //空的构造方法

    public String getArt_id() {
        return art_id;
    }

    public void setArt_id(String art_id) {
        this.art_id = art_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(String theme_id) {
        this.theme_id = theme_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMaptilte() {
        return maptilte;
    }

    public void setMaptilte(String maptilte) {
        this.maptilte = maptilte;
    }
}
