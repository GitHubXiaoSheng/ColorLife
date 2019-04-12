package cn.edu.jssvc.gezhi.colorlife.bean;

public class Collector {
    private  int memberId;
    private  int artsId;
    private String collectorDate;
    private int id;

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

    public String getCollectorDate() {
        return collectorDate;
    }

    public void setCollectorDate(String collectorDate) {
        this.collectorDate = collectorDate;
    }

    @Override
    public String toString() {
        return memberId+","+artsId+","+collectorDate;
    }
}
