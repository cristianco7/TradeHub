package domain.model;

import java.math.BigDecimal;

public class OrderDetails {
    private final String ticker;
    private final int quantity;
    private final BigDecimal price;

    public OrderDetails(String ticker, int quantity, BigDecimal price) {
        if (ticker == null || ticker.isBlank()) {
            throw new IllegalArgumentException("Ticker is required");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be grater than zero");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
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
