package cn.edu.jssvc.gezhi.colorlife.bean;

public class ShoppingCar {
    private  int  memberId;
    private  int artsId;
    private  float price;
    private int number;
    private float sum;
    private int ShoppingCartId;


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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public int getShoppingCartId() {
        return ShoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        ShoppingCartId = shoppingCartId;
    }

    @Override
    public String toString() {
        return memberId+","+artsId+","+price+","+number+","+sum+","+ShoppingCartId;
    }
}
