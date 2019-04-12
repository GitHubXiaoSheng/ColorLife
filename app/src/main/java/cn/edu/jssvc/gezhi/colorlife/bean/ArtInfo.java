package cn.edu.jssvc.gezhi.colorlife.bean;

public class ArtInfo {
    private int id;//自增长的id
    private int authorId;//作者id
    private int themeId;//艺术属性
    private int classifyId;//艺术分类
    private String url;//艺术品id
    private String createData;//创作日期
    private String releaseData;//下架日期
    private String mapTitle;//标题
    private String content;//内容
    private String tags;//标记
    private float price;//价格

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(String releaseData) {
        this.releaseData = releaseData;
    }

    public String getMapTitle() {
        return mapTitle;
    }

    public void setMapTitle(String mapTitle) {
        this.mapTitle = mapTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ArtInfo{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", themeId=" + themeId +
                ", classifyId=" + classifyId +
                ", url='" + url + '\'' +
                ", createData='" + createData + '\'' +
                ", releaseData='" + releaseData + '\'' +
                ", mapTitle='" + mapTitle + '\'' +
                ", content='" + content + '\'' +
                ", tags='" + tags + '\'' +
                ", price=" + price +
                '}';
    }
}
