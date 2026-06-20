package domain.model;

public class Order {
    private final String id;
    private final String transactionId;
    private final String accountId;
    private final OrderType type;
    private final OrderDetails details;

    public Order(String id, String transactionId, String accountId, OrderType type, OrderDetails details) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Order id is required");
        }

        if (transactionId == null || transactionId.isBlank()) {
            throw new IllegalArgumentException("Transaction id is required");
        }

        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account id is required");
        }

        if (type == null) {
            throw new IllegalArgumentException("Order type is required");
        }

        if (details == null) {
            throw new IllegalArgumentException("Order details are required");
        }

        this.id = id;
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public OrderType getType() {
        return type;
    }

    public OrderDetails getDetails() {
        return details;
    }
}
