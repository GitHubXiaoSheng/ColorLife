package cn.edu.jssvc.gezhi.colorlife.bean;

public class Collector {
    private int id;//key值
    private  int memberId;//会员id
//    private String memberName;//会员名称
    private String headUrl;//会员头像
    private  int artsId;//作品id
    private String artUrl;//作品url
    private String collectorDate;//收藏日期

//    public String getMemberName() {
//        return memberName;
//    }
//
//    public void setMemberName(String memberName) {
//        this.memberName = memberName;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getArtsId() {
        return artsId;
    }

    public void setArtsId(int artsId) {
        this.artsId = artsId;
    }

    public String getArtUrl() {
        return artUrl;
    }

    public void setArtUrl(String artUrl) {
        this.artUrl = artUrl;
    }

    public String getCollectorDate() {
        return collectorDate;
    }

    public void setCollectorDate(String collectorDate) {
        this.collectorDate = collectorDate;
    }

    @Override
    public String toString() {
        return "Collector{" +
                "id=" + id +
                ", memberId=" + memberId +
//                ", memberName='" + memberName + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", artsId=" + artsId +
                ", artUrl='" + artUrl + '\'' +
                ", collectorDate='" + collectorDate + '\'' +
                '}';
    }
}
