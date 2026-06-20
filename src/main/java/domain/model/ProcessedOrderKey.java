package domain.model;

import java.util.Objects;

public class ProcessedOrderKey {
    private final String accountId;
    private final String transactionId;
    private final String type;

    public ProcessedOrderKey(String accountId, String transactionId, String type) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessedOrderKey that)) return false;
        return Objects.equals(accountId, that.accountId)
                && Objects.equals(transactionId, that.transactionId)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, transactionId, type);
    }
}
