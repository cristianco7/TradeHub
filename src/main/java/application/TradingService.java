package application;

import domain.repository.AccountRepository;
import domain.repository.ProcessedOrderRepository;

public class TradingService {
    private final AccountRepository accountRepository;
    private final ProcessedOrderRepository processedOrderRepository;

    public TradingService(AccountRepository accountRepository, ProcessedOrderRepository processedOrderRepository) {
        this.accountRepository = accountRepository;
        this.processedOrderRepository = processedOrderRepository;
    }
}
