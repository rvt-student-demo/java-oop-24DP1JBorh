package OnlineShop;

public class Item {
    private String productName;
    private int quantity;
    private int unitPrice;

    public Item(String product, int qty, int unitPrice) {
        this.productName = product;
        this.quantity = qty;
        this.unitPrice = unitPrice;
    }

    public int price() {
        return quantity * unitPrice;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public String toString() {
        return productName + ": " + quantity;
    }

}
