package cn.edu.jssvc.gezhi.colorlife.my.item1;

public class Item1_Bean {
    private String headImgUrl;
    private String name;
    private String contentmgUrl;
    private int share;
    private int comment;
    private int like;

    public Item1_Bean(String headImgUrl, String name, String contentmgUrl, int share, int comment, int like) {
        this.headImgUrl = headImgUrl;
        this.name = name;
        this.contentmgUrl = contentmgUrl;
        this.share = share;
        this.comment = comment;
        this.like = like;
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
        return "Item1_Bean{" +
                "headImgUrl='" + headImgUrl + '\'' +
                ", name='" + name + '\'' +
                ", contentmgUrl='" + contentmgUrl + '\'' +
                ", share=" + share +
                ", comment=" + comment +
                ", like=" + like +
                '}';
    }
}
