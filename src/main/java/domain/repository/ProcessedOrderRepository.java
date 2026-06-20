package domain.repository;

import domain.model.ProcessedOrderKey;

public interface ProcessedOrderRepository {
    boolean exits(ProcessedOrderKey key);

    void save(ProcessedOrderKey key);
}
