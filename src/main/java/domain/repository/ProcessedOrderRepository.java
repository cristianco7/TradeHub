package domain.repository;

import domain.model.ProcessedOrderKey;

public interface ProcessedOrderRepository {
    boolean exists(ProcessedOrderKey key);

    void save(ProcessedOrderKey key);
}
