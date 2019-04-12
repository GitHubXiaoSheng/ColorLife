package cn.edu.jssvc.gezhi.colorlife.bean;

public class ReadHistory {
    private  int memberId;
    private  int  artsId;
    private String browseDate;
    private int orderId;
    private String orderType;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
