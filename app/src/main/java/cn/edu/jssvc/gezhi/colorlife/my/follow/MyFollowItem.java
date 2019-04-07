package cn.edu.jssvc.gezhi.colorlife.my.follow;

public class MyFollowItem {
    private String headUrl;
    private String name;
    private String details;
    private boolean isFollow;

    public MyFollowItem(String headUrl, String name, String details, boolean isFollow) {
        this.headUrl = headUrl;
        this.name = name;
        this.details = details;
        this.isFollow = isFollow;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }
}
