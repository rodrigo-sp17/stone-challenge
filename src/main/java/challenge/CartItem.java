package challenge;

public class CartItem {

    private final String itemName;
    private Integer quantity;

    // Since we will not be using floating points, Long type is fine for the price
    private final Long princeInCents;

    public CartItem(String itemName, Integer quantity, Long princeInCents) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.princeInCents = princeInCents;
    }

    public Long getTotalPrice() {
        return quantity * princeInCents;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrinceInCents() {
        return princeInCents;
    }

    @Override
    public String toString() {
        return String.format("%s - Q: %d - P: %d", itemName, quantity, princeInCents);
    }
}
