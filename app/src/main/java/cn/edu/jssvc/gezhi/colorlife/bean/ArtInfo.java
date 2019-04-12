package cn.edu.jssvc.gezhi.colorlife.bean;

public class ArtInfo {
    private int id;
    private int authorId;
    private int themeId;
    private int classifyId;
    private String url;
    private String createData;
    private String releaseData;
    private String mapTitle;
    private String content;
    private String tags;
    private float price;

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
