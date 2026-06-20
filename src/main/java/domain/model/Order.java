package domain.model;

public class Order {
    private final String id;
    private final String transactionId;
    private final String accountId;
    private final OrderType type;
    private final OrderDetails details;

    public Order(String id, String transactionId, String accountId, OrderType type, OrderDetails details) {
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
