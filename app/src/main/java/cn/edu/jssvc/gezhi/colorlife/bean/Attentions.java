package cn.edu.jssvc.gezhi.colorlife.bean;

public class Attentions {
    private int  attentionsMemberId;
    private  int  attentionedMemberId;
    private String attentionDates;


    public int getAttentionsMemberId() {
        return attentionsMemberId;
    }

    public void setAttentionsMemberId(int attentionsMemberId) {
        this.attentionsMemberId = attentionsMemberId;
    }

    public int getAttentionedMemberId() {
        return attentionedMemberId;
    }

    public void setAttentionedMemberId(int attentionedMemberId) {
        this.attentionedMemberId = attentionedMemberId;
    }

    public String getAttentionDates() {
        return attentionDates;
    }

    public void setAttentionDates(String attentionDates) {
        this.attentionDates = attentionDates;
    }


    @Override
    public String toString() {
        return attentionsMemberId+","+attentionedMemberId+","+attentionDates+",";
    }
}
