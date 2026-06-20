package domain.model;

public record ProcessedOrderKey(
        String accountId,
        String transactionId,
        OrderType type
) {
}
