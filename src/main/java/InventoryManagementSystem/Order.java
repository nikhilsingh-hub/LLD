package InventoryManagementSystem;

public class Order implements Comparable<Order>{
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;

    public boolean isExpress() {
        return isExpress;
    }

    public void setExpress(boolean express) {
        isExpress = express;
    }

    private boolean isExpress;

    @Override
    public int compareTo(Order o) {
        if(this.isExpress == o.isExpress) return this.orderId.compareTo(o.orderId);
        if(this.isExpress) return -1;
        return 1;
    }

    public Order(String orderId, boolean isExpress){
        this.orderId = orderId;
        this.isExpress = isExpress;
    }
}
