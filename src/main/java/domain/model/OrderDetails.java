package domain.model;

import java.math.BigDecimal;

public class OrderDetails {
    private final String ticker;
    private final int quantity;
    private final BigDecimal price;

    public OrderDetails(String ticker, int quantity, BigDecimal price) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
