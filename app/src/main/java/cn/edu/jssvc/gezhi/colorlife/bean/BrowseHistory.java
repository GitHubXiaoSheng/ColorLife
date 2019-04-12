package cn.edu.jssvc.gezhi.colorlife.bean;

public class BrowseHistory {
   private  int memberId;
   private  int artsId;
   private String browseDate;
   private  int id;

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

    public int getArtsId() {
        return artsId;
    }

    public void setArtsId(int artsId) {
        this.artsId = artsId;
    }

    public String getBrowseDate() {
        return browseDate;
    }

    public void setBrowseDate(String browseDate) {
        this.browseDate = browseDate;
    }

    @Override
    public String toString() {
        return memberId+","+artsId+","+browseDate;
    }
}
