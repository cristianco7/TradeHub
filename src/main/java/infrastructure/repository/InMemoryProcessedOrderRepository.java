package infrastructure.repository;

import domain.model.ProcessedOrderKey;
import domain.repository.ProcessedOrderRepository;

import java.util.HashSet;
import java.util.Set;

public class InMemoryProcessedOrderRepository implements ProcessedOrderRepository {
    private final Set<ProcessedOrderKey> processedOrders = new HashSet<>();

    @Override
    public boolean exits(ProcessedOrderKey key) {
        return processedOrders.contains(key);
    }

    @Override
    public void save(ProcessedOrderKey key) {
        processedOrders.add(key);
    }
}
