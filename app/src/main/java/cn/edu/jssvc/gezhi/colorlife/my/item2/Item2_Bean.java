package cn.edu.jssvc.gezhi.colorlife.my.item2;

public class Item2_Bean {
    private String headImgUrl;//用户头像
    private String name;//用户名
    private String content;//内容
    private String contentmgUrl;//艺术图片
    private int share;//分享数
    private int comment;//讨论数
    private int like;//喜欢数
    private int artId;
    private boolean isLikeing;

    public Item2_Bean(String headImgUrl, String name, String content, String contentmgUrl, int share, int comment, int like, boolean isLike) {
        this.headImgUrl = headImgUrl;
        this.name = name;
        this.content = content;
        this.contentmgUrl = contentmgUrl;
        this.share = share;
        this.comment = comment;
        this.like = like;
        this.isLikeing = isLike;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public boolean isLikeing() {
        return isLikeing;
    }

    public void setLikeing(boolean like) {
        isLikeing = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentmgUrl() {
        return contentmgUrl;
    }

    public void setContentmgUrl(String contentmgUrl) {
        this.contentmgUrl = contentmgUrl;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Item2_Bean{" +
                "headImgUrl='" + headImgUrl + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", contentmgUrl='" + contentmgUrl + '\'' +
                ", share=" + share +
                ", comment=" + comment +
                ", like=" + like +
                ", artId=" + artId +
                ", isLikeing=" + isLikeing +
                '}';
    }
}
