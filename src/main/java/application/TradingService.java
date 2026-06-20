package application;

import domain.exception.AccountNotFoundException;
import domain.exception.DuplicateOrderException;
import domain.model.*;
import domain.repository.AccountRepository;
import domain.repository.ProcessedOrderRepository;

public class TradingService {
    private final AccountRepository accountRepository;
    private final ProcessedOrderRepository processedOrderRepository;

    public TradingService(AccountRepository accountRepository, ProcessedOrderRepository processedOrderRepository) {
        this.accountRepository = accountRepository;
        this.processedOrderRepository = processedOrderRepository;
    }

    public void process(Order order) {
        ProcessedOrderKey processedOrderKey = new ProcessedOrderKey(
                order.getAccountId(),
                order.getTransactionId(),
                order.getType()
        );

        if (processedOrderRepository.exists(processedOrderKey)) {
            throw new DuplicateOrderException("Order has already been processed");
        }

        Account account = accountRepository.findById(order.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + order.getAccountId()));

        OrderDetails details = order.getDetails();

        switch (order.getType()) {
            case BUY -> account.buy(details.getTicker(), details.getQuantity(), details.getPrice());
            case SELL -> account.sell(details.getTicker(), details.getQuantity(), details.getPrice());
        }

        accountRepository.save(account);
        processedOrderRepository.save(processedOrderKey);
    }


}
